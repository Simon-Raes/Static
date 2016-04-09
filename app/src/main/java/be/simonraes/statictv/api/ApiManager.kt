package be.simonraes.statictv.api

import android.content.Context
import be.simonraes.statictv.*
import be.simonraes.statictv.api.interceptors.AuthInterceptor
import be.simonraes.statictv.api.interceptors.HeadersInterceptor
import be.simonraes.statictv.api.services.ApiService
import be.simonraes.statictv.api.services.AuthorizedApiService
import be.simonraes.statictv.api.services.OAuthApiService
import be.simonraes.statictv.model.CalendarShowItem
import be.simonraes.statictv.model.event.AbstractEvent
import be.simonraes.statictv.model.event.HistoryEvent
import be.simonraes.statictv.model.event.RatingEvent
import be.simonraes.statictv.model.item.CommentEvent
import be.simonraes.statictv.model.oauth.AccessTokens
import be.simonraes.statictv.model.oauth.AccessTokenPostData
import be.simonraes.statictv.model.oauth.RefreshTokenPostData
import be.simonraes.statictv.model.social.Friend
import be.simonraes.statictv.model.social.Stats
import be.simonraes.statictv.model.social.User
import okhttp3.OkHttpClient
import org.joda.time.DateTime
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.*

/**
 * Created by SimonRaes on 06/03/16.
 */
class ApiManager private constructor(val clientId: String,
                                     val clientSecret: String,
                                     val redirectUri: String,
                                     val accessToken: String) {

    private val apiService: ApiService
    private val oAuthApiService: OAuthApiService
    private val authApiService: AuthorizedApiService


    init {
        apiService = createApiService(ApiService::class.java)
        oAuthApiService = createApiService(OAuthApiService::class.java, traktInfoHeaders = false)
        authApiService = createApiService(AuthorizedApiService::class.java, authorized = true)
    }


    /**
     * @param authorized Whether or not this ApiServices's calls should include the access token. Default is false.
     * @param traktInfoHeaders Whether or not this ApiServices's calls should include the Trakt info headers. Default is true
     */
    fun <T> createApiService(apiService: Class<T>, authorized: Boolean = false, traktInfoHeaders: Boolean = true): T {

        val clientBuilder = OkHttpClient.Builder()
        if(traktInfoHeaders)
        {
            clientBuilder.interceptors().add(HeadersInterceptor(clientId))
        }
        if (authorized) {
            clientBuilder.interceptors().add(AuthInterceptor(accessToken))
        }
        val client = clientBuilder.build()

        val restBuilder = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .baseUrl(BuildConfig.API_BASE_URL)

        val restAdapter = restBuilder.build()

        return restAdapter.create(apiService)
    }


    // Companion object for singleton
    companion object {
        private var apiManager: ApiManager? = null
        private var context: Context? = null

        fun getInstance(): ApiManager {
            context?.let {  if (apiManager == null) {
                apiManager = ApiManager(it)
            }

                return apiManager!!
            }

            throw RuntimeException("ApiManager must have been initialised from the Application class!")
        }

        fun init(context : Context){
            this.context = context
        }
    }

    constructor(context: Context) : this(context.getString(R.string.client_id),
            context.getString(R.string.client_secret),
            context.getString(R.string.redirect_uri),
            PreferencesHelper.getAccessToken(context))


    /**
     * OAuth
     */


    fun accessToken(code: String): Observable<AccessTokens> {
        val accessTokenPostData = AccessTokenPostData(code, clientId, clientSecret, redirectUri, "authorization_code")

        return oAuthApiService
                .accessToken(accessTokenPostData)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }

    // TODO: 06/03/16 refresh when needed (before 3 months - or when 401 occurs?)
    fun refreshToken(refreshToken: String, clientId: String, clientSecret: String, redirectUri: String): Observable<AccessTokens> {
        val refreshTokenPostData = RefreshTokenPostData(refreshToken, clientId, clientSecret, redirectUri, "authorization_code")

        return oAuthApiService
                .refreshToken(refreshTokenPostData)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }



    // Authorized calls

    fun calendarShows() : Observable<Array<CalendarShowItem>> {
        return authApiService.calendarShows(DateUtils.firstDayOfMonth().toYyyyMmDdFormat(), DateTime().dayOfMonth().maximumValue)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }



    // Basic calls

    /**
     * Users
     */


    fun friends(userName: String): Observable<Array<Friend>> {
        return authApiService.friends(userName)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun following(userName: String): Observable<Array<Friend>> {
        return authApiService.following(userName)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun comments(userName: String): Observable<Array<CommentEvent>> {
        return authApiService.comments(userName, 1, 10)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun history(userName: String): Observable<Array<HistoryEvent>> {
        return authApiService.history(userName, 1, 10)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun ratings(userName: String): Observable<Array<RatingEvent>> {
        return authApiService.ratings(userName, 1, 10)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun stats(userName : String) :Observable<Stats>{
        return authApiService
                .stats(userName)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }


    // Own merged data (naming)

    fun friendsFeed(includeself: Boolean = true): Observable<ArrayList<AbstractEvent>> {
        return following("me")
                .flatMap { friends ->

                    if (includeself) {

                        val friendsList: MutableList<Friend> = arrayListOf()

                        val selfUser = User("me", false, "You", false, false, null)
                        val self = Friend("", selfUser)

                        friendsList.add(self)
                        friendsList.addAll(friends)

                        Observable.from(friendsList)
                    } else {
                        Observable.from(friends)
                    }
                }
                .flatMap { friend ->
                    Observable.zip(comments(friend.user.username), history(friend.user.username), ratings(friend.user.username)) { commentEvents, historyEvents, ratingEvents ->

                        val items = ArrayList<AbstractEvent>()

                        items.addAll(commentEvents.asList())
                        items.addAll(historyEvents.asList())

                        var addedExtraRating = false

                        // todo keep 1 extra rating that is older than the max
                        ratingEvents.forEach {


                            //                            if ((commentEvents.size > 0 && it.ratedAt.toLongTimeStamp().toLong() < commentEvents?.get(0)?.comment?.createdAt?.toLongTimeStamp()?.toLong() ?: Long.MAX_VALUE ) ||
                            //                                    (historyEvents.size > 0 && it.ratedAt.toLongTimeStamp().toLong() < historyEvents.get(0).watchedAt.toLongTimeStamp().toLong())) {
                            //
                            //
                            //                                if (!addedExtraRating) {
                            //                                    items.add(it)
                            //                                    addedExtraRating = true
                            //                                }
                            //                            } else {
                            items.add(it)
                            //                            }
                        }

                        //
                        //                        items.reverse()

                        // todo add name instead of username if it is available
                        items.forEach { it.user = friend.user.username }

                        // todo merge items if possible (eg comment event right after watch event)
                        // first sort
                        items.sort()

                        // then merge


                        items
                    }
                }
                //                .collect(Func0<ArrayList<AbstractEvent>> { ArrayList() },
                //                        Action2<ArrayList<AbstractEvent>, ArrayList<AbstractEvent>> { abstractEvents, s -> abstractEvents.addAll(s) })
                .collect({ ArrayList<AbstractEvent>() }, { abstractEvents, s -> abstractEvents.addAll(s) })
                .map { items -> items.sort(); items.reverse(); items }
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }
}

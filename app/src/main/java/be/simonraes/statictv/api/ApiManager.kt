package be.simonraes.statictv.api

import android.content.Context
import be.simonraes.statictv.R
import be.simonraes.statictv.model.event.AbstractEvent
import be.simonraes.statictv.model.event.HistoryEvent
import be.simonraes.statictv.model.event.RatingEvent
import be.simonraes.statictv.model.item.CommentEvent
import be.simonraes.statictv.model.oauth.AccessToken
import be.simonraes.statictv.model.oauth.AccessTokenPostData
import be.simonraes.statictv.model.oauth.RefreshTokenPostData
import be.simonraes.statictv.model.oauth.TokenPostData
import be.simonraes.statictv.model.social.Friend
import be.simonraes.statictv.model.social.User
import okhttp3.OkHttpClient
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
                                     val redirectUri: String) {

    val API_BASE_URL = "https://api-v2launch.trakt.tv"

    private val apiService: ApiService

    init {

        val clientBuilder = OkHttpClient.Builder()
        clientBuilder.interceptors().add(HeadersInterceptor(clientId))
        val client = clientBuilder.build()

        val restBuilder = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .baseUrl(API_BASE_URL)

        val restAdapter = restBuilder.build()

        apiService = restAdapter.create(ApiService::class.java)
    }


    // todo init this once (in application) so the rest doesn't have to pass in a context

    // Companion object for singleton
    companion object {
        private var instance: ApiManager? = null
        fun getInstance(context: Context): ApiManager {
            if (instance == null) {
                instance = ApiManager(context)
            }

            return instance!!
        }
    }

    constructor(context: Context) : this(context.getString(R.string.client_id),
            context.getString(R.string.client_secret),
            context.getString(R.string.redirect_uri))


    /**
     * OAuth
     */


    fun accessToken(code: String): Observable<AccessToken> {
        val accessTokenPostData = AccessTokenPostData(code, clientId, clientSecret, redirectUri, "authorization_code")

        return token(accessTokenPostData)
    }

    // TODO: 06/03/16 refresh when needed
    fun refreshToken(refreshToken: String, clientId: String, clientSecret: String, redirectUri: String): Observable<AccessToken> {
        val refreshTokenPostData = RefreshTokenPostData(refreshToken, clientId, clientSecret, redirectUri, "authorization_code")

        return token(refreshTokenPostData)
    }

    private fun token(data: TokenPostData): Observable<AccessToken> {
        return apiService.getAccessToken(data).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
    }


    // Base calls

    /**
     * Social
     */


    fun friends(userName: String): Observable<Array<Friend>> {
        return apiService.friends(userName)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun following(userName: String): Observable<Array<Friend>> {
        return apiService.following(userName)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun comments(userName: String): Observable<Array<CommentEvent>> {
        return apiService.comments(userName, 1, 10)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun history(userName: String): Observable<Array<HistoryEvent>> {
        return apiService.history(userName, 1, 10)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }

    fun ratings(userName: String): Observable<Array<RatingEvent>> {
        return apiService.ratings(userName, 1, 10)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
    }


    // Own merged data (naming)

    fun friendsFeed(includeself: Boolean = true): Observable<ArrayList<AbstractEvent>> {
        return following("Voshond")
                .flatMap { friends ->

                    if (includeself) {

                        val friendsList: MutableList<Friend> = arrayListOf()


                        val selfUser = User("Voshond", false, "You", false, false)
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

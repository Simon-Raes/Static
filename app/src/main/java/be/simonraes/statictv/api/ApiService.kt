package be.simonraes.statictv.api

import be.simonraes.statictv.model.event.HistoryEvent
import be.simonraes.statictv.model.event.RatingEvent
import be.simonraes.statictv.model.item.CommentEvent
import be.simonraes.statictv.model.oauth.AccessToken
import be.simonraes.statictv.model.oauth.TokenPostData
import be.simonraes.statictv.model.social.Friend
import retrofit2.http.*
import rx.Observable

/**
 * Created by SimonRaes on 06/03/16.
 */
interface ApiService {
    // TODO: 06/03/16 can probably set content-type header with interceptor for every call


    // OAuth


    /**
     * @param data The data to post. Use AccessTokenPostData when requesting the first set of tokens. Use RefreshTokenPostData to refresh the access token.
     */
    @POST("/oauth/token")
    @Headers("Content-Type: application/json")
    fun getAccessToken(@Body data: TokenPostData): Observable<AccessToken>


    // Sync

    @GET("/sync/history")
    fun history() = TODO()


    // Social


    @GET("/users/{username}/friends")
    fun friends(@Path("username") userName: String): Observable<Array<Friend>>

    @GET("/users/{username}/following")
    fun following(@Path("username") userName: String): Observable<Array<Friend>>


    // todo cap at 10 for friends feed, use pagination (is tricky though) for friend feed

    @GET("/users/{username}/history")
    fun history(@Path("username") userName: String, @Query("page") page: Int, @Query("limit") limit: Int): Observable<Array<HistoryEvent>>

    @GET("/users/{username}/comments")
    fun comments(@Path("username") userName: String, @Query("page") page: Int, @Query("limit") limit: Int): Observable<Array<CommentEvent>>

    @GET("/users/{username}/ratings")
    fun ratings(@Path("username") userName: String, @Query("page") page: Int, @Query("limit") limit: Int): Observable<Array<RatingEvent>>
}

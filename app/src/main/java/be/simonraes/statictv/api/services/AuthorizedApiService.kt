package be.simonraes.statictv.api.services

import be.simonraes.statictv.model.CalendarShowItem
import be.simonraes.statictv.model.event.HistoryEvent
import be.simonraes.statictv.model.event.RatingEvent
import be.simonraes.statictv.model.item.CommentEvent
import be.simonraes.statictv.model.social.Friend
import be.simonraes.statictv.model.social.Stats
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import rx.Observable

/**
 * Created by SimonRaes on 26/03/16.
 * API calls that include the accessToken with the request.
 */
interface AuthorizedApiService {

    // Calendars

    @GET("calendars/my/shows/{start_date}/{days}")
    fun calendarShows(@Path("start_date") startDate : String, @Path("days") days : Int) : Observable<Array<CalendarShowItem>>

    // Sync

    @GET("sync/history")
    fun history() : Observable<HistoryEvent>

    // Users

    //    todo make request params more dynamic AND/OR don't always request the avatar
    // can save avatar and only refresh when viewing person's profile
    @GET("users/{username}/friends?extended=images")
    fun friends(@Path("username") userName: String): Observable<Array<Friend>>

    @GET("users/{username}/following")
    fun following(@Path("username") userName: String): Observable<Array<Friend>>


    // todo cap at 10 for friends feed, use pagination (is tricky though) for friend feed

    @GET("users/{username}/history")
    fun history(@Path("username") userName: String, @Query("page") page: Int, @Query("limit") limit: Int): Observable<Array<HistoryEvent>>

    @GET("users/{username}/comments")
    fun comments(@Path("username") userName: String, @Query("page") page: Int, @Query("limit") limit: Int): Observable<Array<CommentEvent>>

    @GET("users/{username}/ratings")
    fun ratings(@Path("username") userName: String, @Query("page") page: Int, @Query("limit") limit: Int): Observable<Array<RatingEvent>>

    @GET("users/{username}/stats")
    fun stats(@Path("username") userName: String): Observable<Stats>

}
package be.simonraes.statictv.api.services

import be.simonraes.statictv.model.event.HistoryEvent
import retrofit2.http.GET
import rx.Observable

/**
 * Created by SimonRaes on 26/03/16.
 * API calls that require the accessToken. Used for calls that request the user's personal info.
 */
interface AuthorizedApiService {

    // Sync

    @GET("/sync/history")
    fun history() : Observable<HistoryEvent>

}
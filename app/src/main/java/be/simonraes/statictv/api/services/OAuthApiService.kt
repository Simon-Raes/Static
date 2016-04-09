package be.simonraes.statictv.api.services

import be.simonraes.statictv.model.oauth.AccessTokens
import be.simonraes.statictv.model.oauth.AccessTokenPostData
import be.simonraes.statictv.model.oauth.RefreshTokenPostData
import retrofit2.http.Body
import retrofit2.http.POST
import rx.Observable

/**
 * Created by SimonRaes on 27/03/16.
 * Calls related to login.
 */
interface OAuthApiService {

    /**
     * @param data The data to post. Use AccessTokenPostData when requesting the first set of tokens.
     */
    @POST("oauth/token")
    fun accessToken(@Body data: AccessTokenPostData): Observable<AccessTokens>


    /**
     * @param data The data to post. Use RefreshTokenPostData to refresh the access token.
     */
    @POST("oauth/token")
    fun refreshToken(@Body data: RefreshTokenPostData): Observable<AccessTokens>


}
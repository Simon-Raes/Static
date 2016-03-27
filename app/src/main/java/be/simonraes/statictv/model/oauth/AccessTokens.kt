package be.simonraes.statictv.model.oauth

import com.google.gson.annotations.SerializedName

/**
 * Created by SimonRaes on 06/03/16.
 * Returned by the Trakt.tv API when requesting access/refresh tokens
 */
class AccessTokens(@SerializedName("access_token")
                   val accessToken: String,
                   @SerializedName("token_type")
                   val tokenType: String,
                   @SerializedName("expires_in")
                   val expiresIn: Long,
                   @SerializedName("refresh_token")
                   val refreshToken: String,
                   @SerializedName("scope")
                   val scope: String)
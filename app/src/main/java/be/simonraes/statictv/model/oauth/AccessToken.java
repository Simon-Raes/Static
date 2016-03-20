package be.simonraes.statictv.model.oauth;

import com.google.gson.annotations.SerializedName;

/**
 * Created by SimonRaes on 06/03/16.
 * Returned by the Trakt.tv API when requesting access/refresh tokens
 */
public class AccessToken
{
    @SerializedName("access_token")
    private String accessToken;
    @SerializedName("token_type")
    private String tokenType;
    @SerializedName("expires_in")
    private int expiresIn;
    @SerializedName("refresh_token")
    private String refreshToken;
    @SerializedName("scope")
    private String scope;

    public AccessToken()
    {
    }

    public String getAccessToken()
    {
        return this.accessToken;
    }

    public String getTokenType()
    {
        return this.tokenType;
    }

    public int getExpiresIn()
    {
        return this.expiresIn;
    }

    public String getRefreshToken()
    {
        return this.refreshToken;
    }

    public String getScope()
    {
        return this.scope;
    }
}

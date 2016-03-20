package be.simonraes.statictv.model.oauth;

/**
 * Created by SimonRaes on 06/03/16.
 * Used to refresh the access token.
 */
public class RefreshTokenPostData extends TokenPostData
{
    private final String refresh_token;

    public RefreshTokenPostData(String refresh_token, String client_id, String client_secret, String redirect_uri, String grant_type)
    {
        super(client_id, client_secret, redirect_uri, grant_type);
        this.refresh_token = refresh_token;
    }
}

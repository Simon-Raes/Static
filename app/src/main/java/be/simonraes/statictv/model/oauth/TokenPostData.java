package be.simonraes.statictv.model.oauth;

/**
 * Created by SimonRaes on 06/03/16.
 */
public class TokenPostData
{
    protected final String client_id;
    protected final String client_secret;
    protected final String redirect_uri;
    protected final String grant_type;

    public TokenPostData(String client_id, String client_secret, String redirect_uri, String grant_type)
    {
        this.client_id = client_id;
        this.client_secret = client_secret;
        this.redirect_uri = redirect_uri;
        this.grant_type = grant_type;
    }
}

package be.simonraes.statictv.model.oauth;

/**
 * Created by SimonRaes on 06/03/16.
 * Used when first logging in to request the first access and refresh tokens.
 */
public class AccessTokenPostData extends TokenPostData
{
    private final String code;

    public AccessTokenPostData(String code, String client_id, String client_secret, String redirect_uri, String grant_type)
    {
        super(client_id, client_secret, redirect_uri, grant_type);
        this.code = code;
    }
}

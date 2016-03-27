package be.simonraes.statictv.api.interceptors

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by SimonRaes on 26/03/16.
 * Interceptor for API calls that require user authorization.
 */
class AuthInterceptor (val accessToken : String): Interceptor {

    override fun intercept(chain: Interceptor.Chain?): Response? {
        var request = chain?.request()

        request = request?.newBuilder()?.
                addHeader("Authorization", "Bearer " + accessToken)?.
                build()

        val response = chain?.proceed(request);
        return response;
    }
}
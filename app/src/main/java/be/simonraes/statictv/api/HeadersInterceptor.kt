package be.simonraes.statictv.api

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by SimonRaes on 26/03/16.
 * Adds basic headers required for every call
 */

class HeadersInterceptor (val clientId : String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain?): Response? {
        var request = chain?.request()

        request = request?.newBuilder()?.
                addHeader("Content-Type", "application/json: 2")?.
                addHeader("trakt-api-version", "2")?.
                addHeader("trakt-api-key", clientId)?.
                build()

        val response = chain?.proceed(request);
        return response;
    }

}

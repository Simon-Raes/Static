package be.simonraes.statictv

import android.content.Context
import android.content.SharedPreferences
import be.simonraes.statictv.model.oauth.AccessTokens

/**
 * Created by SimonRaes on 26/03/16.
 */
class PreferencesHelper private constructor() {

    companion object {}

}

val PREFERENCES: String = "staticPreferences"

val KEY_ACCESS_TOKEN = "accessToken"
val KEY_REFRESH_TOKEN = "refreshToken"
val KEY_REFRESH_TTIMESTAMP = "refreshTimeStamp"

// Refresh the token two days before it will expire
private val REFRESH_MARGIN = 1000 * 60 * 60 * 24 * 2;


fun getPreferences(context: Context): SharedPreferences {
    return context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE)
}

private fun getEditor(context: Context) = getPreferences(context).edit()

fun PreferencesHelper.Companion.saveAccessToken(context: Context, accessTokens: AccessTokens) {
    getEditor(context)
            .putString(KEY_ACCESS_TOKEN, accessTokens.accessToken)
            .putString(KEY_REFRESH_TOKEN, accessTokens.refreshToken)
            .putLong(KEY_REFRESH_TTIMESTAMP, System.currentTimeMillis() + accessTokens.expiresIn)
            .apply()
}

fun PreferencesHelper.Companion.getAccessToken(context: Context) =
        getPreferences(context)
                .getString(KEY_ACCESS_TOKEN, "")

fun PreferencesHelper.Companion.getRefreshToken(context: Context) =
        getPreferences(context)
                .getString(KEY_REFRESH_TOKEN, "")

//todo use this
fun PreferencesHelper.Companion.shouldRefreshToken(context: Context) =
        getPreferences(context)
                .getLong(KEY_REFRESH_TTIMESTAMP, 0) < (System.currentTimeMillis() + REFRESH_MARGIN)


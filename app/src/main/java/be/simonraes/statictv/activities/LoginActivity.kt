package be.simonraes.statictv.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import be.simonraes.statictv.BuildConfig
import be.simonraes.statictv.PreferencesHelper
import be.simonraes.statictv.R
import be.simonraes.statictv.api.ApiManager
import be.simonraes.statictv.saveAccessToken

/**
 * Created by SimonRaes on 26/03/16.
 */
class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)

        val loginButton = findViewById(R.id.loginbutton) as Button
        loginButton.setOnClickListener {
            val intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(BuildConfig.SITE_BASE_URL + "oauth/authorize?response_type=code&client_id=" +
                            "${getString(R.string.client_id)}" +
                            "&redirect_uri=${getString(R.string.redirect_uri)}" +
                            "&state=12345679"))
            //            todo check state value in callback

            startActivity(intent)
        }


    }

    override fun onResume() {
        super.onResume()

        intent.data?.let { uri ->

            if (uri.toString().startsWith(getString(R.string.redirect_uri))) {
                // use the parameter your API exposes for the code (mostly it's "code")

                uri.getQueryParameter("code")?.let {

                    ApiManager
                            .getInstance()
                            .accessToken(it)
                            .subscribe(
                                    { tokens ->
                                        PreferencesHelper.saveAccessToken(this, tokens)
                                        finish()
                                    },
                                    // todo proper error handling
                                    { error -> println(error) })
                }

                uri.getQueryParameter("error")?.let {
                    // todo error feedback
                }
            }
        }
    }
}
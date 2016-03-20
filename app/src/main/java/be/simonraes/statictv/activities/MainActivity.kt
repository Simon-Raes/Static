package be.simonraes.statictv.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import be.simonraes.statictv.R
import be.simonraes.statictv.api.ApiManager
import be.simonraes.statictv.model.event.HistoryEvent
import be.simonraes.statictv.model.item.CommentEvent
import be.simonraes.statictv.model.oauth.AccessToken
import rx.Observable
import rx.Observer
import rx.functions.Func2

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val loginButton = findViewById(R.id.loginbutton) as Button
        loginButton.setOnClickListener {
            val intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://trakt.tv/oauth/authorize?response_type=code&client_id=" +
                            "${getString(R.string.client_id)}" +
                            "&redirect_uri=${getString(R.string.redirect_uri)}" +
                            "&state=12345679"))
//            todo check state in callback

            startActivity(intent)
        }


        val intent = Intent(this, FriendsFeedActivity::class.java)
        startActivity(intent)
    }

//    todo move to a login activity
    override fun onResume() {
        super.onResume()

        val uri = intent.data
        if (uri != null && uri.toString().startsWith(getString(R.string.redirect_uri))) {
            // use the parameter your API exposes for the code (mostly it's "code")
            val code = uri.getQueryParameter("code")
            if (code != null) {
                // get access token
                // we'll do that in a minute


                ApiManager.getInstance(this).accessToken(code).subscribe(object : Observer<AccessToken> {
                    override fun onCompleted() {

                    }

                    override fun onError(e: Throwable) {

                        println("err")

                    }

                    override fun onNext(accessToken: AccessToken) {
                        // TODO: 06/03/16 encrypt and save the tokens
                        println(accessToken)
                    }
                })


            } else if (uri.getQueryParameter("error") != null) {
                // show an error message here
            }
        }
    }
}

package be.simonraes.statictv.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import be.simonraes.statictv.PreferencesHelper
import be.simonraes.statictv.R
import be.simonraes.statictv.getAccessToken

import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(this, FriendsFeedActivity::class.java)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()

        textview_access_token.text = PreferencesHelper.getAccessToken(this)
    }


}

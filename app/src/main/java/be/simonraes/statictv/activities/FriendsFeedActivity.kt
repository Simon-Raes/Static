package be.simonraes.statictv.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import be.simonraes.statictv.fragments.FriendsFeedFragment

/**
 * Created by SimonRaes on 19/03/16.
 */
class FriendsFeedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager
                .beginTransaction()
                .add(android.R.id.content, FriendsFeedFragment(), "tag")
                .commit()
    }
}

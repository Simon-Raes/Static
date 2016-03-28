package be.simonraes.statictv.activities

import android.os.Bundle
import android.support.v4.view.GravityCompat
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import be.simonraes.statictv.R
import be.simonraes.statictv.fragments.FriendsFeedFragment
import be.simonraes.statictv.fragments.FriendsListFragment
import be.simonraes.statictv.fragments.PlaceholderFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar_main)
        with(supportActionBar!!) {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_menu)
        }

        navigation_view.setNavigationItemSelectedListener {
            it.setChecked(true)

            when (it.itemId) {
                R.id.drawer_calendar ->
                    supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.content, FriendsFeedFragment(), "tag")
                            .commit()
                R.id.drawer_friends ->
                    supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.content, FriendsListFragment(), "tag")
                            .commit()
                R.id.drawer_trending ->
                    supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.content, PlaceholderFragment(), "tag")
                            .commit()
            }

            // todo a small delay before closing?
            drawerlayout_main.closeDrawers()
            true
        }


        //        val intent = Intent(this, FriendsFeedActivity::class.java)
        //        startActivity(intent)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when (item?.itemId) {
            android.R.id.home -> {
                drawerlayout_main.openDrawer(GravityCompat.START)
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

}

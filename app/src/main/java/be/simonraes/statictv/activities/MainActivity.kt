package be.simonraes.statictv.activities

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.GravityCompat
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import be.simonraes.statictv.R
import be.simonraes.statictv.fragments.CalendarGridFragment
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

        switchFragment(CalendarGridFragment())

        navigation_view.setNavigationItemSelectedListener {
            it.setChecked(true)

            when (it.itemId) {
                R.id.drawer_login -> {
                    val loginIntent = Intent(this, LoginActivity::class.java)
                    startActivity(loginIntent)
                }
                R.id.drawer_calendar -> switchFragment(CalendarGridFragment())
                R.id.drawer_activity -> switchFragment(FriendsFeedFragment())
                R.id.drawer_friends -> switchFragment(FriendsListFragment())
                R.id.drawer_trending -> switchFragment(PlaceholderFragment())
            }

            // todo a small delay before closing?
            drawerlayout_main.closeDrawers()
            true
        }
    }

    fun switchFragment(fragment: Fragment) {
        // todo don't switch if the fragment is already active
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.content, fragment, fragment.javaClass.name)
                .commit()
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

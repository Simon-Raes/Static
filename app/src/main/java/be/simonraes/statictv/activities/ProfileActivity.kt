package be.simonraes.statictv.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import be.simonraes.statictv.api.ApiManager

/**
 * Created by SimonRaes on 28/03/16.
 */
class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        ApiManager.getInstance()
                .stats("voshond")
                .subscribe(
                        { items -> println(items)  },
                        { error -> println(error) })
    }

}

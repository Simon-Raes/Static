package be.simonraes.statictv.application

import android.app.Application
import be.simonraes.statictv.api.ApiManager
import com.facebook.stetho.Stetho

import net.danlew.android.joda.JodaTimeAndroid

/**
 * Created by SimonRaes on 13/03/16.
 */
class StaticApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        ApiManager.init(this)
        JodaTimeAndroid.init(this)
        Stetho.initializeWithDefaults(this);
    }
}

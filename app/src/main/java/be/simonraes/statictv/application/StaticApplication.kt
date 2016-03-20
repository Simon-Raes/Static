package be.simonraes.statictv.application

import android.app.Application

import net.danlew.android.joda.JodaTimeAndroid

/**
 * Created by SimonRaes on 13/03/16.
 */
class StaticApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        JodaTimeAndroid.init(this)
    }
}

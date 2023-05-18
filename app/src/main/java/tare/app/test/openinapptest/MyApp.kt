package tare.app.test.openinapptest

import android.app.Application
import com.mocklets.pluto.Pluto
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApp: Application(){
    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Pluto.initialize(this)
        }
    }
}
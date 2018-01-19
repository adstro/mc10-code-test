package com.mc10inc.codetest

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import com.mc10inc.codetest.di.ApplicationComponent
import com.mc10inc.codetest.di.ApplicationModule
import com.mc10inc.codetest.di.DaggerApplicationComponent
import timber.log.Timber

/**
 * TODO
 *
 * @author Adam Stroud &#60;[adam.stroud@gmail.com](mailto:adam.stroud@gmail.com)&#62;
 */
class CodeTestApplication : Application() {
    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }
}
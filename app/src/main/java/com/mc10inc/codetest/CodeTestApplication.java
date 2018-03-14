package com.mc10inc.codetest;

import android.app.Application;

import com.jakewharton.threetenabp.AndroidThreeTen;
import com.mc10inc.codetest.di.ApplicationComponent;
import com.mc10inc.codetest.di.ApplicationModule;
import com.mc10inc.codetest.di.DaggerApplicationComponent;

import timber.log.Timber;

/**
 * TODO
 *
 * @author Adam Stroud &#60;<a href="mailto:adam.stroud@gmail.com">adam.stroud@gmail.com</a>&#62;
 */
public class CodeTestApplication extends Application {
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        AndroidThreeTen.init(this);

        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        applicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    public void getApplicationComponent(ApplicationComponent applicationComponent) {
        this.applicationComponent = applicationComponent;
    }
}

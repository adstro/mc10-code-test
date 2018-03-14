package com.mc10inc.codetest.login;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;

import com.mc10inc.codetest.BuildConfig;

/**
 * TODO
 *
 * @author Adam Stroud &#60;<a href="mailto:adam.stroud@gmail.com">adam.stroud@gmail.com</a>&#62;
 */
public class LoginViewModel extends AndroidViewModel {
    private String userName;
    private String password;

    public LoginViewModel(@NonNull Application application) {
        super(application);

        if (BuildConfig.DEBUG) {
            userName = "amartin+mobiletest@mc10inc.com";
            password = "yf9C79%AY9";
        }
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

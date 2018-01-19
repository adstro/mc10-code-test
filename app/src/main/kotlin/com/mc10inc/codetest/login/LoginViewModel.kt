package com.mc10inc.codetest.login

import android.app.Application
import android.arch.lifecycle.AndroidViewModel

/**
 * TODO
 *
 * @author Adam Stroud &#60;[adam.stroud@gmail.com](mailto:adam.stroud@gmail.com)&#62;
 */
class LoginViewModel(application: Application) : AndroidViewModel(application) {
    var userName: String? = null
    var password: String? = null
}
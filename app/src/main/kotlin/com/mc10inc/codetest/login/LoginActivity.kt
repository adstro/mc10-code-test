package com.mc10inc.codetest.login

import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import com.jakewharton.rxbinding2.widget.textChanges
import com.mc10inc.codetest.CodeTestApplication
import com.mc10inc.codetest.PreferenceManager
import com.mc10inc.codetest.R
import com.mc10inc.codetest.api.Mc10WebService
import com.mc10inc.codetest.base.BaseActivity
import com.mc10inc.codetest.databinding.ActivityLogInBinding
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

/**
 * TODO
 *
 * @author Adam Stroud &#60;[adam.stroud@gmail.com](mailto:adam.stroud@gmail.com)&#62;
 */
class LoginActivity : BaseActivity() {
    @Inject
    lateinit var webService: Mc10WebService

    @Inject
    lateinit var preferenceManager: PreferenceManager

    private lateinit var binding: ActivityLogInBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_log_in)

        viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        binding.username.editText?.setText(viewModel.userName)
        binding.password.editText?.setText(viewModel.password)

        manage(binding.username.editText?.textChanges()
                ?.subscribe { viewModel.userName = it.toString() })

        manage(binding.password.editText?.textChanges()
                ?.subscribe { viewModel.password = it.toString() })

        (application as CodeTestApplication).applicationComponent.inject(this)

        binding.logInButton.setOnClickListener {
            manage(webService.login(binding.username.editText?.text.toString(), binding.password.editText?.text.toString())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeBy(onError = {
                        throwable -> Timber.e(throwable, "Could not log in")
                        manage(ErrorDialogFragment().show(supportFragmentManager)
                                .subscribe { it.dialog.dismiss() })
                    }, onSuccess = { loginResponse ->
                                preferenceManager.accessToken = loginResponse.accessToken
                                preferenceManager.accessTokenExpiration = loginResponse.expiration
                                preferenceManager.accountId = loginResponse.user.accountId
                                preferenceManager.userId = loginResponse.user.id
                                finish()
                            }))
        }

    }
}
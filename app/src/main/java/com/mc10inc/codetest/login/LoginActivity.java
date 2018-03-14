package com.mc10inc.codetest.login;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jakewharton.rxbinding2.widget.RxTextView;
import com.mc10inc.codetest.CodeTestApplication;
import com.mc10inc.codetest.PreferenceManager;
import com.mc10inc.codetest.R;
import com.mc10inc.codetest.api.Mc10WebService;
import com.mc10inc.codetest.base.BaseActivity;
import com.mc10inc.codetest.databinding.ActivityLogInBinding;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * TODO
 *
 * @author Adam Stroud &#60;<a href="mailto:adam.stroud@gmail.com">adam.stroud@gmail.com</a>&#62;
 */
public class LoginActivity extends BaseActivity {
    @Inject
    public Mc10WebService webService;

    @Inject
    public PreferenceManager preferenceManager;

    private ActivityLogInBinding binding;
    private LoginViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_log_in);

        viewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        binding.username.getEditText().setText(viewModel.getUserName());
        binding.password.getEditText().setText(viewModel.getPassword());

        manage(RxTextView.textChanges(binding.username.getEditText())
                .subscribe(text -> viewModel.setUserName(text.toString())));

        manage(RxTextView.textChanges(binding.password.getEditText())
                .subscribe(text -> viewModel.setPassword(text.toString())));

        ((CodeTestApplication) getApplication()).getApplicationComponent().inject(this);

        binding.logInButton.setOnClickListener(view -> {
            manage(webService.login(binding.username.getEditText().getText().toString(), binding.password.getEditText().getText().toString())
                .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(loginResponse -> {
                        preferenceManager.setAccessToken(loginResponse.getAccessToken());
                        preferenceManager.setAccessTokenExpiration(loginResponse.getExpiration());
                        preferenceManager.setAccountId(loginResponse.getUser().getAccountId());
                        preferenceManager.setUserId(loginResponse.getUser().getId());
                        finish();
                    }, throwable -> {
                        Timber.e(throwable, "Could not log in");
                        manage(new ErrorDialogFragment().show(getSupportFragmentManager())
                                .subscribe(clickEvent -> clickEvent.getDialog().dismiss()));
                    }));
        });
    }
}

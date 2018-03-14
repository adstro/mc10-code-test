package com.mc10inc.codetest.base;

import android.support.v7.app.AppCompatActivity;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * TODO
 *
 * @author Adam Stroud &#60;<a href="mailto:adam.stroud@gmail.com">adam.stroud@gmail.com</a>&#62;
 */
public abstract class BaseActivity extends AppCompatActivity {
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    protected Disposable manage(Disposable disposable) {
        compositeDisposable.add(disposable);
        return disposable;
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}

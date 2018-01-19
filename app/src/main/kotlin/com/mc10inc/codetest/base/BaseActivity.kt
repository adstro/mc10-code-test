package com.mc10inc.codetest.base

import android.support.v7.app.AppCompatActivity
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.addTo

/**
 * TODO
 *
 * @author Adam Stroud &#60;[adam.stroud@gmail.com](mailto:adam.stroud@gmail.com)&#62;
 */
abstract class BaseActivity : AppCompatActivity() {
    private val compositeDisposable = CompositeDisposable()

    override fun onDestroy() {
        clearDisposables()
        super.onDestroy()
    }

    protected fun manage(disposable: Disposable?) = disposable?.addTo(compositeDisposable)

    protected fun clearDisposables() {
        compositeDisposable.clear()
    }
}
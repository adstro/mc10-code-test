package com.mc10inc.codetest.studylist

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import com.mc10inc.codetest.CodeTestApplication
import com.mc10inc.codetest.PreferenceManager
import com.mc10inc.codetest.api.Mc10WebService
import com.mc10inc.codetest.model.Study
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import okhttp3.Credentials
import timber.log.Timber
import javax.inject.Inject

/**
 * TODO
 *
 * @author Adam Stroud &#60;[adam.stroud@gmail.com](mailto:adam.stroud@gmail.com)&#62;
 */
class StudyListViewModel(application: Application) : AndroidViewModel(application) {
    private val compositeDisposable = CompositeDisposable()
    val studyList = MutableLiveData<List<Study>>()
    val throwable = MutableLiveData<Throwable>()

    @Inject
    internal lateinit var preferenceManager: PreferenceManager

    @Inject
    internal lateinit var webService: Mc10WebService

    init {
        (application as CodeTestApplication).applicationComponent.inject(this)
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    fun getStudies() {
        compositeDisposable.add(webService.studies(preferenceManager.accountId, Credentials.basic(preferenceManager.userId.toString(), preferenceManager.accessToken!!))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(onError = {
                    Timber.e(it, "Could not get studies")
                    throwable.postValue(it)
                }, onSuccess = { studyList.postValue(it) } ))
    }
}
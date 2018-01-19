package com.mc10inc.codetest.studylist

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.bluetooth.BluetoothAdapter
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import com.mc10inc.codetest.CodeTestApplication
import com.mc10inc.codetest.PreferenceManager
import com.mc10inc.codetest.R
import com.mc10inc.codetest.base.BaseActivity
import com.mc10inc.codetest.login.LoginActivity
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.activity_study_list.*
import timber.log.Timber
import javax.inject.Inject

/**
 * TODO
 *
 * @author Adam Stroud &#60;[adam.stroud@gmail.com](mailto:adam.stroud@gmail.com)&#62;
 */
class StudyListActivity : BaseActivity() {
    @Inject
    lateinit var preferenceManager: PreferenceManager

    private lateinit var viewModel: StudyListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_study_list)

        viewModel = ViewModelProviders.of(this).get(StudyListViewModel::class.java)

        (application as CodeTestApplication).applicationComponent.inject(this)

        recyclerView.adapter = StudyAdapter()
        recyclerView.addItemDecoration(DividerItemDecoration(this, (recyclerView.layoutManager as LinearLayoutManager).orientation))
    }

    override fun onStart() {
        super.onStart()

        val bluetoothEnabled = BluetoothAdapter.getDefaultAdapter()?.isEnabled ?: false

        if (TextUtils.isEmpty(preferenceManager.accessToken)) {
            startActivity(Intent(this, LoginActivity::class.java))
        } else if (!bluetoothEnabled) {
            manage(BluetoothDisabledDialogFragment().show(supportFragmentManager)
                    .subscribeBy(onError = { Timber.e(it, "Could not process clicks") },
                            onNext = {
                                it.dialog.dismiss()
                                preferenceManager.clear()
                                startActivity(Intent(this, LoginActivity::class.java))
                            }))
        } else {
            viewModel.studyList.observe(this, Observer {
                progress.hide()
                if (it != null) {
                    (recyclerView.adapter as StudyAdapter).swap(it)
                }
            })

            viewModel.throwable.observe(this, Observer {
                Timber.e(it, "Could not load studies")

                manage(ErrorDialogFragment().show(supportFragmentManager)
                        .subscribe { it.dialog.dismiss() })
            })

            when (viewModel.studyList.value?.isEmpty()) {
                null, true -> {
                    progress.show()
                    viewModel.getStudies()
                }
            }
        }
    }
}

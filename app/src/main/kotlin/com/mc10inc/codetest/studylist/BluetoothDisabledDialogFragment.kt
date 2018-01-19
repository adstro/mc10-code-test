package com.mc10inc.codetest.studylist

import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import android.support.v7.app.AlertDialog
import com.mc10inc.codetest.R
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

/**
 * TODO
 *
 * @author Adam Stroud &#60;[adam.stroud@gmail.com](mailto:adam.stroud@gmail.com)&#62;
 */
class BluetoothDisabledDialogFragment : DialogFragment() {
    private val clickSubject = PublishSubject.create<ClickEvent>()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(context!!)
                .setTitle(R.string.title_dialog_bluetooth_disabled)
                .setMessage(R.string.message_dialog_bluetooth_disabled)
                .setCancelable(false)
                .setPositiveButton(android.R.string.ok, { dialog, which ->
                    clickSubject.onNext(ClickEvent(dialog, which)) })
                .setOnDismissListener { clickSubject.onComplete() }
                .create()
    }

    fun show(manager: FragmentManager?): Observable<ClickEvent> {
        super.show(manager, BluetoothDisabledDialogFragment::class.java.simpleName)
        return clickSubject
    }

    inner class ClickEvent(val dialog: DialogInterface, val which: Int)
}
package com.mc10inc.codetest.login

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
class ErrorDialogFragment : DialogFragment() {
    private val clickSubject = PublishSubject.create<ClickEvent>()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(context!!)
                .setMessage(R.string.message_dialog_login_error)
                .setCancelable(false)
                .setPositiveButton(android.R.string.ok, { dialog, which ->
                    clickSubject.onNext(ClickEvent(dialog, which)) })
                .setOnDismissListener { clickSubject.onComplete() }
                .create()
    }

    fun show(manager: FragmentManager?): Observable<ClickEvent> {
        super.show(manager, ErrorDialogFragment::class.java.simpleName)
        return clickSubject
    }

    inner class ClickEvent(val dialog: DialogInterface, val which: Int)
}
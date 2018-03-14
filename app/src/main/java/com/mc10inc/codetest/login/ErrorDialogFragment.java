package com.mc10inc.codetest.login;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AlertDialog;

import com.mc10inc.codetest.R;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * TODO
 *
 * @author Adam Stroud &#60;<a href="mailto:adam.stroud@gmail.com">adam.stroud@gmail.com</a>&#62;
 */
public class ErrorDialogFragment extends DialogFragment {
    private final PublishSubject<ClickEvent> clickSubject = PublishSubject.create();

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return new AlertDialog.Builder(getContext())
                .setMessage(R.string.message_dialog_login_error)
                .setCancelable(false)
                .setPositiveButton(android.R.string.ok, (dialog, which) -> clickSubject.onNext(new ClickEvent(dialog, which)))
                .setOnDismissListener(dialog -> clickSubject.onComplete())
                .create();
    }

    public Observable<ClickEvent> show(FragmentManager manager)  {
        super.show(manager, ErrorDialogFragment.class.getSimpleName());
        return clickSubject;
    }

    public static class ClickEvent {
        private final DialogInterface dialog;
        private final int which;

        public ClickEvent(DialogInterface dialog, int which) {
            this.dialog = dialog;
            this.which = which;
        }

        public DialogInterface getDialog() {
            return dialog;
        }

        public int getWhich() {
            return which;
        }
    }
}

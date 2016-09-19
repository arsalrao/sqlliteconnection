package com.boost.entertainment.sqliteapplication;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;

/**
 * Created by waqas on 19/09/2016.
 */
public class MyDialog extends AppCompatDialogFragment {

    private static AlertDialog.Builder builder ;

    public interface YesNoListener{
        void yes(DialogInterface dialogInterface);
        void no(DialogInterface dialogInterface);
    }

    public static  MyDialog  newInstance(AlertDialog.Builder myBuilder){
        builder = myBuilder ;

        MyDialog dialog = new MyDialog();

        return  dialog ;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (!(getActivity() instanceof YesNoListener)){
            throw new ClassCastException(getActivity().toString()+" must be implemented YesNoListener");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                ((YesNoListener) getActivity()).yes(dialogInterface);

            }
        }).setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {


                ((YesNoListener) getActivity()).no(dialogInterface);

            }
        }).create();
    }
}

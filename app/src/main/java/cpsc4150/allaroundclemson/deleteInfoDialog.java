/*
Name: Allison Tizik and Austin Walters
Date: 4/18/2021

Description: Delete dialog that opens when the user wants to delete a class
*/
package cpsc4150.allaroundclemson;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatDialog;
import androidx.appcompat.app.AppCompatDialogFragment;

public class deleteInfoDialog extends AppCompatDialogFragment {
    public interface deleteInfoDialogListener {
        void deleteClassinfo(String name, String code);
    }

    private deleteInfoDialog.deleteInfoDialogListener listener;
    private String className = "";
    private String classCode = "";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_deleteinfo, null);



        builder.setView(view)
                .setTitle("Delete Class Information")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("Delete Class", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String copy = ((EditText) view.findViewById(R.id.edittext2)).getText().toString();
                        String newCopy[] = copy.split(" ");
                        newCopy[1].trim();

                        className = newCopy[0];
                        classCode = newCopy[1];

                        listener.deleteClassinfo(className, classCode);


                    }
                });

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (deleteInfoDialog.deleteInfoDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement deleteInfoDialogListener");
        }
    }
}

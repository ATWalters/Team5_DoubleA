package cpsc4150.allaroundclemson;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatDialogFragment;

public class classInfoDialog extends AppCompatDialogFragment {

    public interface classInfoDialogListener {
        void applyClassinfo(String name, String code, int section, int time);
    }

    private classInfoDialog.classInfoDialogListener listener;
    private String className = "";
    private String classCode = "";
    private int classSection;
    private int classTime;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_classinfodialog, null);


        builder.setView(view)
                .setTitle("Class Information")
                .setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        className = ((EditText) view.findViewById(R.id.className)).getText().toString();
                        classCode = ((EditText) view.findViewById(R.id.classCode)).getText().toString();
                        String SclassSection = ((EditText) view.findViewById(R.id.classSection)).getText().toString();
                        classSection = Integer.parseInt(SclassSection);
                        String SclassTime = ((EditText) view.findViewById(R.id.classTime)).getText().toString();
                        classTime = Integer.parseInt(SclassTime);

                        listener.applyClassinfo(className, classCode, classSection, classSection);
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (classInfoDialog.classInfoDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement classInfoDialogListener");
        }
    }
}

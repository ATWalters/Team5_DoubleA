package cpsc4150.allaroundclemson;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDialogFragment;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

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
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        className = ((EditText) view.findViewById(R.id.className)).getText().toString();
                        className = className.toUpperCase();
                        classCode = ((EditText) view.findViewById(R.id.classCode)).getText().toString();
                        String SclassSection = ((EditText) view.findViewById(R.id.classSection)).getText().toString();
                        classSection = Integer.parseInt(SclassSection);
                        String SclassTime = ((EditText) view.findViewById(R.id.classTime)).getText().toString();


                        if (checkInfo(className, classCode, classSection, classSection)){
                            listener.applyClassinfo(className, classCode, classSection, classSection);
                            dialog.dismiss();
                        }else{

                        }

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

    public boolean checkInfo(String name, String code, int Section, int time){
        boolean classPassed = false;
        AlertDialog dialog = (AlertDialog) getDialog();

        String[] classOptions = getResources().getStringArray(R.array.class_nicknames);
        int size = classOptions.length;
        Log.d("Size of Array", Integer.toString(size));
       for(int i = 0; i < classOptions.length; i++){
            if(name.equals(classOptions[i])){
                classPassed =true;
            }

        }

       boolean codePassed = false;

       if (Integer.valueOf(code) >=1000 && Integer.valueOf(code) < 10000  ){
           codePassed = true;
       }

       boolean sectionPassed = false;

       if(Section > 0 && Section < 100){
           sectionPassed = true;
       }

       boolean timePassed = false;



       if(codePassed && classPassed && sectionPassed){
           return true;
       }else if(!codePassed){
            Toast.makeText(getContext(), "Code should be between 1000 and 9999", Toast.LENGTH_SHORT).show();
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
        }else if(!classPassed){
           Toast.makeText(getContext(), name + " is not a class nickname", Toast.LENGTH_SHORT).show();
           dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
       }else if(!sectionPassed){
           Toast.makeText(getContext(), Section + " is not a section", Toast.LENGTH_SHORT).show();
           dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
       }

        return false;
    }
}

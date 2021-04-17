package cpsc4150.allaroundclemson;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatDialogFragment;

import java.util.Calendar;

public class ringDialog extends AppCompatDialogFragment {

    public interface ringDialogListener {
        void correctRingYear(boolean correct);
    }

    private ringDialog.ringDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_ringdialog, null);



        builder.setView(view)
                .setTitle("Clemson Ring Icon")
                .setMessage("Receive a Clemson ring")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("Complete it", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String copy = ((EditText) view.findViewById(R.id.gradYear)).getText().toString();
                        int yr = Integer.parseInt(copy);

                        Calendar calendar = Calendar.getInstance();
                        int currentYR = calendar.get(Calendar.YEAR);

                        if (yr <= currentYR){
                            listener.correctRingYear(true);
                        }else{
                            listener.correctRingYear(false);
                        }

                    }
                });

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (ringDialog.ringDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement ringDialogListener");
        }
    }
}
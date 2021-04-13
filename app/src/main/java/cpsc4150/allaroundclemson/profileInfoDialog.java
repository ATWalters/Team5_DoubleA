package cpsc4150.allaroundclemson;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatDialogFragment;

public class profileInfoDialog extends AppCompatDialogFragment {

    public interface profileInfoDialogListener {
        void applyProfile(int profile);
    }

    private profileInfoDialogListener listener;
    private int profileChosen = 0;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_profileinfodialog, null);
        RadioGroup profile_button = (RadioGroup) view.findViewById(R.id.profile_choice);

        profile_button.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedID) {
                switch (checkedID) {

                    case R.id.ProspectiveStudent:
                        profileChosen = 0;
                        break;
                    case R.id.CurrentStudent:
                        profileChosen = 1;
                        break;
                    case R.id.Alumni:
                        profileChosen = 2;
                        break;
                    case R.id.fan:
                        profileChosen = 3;
                        break;
                }
            }
        });

        builder.setView(view)
                .setTitle("Profile Information")
                .setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listener.applyProfile(profileChosen);
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
            listener = (profileInfoDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement profileInfoDialogListener");
        }
    }
}


package cpsc4150.allaroundclemson;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class PenaltyDescriptionFragment extends Fragment {
    private Penalty mPenalty;

    public static cpsc4150.allaroundclemson.PenaltyDescriptionFragment newInstance(int penaltyId){
        cpsc4150.allaroundclemson.PenaltyDescriptionFragment fragment = new cpsc4150.allaroundclemson.PenaltyDescriptionFragment();
        Bundle args = new Bundle();
        args.putInt("penaltyId", penaltyId);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        int penaltyId = 1;
        if(getArguments() != null){
            penaltyId = getArguments().getInt("penaltyId");
        }

        mPenalty = PenaltyDatabase.get(getContext()).getPenalty(penaltyId);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_penalty_description, container, false);

        TextView descriptionNameTextView = view.findViewById(R.id.penalty_desc_name);
        descriptionNameTextView.setText(getString(R.string.penaltyDescName, mPenalty.getName()));

        TextView descriptionTextView = view.findViewById(R.id.penalty_desc);
        descriptionTextView.setText(getString(R.string.penaltyDesc, mPenalty.getDescription()));

        return view;
    }
}

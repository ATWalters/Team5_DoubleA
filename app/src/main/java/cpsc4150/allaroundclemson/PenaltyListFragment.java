package cpsc4150.allaroundclemson;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PenaltyListFragment extends Fragment {

    public interface OnPenaltySelectedListener{
        void onPenaltySelected(int penaltyId);
    }

    private OnPenaltySelectedListener mListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
         View view = inflater.inflate(R.layout.penalty_fragment_list, container, false);
         RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.penalty_recycler_view);
         recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

         PenaltyAdapter adapter = new PenaltyAdapter(PenaltyDatabase.get(getContext()).getPenalties());
         recyclerView.setAdapter(adapter);

         return view;
    }

    private class PenaltyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private Penalty mPenalties;
        private final TextView mTextView;

        public PenaltyHolder(LayoutInflater inflater, ViewGroup parent){
            super(inflater.inflate(R.layout.list_item_penalty, parent, false));
            itemView.setOnClickListener(this);
            mTextView = (TextView) itemView.findViewById(R.id.penaltyName);
        }

        public void bind(Penalty penalty){
            mPenalties = penalty;
            mTextView.setText(mPenalties.getName());
        }

        @Override
        public void onClick(View v) {
            mListener.onPenaltySelected(mPenalties.getId());
        }
    }

    private class PenaltyAdapter extends RecyclerView.Adapter<PenaltyHolder>{

        private final List<Penalty> mPenalties;

        public PenaltyAdapter(List<Penalty> penalties){
            mPenalties = penalties;
        }

        @NonNull
        @Override
        public PenaltyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new PenaltyHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(PenaltyHolder holder, int position) {
            Penalty penalty = mPenalties.get(position);
            holder.bind(penalty);
        }

        @Override
        public int getItemCount() {
            return mPenalties.size();
        }
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        if(context instanceof OnPenaltySelectedListener){
            mListener = (OnPenaltySelectedListener) context;
        }else{
            throw new RuntimeException(context.toString()
                    + " must implement OnPenaltySelectedListener");
        }
    }

    @Override
    public void onDetach(){
        super.onDetach();
        mListener = null;
    }

    private View.OnClickListener buttonClickListener = new View.OnClickListener(){
        @Override
        public void onClick(View view){
            String penaltyId = (String) view.getTag();
            mListener.onPenaltySelected(Integer.parseInt(penaltyId));
        }
    };
}

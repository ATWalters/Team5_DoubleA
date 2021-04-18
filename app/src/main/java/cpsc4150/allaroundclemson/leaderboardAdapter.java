package cpsc4150.allaroundclemson;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;

public class leaderboardAdapter extends RecyclerView.Adapter<leaderboardAdapter.ViewHolder>{
    private ArrayList<leaderboard> ldScores = new ArrayList<leaderboard>();
    private LayoutInflater mInflator;

    public leaderboardAdapter(ArrayList<leaderboard> ld, Context context) {
        this.ldScores = ld;
        this.mInflator = LayoutInflater.from(context);

    }

    //Adding a leaderboard to the adapter
    public void add(leaderboard content) {
        leaderboard current = content;

        ldScores.add(content);

        Collections.sort(ldScores);

        notifyItemRangeChanged(0, getItemCount());
    }

    //Removing a leaderboard from the adapter
    public void deleteItem(int position){
        leaderboard current = ldScores.get(position);
        ldScores.remove(position);
        notifyDataSetChanged();
    }


    @Override
    public leaderboardAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = mInflator.inflate(R.layout.recycerview_leaderboard, parent, false);
        return new leaderboardAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(leaderboardAdapter.ViewHolder holder, int i) {
        leaderboard current = ldScores.get(getItemCount() - i - 1);
        String username = current.getUsername();
        String score = String.valueOf(current.getScore());
        holder.listItem.setText(username + " " + score);
        holder.listItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteItem(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(ldScores.size()>5){
            return 5;
        }else{
            return ldScores.size();
        }
    }

    public ArrayList<leaderboard> currentList(){
        return ldScores;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView listItem;

        public ViewHolder(View itemView) {
            super(itemView);
            listItem = itemView.findViewById(R.id.ldView);
        }

    }

}


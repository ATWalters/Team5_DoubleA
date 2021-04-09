package cpsc4150.allaroundclemson;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class clemsonClassAdapter extends RecyclerView.Adapter<clemsonClassAdapter.ViewHolder> {

    private ArrayList<clemsonClass> listClemsonClasses = new ArrayList<>();
    private Context context;
    public clemsonClassAdapter(ArrayList<clemsonClass> listItemTexts, Context context) {
        this.listClemsonClasses = listItemTexts;
        this.context = context;
    }

    public void add(clemsonClass content) {
        listClemsonClasses.add(content);
        notifyItemInserted(getItemCount());
    }

    public void remove(int position) {

        clemsonClass remove = listClemsonClasses.remove(position);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recyclerview_classlayout, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        clemsonClass current = listClemsonClasses.get(i);
        viewHolder.listItem.setText(current.getName());
        viewHolder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return listClemsonClasses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView listItem;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            listItem = itemView.findViewById(R.id.textview1);
            parentLayout = itemView.findViewById(R.id.classLayout);
        }
    }
}

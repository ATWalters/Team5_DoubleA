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

    private ArrayList<clemsonClass> listClemsonClasses;
    private LayoutInflater mInflator;

    public clemsonClassAdapter(ArrayList<clemsonClass> listItemTexts, Context context) {
        this.listClemsonClasses = listItemTexts;
        this.mInflator = LayoutInflater.from(context);

    }

    public void add(clemsonClass content) {
        listClemsonClasses.add(content);
        notifyItemInserted(getItemCount());
    }

    public void remove(int position) {

        clemsonClass remove = listClemsonClasses.remove(position);
        listClemsonClasses.remove(remove);
        notifyDataSetChanged();
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = mInflator.inflate(R.layout.recyclerview_classlayout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,  int i) {
        clemsonClass current = listClemsonClasses.get(i);
        String className = current.getName();
        String classCode = current.getClassCode();
        holder.listItem.setText(className + " " + classCode);
    }

    @Override
    public int getItemCount() {
        return listClemsonClasses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView listItem;
        //RelativeLayout parentLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            listItem = itemView.findViewById(R.id.textview1);
            //parentLayout = itemView.findViewById(R.id.classLayout);
        }
    }
}

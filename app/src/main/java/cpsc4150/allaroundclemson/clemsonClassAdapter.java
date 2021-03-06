/*
Name: Allison Tizik and Austin Walters
Date: 4/18/2021

Description: Adapter that adds the classes the user inputs in the classInfoDialog into the currentStudent Activity.
*/
package cpsc4150.allaroundclemson;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class clemsonClassAdapter extends RecyclerView.Adapter<clemsonClassAdapter.ViewHolder>{

    private ArrayList<clemsonClass> listClemsonClasses;
    private LayoutInflater mInflator;

    public clemsonClassAdapter(ArrayList<clemsonClass> listItemTexts, Context context) {
        this.listClemsonClasses = listItemTexts;
        this.mInflator = LayoutInflater.from(context);

    }

    //Method that adds a class to the adapter
    public void add(clemsonClass content) {
        listClemsonClasses.add(content);
        notifyItemInserted(getItemCount());
    }

    //Method that deletes a class from the adapter
    public void deleteItem(int position){
        clemsonClass current = listClemsonClasses.get(position);
        String className = current.getName() + " " + current.getClassCode();
        AlertDialog myQuittingDialogBox = new AlertDialog.Builder(mInflator.getContext())
                // set message, title, and icon
                .setTitle("Delete")
                .setMessage("Do you want to Delete " + className)

                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int whichButton) {
                        listClemsonClasses.remove(position);
                        notifyDataSetChanged();
                        dialog.dismiss();
                    }

                })
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                    }
                })
                .create();
    }

    //Method that removes the class from the adapter that matches the name and code passed in
    public void remove(String name, String code) {

        name = name.toUpperCase();
        int size = listClemsonClasses.size();
        boolean check = false;

        for(int i = 0; i < size; i++){
            if(listClemsonClasses.get(i).getName().equals(name)){
                if(listClemsonClasses.get(i).getClassCode().equals(code)){
                    listClemsonClasses.remove(i);
                    notifyDataSetChanged();
                    i = size;
                    check = true;
                }
            }
        }

        if(!check){
            Toast.makeText(mInflator.getContext(),  name + " " + code + " is not a class in the list", Toast.LENGTH_LONG).show();
        }

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
        holder.listItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteItem(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(listClemsonClasses.size() > 7){
            return 7;
        }else{
            return listClemsonClasses.size();
        }

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView listItem;

        public ViewHolder(View itemView) {
            super(itemView);
            listItem = itemView.findViewById(R.id.textview1);
        }

    }

}

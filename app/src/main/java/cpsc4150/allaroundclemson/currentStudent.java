/*
Name: Allison Tizik and Austin Walters
Date: 4/18/2021

Description: Presents the currect student activity and allows for classes to be added or deleted by user
*/
package cpsc4150.allaroundclemson;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class currentStudent extends AppCompatActivity implements
        classInfoDialog.classInfoDialogListener,
        deleteInfoDialog.deleteInfoDialogListener,
        profileInfoDialog.profileInfoDialogListener {
    private ArrayList<clemsonClass> items = new ArrayList<>();
    private Button btn;
    private Button deleteBtn;
    RecyclerView recyclerView;
    clemsonClassAdapter adapter;
    private ClassDatabase db = new ClassDatabase(this);


    profileInfoDialog infoDialog = new profileInfoDialog();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currentstudentprofile);

        recyclerView = findViewById(R.id.rvClasses);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new clemsonClassAdapter(items, this);
        recyclerView.setAdapter(adapter);

        btn = findViewById(R.id.addClass);
        db.popAdapter(adapter);

        Button change = (Button) findViewById(R.id.changeBtn);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("CLICK", "Change clicked");
                infoDialog.show(getSupportFragmentManager(), "profileInfoDialog");
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openClassDialog();
            }
        });

        deleteBtn = findViewById(R.id.removeClass);

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDeleteDialog();
            }
        });

        ImageButton back = (ImageButton) findViewById(R.id.backBtn);
        back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }

        });

    }

    @Override
    public void applyClassinfo(String name, String code, int section) {

        clemsonClass cl = new clemsonClass(name, code, section);
        adapter.add(cl);
        long id = db.addClass(cl);
    }

    /*
    Pre: add class button was pressed
    Post: a dialog is opened to allow user input of a class
     */
    public void openClassDialog(){
        classInfoDialog infoDialog = new classInfoDialog();
        infoDialog.show(getSupportFragmentManager(), "classInfoDialog");
    }


    /*
    Pre: name - is a class, code - is a 4 digit number
    Post: class is deleted from the adapter list
     */
    @Override
    public void deleteClassinfo(String name, String code) {

        adapter.remove(name, code);
        db.deleteClass(name, code);

    }

    /*
    Pre: delete button was pressed
    Post: a dialog is opened to allow user input of class to delete
     */
    public void openDeleteDialog(){
        deleteInfoDialog infoDialog = new deleteInfoDialog();
        infoDialog.show(getSupportFragmentManager(), "deleteInfoDialog");
    }

    /*
    Pre: 0 <= profile <=3
     */
    @Override
    public void applyProfile(int profile){
        if(profile == 0){
            Intent myIntent = new Intent(this, prospectiveStudent.class);
            startActivity(myIntent);

        }else if(profile ==1){
            Intent myIntent = new Intent(this, currentStudent.class);
            startActivity(myIntent);

        }else if(profile == 2){
            Intent myIntent = new Intent(this, alumni.class);
            startActivity(myIntent);

        }else if (profile == 3){
            Intent myIntent = new Intent(this, fan.class);
            startActivity(myIntent);

        }
    }
}

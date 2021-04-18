package cpsc4150.allaroundclemson;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class currentStudent extends AppCompatActivity implements classInfoDialog.classInfoDialogListener, deleteInfoDialog.deleteInfoDialogListener {
    private ArrayList<clemsonClass> items = new ArrayList<>();
    private Button btn;
    private Button deleteBtn;
    RecyclerView recyclerView;
    clemsonClassAdapter adapter;
    private ClassDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currentstudentprofile);

        recyclerView = findViewById(R.id.rvClasses);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new clemsonClassAdapter(items, this);
        recyclerView.setAdapter(adapter);

        btn = findViewById(R.id.addClass);

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
    public void applyClassinfo(String name, String code, int section, int time) {

        clemsonClass cl = new clemsonClass(name, code, section, time);
        adapter.add(cl);
        long id = db.addClass(cl);
        cl.setId(id);
    }

    public void openClassDialog(){
        classInfoDialog infoDialog = new classInfoDialog();
        infoDialog.show(getSupportFragmentManager(), "classInfoDialog");
    }

    @Override
    public void deleteClassinfo(String name, String code) {

        adapter.remove(name, code);
    }

    public void openDeleteDialog(){
        deleteInfoDialog infoDialog = new deleteInfoDialog();
        infoDialog.show(getSupportFragmentManager(), "deleteInfoDialog");
    }
}

package cpsc4150.allaroundclemson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class gameandicons extends AppCompatActivity implements gameInfoDialog.gameInfoDialogListener {
    private Button startBtn;
    private Button iconBtn;

    private String Username;
    private ArrayList<leaderboard> ldboard = new ArrayList<leaderboard>();
    RecyclerView recyclerView;
    leaderboardAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameandicons);

        recyclerView = findViewById(R.id.rvleaderboard);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new leaderboardAdapter(ldboard, this);
        recyclerView.setAdapter(adapter);

        startBtn = findViewById(R.id.startButton);
        iconBtn = findViewById(R.id.iconsButton);

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

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGameDialog();

                //games beings
                // get 10 random multiple choice questions
                // count the ones they get right
                // give score
                //return score and username
            }
        });

        /*
            Icon button brings you to new page with all the icons
         */

        iconBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startIconActivity();

            }
        });

    }

    private void startIconActivity() {
        Intent myIntent = new Intent(this, icons.class);
        ArrayList<leaderboard> list = adapter.currentList();

        for(int i = 0; i < list.size(); i++){
            if ((list.get(i).getScore() >= 8)){
                myIntent.putExtra("SCORE",list.get(i).getScore());
                i = list.size();
            }
        }

        startActivity(myIntent);
        /*create recyclerview
            has both username and score
         */



        /*
            Icon button brings you to new page with all the icons
         */

    }

    public void openGameDialog(){
        gameInfoDialog infoDialog = new gameInfoDialog();
        infoDialog.show(getSupportFragmentManager(), "gameInfoDialog");
    }

    @Override
    public void applyUsername(String name) {
        Username = name;
        Intent myIntent = new Intent(this, trivia.class);
        startActivityForResult(myIntent, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                int newscore = data.getIntExtra("SCORE", 0);
                leaderboard copy = new leaderboard(Username, newscore);
                //ldboard.add(copy);

                adapter.add(copy);


            }
        }
    }
}
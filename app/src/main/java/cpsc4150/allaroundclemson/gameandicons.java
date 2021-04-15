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
/*
Name: Allison Tizik and Austin Walters
Date: 4/18/2021

Description: opens the game and icons activity, which shows a leaderboard for trivia game, allows you
to start a trivia game, and allows you to go to the icons activity.
*/
package cpsc4150.allaroundclemson;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class gameandicons extends AppCompatActivity implements gameInfoDialog.gameInfoDialogListener {
    private Button startBtn;
    private Button iconBtn;
    private final String TAG = "Game";
    private String Username;
    private ArrayList<leaderboard> ldboard = new ArrayList<leaderboard>();
    RecyclerView recyclerView;
    leaderboardAdapter adapter;

    private LeaderboardDatabase db = new LeaderboardDatabase(this);

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

        db.popAdapter(adapter);

        SharedPreferences prefs = getSharedPreferences("myprefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();


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
                //openGameDialog();

                boolean firstGameClick = prefs.getBoolean("first_game_click", true);

                if(firstGameClick){
                    Log.e(TAG, "First game click");
                    editor.putBoolean("first_game_click", false).commit();
                    openGameDialog();
                }else{
                    Log.e(TAG, "Game clicked");
                    Username = prefs.getString("username", "Default");
                    applyUsername(Username);
                }

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

    //Method that starts the icon activity
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

    /*Method that opens the game dialog
    Pre: start game button was clicked
    Post: game dialog pops up asking for username
     */
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

                adapter.add(copy);
                db.addLeaderboard(copy);
            }
        }
    }
}
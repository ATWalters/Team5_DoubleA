package cpsc4150.allaroundclemson;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MainActivity extends AppCompatActivity implements profileInfoDialog.profileInfoDialogListener {

    String TAG = "Main";
    profileInfoDialog infoDialog = new profileInfoDialog();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences prefs = getSharedPreferences("myprefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        boolean firstProfClick = prefs.getBoolean("first_profile_click", true);


        CardView profile = findViewById(R.id.profile_view);
        CardView maps = findViewById(R.id.map_view);
        CardView sports = findViewById(R.id.sports_view);
        CardView game = findViewById(R.id.funfacts_view);

        profile.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(firstProfClick){
                    Log.e(TAG, "First profile click");
                    editor.putBoolean("first_profile_click", false).commit();
                    openProfileDialog();
                }else{
                    Log.e(TAG, "Profile clicked");
                    int userType = prefs.getInt("profile_chosen", 1);
                    applyProfile(userType);
                }
            }

        });

        maps.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startMapsView();
            }

        });

        sports.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startSportsView();
            }

        });

        game.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startGameView();
            }

        });

    }

    public void openProfileDialog(){
        infoDialog.show(getSupportFragmentManager(), "profileInfoDialog");
    }

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

    public void startGameView(){
        Intent myIntent = new Intent(this, gameandicons.class);
        startActivity(myIntent);
    }

    public void startSportsView(){
        Intent myIntent = new Intent(this, SportsActivity.class);
        startActivity(myIntent);
    }

    public void startMapsView(){
        Intent myIntent = new Intent(this, MapsActivity.class);
        startActivity(myIntent);
    }

}
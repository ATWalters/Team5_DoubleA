package cpsc4150.allaroundclemson;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements profileInfoDialog.profileInfoDialogListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CardView profile = findViewById(R.id.profile_view);
        CardView maps = findViewById(R.id.map_view);
        CardView sports = findViewById(R.id.sports_view);
        CardView game = findViewById(R.id.funfacts_view);

        profile.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                openProfileDialog();
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
        profileInfoDialog infoDialog = new profileInfoDialog();
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
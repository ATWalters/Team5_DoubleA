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
                Intent intent = new Intent();
                startActivity(intent);
            }

        });

        sports.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent();
                startActivity(intent);
            }

        });

        game.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent();
                startActivity(intent);
            }

        });



    }

    public void openProfileDialog(){
        profileInfoDialog infoDialog = new profileInfoDialog();
        infoDialog.show(getSupportFragmentManager(), "profileInfoDialog");
    }

    @Override
    public void applyProfile(int profile){
        int profileChoice = profile;
        Intent intent = new Intent(this, profile.class);
        intent.putExtra("PROFILE", profileChoice);
        startActivity(intent);
    }
}
/*
Name: Allison Tizik and Austin Walters
Date: 4/18/2021

Description: Opens the fan activity that allows for users to click on links to sports schedules
*/
package cpsc4150.allaroundclemson;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class fan extends AppCompatActivity implements
        profileInfoDialog.profileInfoDialogListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fan);
        setupHyperlinks();

        profileInfoDialog infoDialog = new profileInfoDialog();

        Button change = (Button) findViewById(R.id.changeBtn);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("CLICK", "Change clicked");
                infoDialog.show(getSupportFragmentManager(), "profileInfoDialog");
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

    private void setupHyperlinks() {
        TextView linkTextView = findViewById(R.id.football_Link);
        linkTextView.setMovementMethod(LinkMovementMethod.getInstance());

        TextView linkTextView2 = findViewById(R.id.baseball_link);
        linkTextView2.setMovementMethod(LinkMovementMethod.getInstance());

        TextView linkTextView3 = findViewById(R.id.softball_link);
        linkTextView3.setMovementMethod(LinkMovementMethod.getInstance());

        TextView linkTextView4 = findViewById(R.id.mens_basketball_link);
        linkTextView4.setMovementMethod(LinkMovementMethod.getInstance());

        TextView linkTextView5 = findViewById(R.id.womens_basketball_link);
        linkTextView5.setMovementMethod(LinkMovementMethod.getInstance());

        TextView linkTextView6 = findViewById(R.id.mens_soccer_link);
        linkTextView6.setMovementMethod(LinkMovementMethod.getInstance());

        TextView linkTextView7 = findViewById(R.id.womens_soccer_link);
        linkTextView7.setMovementMethod(LinkMovementMethod.getInstance());

        TextView linkTextView8 = findViewById(R.id.mens_tennis_link);
        linkTextView8.setMovementMethod(LinkMovementMethod.getInstance());

        TextView linkTextView9 = findViewById(R.id.womens_tennis_link);
        linkTextView9.setMovementMethod(LinkMovementMethod.getInstance());

        TextView linkTextView10 = findViewById(R.id.mens_golf_link);
        linkTextView10.setMovementMethod(LinkMovementMethod.getInstance());

        TextView linkTextView11 = findViewById(R.id.womens_golf_link);
        linkTextView11.setMovementMethod(LinkMovementMethod.getInstance());
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
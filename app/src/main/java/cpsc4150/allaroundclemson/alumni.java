/*
Name: Allison Tizik and Austin Walters
Date: 4/18/2021

Description: Activity that presents the alumni activity with information for Clemson Alumni
*/

package cpsc4150.allaroundclemson;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class alumni extends AppCompatActivity implements
        profileInfoDialog.profileInfoDialogListener {

    profileInfoDialog infoDialog = new profileInfoDialog();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumni);
        setupHyperlink();

        Button change = (Button) findViewById(R.id.changeBtn);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

    private void setupHyperlink() {
        TextView linkTextView = findViewById(R.id.alumniLink);
        linkTextView.setMovementMethod(LinkMovementMethod.getInstance());
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
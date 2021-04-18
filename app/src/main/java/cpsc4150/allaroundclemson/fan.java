/*
Name: Allison Tizik and Austin Walters
Date: 4/18/2021

Description: Opens the fan activity that allows for users to click on links to sports schedules
*/
package cpsc4150.allaroundclemson;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class fan extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fan);
        setupHyperlinks();

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
}
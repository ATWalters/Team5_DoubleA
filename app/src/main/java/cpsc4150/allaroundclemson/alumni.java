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
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class alumni extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumni);
        setupHyperlink();

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

}
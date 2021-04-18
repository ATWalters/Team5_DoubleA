package cpsc4150.allaroundclemson;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class prospectiveStudent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prospectivestudentprofile);
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
        TextView linkTextView = findViewById(R.id.psLink);
        linkTextView.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
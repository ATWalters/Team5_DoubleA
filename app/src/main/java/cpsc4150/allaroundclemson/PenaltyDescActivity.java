package cpsc4150.allaroundclemson;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class PenaltyDescActivity extends AppCompatActivity {
    public static String EXTRA_PENALTY_ID = "penaltyId";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_penalty_desc);

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.penaltyFragmentContainer);

        if(fragment == null){
            int penaltyId = getIntent().getIntExtra(EXTRA_PENALTY_ID, 1);
            fragment = PenaltyDescriptionFragment.newInstance(penaltyId);
            fragmentManager.beginTransaction().add(R.id.penaltyFragmentContainer, fragment).commit();
        }
    }
}

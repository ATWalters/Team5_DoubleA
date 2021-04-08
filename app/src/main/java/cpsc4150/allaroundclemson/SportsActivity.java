package cpsc4150.allaroundclemson;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;


public class SportsActivity extends AppCompatActivity
        implements PenaltyListFragment.OnPenaltySelectedListener{
    private static final String KEY_PENALTY_ID = "penaltyId";
    private int mPenaltyId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports);

        mPenaltyId = -1;
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.penaltyFragmentContainer);

        //Testing to make sure this would update textview, leaving commented for now to reference later
        //TextView homeTeamName = (TextView)findViewById(R.id.homeTeamName);
        //homeTeamName.setText(getString(R.string.homeTeam));

        //TODO: Add call to functions that handle API stuff

        if(fragment == null){
            fragment = new PenaltyListFragment();
            fragmentManager.beginTransaction()
                    .add(R.id.penaltyFragmentContainer, fragment)
                    .commit();
        }

        if(savedInstanceState != null && savedInstanceState.getInt(KEY_PENALTY_ID) != 0){
            mPenaltyId = savedInstanceState.getInt(KEY_PENALTY_ID);
            Fragment penaltyFragment = PenaltyDescriptionFragment.newInstance(mPenaltyId);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.penaltyFragmentContainer, penaltyFragment)
                    .commit();
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);

        if(mPenaltyId != -1){
            savedInstanceState.putInt(KEY_PENALTY_ID, mPenaltyId);
        }
    }

    @Override
    public void onPenaltySelected(int penaltyId) {
        mPenaltyId = penaltyId;

        if(findViewById(R.id.penaltyFragmentContainer) == null){
            Intent intent = new Intent(this, PenaltyDescActivity.class);
            intent.putExtra(PenaltyDescActivity.EXTRA_PENALTY_ID, penaltyId);
            startActivity(intent);
        }else{
            Fragment penaltyFragment = PenaltyDescriptionFragment.newInstance(penaltyId);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.penaltyFragmentContainer, penaltyFragment)
                    .commit();

        }
    }

}
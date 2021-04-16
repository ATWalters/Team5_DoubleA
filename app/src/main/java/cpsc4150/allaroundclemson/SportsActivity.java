package cpsc4150.allaroundclemson;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.VolleyError;

import java.util.Calendar;
import java.util.Date;


public class SportsActivity extends AppCompatActivity
        implements PenaltyListFragment.OnPenaltySelectedListener{
    private static final String KEY_PENALTY_ID = "penaltyId";
    private int mPenaltyId;
    private SportsFetcher mSportsFetcher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports);


        //Testing to make sure this would update textview, leaving commented for now to reference later
        //TextView homeTeamName = (TextView)findViewById(R.id.homeTeamName);
        //homeTeamName.setText(getString(R.string.homeTeam));

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

        mSportsFetcher = new SportsFetcher(this);
        mSportsFetcher.fetchCurrentGames(mFetchListener);
        Date currentDate = Calendar.getInstance().getTime();
        Log.e("TIME TEST", currentDate.toString());

        mPenaltyId = -1;
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentById(R.id.penaltyFragmentContainer);

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

    private SportsFetcher.OnSportsDataReceivedListener mFetchListener = new SportsFetcher.OnSportsDataReceivedListener() {
        @Override
        public void onCurrentGamesReceived(Boolean isGame) {
            if(isGame){

            }else {
                //Display text in a toast saying Clemson is not playing a game right now or something
                Context context = getApplicationContext();
                int duration = Toast.LENGTH_LONG;
                String text = "Clemson is not playing a game right now";
                Toast toast = Toast.makeText(context, text, duration);
                toast.setGravity(Gravity.CENTER|Gravity.CENTER_VERTICAL, 0, 0);
                toast.show();
            }
        }

        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(getApplicationContext(), "Error getting API response. Try again later", Toast.LENGTH_LONG).show();
        }
    };

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


    public void back_to_list_btn(View view) {
        setContentView(R.layout.activity_sports);

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

        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment;

        fragment = new PenaltyListFragment();
        fragmentManager.beginTransaction()
                .replace(R.id.penaltyFragmentContainer, fragment)
                .commit();
    }
}
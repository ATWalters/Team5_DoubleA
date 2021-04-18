package cpsc4150.allaroundclemson;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import java.util.Calendar;

public class icons extends AppCompatActivity implements gradDialog.gradDialogListener, ringDialog.ringDialogListener {

    private LayoutInflater mInflator;
    private static final int CAMERA_REQUEST = 1888;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;

    private CardView shirtView;
    private CardView firstM;
    private CardView secondM;
    private CardView baseball;
    private CardView soccer;
    private CardView basketball;
    private CardView football;
    private CardView gradCap;
    private CardView ring;


    private int view;



    private void setMedals(int s){
        if(s == 10){
            secondM.setCardBackgroundColor(getColor(R.color.orange));
            firstM.setCardBackgroundColor(getColor(R.color.orange));
        }else if (s > 8){
            secondM.setCardBackgroundColor(getColor(R.color.orange));
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_icons);

        ImageButton back = (ImageButton) findViewById(R.id.backBtn);
        back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(getApplicationContext(), gameandicons.class);
                startActivity(intent);
            }

        });

        Intent mIntent = getIntent();
        int score = mIntent.getIntExtra("SCORE", 0);

        this.mInflator = LayoutInflater.from(this);
        shirtView = findViewById(R.id.shirt_view);
        firstM = findViewById(R.id.firstmedal_view);
        secondM = findViewById(R.id.second_medal);
        baseball = findViewById(R.id.baseball_view);
        basketball = findViewById(R.id.basketball_view);
        soccer = findViewById(R.id.soccer_view);
        football = findViewById(R.id.football_view);
        gradCap = findViewById(R.id.gradcap_view);
        ring = findViewById(R.id.ring_view);

        setMedals(score);

        shirtView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                if (shirtView.getCardBackgroundColor().getDefaultColor() == ContextCompat.getColor(getApplication(), R.color.white)){


                    AlertDialog completed = new AlertDialog.Builder(icons.this)
                            // set message, title, and icon
                            .setTitle("Solid Orange Icon")
                            .setMessage("Wear an orange shirt on Friday")

                            .setPositiveButton("Complete it", new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int whichButton) {
                                    //open thing to complete
                                    //if it is completed turn the background orange

                                    Calendar calendar = Calendar.getInstance();
                                    int day = calendar.get(Calendar.DAY_OF_WEEK);
                                    view = 0;

                                    if(day == Calendar.FRIDAY) {
                                        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                                        {
                                            Log.e("icons", "No Camera Permission");
                                            requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
                                        }
                                        else
                                        {

                                            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                                            startActivityForResult(cameraIntent, CAMERA_REQUEST);

                                        }

                                    }else{
                                        Toast.makeText(getApplicationContext(), "It isn't Friday yet!", Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();
                                    }

                                }

                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .create();

                    completed.show();

                }else{

                    AlertDialog completed = new AlertDialog.Builder(mInflator.getContext())
                            // set message, title, and icon
                            .setTitle("Solid Orange Icon")
                            .setMessage("Wore orange on Friday")

                            .setPositiveButton("ok", new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int whichButton) {

                                }

                            })
                            .create();

                    completed.show();
                }

            }

        });

        firstM.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {



                if (firstM.getCardBackgroundColor().getDefaultColor() == ContextCompat.getColor(getApplication(), R.color.white)){

                    AlertDialog completed = new AlertDialog.Builder(icons.this)
                            // set message, title, and icon
                            .setTitle("First Place Medal Icon")
                            .setMessage("Receive a 10/10 on the Trivia Game")
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .create();

                    completed.show();

                }else{

                    AlertDialog completed = new AlertDialog.Builder(mInflator.getContext())
                            // set message, title, and icon
                            .setTitle("First Place Medal Icon")
                            .setMessage("Received a 10/10 on the Trivia Game")

                            .setPositiveButton("ok", new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int whichButton) {

                                }

                            })
                            .create();

                    completed.show();
                }

            }

        });

        secondM.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                if (secondM.getCardBackgroundColor().getDefaultColor() == ContextCompat.getColor(getApplication(), R.color.white)){

                    AlertDialog completed = new AlertDialog.Builder(icons.this)
                            // set message, title, and icon
                            .setTitle("Second Place Medal Icon")
                            .setMessage("Receive a 8/10 on the Trivia Game")
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .create();

                    completed.show();

                }else{
                    AlertDialog completed = new AlertDialog.Builder(mInflator.getContext())
                            // set message, title, and icon
                            .setTitle("2nd Place Medal")
                            .setMessage("Received an 8/10 on the Trivia game")

                            .setPositiveButton("ok", new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int whichButton) {

                                }

                            })
                            .create();

                    completed.show();
                }

            }

        });

        baseball.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {


                if (baseball.getCardBackgroundColor().getDefaultColor() == ContextCompat.getColor(getApplication(), R.color.white)){

                    AlertDialog completed = new AlertDialog.Builder(icons.this)
                            // set message, title, and icon
                            .setTitle("Baseball Icon")
                            .setMessage("Attend a home Baseball game")

                            .setPositiveButton("Complete it", new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int whichButton) {
                                    //open thing to complete
                                    //if it is completed turn the background orange
                                    Calendar calendar = Calendar.getInstance();
                                    int month = calendar.get(Calendar.MONTH);
                                    view = 1;

                                    if(month > 1 && month < 7) {
                                        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                                        {
                                            requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
                                        }
                                        else
                                        {

                                            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                                            startActivityForResult(cameraIntent, CAMERA_REQUEST);

                                        }

                                    }else{
                                        Toast.makeText(getApplicationContext(), "It isn't baseball season yet!", Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();
                                    }

                                }

                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .create();

                    completed.show();

                }else{
                    AlertDialog completed = new AlertDialog.Builder(mInflator.getContext())
                            // set message, title, and icon
                            .setTitle("Baseball Icon")
                            .setMessage("Attended a home baseball game")

                            .setPositiveButton("ok", new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int whichButton) {

                                }

                            })
                            .create();

                    completed.show();
                }

            }

        });

        basketball.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                if (basketball.getCardBackgroundColor().getDefaultColor() == ContextCompat.getColor(getApplication(), R.color.white)){

                    AlertDialog completed = new AlertDialog.Builder(icons.this)
                            // set message, title, and icon
                            .setTitle("Basketball Icon")
                            .setMessage("Attend a home Basketball game")

                            .setPositiveButton("Complete it", new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int whichButton) {
                                    //open thing to complete
                                    //if it is completed turn the background orange
                                    Calendar calendar = Calendar.getInstance();
                                    int month = calendar.get(Calendar.MONTH);
                                    view = 2;

                                    if (month > 10 || month < 4) {
                                        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                                            requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
                                        } else {

                                            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                                            startActivityForResult(cameraIntent, CAMERA_REQUEST);

                                        }

                                    }else{
                                        Toast.makeText(getApplicationContext(), "It isn't basketball season yet!", Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();
                                    }
                                }

                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .create();

                    completed.show();

                }else{

                    AlertDialog completed = new AlertDialog.Builder(mInflator.getContext())
                            // set message, title, and icon
                            .setTitle("Basketball Icon")
                            .setMessage("Attended a home basketball game")

                            .setPositiveButton("ok", new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int whichButton) {

                                }

                            })
                            .create();

                    completed.show();
                }

            }

        });

        soccer.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                if (soccer.getCardBackgroundColor().getDefaultColor() == ContextCompat.getColor(getApplication(), R.color.white)){

                    AlertDialog completed = new AlertDialog.Builder(icons.this)
                            // set message, title, and icon
                            .setTitle("Soccer Icon")
                            .setMessage("Attend a home Soccer game")

                            .setPositiveButton("Complete it", new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int whichButton) {
                                    //open thing to complete
                                    //if it is completed turn the background orange
                                    Calendar calendar = Calendar.getInstance();
                                    int month = calendar.get(Calendar.MONTH);
                                    view = 3;

                                    if(month > 8) {
                                        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                                            requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
                                        } else {

                                            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                                            startActivityForResult(cameraIntent, CAMERA_REQUEST);

                                        }
                                    }else{
                                        Toast.makeText(getApplicationContext(), "It isn't soccer season yet!", Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();
                                    }

                                }

                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .create();

                    completed.show();

                }else{
                    AlertDialog completed = new AlertDialog.Builder(mInflator.getContext())
                            // set message, title, and icon
                            .setTitle("Soccer Icon")
                            .setMessage("Attended a home soccer game")

                            .setPositiveButton("ok", new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int whichButton) {

                                }

                            })
                            .create();
                    completed.show();

                }

            }

        });

        football.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                if (football.getCardBackgroundColor().getDefaultColor() == ContextCompat.getColor(getApplication(), R.color.white)){
                    //open dialog explaing what it is and ask if they want to complete it
                    AlertDialog completed = new AlertDialog.Builder(icons.this)
                            // set message, title, and icon
                            .setTitle("Football Icon")
                            .setMessage("Attend a home Football game")

                            .setPositiveButton("Complete it", new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int whichButton) {
                                    //open thing to complete
                                    //if it is completed turn the background orange
                                    Calendar calendar = Calendar.getInstance();
                                    int month = calendar.get(Calendar.MONTH);
                                    view = 4;

                                    if(month > 8 || month < 2) {
                                        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                                            requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
                                        } else {

                                            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                                            startActivityForResult(cameraIntent, CAMERA_REQUEST);

                                        }
                                    }else{
                                        Toast.makeText(getApplicationContext(), "It isn't football season yet!", Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();
                                    }

                                }

                            })
                            .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                }
                            })
                            .create();

                    completed.show();
                }else{
                    AlertDialog completed = new AlertDialog.Builder(mInflator.getContext())
                            // set message, title, and icon
                            .setTitle("Football Icon")
                            .setMessage("Attended a home football game")

                            .setPositiveButton("ok", new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int whichButton) {

                                }

                            })
                            .create();

                    completed.show();
                }

            }

        });

        gradCap.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                if (gradCap.getCardBackgroundColor().getDefaultColor() == ContextCompat.getColor(getApplication(), R.color.white)){
                    gradDialog();
                }else{
                    AlertDialog completed = new AlertDialog.Builder(mInflator.getContext())
                            // set message, title, and icon
                            .setTitle("Graduate Icon")
                            .setMessage("Graduated from Clemson University")

                            .setPositiveButton("ok", new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int whichButton) {

                                }

                            })
                            .create();
                    completed.show();
                }

            }

        });

        ring.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                if (ring.getCardBackgroundColor().getDefaultColor() == ContextCompat.getColor(getApplication(), R.color.white)){
                    ringDialog();

                }else{
                    AlertDialog completed = new AlertDialog.Builder(mInflator.getContext())
                            // set message, title, and icon
                            .setTitle("Clemson Ring Icon")
                            .setMessage("Received Clemson Ring")

                            .setPositiveButton("ok", new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int whichButton) {

                                }

                            })
                            .create();
                    completed.show();
                }

            }

        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
                setView(view);
            }
            else
            {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            setView(view);
        }
    }

    private void setView(int v){
        switch(v){
            case 0:
                shirtView.setCardBackgroundColor(getColor(R.color.orange));
                break;
            case 1:
                baseball.setCardBackgroundColor(getColor(R.color.orange));
                break;
            case 2:
                basketball.setCardBackgroundColor(getColor(R.color.orange));
                break;
            case 3:
                soccer.setCardBackgroundColor(getColor(R.color.orange));
                break;
            case 4:
                football.setCardBackgroundColor(getColor(R.color.orange));
                break;
            case 5:
                gradCap.setCardBackgroundColor(getColor(R.color.orange));
                break;
            case 6:
                ring.setCardBackgroundColor(getColor(R.color.orange));
                break;
        }
    }

    private void gradDialog(){
        gradDialog infoDialog = new gradDialog();
        infoDialog.show(getSupportFragmentManager(), "gradDialog");
    }

    private void ringDialog(){
        ringDialog infoDialog = new ringDialog();
        infoDialog.show(getSupportFragmentManager(), "ringDialog");
    }

    @Override
    public void correctYear(boolean correct) {
        if(correct){
            view = 5;
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
            } else {

                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);

            }
        }
    }

    @Override
    public void correctRingYear(boolean correct) {
        if(correct) {
            view = 6;
            if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
            } else {

                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);

            }
        }
    }
}
package cpsc4150.allaroundclemson;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class trivia extends AppCompatActivity {

    private TextView vScore;
    private TextView vQuestion;
    private Button vChoice1;
    private Button vChoice2;
    private Button vChoice3;
    private Button vChoice4;
    private String answer;
    private int score = 0;
    private int questionNumber = 0;
    private List<Question> allQuestions = new ArrayList<Question>();
    private List<String> choices = new ArrayList<String>();
    private List<Integer> randomNumber = new ArrayList<Integer>();

    //Reads all of the questions from the triviaquestions.json file, as well as the correct answer
    // and choices for the question
    private void setUpQuestions() {

        try {
            JSONObject obj = new JSONObject(Utils.getJsonFromAssets(getApplicationContext(), "triviaquestions.json"));

            JSONArray q_array = obj.getJSONArray("trivia");
            String q;
            String a;
            List<String> c = new ArrayList<String>();
            for(int i = 0; i < q_array.length(); i++){
                JSONObject copy = q_array.getJSONObject(i);
                q = copy.getString("question");
                a = copy.getString("answer");
                JSONArray Ch = copy.getJSONArray("choices");
                JSONObject cp = Ch.getJSONObject(0);
                String choice1 =  cp.getString("1");
                c.add(choice1);
                String choice2 =  cp.getString("2");
                c.add(choice2);
                String choice3 =  cp.getString("3");
                c.add(choice3);
                String choice4 =  cp.getString("4");
                c.add(choice4);


                Question holder = new Question(q, a, c);
                allQuestions.add(holder);
                c.clear();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //Makes a list of random numbers to use in the trivia game that decides which questions are asked

    private void randomGen() {
        Random numberGenerator = new Random();

        for (int i = 0; i < 10; i++) {
            int random = numberGenerator.nextInt(48);
            if (randomNumber.contains(random)) {
                i--;
            } else {
                randomNumber.add(random);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trivia);

        setUpQuestions();
        randomGen();


        vScore = findViewById(R.id.Score);
        vQuestion = findViewById(R.id.txtquestion);
        vChoice1 = findViewById(R.id.choice1);
        vChoice2 = findViewById(R.id.choice2);
        vChoice3 = findViewById(R.id.choice3);
        vChoice4 = findViewById(R.id.choice4);


        updateQuestion(randomNumber.get(questionNumber));
        updateScore(false);

        vChoice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vChoice1.getText().equals(answer)){
                    //say it is correct
                    updateScore(true);
                    if(!gameover()) {
                        updateQuestion(randomNumber.get(questionNumber));
                    }
                }else{
                    //say it is incorrect
                    updateScore(false);
                    if(!gameover()) {
                        updateQuestion(randomNumber.get(questionNumber));
                    }
                }
            }
        });

        vChoice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vChoice2.getText().equals(answer)){
                    //say it is correct
                    updateScore(true);
                    if(!gameover()) {
                        updateQuestion(randomNumber.get(questionNumber));
                    }
                }else{
                    //say it is incorrect
                    updateScore(false);
                    if(!gameover()) {
                        updateQuestion(randomNumber.get(questionNumber));
                    }
                }
            }
        });

        vChoice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vChoice3.getText().equals(answer)){
                    //say it is correct
                    updateScore(true);
                    if(!gameover()) {
                        updateQuestion(randomNumber.get(questionNumber));
                    }
                }else{
                    //say it is incorrect
                    updateScore(false);
                    if(!gameover()) {
                        updateQuestion(randomNumber.get(questionNumber));
                    }
                }
            }
        });

        vChoice4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vChoice4.getText().equals(answer)){
                    //say it is correct
                    updateScore(true);
                    if(!gameover()) {
                        updateQuestion(randomNumber.get(questionNumber));
                    }
                }else{
                    //say it is incorrect
                    updateScore(false);
                    if(!gameover()) {
                        updateQuestion(randomNumber.get(questionNumber));
                    }
                }
            }
        });
    }

    //Chances what the currently displayed question on the screen is
    public void updateQuestion(int position){
        vQuestion.setText(allQuestions.get(position).getQuestion());
        answer = allQuestions.get(position).getAnswer();
        choices = allQuestions.get(position).getChoices();

        vChoice1.setText(choices.get(0));
        vChoice2.setText(choices.get(1));
        vChoice3.setText(choices.get(2));
        vChoice4.setText(choices.get(3));

        questionNumber++;

    }

    //Updates the score as the user gets answers correct or wrong
    public void updateScore(boolean right) {
        if (right) {
            score++;
            vScore.setText(String.valueOf(score) + " out of " + questionNumber);
        }else{
            vScore.setText(String.valueOf(score) + " out of " + questionNumber);
        }
    }

    //Once the last question is answered end the game passing back necessary info to the
    // gamesandicon activity
    public boolean gameover(){
        if(questionNumber == 10){
            //dialog message of score
            Intent intent = new Intent(this, gameandicons.class);
            intent.putExtra("SCORE", score);
            setResult(RESULT_OK, intent);
            finish();
            //after click return to main screen and update recycler view
            return true;
        }
        return false;
    }
}
package cpsc4150.allaroundclemson;


import java.util.ArrayList;
import java.util.List;

//Class that holds information about a question used in the trivia game
public class Question {
    String Question;
    String Answer;
    List<String> Choices = new ArrayList<String>();

    //Constructor for a Question object
    public Question(String q, String a, List<String> c){
        this.Question = q;
        this.Answer = a;

        for(int i = 0; i < c.size(); i++){
            this.Choices.add(c.get(i));
        }
    }

    //Gets the question
    public String getQuestion() {
        return Question;
    }

    //Gets the correct answer
    public String getAnswer() {
        return Answer;
    }

    //Gets the list of possible answers
    public List<String> getChoices() {
        return Choices;
    }

    //Sets the question
    public void setQuestion(String question) {
        Question = question;
    }

    //Sets the answer
    public void setAnswer(String answer) {
        Answer = answer;
    }

    //Sets the choices
    public void setChoices(List<String> choices) {
        Choices = choices;
    }

    //Gives a nice string representation of the Question object
    @Override
    public String toString(){
        return "User {" + "question='" + Question + '\'' +
                ", age=" + Answer +
                ", choices=" + Choices +
                "}";
    }
}


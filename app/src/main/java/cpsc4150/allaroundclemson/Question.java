package cpsc4150.allaroundclemson;


import java.util.ArrayList;
import java.util.List;

public class Question {
    String Question;
    String Answer;
    List<String> Choices = new ArrayList<String>();

    public Question(String q, String a, List<String> c){
        this.Question = q;
        this.Answer = a;

        for(int i = 0; i < c.size(); i++){
            this.Choices.add(c.get(i));
        }
    }

    public String getQuestion() {
        return Question;
    }

    public String getAnswer() {
        return Answer;
    }

    public List<String> getChoices() {
        return Choices;
    }

    public void setQuestion(String question) {
        Question = question;
    }

    public void setAnswer(String answer) {
        Answer = answer;
    }

    public void setChoices(List<String> choices) {
        Choices = choices;
    }

    @Override
    public String toString(){
        return "User {" + "question='" + Question + '\'' +
                ", age=" + Answer +
                ", choices=" + Choices +
                "}";
    }
}
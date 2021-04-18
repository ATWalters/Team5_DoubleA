package cpsc4150.allaroundclemson;

public class leaderboard implements Comparable<leaderboard>{
    String username;
    int score;

    //Constructor for a leaderboard object
    public leaderboard(String u, int s){
        this.username = u;
        this.score = s;
    }

    //Compares this leaderboard object to the leaderboard object passed in
    public int compareTo(leaderboard anotherValue){
        return this.score - anotherValue.score;
    }

    //Returns the score of this leaderboard object
    public int getScore() {
        return score;
    }

    //Returns the username of the user who this leaderboard object belongs to
    public String getUsername() {
        return username;
    }

    //Sets the score of this leaderboard object
    public void setScore(int score) {
        this.score = score;
    }

    //Set the username of the user who this leaderboard object
    public void setUsername(String username) {
        this.username = username;
    }
}



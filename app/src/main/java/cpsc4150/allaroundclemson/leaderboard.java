package cpsc4150.allaroundclemson;

public class leaderboard implements Comparable<leaderboard>{
    String username;
    int score;

    public leaderboard(String u, int s){
        this.username = u;
        this.score = s;
    }

    public int compareTo(leaderboard anotherValue){
        return this.score - anotherValue.score;
    }



    public int getScore() {
        return score;
    }

    public String getUsername() {
        return username;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}



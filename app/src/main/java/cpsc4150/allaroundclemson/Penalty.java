package cpsc4150.allaroundclemson;

public class Penalty {
    private int mId;
    private String mName;
    private String mDescription;

    public Penalty(){}

    //Constructor for the penalty object
    public Penalty(int id, String name, String desc){
        this.mId = id;
        this.mName = name;
        this.mDescription = desc;
    }

    //Gets the id to tell where this is in the recyclerview
    public int getId(){
        return this.mId;
    }

    //Sets the id of the penalty object
    public void setId(int id){
        this.mId = id;
    }

    //Gets the name of the penalty object
    public String getName(){
        return this.mName;
    }

    //Sets the name of the penalty object
    public void setName(String name){
        this.mName = name;
    }

    //Gets the description of what the penalty means
    public String getDescription(){
        return this.mDescription;
    }

    //Sets the description of what the penalty means
    public void setDescription(String desc){
        this.mDescription = desc;
    }

}

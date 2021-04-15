package cpsc4150.allaroundclemson;

public class Penalty {
    private int mId;
    private String mName;
    private String mDescription;

    public Penalty(){}

    public Penalty(int id, String name, String desc){
        this.mId = id;
        this.mName = name;
        this.mDescription = desc;
    }

    public int getId(){
        return this.mId;
    }

    public void setId(int id){
        this.mId = id;
    }

    public String getName(){
        return this.mName;
    }

    public void setName(String name){
        this.mName = name;
    }

    public String getDescription(){
        return this.mDescription;
    }

    public void setDescription(String desc){
        this.mDescription = desc;
    }

}

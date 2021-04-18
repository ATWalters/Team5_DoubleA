package cpsc4150.allaroundclemson;

public class clemsonClass {
    private String name;
    private String classCode;
    private int section;

    //Constructor for a clemsonClass object
    public clemsonClass(String Mname, String MclassCode, int Msection){
        this.name = Mname;
        this.classCode = MclassCode;
        this.section = Msection;
    }

    //Method to get the section of the class
    public int getSection() {
        return section;
    }

    //Method to get the class code of the class
    public String getClassCode() {
        return classCode;
    }

    //Method to get the name of the class
    public String getName() {
        return name;
    }

    //Method to set the class code of the class
    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    //Method to set the name of the class
    public void setName(String name) {
        this.name = name;
    }

    //Method to set the section of the class
    public void setSection(int section) {
        this.section = section;
    }

}

/*
Name: Allison Tizik and Austin Walters
Date: 4/18/2021

Description: Object created to hold class information
*/
package cpsc4150.allaroundclemson;

public class clemsonClass {
    private String name;
    private String classCode;
    private int section;
    private long id;

    public clemsonClass(String Mname, String MclassCode, int Msection){
        this.name = Mname;
        this.classCode = MclassCode;
        this.section = Msection;
    }

    public void setId(long ID){
        this.id = ID;
    }
    public int getSection() {
        return section;
    }


    public String getClassCode() {
        return classCode;
    }

    public String getName() {
        return name;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSection(int section) {
        this.section = section;
    }

}

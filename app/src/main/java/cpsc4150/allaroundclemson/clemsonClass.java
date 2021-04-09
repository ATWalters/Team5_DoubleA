package cpsc4150.allaroundclemson;

public class clemsonClass {
    private String name;
    private String classCode;
    private int section;
    private int time;

    clemsonClass(String Mname, String MclassCode, int Msection, int Mtime){
        name = Mname;
        classCode = MclassCode;
        section = Msection;
        time = Mtime;
    }

    public int getSection() {
        return section;
    }

    public int getTime() {
        return time;
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

    public void setTime(int time) {
        this.time = time;
    }
}

package Main;

import java.io.Serializable;
import java.util.ArrayList;

public class Course implements Serializable{
    private String type;
    private String code;
    private String name;
    private String[] time;
    private String[] room;
    private ArrayList<Course> preRequisites, postConditions;
    private final int credits;
    private String instructor;

    
    public Course(String type, String name, String code, String instructor, int credits, String[] time, String[] room, ArrayList<Course> preRequisites, ArrayList<Course> postConditions) {
        this.type = type;
        this.name = name;
        this.code = code;
        this.instructor = instructor;
        this.time = time;
        this.room = room;
        this.credits = credits;
        this.preRequisites = preRequisites;
        this.postConditions = postConditions;
    }
    @Override
    public String toString(){
        String s ="type:" + type+  "course code:" + code+ " name: " + name + " credits: " + credits + " instructor: "+ instructor + " Monday: "+ time[0] + " Tuesday:"+ time[1] ;
        return (s);
    }
    public String getName(){
        return name;
    }
    public String getType(){
        return type;
    }
    public String getCode(){
        return code;
    }
    public int getCredits(){
        return credits;
    }
    
    
//    public String getInfo(){
//
//    }
}
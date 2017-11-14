package Main;

import java.io.Serializable;
import java.util.ArrayList;

public class Course implements Serializable {

    private String type;
    private String code;
    private String name;
    private String[] time;
    private String[] room;
    private ArrayList<String> preRequisites, postConditions;
    private final int credits;
    private String instructor;
    private String postConditionsString = "";

    @Override
    public boolean equals(Object obj) {

        return name.equals(((Course) obj).getName()) && code.equals(((Course) obj).getCode());
    }

    public Course(String type, String name, String code, String instructor, int credits, String[] time, String[] room, ArrayList<String> preRequisites, ArrayList<String> postConditions) {
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
    public String toString() {
        String s = "type:" + type + "course code:" + code + " name: " + name + " credits: " + credits + " instructor: " + instructor + " Monday: " + time[0] + " Tuesday:" + time[1] + " ::" + room[0];
        //String s =
        return (s);
    }

    public void convertToString(ArrayList<String> postConditions) {
        postConditionsString = "";
        for (int i = 0; i < postConditions.size(); i++) {
            postConditionsString = postConditionsString.concat((i + 1) + ". " + postConditions.get(i) + "\n");
        }

        //System.out.println( "holaaa :  " + postConditionsString);
    }

    public ArrayList<String> getPostConditions() {
        return postConditions;
    }

    public String getPostConditionsString() {
        return postConditionsString;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getCode() {
        return code;
    }

    public int getCredits() {
        return credits;
    }

    public String[] getTime() {
        return time;
    }

    public String[] getRoom() {
        return room;
    }

    public String getInstructor() {
        return instructor;
    }

    @Override
    public boolean equals(Object obj) {

        return name.equals(((Course)obj).getName()) && code.equals(((Course)obj).getCode());
    }

    public boolean checkTime(Course course){

        for(int i=0;i<7;i++){
            if(!time[i].equals("-") && !course.getTime()[i].equals("-")) {
                String startTime = time[i].split("-")[0];
                String endTime = time[i].split("-")[1];
                String startTimeCourse = course.getTime()[i].split("-")[0];
                String endTimeCourse = course.getTime()[i].split("-")[1];

                if (startTime.compareTo(startTimeCourse) < 0
                        && startTime.compareTo(endTimeCourse) > 0) {

                    return false;

                } else if (endTime.compareTo(startTimeCourse) < 0
                        && endTime.compareTo(endTimeCourse) > 0) {

                    return false;
                } else if (startTimeCourse.compareTo(startTime) < 0
                        && startTimeCourse.compareTo(endTime) > 0) {

                    return false;

                } else if (endTimeCourse.compareTo(startTime) < 0
                        && endTimeCourse.compareTo(endTime) > 0) {

                    return false;

                }
            }
        }
        return true;
    }
    //    public String getInfo(){
//
//    }
}

package Main;

import java.io.Serializable;
import java.util.ArrayList;
/**
 * Stores data of each course
 * @author Lenovo
 */
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
/**
 * Converting postCOnditions to string form to enable search
 * @param postConditions : list of all postconditions of a course
 */
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

        System.out.println("this course ::" + name + " that :::" + course.getName());

        for(int i=0;i<7;i++){
            if(!time[i].equals("-") && !course.getTime()[i].equals("-")) {
                String startTime = time[i].split("-")[0];
                String endTime = time[i].split("-")[1];
                String startTimeCourse = course.getTime()[i].split("-")[0];
                String endTimeCourse = course.getTime()[i].split("-")[1];

                int startHour = Integer.parseInt(startTime.split(":")[0]);
                int startMin = Integer.parseInt(startTime.split(":")[1]);
                int startHourCourse = Integer.parseInt(startTimeCourse.split(":")[0]);
                int startMinCourse = Integer.parseInt(startTimeCourse.split(":")[1]);

                int endHour = Integer.parseInt(endTime.split(":")[0]);
                int endMin = Integer.parseInt(endTime.split(":")[1]);
                int endHourCourse = Integer.parseInt(endTimeCourse.split(":")[0]);
                int endMinCourse = Integer.parseInt(endTimeCourse.split(":")[1]);

//                String setTimeStart = "";
//                String setTimeStartCourse = "";
//                String setTimeEnd ="";
//                String setTimeEndCourse = "";


                if(startHour<8){
                    startHour+=12;
                }

                if(endHour<8){
                    endHour+=12;
                }
                if(startHourCourse<8){
                    startHourCourse+=12;
                }

                if(endHourCourse<8){
                    endHourCourse+=12;
                }




                if(startHour/10==0) {
                    if(startMin==0)
                        startTime = "0" + startHour + ":00";
                    else
                        startTime = "0" + startHour + ":" + startMin;
                }
                if(endHour/10==0) {
                    if(endMin==0)
                        endTime = "0" + endHour + ":00";
                    else
                        endTime = "0" + endHour + ":" + endMin;
                }
                if(startHourCourse/10==0) {

                    if(startMinCourse==0)
                        startTimeCourse = "0" + startHourCourse + ":00";
                    else
                        startTimeCourse = "0" + startHourCourse + ":" + startMinCourse;
                }
                if(endHourCourse/10==0) {
                    if(endMinCourse==0)
                        endTimeCourse = "0" + endHourCourse + ":00";
                    else

                        endTimeCourse = "0" + endHourCourse + ":" + endMinCourse;
                }

//                if(startHour<8){
//                    setTimeStart = "PM";
//                }
//                else {
//                    setTimeStart = "AM";
//                }
//                if(endHour<8){
//                    setTimeEnd = "PM";
//                }
//                else {
//                    setTimeEnd = "AM";
//                }
//
//                if(startHourCourse<8)
//                    setTimeStartCourse = "PM";
//                else
//                    setTimeStartCourse = "AM";
//                if(endHourCourse<8)
//                    setTimeEndCourse = "PM";
//                else
//                    setTimeEndCourse = "AM";




//                if(startHour<startHourCourse && startHour>endHourCourse){
//
//                }
//
//                else if(endHour<startHourCourse && endHour>endHourCourse){
//
//                }
//                else if(startHourCourse<startHour && startHourCourse>endHour){
//
//                }
//                else if(endHourCourse<startHour && endHourCourse>endHour){
//
//                }


                System.out.println("0");
                System.out.println("debug" + startTime);
                System.out.println("debug" + startTimeCourse);
                System.out.println("debug" + endTime);
                System.out.println("debug" + endTimeCourse);

                if (startTime.compareTo(startTimeCourse) < 0
                        && startTime.compareTo(endTimeCourse) > 0) {
                    System.out.println("1");
                    System.out.println(startTimeCourse);
                    System.out.println(startTime);
                    System.out.println(endTime);

                    return false;

                } else if (endTime.compareTo(startTimeCourse) < 0
                        && endTime.compareTo(endTimeCourse) > 0) {
                    System.out.println("2");
                    System.out.println(startTimeCourse);
                    System.out.println(startTime);
                    System.out.println(endTime);

                    return false;
                } else if (startTimeCourse.compareTo(startTime) < 0
                        && startTimeCourse.compareTo(endTime) > 0) {
                    System.out.println("3");

                    System.out.println(startTimeCourse);
                    System.out.println(startTime);
                    System.out.println(endTime);

                    return false;

                } else if (endTimeCourse.compareTo(startTime) < 0
                        && endTimeCourse.compareTo(endTime) > 0) {
                    System.out.println("4");
                    System.out.println(startTimeCourse);
                    System.out.println(startTime);
                    System.out.println(endTime);

                    return false;

                }
                else if(startTime.compareTo(startTimeCourse)==0
                        && endTime.compareTo(endTimeCourse)==0){
                    System.out.println("5");
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

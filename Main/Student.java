package Main;


import java.util.ArrayList;

public class Student extends User {

    private TimeTable myTimeTable;
    private ArrayList<Course> courses;

    Student(String name, String emailId, String password, String type) {
        super(name, emailId, password, type);
    }

//    Student(String name, String emailId, String password, String type) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
    public ArrayList<Course> viewCourses(){
        return courses;
    }
    @Override
    public void BookRoom(Room room, String startTime, String endTime, int date) {

    }

    @Override
    public void CancelRoom(Room room) {

    }

    public TimeTable ViewTimeTable() {
        return myTimeTable;
    }

    public void SearchCourses(){
//        return 
    }

    public void AddCourses(Course course){

    }
}
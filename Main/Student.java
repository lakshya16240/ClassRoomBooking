package Main;


import java.util.ArrayList;
import java.util.HashMap;

public class Student extends User {

    private TimeTable myTimeTable;
    private ArrayList<Course> courses;
    private ArrayList<Student> myFriends;
    private ArrayList<Student> FriendRequests;

    
    Student(String name, String emailId, String password, String type) {
        super(name, emailId, password, type);
        courses = new ArrayList<>();
    }

    
    public ArrayList<Student> getMyFriends() {
        return myFriends;
    }

    public ArrayList<Student> getFriendRequests() {
        return FriendRequests;
    }
            
    
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

    public void addCourses(Course course){

        courses.add(course);

    }
}
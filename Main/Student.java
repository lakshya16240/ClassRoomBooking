package Main;


import java.util.ArrayList;
import java.util.HashMap;

public class Student extends User {

    private String course;
    private TimeTable myTimeTable;
    private ArrayList<Course> courses;
    private ArrayList<Student> myFriends;
    private ArrayList<FriendRequest> FriendRequests;

    
    Student(String name, String emailId, String password, String type, String course) {
        super(name, emailId, password, type);
        this.course = course;
        courses = new ArrayList<>();
    }

    
    public ArrayList<Student> getMyFriends() {
        return myFriends;
    }

    public ArrayList<FriendRequest> getFriendRequests() {
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
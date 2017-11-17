package Main;


import java.util.ArrayList;
import java.util.HashMap;

public class Student extends User {

    private String courseType;
    private TimeTable myTimeTable;
    private ArrayList<Course> courses;
    private ArrayList<Student> myFriends;
    private ArrayList<FriendRequest> friendRequests;

    
    Student(String name, String emailId, String password, String type, String courseType) {
        super(name, emailId, password, type);
        this.courseType = courseType;
        courses = new ArrayList<>();
        friendRequests = new ArrayList<>();
        myFriends = new ArrayList<>();
    }

    public String getCourseType() {
        return courseType;
    }

    public ArrayList<Student> getMyFriends() {
        return myFriends;
    }

    public ArrayList<FriendRequest> getFriendRequests() {
        return friendRequests;
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
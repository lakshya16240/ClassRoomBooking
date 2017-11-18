package Main;


import java.util.ArrayList;
import java.util.HashMap;

/**
 * A class for users which are of type student - extends {User}
 */
public class Student extends User {

    private String courseType;
    private String batch;
    private TimeTable myTimeTable;
    private ArrayList<Course> courses;
    private ArrayList<Student> myFriends;
    private ArrayList<FriendRequest> friendRequests;
    private String courseInfoString = "";

    
    Student(String name, String emailId, String password, String type, String courseType, String batch) {
        super(name, emailId, password, type);
        this.courseType = courseType;
        this.batch = batch;
        courses = new ArrayList<>();
        friendRequests = new ArrayList<>();
        myFriends = new ArrayList<>();
    }

    public String getCourseType() {
        return courseType;
    }

    public String getBatch() {
        return batch;
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

    public String getCourseInfoString() {
        return courseInfoString;
    }

    public void convertToString(ArrayList<Course> courses){

        courseInfoString = "";
        for(int i=0;i<courses.size();i++){
            courseInfoString = courseInfoString + Integer.toString(i+1) + ". " + courses.get(i).getName() +  " - " + courses.get(i).getInstructor() + "\n";
        }

    }

    /**
     * adds the course to the list of courses
     * @param course course to be added
     */
    public void addCourses(Course course){

        courses.add(course);

    }
}
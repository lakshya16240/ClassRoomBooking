package Main;

import java.util.ArrayList;


public class Faculty extends User {

    private ArrayList<Course> coursesTaught;

    Faculty(String name, String emailId, String password, String type) {
        super(name, emailId, password, type);
        coursesTaught = new ArrayList<>();
    }

    @Override
    public void BookRoom(Room room, String startTime, String endTime, int date) {

    }

    @Override
    public void CancelRoom(Room room) {

    }

    public ArrayList<Course> getCoursesTaught() {
        return coursesTaught;
    }
}
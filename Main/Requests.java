package Main;

import java.io.Serializable;

public class Requests implements Serializable{

    private Room roomNumber;
    private String date;
    private boolean status;
    private String startTime, endTime;
    private String reason;
    private String userType;
    
    public Requests(String date, String startTime, String endTime, String reason, String userType) {
//        this.roomNumber = roomNumber;
        this.date = date;
        this.status = false;
        this.startTime = startTime;
        this.endTime = endTime;
        this.userType = userType;
        this.reason = reason;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
    
    @Override
    public String toString() {
        return "Requests{" + "roomNumber=" + roomNumber + ", date=" + date + ", status=" + status + ", startTime=" + startTime + ", endTime=" + endTime + ", reason=" + reason + ", userType=" + userType + '}';
    }
    
//    public String toSring(){
//        return ();
//    }
    public Room getRoomNumber() {
        return roomNumber;
    }

    public boolean isStatus() {
        return status;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }
    public static void main(String[] args) {
        System.out.println("ABHISHEK");
    }
}
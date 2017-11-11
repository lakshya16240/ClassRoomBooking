package Main;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class User implements Serializable {

    private String name, emailId, password, type;
    private ArrayList<Requests> requests;

    public User(String name, String emailId, String password, String type) {
        this.name = name;
        this.emailId = emailId;
        this.password = password;
        this.type = type;
        requests = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "User{" + "name=" + name + ", emailId=" + emailId + ", password=" + password + ", type=" + type + '}';
    }

    public String getEmailId() {
        return emailId;
    }

   public void ViewRoom(int capacity, String startTime, String endTime){

   }

    public ArrayList<Requests> getRequests() {
        return requests;
    }

    public abstract void BookRoom(Room room, String startTime, String endTime, int date);

   public abstract void CancelRoom(Room room);

   public void ShowAll(){

   }

   public String ViewStatus(Room room){

       return "";
   }
}

//package Main;
//
//import java.io.Serializable;
//
//public abstract class User implements Serializable {
//
//    private String name, emailId, password, type;
//
//    public User(String name, String emailId, String password, String type) {
//        this.name = name;
//        this.emailId = emailId;
//        this.password = password;
//        this.type = type;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public String getEmailId() {
//        return emailId;
//    }
//
//   public void ViewRoom(int capacity, String startTime, String endTime){
//
//   }
//
//   public abstract void BookRoom(Room room, String startTime, String endTime, int date);
//
//   public abstract void CancelRoom(Room room);
//
//   public void ShowAll(){
//
//   }
//
//   public String ViewStatus(Room room){
//
//       return "";
//   }
//}
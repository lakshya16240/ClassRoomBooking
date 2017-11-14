package Main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.Serializable;

public class College implements Serializable{

    
    private ArrayList<User> allUsers;
    private HashMap<String,String> credentials;
    private HashMap<String,User> allUsersMap;
    private ArrayList<Room> allRooms;
    private ArrayList<Course> allCourses;

    public College(){
        allUsers = new ArrayList<>();
        allRooms =new ArrayList<>();
        allCourses = new ArrayList<>();
        credentials = new HashMap<>();
        allUsersMap = new HashMap<>();
    }
    
    
    public static void serialize(College p) throws IOException {

        ObjectOutputStream out = null;
        try {
//			System.out.println(p.getName());x` 
            out = new ObjectOutputStream(new FileOutputStream("./src/" + "data" + ".txt"));
            System.out.println("created");
            out.writeObject(p);
        } finally {
            out.close();
        }
    }

    public static College deserialize(String filename) throws IOException, ClassNotFoundException {
        System.out.println("deserializing");
        ObjectInputStream in = null;
        try {

            in = new ObjectInputStream(new FileInputStream("./src/" + filename + ".txt"));
            College obj = (College) in.readObject();
            //			in.readObject();
            return obj;
        } finally {
            in.close();
        }
    }
    public ArrayList<Room> getAllRooms() {
        return allRooms;
    }

    public ArrayList<Course> getAllCourses() {
        return allCourses;
    }
    public ArrayList<User> getAllUsers() {
        return allUsers;
    }
    
    public HashMap<String,String> getCredentials(){
        return credentials;
    }

    public HashMap<String, User> getAllUsersMap() {
        return allUsersMap;
    }

    public int SignUp(String name, String emailId, String password, String type){
        if (credentials.get(emailId)!=null){
            return 0;
        }
        if (!emailId.contains("@iiitd.ac.in")){
            return 2;
        }
        
        User user;
        System.out.println(type);
        if (type.equals("Student")){
            user = new Student(name,emailId,password,type);
            System.out.println("Student created");
        }
        else if (type.equals("Admin")){
            user = new Admin(name,emailId,password,type);
        }
        else{
            user = new Faculty(name,emailId,password,type);
        }
        
        allUsers.add(user);
        allUsersMap.put(emailId,user);
        credentials.put(emailId,password);
        return 1;
    }

    public int Login(String emailId, String password){
        
        System.out.println(emailId + " " + password);
        String actualPassword = credentials.get(emailId);
        System.out.println(actualPassword);

        if(actualPassword!=null && actualPassword.equals(password))
            return 1;
        else
            return 0;
    }


}

//package Main;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.io.Serializable;
//
//public class College implements Serializable{
//    private ArrayList<User> allUsers;
//    HashMap<String,String> credentials;
//    private ArrayList<Room> allRooms;
//    private ArrayList<Course> allCourses;
//
//    public College(){
//        allUsers = new ArrayList<>();
//        allRooms =new ArrayList<>();
//        allCourses = new ArrayList<>();
//        credentials = new HashMap<>();
//    }
//
//    public ArrayList<Room> getAllRooms() {
//        return allRooms;
//    }
//
//    public ArrayList<Course> getAllCourses() {
//        return allCourses;
//    }
//    public ArrayList<User> getAllUsers() {
//        return allUsers;
//    }
//    
//    public HashMap<String,String> getCredentials(){
//        return credentials;
//    }
////    public ArrayList<Student> getAllStudents() {
////
////    }
////
////    public ArrayList<Faculty> getAllFaculty() {
////
////    }
////
////    public ArrayList<Admin> getAdmin() {
////
////    }
//
//    public int SignUp(String name, String emailId, String password, String type){
//        if (credentials.get(emailId)!=null){
//            return 0;
//        }
//        User user;
//        if (type.equals("Student")){
//            user = new Student(name,emailId,password,type);
//        }
//        else if (type.equals("Admin")){
//            user = new Admin(name,emailId,password,type);
//        }
//        else{
//            user = new Faculty(name,emailId,password,type);
//        }
//        
//        allUsers.add(user);
//        credentials.put(emailId,password);
//        return 1;
//    }
//
//    public int Login(String emailId, String password){
//        String actualPassword = credentials.get(emailId);
//        if(actualPassword!=null && actualPassword.equals(password))
//            return 1;
//        else
//            return 0;
//    }
//
//
//}
package Main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.Serializable;
import static Main.MainPage.clgobj;
/**
 * College class is the main class which stores data of all the users, courses and rooms
 * @author Lenovo
 */
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
    
    /**
     * To serialize an object of type College
     * @param p the object to be serialized
     * @throws IOException 
     */
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
    /**
     * To deserialize the college object
     * @param filename represents the name of file to be deserialized
     * @return college type object obtained after deserialization 
     * @throws IOException
     * @throws ClassNotFoundException 
     */
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

    /**
     *
     * @return
     */
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
    
    /**
     * 
     * @param name name of user
     * @param emailId emailID of user
     * @param password password of user 
     * @param type type of user
     * @param courseType branch of user
     * @param batch The batch of the user.
     * @return 0 if user already exists, 1 if sign up is successful, 2 if email ID is incorrect
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public int SignUp(String name, String emailId, String password, String type, String courseType, String batch) throws IOException, ClassNotFoundException {
        if (credentials.get(emailId)!=null){
            return 0;
        }
//        if (!emailId.contains("@iiitd.ac.in")){
//            return 2;
//        }
        
        User user;
        if (type.equals("Student")){
            user = new Student(name,emailId,password,type,courseType, batch);

            allCourses = College.deserialize("data").getAllCourses();
            for(int i=0;i<allCourses.size();i++){
                Course course = allCourses.get(i);
                if(course.getType().equalsIgnoreCase("Mandatory")){

                    if(courseType.equalsIgnoreCase("CSAM")) {

                        if (course.getCode().charAt(4) == 'x' && course.getCode().charAt(5) == 'x') {

                            ((Student) user).viewCourses().add(course);
                        }
                    }
                    else if(courseType.equalsIgnoreCase("CSE")){

                        if (course.getCode().charAt(0) == 'C' && course.getCode().charAt(1) == 'S' && course.getCode().charAt(2)=='E' && !(course.getCode().charAt(4) == 'x' && course.getCode().charAt(5) == 'x')) {

                            ((Student) user).viewCourses().add(course);
                        }
                    }
                    else if(courseType.equalsIgnoreCase("ECE")){

                        if (course.getCode().charAt(0) == 'E' && course.getCode().charAt(1) == 'C' && course.getCode().charAt(2)=='E') {

                            ((Student) user).viewCourses().add(course);
                        }
                    }
                }
            }
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
    
    /**
     * To authenticate login of a user
     * @param emailId emailId of the user
     * @param password password of the user 
     * @return 1 if login is successful, 0 if unsuccessful
     */
    public int Login(String emailId, String password){
        
        String actualPassword = credentials.get(emailId);

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
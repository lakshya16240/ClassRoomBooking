package Main;

import java.io.IOException;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Main.College.*;
import static Main.College.deserialize;

/**
 *
 * @author Lenovo
 */
public class MainPage extends Application {
    public static College clgobj;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    public static List<Course> readCourseCSV() throws IOException {

        List<Course> courseinfo = new ArrayList<>();
//        String pathToFile = "/Users/pc.csv"; 
//        String pathToFile = "/Users/finalap.csv";
        String pathToFile = "./src/timetable.csv";

        HashMap<String,ArrayList<String>> hashMapPC = readPostConditionsCSV();

        System.out.println(pathToFile + " path ");
        try (BufferedReader br = new BufferedReader(new FileReader(pathToFile))) {
            String line = br.readLine();
            line = br.readLine();
            while (line != null) {
                String[] attributes = line.split(",");
                //                Book book = createBook(attributes); // adding book into ArrayList books.add(book); // read next line before looping // if end of file reached, line would be null line = br.readLine(); } } catch (IOException ioe) { ioe.printStackTrace(); }
                String[] schedule = new String[5];
                for (int i = 0; i < 5; i++) {
                    schedule[i] = attributes[6 + i];
                    //System.out.println("print it agaiinnn ::::::   " + schedule[i] + " " + attributes[6+i]);
                }
                if(hashMapPC.containsKey(attributes[2])) {
                    ArrayList<String> postConditions = hashMapPC.get(attributes[2]);
                    for(int i=0;i<postConditions.size();i++){
                        System.out.println(postConditions.get(i));
                    }
                    courseinfo.add(new Course(attributes[0], attributes[1], attributes[2], attributes[3], Integer.valueOf(attributes[4]), schedule, null, null, postConditions));
                }
                line = br.readLine();

//                for (int i = 0; i < 5; i++) {
//                    if (!schedule[i].equals("-")) {
//                        String[] timeAndVenue = schedule[i].split("\\$");
//                        System.out.println("haijajsjsojdajjaoidjiodojjodoiiiooisdjjjoid :::::::::: " + timeAndVenue[0]);
//                        initializer.addBookings(timeAndVenue, i);
//                    }
//                }

            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return courseinfo;
    }

    public static HashMap<String,ArrayList<String>> readPostConditionsCSV() throws IOException{

        ArrayList<String> postConditionsArrayList;
        HashMap<String,ArrayList<String>> postConditionsHashMap = new HashMap<>();

        String pathToFile = "./src/postConditions.csv";

        try(BufferedReader br = new BufferedReader(new FileReader(pathToFile))){

            //System.out.println("HSAIDJAJJDJIIAJIDAJIJIAJIDA");
            String line  = br.readLine();
            line = br.readLine();
            while(line != null){
                //System.out.println("hoaooslasllalloalloaloaojsajojdjdojaodjojad");
                String[] attributes = line.split(",");

                postConditionsArrayList = new ArrayList<>();
                for(int i=0;i<5 && (3+i)<attributes.length;i++){
                    //System.out.println("yolaa   " + attributes[3+i]);
                    postConditionsArrayList.add(attributes[3+i]);
                }

                postConditionsHashMap.put(attributes[0],postConditionsArrayList);
                line = br.readLine();
            }

        }

        return postConditionsHashMap;

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        College clgobj = deserialize("data");
        System.out.println();

        Admin obj = (Admin) clgobj.getAllUsersMap().get("abhishek16126");
        System.out.println("after deserializing at start");

        System.out.println(obj.getRequests());
//        ArrayList<Integer> myarray = new ArrayList<Integer>();
     
        List<Course> courseinfo = new ArrayList<>();
        courseinfo = readCourseCSV();
        for (int i = 0; i < courseinfo.size(); i++) {
            Course c = courseinfo.get(i);
            System.out.println(c);
            ArrayList<String> pc = c.getPostConditions();
            for(int j=0;j<pc.size();j++){
                System.out.println(pc.get(j));
            }
        }
        HashMap<String, Room> RoomData = new HashMap<String, Room>();
        RoomData = Room.deserializeRoom();
        Room RoomObj  = RoomData.get("C21");
        System.out.println("room data coming");
        RoomObj.printAvailability();

//        System.out.println("hello22");
//                
//        System.out.println("ABHISHEK");
        launch(args);
    }

}

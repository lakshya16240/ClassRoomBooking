package Main;

import java.io.IOException;
import java.io.*;
import java.util.ArrayList;
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
//    public static College deserialize(String filename) throws IOException, ClassNotFoundException{
//		
//		ObjectInputStream in = null;
//		try {
//			
//			in = new ObjectInputStream(new FileInputStream("./src/" + filename + ".txt"));
//			College obj = (College)in.readObject();
//			//			in.readObject();
//			return obj;
//		}
//		finally {
//			in.close();
//		}
//	}
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("MainPage.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    public static List<Course> readCSV() throws IOException {
        List<Course> courseinfo = new ArrayList<>();
//        String pathToFile = "/Users/pc.csv"; 
//        String pathToFile = "/Users/finalap.csv";
        String pathToFile = "./src/finalap.csv";

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
                    //System.out.println(schedule[i] + " " + attributes[6+i]);
                }
                courseinfo.add(new Course(attributes[0], attributes[1], attributes[2], attributes[3], Integer.valueOf(attributes[4]), schedule, null, null, null));
                line = br.readLine();

                for (int i = 0; i < 5; i++) {
                    if (!schedule[i].equals("-")) {
                        String[] timeAndVenue = schedule[i].split("\\$");

                        Admin.addBookings(timeAndVenue, i);
                    }
                }

            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return courseinfo;
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
        courseinfo = readCSV();
        for (int i = 0; i < courseinfo.size(); i++) {
            Course c = courseinfo.get(i);
            System.out.println(c);
        }
//        System.out.println("hello22");
//                
//        System.out.println("ABHISHEK");
        launch(args);
    }

}

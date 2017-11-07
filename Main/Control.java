
package Main;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import org.controlsfx.control.textfield.TextFields;

import javafx.stage.Stage;

/**
 *
 * @author Lenovo
 */
public class Control implements Initializable  {
//    @FXML 
//    ToggleGroup Users;
//  
//	public static void serialize(College p) throws IOException{
//
//		ObjectOutputStream out = null;
//		try {
////			System.out.println(p.getName());x` 
//			out = new ObjectOutputStream(new FileOutputStream("./src/" + "data" + ".txt"));
////			System.out.println("created");
//			out.writeObject(p);
//		}
//		finally {
//			out.close();
//		}
//	}        
//        public static College deserialize(String filename) throws IOException, ClassNotFoundException{
//		System.out.println("deserializing");
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
//    College clgobj = deserialize("data");
    @FXML
    private TableView<Course> mytable;
        
//    @FXML
//    private TextField username;
//    
//    @FXML
//    private AnchorPane studentPane;
//    
//    @FXML
//    private AnchorPane facultyPane;
//    
    @FXML
    private AnchorPane adminPane;
//    
//    @FXML
//    private TextField pass;
//
//    @FXML
//    private Button LogIn;
//    
    @FXML
    private TableColumn<Course,String> tc1;
    @FXML

    private TableColumn<Course,String> tc2; 
    @FXML
    private TableColumn<Course,String> tc3; 
    
    @FXML
    private TableColumn<Course, Integer> tc4; 
    
    @FXML
    private Button tbutton;
    
    
    @FXML
    private Button tbutton1;

    @FXML
    private TextField searchCourses;
//    @FXML javafx.scene.control.TableColumn tc1;
//    @FXML javafx.scene.control.TableColumn tc2;
//    @FXML javafx.scene.control.TableColumn tc3;
//    @FXML javafx.scene.control.TableColumn tc4;

    
//    @FXML
//    private TableColumn<?, ?> tc1;
//    
//    @FXML
//    private TextField name;
//
//    @FXML
//    private TextField emailId;
//
//    @FXML
//    private PasswordField password;
//
//    @FXML
//    private ToggleGroup Users;
//
//    @FXML
//    private Button SignUp;
//    
//    @FXML
//    private Label loginlabel;
//
//    @FXML
//    private Label signuplabel;
//
//    @FXML
//    private TableView tableView;
//
//    @FXML
//    void SignUp(ActionEvent event) throws IOException, ClassNotFoundException {
////        College clgobj = deserialize("data");
//        College clgobj = deserialize("data");
//        String user_name = name.getText();
//        String email = emailId.getText();
//        String passwd = password.getText();
//        RadioButton selectedRadioButton = (RadioButton) Users.getSelectedToggle();
//        String type = selectedRadioButton.getText();
////        String type = Users.getSelectedToggle().toString();
////        System.out.println("tt= "+ toogleGroupValue);
//        name.setText(null);
//        emailId.setText(null);
//        password.setText(null);
////        System.out.println(u);
//        int val = clgobj.SignUp(user_name , email, passwd, type ); 
//        if (val==0){
//            signuplabel.setText("User already exists");
//        }
//        else{
//            signuplabel.setText("Sign up succesful");
//           
//        }
//        System.out.println(type);
//        serialize(clgobj);
//    }
//
//    @FXML
//    void userlogin(ActionEvent event) throws IOException, ClassNotFoundException {
//        College clgobj = deserialize("data");
////        College clgobj = new College();
////        username.setVisible(true);
////        tc1.setCellValueFactory(value);
//        System.out.println("login mein aayaa");
////        MainPage mpg = new MainPage();
////        List<Course> courseinfo = new ArrayList<>(); 
////
////        courseinfo = mpg.readCSV();
////        
////        mytable.getItems().add(courseinfo.get(0)); 
//        String email = username.getText();
//        String passwd = pass.getText();
//        username.setText(null);
//        pass.setText(null);
//        Parent root2;
////        College clgobj = new College();
//        System.out.println("hello");
////    
////        List<Course> courseinfo = new ArrayList<>(); 
////        courseinfo = readCSV("pc");
////        for (int i = 0 ; i<courseinfo.size() ; i++){
////            Course c = courseinfo.get(i);
////            System.out.println(c);
////        }
////        System.out.println("hello22");
////                
//
//        int num = clgobj.Login(email, passwd);
//        if (num == 1 ){
//            loginlabel.setText("successful");
////            loginlabel.setText("hello1");
//
//            if(clgobj.getAllUsersMap().get(email).getType().equals("Student"))
//                root2 = FXMLLoader.load(getClass().getResource("StdntPage.fxml"));
//
//            else if(clgobj.getAllUsersMap().get(email).getType().equals("Admin"))
//                root2 = FXMLLoader.load(getClass().getResource("AdminPage.fxml"));
//
//            else
//                root2 = FXMLLoader.load(getClass().getResource("FacultyPage.fxml"));
//
//            Scene scene = new Scene(root2);
//            Stage stage = new Stage();
//            stage.setScene(scene);
//            stage.show();
////            loginlabel.setText("hello");
//        }
//        else{
//            loginlabel.setText("unsuccessful");
//        }
//    } 
//    
//    @FXML
//    void displayCourses(ActionEvent event) throws IOException{
//        Pane newstudentPane = FXMLLoader.load(getClass().getResource("Table.fxml"));
//        studentPane.getChildren().add(newstudentPane);
//        tableView.getColumns().add(0,"monday");
//    }
    @FXML
    void viewTimeTable(ActionEvent event) throws IOException{
        Pane newstudentPane = FXMLLoader.load(getClass().getResource("TimeTable.fxml"));
        adminPane.getChildren().clear();
        adminPane.getChildren().add(newstudentPane);
    }
     @FXML
    void roomBooking(ActionEvent event) throws IOException{
        Pane newstudentPane = FXMLLoader.load(getClass().getResource("RoomBooking.fxml"));
        adminPane.getChildren().clear();
        adminPane.getChildren().add(newstudentPane);
    }
    @FXML
    void mthd(ActionEvent event) throws IOException{
        MainPage obj = new MainPage();
        List<Course> myList = new ArrayList<Course>();
        try {
            myList = obj.readCourseCSV();
        } catch (IOException ex) {
            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
        }
        ObservableList<Course> obsList = FXCollections.observableArrayList();
        for (int i=0; i<myList.size() ;i++){
            obsList.add(myList.get(i));
//            System.out.println("type = " + myList.get(i).);
            System.out.println(myList.get(i) + " VAL");
            System.out.println(obsList.get(i) + " VAL");

        }
        
//        final ObservableList<Course> data = FXCollections.observableArrayList(new Course("a", "b" , "c", "d", 5, null, null, null, null));
        tc1.setCellValueFactory(
        new PropertyValueFactory<Course, String>("name"));
        tc2.setCellValueFactory(
        new PropertyValueFactory<Course, String>("type"));
        tc3.setCellValueFactory(
        new PropertyValueFactory<Course, String>("code"));
        tc4.setCellValueFactory(
        new PropertyValueFactory<Course, Integer>("credits"));
//        new PropertyValueFactory<Course, Integer>("credits"));
        
        mytable.setItems(obsList);
//        Course mycourse = mytable.getSelectionModel().getSelectedItem();
//        System.out.println("ENDDDDD");
//        System.out.println(mycourse);
    }
     @FXML
    void mthd1(ActionEvent event) throws IOException{
        ObservableList<Course> mycourse = mytable.getSelectionModel().getSelectedItems();
        System.out.println("ENDDDDD");
        System.out.println(mycourse);        
    }
//    void myMethod(ActionEvent event) throws IOException{
//        Pane newadminPane = FXMLLoader.load(getClass().getResource("CourseTable.fxml"));
//        adminPane.getChildren().clear();
//        adminPane.getChildren().add(newadminPane);
//        MainPage obj = new MainPage();
//        List<Course> myList = new ArrayList<Course>();
//        myList = obj.readCSV();
//        ObservableList<Course> obsList = FXCollections.observableArrayList();
//        for (int i=0; i<myList.size() ;i++){
//            obsList.add(myList.get(i));
//            System.out.println(myList.get(i) + " VAL");
//            System.out.println(obsList.get(i) + " VAL");
//
//        }
////        final ObservableList<Course> data = FXCollections.observableArrayList(new Course("a", "b" , "c", "d", 5, null, null, null, null));
//        tc1.setCellValueFactory(
//        new PropertyValueFactory<Course, String>("name"));
//        tc2.setCellValueFactory(
//        new PropertyValueFactory<Course, String>("type"));
//        tc3.setCellValueFactory(
//        new PropertyValueFactory<Course, String>("code"));
//        tc4.setCellValueFactory(
//        new PropertyValueFactory<Course, String>("code"));
////        new PropertyValueFactory<Course, Integer>("credits"));
//        
//        mytable.setItems(obsList);
//        System.out.println("ENDDDDD");
//    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        List<Course> courseList;
        ArrayList<String> postConditionsList = new ArrayList<>();
        try {
            courseList = MainPage.readCourseCSV();
            for(int i=0;i<courseList.size();i++){

                Course course = courseList.get(i);
                postConditionsList.addAll(course.getPostConditions());
            }
            String[] suggestedPostConditions = new String[postConditionsList.size()];
            for(int i=0;i<postConditionsList.size();i++){
                suggestedPostConditions[i] = postConditionsList.get(i);
            }
            TextFields.bindAutoCompletion(searchCourses,suggestedPostConditions);
        } catch (IOException e) {
            e.printStackTrace();
        }

//                MainPage obj = new MainPage();
//        List<Course> myList = new ArrayList<Course>();
//        try {
//            myList = obj.readCSV();
//        } catch (IOException ex) {
//            Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        ObservableList<Course> obsList = FXCollections.observableArrayList();
//        for (int i=0; i<myList.size() ;i++){
//            obsList.add(myList.get(i));
////            System.out.println("type = " + myList.get(i).);
//            System.out.println(myList.get(i) + " VAL");
//            System.out.println(obsList.get(i) + " VAL");
//
//        }
////        final ObservableList<Course> data = FXCollections.observableArrayList(new Course("a", "b" , "c", "d", 5, null, null, null, null));
//        tc1.setCellValueFactory(
//        new PropertyValueFactory<Course, String>("name"));
//        tc2.setCellValueFactory(
//        new PropertyValueFactory<Course, String>("type"));
//        tc3.setCellValueFactory(
//        new PropertyValueFactory<Course, String>("code"));
//        tc4.setCellValueFactory(
//        new PropertyValueFactory<Course, String>("code"));
////        new PropertyValueFactory<Course, Integer>("credits"));
//        
//        mytable.setItems(obsList);
//        System.out.println("ENDDDDD");
//            
//        List<String[]> courseinfo = new ArrayList<>(); 
//            try {
//                courseinfo = readCSV("pc");
//            } catch (IOException ex) {
//                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        for (int i = 0 ; i<courseinfo.size() ; i++){
//            String[] s = courseinfo.get(i);
//            for (int j=0; j <s.length;j++){
//                System.out.println(s[j]);
//            }
//        }
//        System.out.println("hello22");
    }    
    
//   public static List<Course> readCSV(String fileName) throws IOException{
//       List<Course> courseinfo = new ArrayList<>(); 
//       String pathToFile = "/Users/pc.csv"; 
//       System.out.println(pathToFile + " path ");
//        try (BufferedReader br = new BufferedReader(new FileReader(pathToFile))) {
//            String line = br.readLine();
//            while (line != null) { 
//                String[] attributes = line.split(",");
////                Book book = createBook(attributes); // adding book into ArrayList books.add(book); // read next line before looping // if end of file reached, line would be null line = br.readLine(); } } catch (IOException ioe) { ioe.printStackTrace(); }
//                courseinfo.add(new Course(attributes[0], attributes[1], attributes[2], null, null, null, null));
//                line = br.readLine();
//            }
//        }
//        catch (IOException ioe) { 
//                ioe.printStackTrace(); 
//                } 
//        return courseinfo;
//   }

//Read more: http://www.java67.com/2015/08/how-to-load-data-from-csv-file-in-java.html#ixzz4xAZp1hki
   
   }
   
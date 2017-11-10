
package Main;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.*;
import java.net.URL;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import org.controlsfx.control.textfield.TextFields;


/**
 *
 * @author Lenovo
 */
public class Control implements Initializable  {

    private ArrayList<Course> myCourses = new ArrayList<>();
    public void Start(ArrayList<Course> myCourses) {
        this.myCourses = myCourses;
        populateTable();
    }

    public ArrayList<Course> getMyCourses() {
        return myCourses;
    }

    public void populateTable(){
        MainPage obj = new MainPage();
        List<Course> myList = getMyCourses();
        System.out.println("size please  :  " + myList.size());
        ObservableList<Course> obsList = FXCollections.observableArrayList();
        for (int i = 0; i < myList.size(); i++) {
            obsList.add(myList.get(i));
//            System.out.println("type = " + myList.get(i).);
            //System.out.println(myList.get(i) + " VAL");
            //System.out.println(obsList.get(i) + " VAL");

        }

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
    }

    @FXML
    private TableView<Course> mytable;

    @FXML
    private AnchorPane adminPane;

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
//    @FXML
//    void mthd(ActionEvent event) throws IOException{
//        MainPage obj = new MainPage();
//        List<Course> myList = new ArrayList<Course>();
//        try {
//            myList = obj.readCourseCSV();
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
//
////        final ObservableList<Course> data = FXCollections.observableArrayList(new Course("a", "b" , "c", "d", 5, null, null, null, null));
//        tc1.setCellValueFactory(
//        new PropertyValueFactory<Course, String>("name"));
//        tc2.setCellValueFactory(
//        new PropertyValueFactory<Course, String>("type"));
//        tc3.setCellValueFactory(
//        new PropertyValueFactory<Course, String>("code"));
//        tc4.setCellValueFactory(
//        new PropertyValueFactory<Course, Integer>("credits"));
////        new PropertyValueFactory<Course, Integer>("credits"));
//
//        mytable.setItems(obsList);
////        Course mycourse = mytable.getSelectionModel().getSelectedItem();
////        System.out.println("ENDDDDD");
////        System.out.println(mycourse);
//    }
//     @FXML
//    void mthd1(ActionEvent event) throws IOException{
//        ObservableList<Course> mycourse = mytable.getSelectionModel().getSelectedItems();
//        System.out.println("ENDDDDD");
//        System.out.println(mycourse);
//    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO


//        MainPage obj = new MainPage();
//        List<Course> myList = getMyCourses();
//        ObservableList<Course> obsList = FXCollections.observableArrayList();
//        for (int i = 0; i < myList.size(); i++) {
//            obsList.add(myList.get(i));
////            System.out.println("type = " + myList.get(i).);
//            System.out.println(myList.get(i) + " VAL");
//            System.out.println(obsList.get(i) + " VAL");
//
//        }
//
//        tc1.setCellValueFactory(
//                new PropertyValueFactory<Course, String>("name"));
//        tc2.setCellValueFactory(
//                new PropertyValueFactory<Course, String>("type"));
//        tc3.setCellValueFactory(
//                new PropertyValueFactory<Course, String>("code"));
//        tc4.setCellValueFactory(
//                new PropertyValueFactory<Course, Integer>("credits"));
////        new PropertyValueFactory<Course, Integer>("credits"));
//
//        mytable.setItems(obsList);
    }



}




   
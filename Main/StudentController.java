package Main;
import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.skins.JFXDatePickerSkin;
import com.sun.javafx.scene.control.skin.DatePickerSkin;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.Pane;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;

public class StudentController implements Initializable {
    private Student myStudent;
    public void Start(Student student) {
        myStudent = student;
    }


    @FXML
    private AnchorPane studentPane;

    @FXML
    private JFXButton logoutStudent;

    @FXML
    void displayCourses(ActionEvent event) throws IOException {
        
        ArrayList<Course> myCourses = myStudent.viewCourses();
//        myCourses.add(new Course("ama","ajja","jaja","mamama",4,null,null,null,null));
        System.out.println("sizeeee ::::::   " + myCourses.size());


        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CourseTable.fxml"));
        Pane newstudentPane = fxmlLoader.load();
        Control newcontroller = fxmlLoader.<Control>getController();
        //newcontroller.Test("Super");
        newcontroller.Start(myCourses);


        studentPane.getChildren().add(newstudentPane);
//        tableView.getColumns().add(0,"monday");
    }

    @FXML
    void roomBooking(ActionEvent event) throws IOException {
        Pane newstudentPane = FXMLLoader.load(getClass().getResource("RoomBooking.fxml"));
        studentPane.getChildren().clear();
        studentPane.getChildren().add(newstudentPane);
    }

    @FXML
    void viewTimeTable(ActionEvent event) throws IOException {
        Pane newstudentPane = FXMLLoader.load(getClass().getResource("TimeTable.fxml"));
//        JFXDatePickerSkin datePickerSkin = new JFXDatePickerSkin((new JFXDatePicker(LocalDate.now())));
//        Node popupContent = datePickerSkin.getPopupContent();
//        popupContent.setLayoutX(350.0);
//        popupContent.setLayoutY(100.0);
//        popupContent.setScaleX(1.17);
//        popupContent.setScaleY(0.85);
        //root.setCenter(popupContent);
        studentPane.getChildren().clear();
        //studentPane.getChildren().add(popupContent);
        studentPane.getChildren().add(newstudentPane);
    }

    @FXML
    void searchCoursesStudent(ActionEvent event) throws IOException{


        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SearchCourses.fxml"));
        Pane newstudentPane = fxmlLoader.load();
        studentPane.getChildren().clear();
        studentPane.getChildren().add(newstudentPane);

    }

    @FXML
    public void myRequestsStudent(ActionEvent actionEvent) throws IOException {

        Pane newstudentPane = FXMLLoader.load(getClass().getResource("MyRequests.fxml"));
        studentPane.getChildren().clear();
        studentPane.getChildren().add(newstudentPane);
    }

    @FXML
    public void Logout(ActionEvent actionEvent) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainPage.fxml"));
        Parent root2 = fxmlLoader.load();
        Scene scene = new Scene(root2);
        Stage stage = (Stage) logoutStudent.getScene().getWindow();
        stage.setScene(scene);

    }


    void Test(String s) {
        System.out.println(s + " chalaaa");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }



}

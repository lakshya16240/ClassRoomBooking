package Main;

import java.io.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

public class StudentController {

    private Student myStudent;

    public void Start(Student student) {
        myStudent = student;
    }
    @FXML
    private AnchorPane studentPane;

    @FXML
    void displayCourses(ActionEvent event) throws IOException {
        Pane newstudentPane = FXMLLoader.load(getClass().getResource("Table.fxml"));
        studentPane.getChildren().add(newstudentPane);
//        tableView.getColumns().add(0,"monday");
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CourseTable.fxml"));
//        root2 = (Parent) fxmlLoader.load();
//                root2 = FXMLLoader.load(getClass().getResource("StdntPage.fxml"));
        StudentController newcontroller = fxmlLoader.<StudentController>getController();
        
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
        studentPane.getChildren().clear();
        studentPane.getChildren().add(newstudentPane);
    }

    void Test(String s) {
        System.out.println(s + " chalaaa");
    }
}

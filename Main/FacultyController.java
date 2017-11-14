package Main;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.layout.Pane;

public class FacultyController {

    private Faculty myFaculty;
    public void Start(Faculty faculty){
        myFaculty = faculty;
    }

    @FXML
    private JFXButton logoutFaculty;

    @FXML
    private AnchorPane facultyPane;

    @FXML
    void displayCourses(ActionEvent event) throws IOException {
        ArrayList<Course> myCourses = myFaculty.getCoursesTaught();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CourseTable.fxml"));
        Pane newfacultyPane = fxmlLoader.load();
        Control newcontroller = fxmlLoader.<Control>getController();
        newcontroller.Start(myCourses);
        facultyPane.getChildren().clear();
        facultyPane.getChildren().add(newfacultyPane);
    }

    @FXML
    void roomBooking(ActionEvent event) throws IOException {
        Pane newfacultyPane = FXMLLoader.load(getClass().getResource("RoomBooking.fxml"));
        facultyPane.getChildren().clear();
        facultyPane.getChildren().add(newfacultyPane);
    }

    @FXML
    public void Logout(ActionEvent actionEvent) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainPage.fxml"));
        Parent root2 = fxmlLoader.load();
        Scene scene = new Scene(root2);
        Stage stage = (Stage) logoutFaculty.getScene().getWindow();
        stage.setScene(scene);
    }
}

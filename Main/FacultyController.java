package Main;

import static Main.MainPage.clgobj;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;

import javafx.scene.layout.Pane;
import org.controlsfx.control.Notifications;

/**
 * Controller class for FacultyPage fxml
 * @author Lenovo
 */
public class FacultyController implements Initializable{
    @FXML
    private JFXTextField welcome_user;
    private Faculty myFaculty;
    public void Start(Faculty faculty){
        myFaculty = faculty;
    }
    
        Notifications notifBuilder;
    Node graphic;
    @FXML
    private Button myButton;
    @FXML
    private JFXButton logoutFaculty;

    @FXML
    private AnchorPane facultyPane;
    
    /**
     * Method to display notifications to the faculty
     * @param event mouse click
     * @throws IOException 
     */
    @FXML
    void displayNotification(ActionEvent event) throws IOException {
        System.out.println("Button pressed");
        System.out.println("notif = " +MainPage.current_user.getNotification());
//        if (MainPage.current_user.getNotification()!=null){
            System.out.println("not null");
            notification(Pos.TOP_CENTER, graphic, MainPage.current_user.getNotification());
            MainPage.current_user.setNotification(null);
            notifBuilder.showInformation();
            College.serialize(clgobj);
//        }
        
    }
    
    /**
     * method used as a helper in displaying notifications
     * @param pos to define the position of notification
     * @param graphic the graphic property of the notification
     * @param text the text to be displayed 
     */
    private void notification(Pos pos, Node graphic, String text) {
        notifBuilder = Notifications.create().title("My Notifications").text(text).graphic(graphic).position(pos);
//        Notifications.create().
//        notifBuilder = Notifications.create().title("My Notifications").text(text).graphic(graphic).position(pos)
//        pos = 
    }
    
    /**
     * To display the courses taught by the faculty
     * @param event mouse click
     * @throws IOException 
     */
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
    
    /**
     * method to book a room for faculty
     * @param event
     * @throws IOException 
     */
    @FXML
    void roomBooking(ActionEvent event) throws IOException {
        Pane newfacultyPane = FXMLLoader.load(getClass().getResource("RoomBooking.fxml"));
        facultyPane.getChildren().clear();
        facultyPane.getChildren().add(newfacultyPane);
    }
      /**
     * Method to load the bookings of the faculty
     * @param actionEvent
     * @throws IOException 
     */
    
    @FXML
    public void myRequestsFaculty(ActionEvent actionEvent) throws IOException{
        
        Pane newstudentPane = FXMLLoader.load(getClass().getResource("MyRequests.fxml"));
        facultyPane.getChildren().clear();
        facultyPane.getChildren().add(newstudentPane);
    }
        
    /**
     * To log out to enable other user to log in
     * @param actionEvent
     * @throws IOException 
     */
    @FXML
    public void Logout(ActionEvent actionEvent) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainPage.fxml"));
        Parent root2 = fxmlLoader.load();
        Scene scene = new Scene(root2);
        Stage stage = (Stage) logoutFaculty.getScene().getWindow();
        stage.setScene(scene);
    }
     /**
     * displays any pending notification
     * sets the text for welcome user
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ActionEvent a = new ActionEvent();
        if (MainPage.current_user.getNotification()!=null){
            try {
                displayNotification(a);
            } catch (IOException ex) {
                Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
                welcome_user.setText("Welcome, " + MainPage.current_user.getName());
//            resultInfo.setText("Passed!");
        welcome_user.setStyle("-fx-text-fill: white; -fx-font-size: 16;");
    }
}

package Main;

import static Main.MainPage.clgobj;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

/** 
 * This class is used to take control when the admin logs in
 * @author Lenovo
 */
public class AdminController implements Initializable {
    @FXML
    private JFXTextField welcome_user;
    Admin myAdmin;

    public void Start(Admin admin) {
        myAdmin = admin;
    }
    
    
    Notifications notifBuilder;
    Node graphic;
    @FXML
    private Button myButton;

    @FXML
    private AnchorPane adminPane;

    @FXML
    private JFXButton logoutAdmin;
    
    
    /**
     * method used to display the notifications on click
     * @param event mouse click event
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
     * Method to load the booking requests of the admin
     * @param actionEvent
     * @throws IOException 
     */
    @FXML
    public void myRequestsAdmin(ActionEvent actionEvent) throws IOException{
        
        Pane newstudentPane = FXMLLoader.load(getClass().getResource("MyRequests.fxml"));
        adminPane.getChildren().clear();
        adminPane.getChildren().add(newstudentPane);
    }
    
    /**
     * Method to view the requests pending with the admin
     * @param event
     * @throws IOException 
     */
    @FXML
    void viewRequests(ActionEvent event) throws IOException {
        Pane newadminPane = FXMLLoader.load(getClass().getResource("Requests.fxml"));
        adminPane.getChildren().clear();
        adminPane.getChildren().add(newadminPane);

        System.out.println("ENDDDDD");
    }
    
    /**
     * method to book a room for admin
     * @param event
     * @throws IOException 
     */
    @FXML
    void roomBooking(ActionEvent event) throws IOException {
        Pane newstudentPane = FXMLLoader.load(getClass().getResource("RoomBooking.fxml"));
        adminPane.getChildren().clear();
        adminPane.getChildren().add(newstudentPane);
    }

    /**
     * To view the confirmed bookings
     * @param actionEvent
     * @throws IOException 
     */
    @FXML
    public void viewBookings(ActionEvent actionEvent) throws IOException {

        Pane newstudentPane = FXMLLoader.load(getClass().getResource("ViewBookings.fxml"));
        adminPane.getChildren().clear();
        adminPane.getChildren().add(newstudentPane);
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
        Stage stage = (Stage) logoutAdmin.getScene().getWindow();
        stage.setScene(scene);
    }
    /**
     * displays any pending notification
     * sets the text for welcome user
     * @param url
     * @param rb 
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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

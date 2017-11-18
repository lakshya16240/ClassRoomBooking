package Main;

import static Main.MainPage.clgobj;
import static Main.MainPage.current_user;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;


/**
 * A controller which handled the display of various pages of a student portal.
 */
public class StudentController implements Initializable {

    Notifications notifBuilder;
    Node graphic;
    @FXML
    private Button myButton;

    private Student myStudent;

    public void Start(Student student) {
        myStudent = student;
    }


    /**
     * method to display notifications to the student
     * @param event
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

    
    
//    public void myMethod(String text) throws Exception {
////        String text = "Hello Programmers";
////        Button btn1 = new Button("test notif bbi no");
////        System.out.println("Button added");
//        myButton.setOnAction(e -> {
//            graphic = null;
//            notification(Pos.TOP_CENTER, graphic, text);
//            notifBuilder.showInformation();
//        });
//    }

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

    @FXML
    private AnchorPane studentPane;

    @FXML
    private JFXButton logoutStudent;

    @FXML
    private JFXTextField welcome_user;

    /**
     * To display the courses taught by the faculty
     * @param event mouse click
     * @throws IOException
     */

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
        studentPane.getChildren().clear();
        studentPane.getChildren().add(newstudentPane);
//        tableView.getColumns().add(0,"monday");
    }

    /**
     * method to see the room booking for faculty
     * @param event
     * @throws IOException
     */

    @FXML
    void roomBooking(ActionEvent event) throws IOException {
        Pane newstudentPane = FXMLLoader.load(getClass().getResource("RoomBooking.fxml"));
        studentPane.getChildren().clear();
        studentPane.getChildren().add(newstudentPane);
    }

    /**
     * method to see the time table  page of a student
     * @param event
     * @throws IOException
     */
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

    /**
     * method the see the page for searching of courses
     * @param event
     * @throws IOException
     */

    @FXML
    void searchCoursesStudent(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SearchCourses.fxml"));
        Pane newstudentPane = fxmlLoader.load();
        studentPane.getChildren().clear();
        studentPane.getChildren().add(newstudentPane);

    }
    
//    @FXML
//    public void viewRequets(ActionEvent actionEvent){
//         ObservableList<Requests> requestList = FXCollections.observableArrayList(MainPage.current_user.getRequests());
//
//    }
//
//    @FXML
//    public void approveRequets(ActionEvent actionEvent) throws IOException{
//        ObservableList<FriendRequest> approve_requests = FXCollections.observableArrayList();;
////        requests = requestTable.getSelectionModel().getSelectedItems();
//        for (int i = 0 ; i<approve_requests.size() ; i++){
//            Student st = (Student)current_user;
//            st.getFriendRequests().remove(approve_requests.get(i));
//            approve_requests.get(i).getSender().getMyFriends().add((Student) current_user);
//            st.getMyFriends().add(approve_requests.get(i).getSender());
//        }
//        College.serialize(clgobj);
//    }
//     @FXML
//    public void viewFriends(ActionEvent actionEvent) throws IOException{
//        Student friend = null;
//        //friend is the user obtained on selection from the table
//        Student profile = (Student)(clgobj.getAllUsersMap().get(friend));
//
//    }

    /**
     * method to see the page diplaying the requests sent by the student
     * @param actionEvent
     * @throws IOException
     */
    @FXML
    public void myRequestsStudent(ActionEvent actionEvent) throws IOException {

        Pane newstudentPane = FXMLLoader.load(getClass().getResource("MyRequests.fxml"));
        studentPane.getChildren().clear();
        studentPane.getChildren().add(newstudentPane);
    }

    /**
     * method to see the page for searching of friends
     * @param actionEvent
     * @throws IOException
     */
    @FXML
    public void searchFriends(ActionEvent actionEvent) throws IOException {


        Pane newstudentPane = FXMLLoader.load(getClass().getResource("SearchFriends.fxml"));
        studentPane.getChildren().clear();
        studentPane.getChildren().add(newstudentPane);
    }

    /**
     * method to see the page diplaying the friends of a user
     * @param actionEvent
     * @throws IOException
     */
    @FXML
    public void myFriends(ActionEvent actionEvent) throws IOException {

        Pane newstudentPane = FXMLLoader.load(getClass().getResource("Friends.fxml"));
        studentPane.getChildren().clear();
        studentPane.getChildren().add(newstudentPane);

    }

    /**
     * method to logout the currently logged in student
     * @param actionEvent
     * @throws IOException
     */
    @FXML
    public void Logout(ActionEvent actionEvent) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainPage.fxml"));
        Parent root2 = fxmlLoader.load();
        Scene scene = new Scene(root2);
        Stage stage = (Stage) logoutStudent.getScene().getWindow();
        stage.setScene(scene);

    }




    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        try {
//            myMethod("Hi");
//            System.out.println("Method called");
//        } catch (Exception ex) {
//            Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
//        }
        ActionEvent a = new ActionEvent();
        if (MainPage.current_user.getNotification()!=null){
            try {
                displayNotification(a);
            } catch (IOException ex) {
                Logger.getLogger(StudentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
//        System.out.println(MainPage.current_user);
//        System.out.println(MainPage.current_user.getName());
//        welcome_user.
        welcome_user.setText("Welcome, " + MainPage.current_user.getName());
//            resultInfo.setText("Passed!");
        welcome_user.setStyle("-fx-text-fill: white; -fx-font-size: 16;");
    }



}

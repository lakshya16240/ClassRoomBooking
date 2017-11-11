package Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class AdminController implements Initializable {

    Admin myAdmin;

    public void Start(Admin admin) {
        myAdmin = admin;
    }

    @FXML
    private AnchorPane adminPane;

    @FXML
    private JFXButton logoutAdmin;

    @FXML
    void viewRequests(ActionEvent event) throws IOException {
        Pane newadminPane = FXMLLoader.load(getClass().getResource("Requests.fxml"));
        adminPane.getChildren().clear();
        adminPane.getChildren().add(newadminPane);

        System.out.println("ENDDDDD");
    }

    @FXML
    void roomBooking(ActionEvent event) throws IOException {
        Pane newstudentPane = FXMLLoader.load(getClass().getResource("RoomBooking.fxml"));
        adminPane.getChildren().clear();
        adminPane.getChildren().add(newstudentPane);
    }

    @FXML
    void viewTimeTable(ActionEvent event) {

    }

    @FXML
    public void viewBookings(ActionEvent actionEvent) throws IOException {

        Pane newstudentPane = FXMLLoader.load(getClass().getResource("ViewBookings.fxml"));
        adminPane.getChildren().clear();
        adminPane.getChildren().add(newstudentPane);
    }

    @FXML
    public void Logout(ActionEvent actionEvent) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainPage.fxml"));
        Parent root2 = fxmlLoader.load();
        Scene scene = new Scene(root2);
        Stage stage = (Stage) logoutAdmin.getScene().getWindow();
        stage.setScene(scene);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }



}

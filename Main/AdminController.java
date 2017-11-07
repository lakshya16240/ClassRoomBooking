package Main;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class AdminController implements Initializable {

    Admin myAdmin;

    public void Start(Admin admin) {
        myAdmin = admin;
    }

    @FXML
    private AnchorPane adminPane;

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
}

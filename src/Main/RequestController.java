package Main;
import Main.Admin;
import Main.College;
import Main.Course;
import static Main.MainPage.clgobj;
import Main.Requests;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author Lenovo
 */
public class RequestController implements Initializable {

    @FXML
    private TableView<Requests> requestTable;

    @FXML
    private TableColumn<Requests, String> Date;

    @FXML
    private TableColumn<Requests, String> Timings;

    @FXML
    private TableColumn<Requests, String> UserType;

    @FXML
    private TableColumn<Requests, String> Reason;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<Requests> requests = new ArrayList<Requests>();
        try {
            requests = Controller.deserializeArray();
            clgobj = College.deserialize("data");
            for (int i = 0; i<clgobj.getAllUsers().size(); i++){
                System.out.println(clgobj.getAllUsers().get(i));
            }
            System.out.println("deserialized");
        } 
        catch (IOException ex) {
            Logger.getLogger(RequestController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (ClassNotFoundException ex) {
            Logger.getLogger(RequestController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Admin a = (Admin) clgobj.getAllUsersMap().get("abhishek16126");
        
        ObservableList<Requests> obsList = FXCollections.observableArrayList();
        for (int i = 0; i < requests.size(); i++) {
            obsList.add(requests.get(i));
        }
        System.out.println("arraylist coming");
        System.out.println(a.getRequests());
//        final ObservableList<Course> data = FXCollections.observableArrayList(new Course("a", "b" , "c", "d", 5, null, null, null, null));
        Date.setCellValueFactory(
                new PropertyValueFactory<Requests, String>("date"));
        Timings.setCellValueFactory(
                new PropertyValueFactory<Requests, String>("startTime"));
        UserType.setCellValueFactory(
                new PropertyValueFactory<Requests, String>("userType"));
        Reason.setCellValueFactory(
                new PropertyValueFactory<Requests, String>("reason"));
//        new PropertyValueFactory<Course, Integer>("credits"));

        requestTable.setItems(obsList);
    }
}

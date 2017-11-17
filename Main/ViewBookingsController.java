package Main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import static Main.MainPage.clgobj;

public class ViewBookingsController implements Initializable{

    @FXML
    private TableView<Requests> requestTable;

    @FXML
    private TableColumn<Requests, String> Date;

    @FXML
    private TableColumn<Requests, String> start_time;

    
    @FXML
    private TableColumn<Requests, String> end_time;

    @FXML
    private TableColumn<Requests, String> UserType;

    @FXML
    private TableColumn<Requests, String> Reason;

    @FXML
    public void CancelRequests(ActionEvent actionEvent) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            PopulateTable();
        } catch (IOException ex) {
            Logger.getLogger(ViewBookingsController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ViewBookingsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void PopulateTable() throws IOException, ClassNotFoundException {
        ArrayList<Requests> requests = new ArrayList<Requests>();
        requests = Controller.deserializeArray();
        if (requests == null) {
            requests = new ArrayList<Requests>();
        }
        System.out.println("check in bookings " + requests);
        ObservableList<Requests> obsList = FXCollections.observableArrayList();
        for (int i = 0; i < requests.size(); i++) {
            if(requests.get(i).getStatus().equals("Approved"))
                obsList.add(requests.get(i));
        }
        System.out.println("arraylist coming");
//        final ObservableList<Course> data = FXCollections.observableArrayList(new Course("a", "b" , "c", "d", 5, null, null, null, null));
        Date.setCellValueFactory(
                new PropertyValueFactory<Requests, String>("date"));
        start_time.setCellValueFactory(
                new PropertyValueFactory<Requests, String>("startTime"));
        end_time.setCellValueFactory(
                new PropertyValueFactory<Requests, String>("endTime"));
        
        UserType.setCellValueFactory(
                new PropertyValueFactory<Requests, String>("userType"));
        Reason.setCellValueFactory(
                new PropertyValueFactory<Requests, String>("reason"));
//        new PropertyValueFactory<Course, Integer>("credits"));

        requestTable.setItems(obsList);
        requestTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        College.serialize(clgobj);
    }
}

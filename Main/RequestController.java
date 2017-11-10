package Main;

import static Main.MainPage.clgobj;
import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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

    @FXML
    private Button ApproveButton;

    @FXML
    private Button DisApproveButton;

    @FXML
    void ApproveRequests(ActionEvent event) throws IOException, ClassNotFoundException {
        System.out.println("Approve request called");
        ObservableList<Requests> ApprovedRequests = FXCollections.observableArrayList();;
        ApprovedRequests = requestTable.getSelectionModel().getSelectedItems();
//        System.out.println("req = " + ApprovedRequests);
        ArrayList<Requests> AllRequests = new ArrayList<Requests>();
        AllRequests = Controller.deserializeArray();
        int cnt = ApprovedRequests.size() - 1;
        System.out.println("approved requests = " + ApprovedRequests);
        System.out.println("requests = " + AllRequests);
        ArrayList<Requests> AppReq = new ArrayList<Requests>();
        for (int i = 0; i < ApprovedRequests.size(); i++) {
            AppReq.add(ApprovedRequests.get(i));
        }
        while (cnt>=0){
        for (int i = 0; i < AllRequests.size(); i++) {
            System.out.println(AllRequests.get(i) + " is not equal to " + ApprovedRequests.get(0));
//                Boolean t = Requests.get(i).getReason().equals(ApprovedRequests.get(0).getReason());
//                System.out.println(t);
            if (AllRequests.get(i).equals(AppReq.get(cnt))) {

                AppReq.remove(cnt);
                System.out.println("object found");
                cnt--;
                AllRequests.remove(i);
            }
        }
        
        ObservableList<Requests> display = FXCollections.observableArrayList(AllRequests);
        Controller.serializeArray(AllRequests);
        PopulateTable();
        }
    }
    
    

    @FXML
    void DisApproveRequests(ActionEvent event) {

    }

    public void PopulateTable() {
        ArrayList<Requests> requests = new ArrayList<Requests>();
//        requestTable.getSelectionModel().setCellSelectionEnabled(true);
//        requestTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        try {
            requests = Controller.deserializeArray();

            for (int i = 0; i < clgobj.getAllUsers().size(); i++) {
                System.out.println(clgobj.getAllUsers().get(i));
            }
            System.out.println("deserialized");
        } catch (IOException ex) {
            Logger.getLogger(RequestController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RequestController.class.getName()).log(Level.SEVERE, null, ex);
        }

        ObservableList<Requests> obsList = FXCollections.observableArrayList();
        for (int i = 0; i < requests.size(); i++) {
            obsList.add(requests.get(i));
        }
        System.out.println("arraylist coming");
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
        requestTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        PopulateTable();

    }
}

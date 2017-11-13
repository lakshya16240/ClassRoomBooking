package Main;

import static Main.MainPage.clgobj;
import static Main.MainPage.current_user;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionMode;

public class MyRequestsController implements Initializable {

    @FXML
    private TableView<Requests> myRequestsTable;

    @FXML
    private TableColumn<Requests, String> dateMyRequests;

    @FXML
    private TableColumn<Requests, String> startTimeMyRequests;

    @FXML
    private TableColumn<Requests, String> endTimeMyRequests;

    @FXML
    private TableColumn<Requests, String> roomMyRequests;

    @FXML
    private TableColumn<Requests, String> reasonMyRequests;

    @FXML
    private TableColumn<Requests, String> statusMyRequests;

    @FXML
    private Button CancelRequestStudent;

    @FXML
    void CancelRequest(ActionEvent event) throws IOException, ClassNotFoundException {
        ObservableList<Requests> CancelledRequests = FXCollections.observableArrayList();;
        CancelledRequests = myRequestsTable.getSelectionModel().getSelectedItems();
//        System.out.println("req = " + ApprovedRequests);

        ArrayList<Requests> AllRequests = new ArrayList<Requests>();
        AllRequests = Controller.deserializeArray();
        int cnt = CancelledRequests.size() - 1;
        System.out.println("selected req  " + CancelledRequests);
//        System.out.println("dis - approved requests = " + DisApprovedRequests);
//        System.out.println("requests = " + AllRequests);
        ArrayList<Requests> CancelReq = new ArrayList<Requests>();
        
        for (int i = 0; i < CancelledRequests.size(); i++) {
            CancelReq.add(CancelledRequests.get(i));
//            CancelledRequests.get(i).setStatus("Cancelled");
        }
        
        for (int i = 0; i < CancelledRequests.size(); i++) {
//            CancelReq.add(CancelledRequests.get(i));
            CancelledRequests.get(i).setStatus("Cancelled");
        }
        
//        clgobj.getAllUsersMap().put(MainPage.main.current_user.getEmailId(), MainPage.main.current_user);
        System.out.println("all = " + AllRequests);
        Controller.serializeArray(AllRequests);
        College.serialize(clgobj);
        PopulateTable();
//        System.out.println("serialized");

    }

    public void PopulateTable() {
//        User currentStudent = MainPage.clgobj.getAllUsersMap().get(MainPage.main.current_user.getEmailId());
        System.out.println("requests check " + current_user.getRequests());
        ObservableList<Requests> courseObservableList = FXCollections.observableArrayList(current_user.getRequests());
        System.out.println("check 2 " + courseObservableList);
        dateMyRequests.setCellValueFactory(new PropertyValueFactory<>("date"));
        startTimeMyRequests.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        endTimeMyRequests.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        statusMyRequests.setCellValueFactory(new PropertyValueFactory<>("status"));
        reasonMyRequests.setCellValueFactory(new PropertyValueFactory<>("reason"));
        roomMyRequests.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));

        myRequestsTable.setItems(courseObservableList);
        System.out.println("HAANNNNJIIII");
//        requestTable.setItems(obsList);
        myRequestsTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
//        College.serialize(clgobj);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        PopulateTable();
    }
}

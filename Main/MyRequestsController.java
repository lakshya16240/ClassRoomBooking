package Main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class MyRequestsController implements Initializable{

    @FXML
    private TableView<Requests> myRequestsTable;

    @FXML
    private TableColumn<Requests,String> dateMyRequests;

    @FXML
    private TableColumn<Requests,String> startTimeMyRequests;

    @FXML
    private TableColumn<Requests,String> endTimeMyRequests;

    @FXML
    private TableColumn<Requests,String> roomMyRequests;

    @FXML
    private TableColumn<Requests,String> reasonMyRequests;

    @FXML
    private TableColumn<Requests,String> statusMyRequests;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        User currentStudent = MainPage.clgobj.getAllUsersMap().get(MainPage.main.current_user.getEmailId());
        System.out.println("requests check " + MainPage.main.current_user.getRequests());
        ObservableList<Requests> courseObservableList = FXCollections.observableArrayList(currentStudent.getRequests());


        dateMyRequests.setCellValueFactory(new PropertyValueFactory<>("date"));
        startTimeMyRequests.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        endTimeMyRequests.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        statusMyRequests.setCellValueFactory(new PropertyValueFactory<>("status"));
        reasonMyRequests.setCellValueFactory(new PropertyValueFactory<>("reason"));
        roomMyRequests.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));

        myRequestsTable.setItems(courseObservableList);

    }
}

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

/**
 * Controller class for MyRequests fxml which loads all the booking requests of the user
 * @author Lenovo
 */
public class MyRequestsController implements Initializable {
    public static String s;
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
    
    /**
     * Method to cancel the booking made earlier
     * @param event mouse click 
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    @FXML
    void CancelRequest(ActionEvent event) throws IOException, ClassNotFoundException {
        ObservableList<Requests> CancelledRequests = FXCollections.observableArrayList();;
        CancelledRequests = myRequestsTable.getSelectionModel().getSelectedItems();
//        System.out.println("req = " + ApprovedRequests);

        ArrayList<Requests> AllRequests;
//        AllRequests = Controller.deserializeArray();
        AllRequests = current_user.getRequests();
        int cnt = CancelledRequests.size() - 1;
        System.out.println("selected req  " + CancelledRequests);
//        System.out.println("dis - approved requests = " + DisApprovedRequests);
//        System.out.println("requests = " + AllRequests);
        ArrayList<Requests> CancelReq = new ArrayList<Requests>();
        
        for (int i = 0; i < CancelledRequests.size(); i++) {
            CancelReq.add(CancelledRequests.get(i));
//            CancelledRequests.get(i).setStatus("Cancelled");
        }
        System.out.println("requests check " + current_user.getRequests());
        Requests req = null;
        for (int i = 0; i < CancelReq.size(); i++) {
//            CancelReq.add(CancelledRequests.get(i));
//            CancelledRequests.get(i).setStatus("Cancelled");
            for (int j=0 ; j<AllRequests.size() ; j++){
                if (CancelReq.get(i).equals(AllRequests.get(j))){
                    if (CancelReq.get(i).getStatus().equals("Approved")){
                        req = CancelReq.get(i);
                    }
                    AllRequests.get(j).setStatus("Cancelled");
                    System.out.println("Hua kuch cancel");
                }
            }
//                AllRequests.get(AllRequests.indexOf(CancelReq.get(i))).setStatus("Cancelled");
        }
        System.out.println("requests check " + current_user.getRequests());
//        clgobj.getAllUsersMap().put(MainPage.main.current_user.getEmailId(), MainPage.main.current_user);
        System.out.println("all = " + AllRequests);
        Controller.serializeArray(AllRequests);
        College.serialize(clgobj);
        if (req!=null){
            s = "Room no." + req.getRoomNumber() + "is now available from " + req.getStartTime()+ " to " + req.getEndTime();
            current_user.uppdateNotification(s);
        }
        
        System.out.println("after cancel "+ current_user.getNotification());
        College.serialize(clgobj);
    }
    /**
     * Method to put the requests in the table
     */
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
//        myRequestsTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
//        College.serialize(clgobj);

    }
   
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        PopulateTable();
    }
}

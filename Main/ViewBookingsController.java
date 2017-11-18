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

/**
 * A controller class which the admin to view the rooms booked
 *
 * @author Lenovo
 */

/**
 * Controller handling the display of room booking requests made by users
 */
public class ViewBookingsController implements Initializable {

    @FXML
    private TableView<Requests> requestTable;

    @FXML
    private TableColumn<Requests, String> Date;

    @FXML
    private TableColumn<Requests, String> start_time;

    @FXML
    private TableColumn<Requests, String> end_time;

    @FXML
    private TableColumn<Requests, String> room;

    @FXML
    private TableColumn<Requests, String> UserType;

    @FXML
    private TableColumn<Requests, String> Reason;

    /**
     * This method gives admin the ability to cancel the request of any user
     *
     * @param actionEvent mouse click
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @FXML
    public void CancelRequests(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
        ObservableList<Requests> CancelledRequests = FXCollections.observableArrayList();;
        CancelledRequests = requestTable.getSelectionModel().getSelectedItems();
//        System.out.println("req = " + ApprovedRequests);
        ArrayList<Requests> AllRequests = new ArrayList<Requests>();
        AllRequests = Controller.deserializeArray();
        int cnt = CancelledRequests.size() - 1;
//        System.out.println("dis - approved requests = " + DisApprovedRequests);
//        System.out.println("requests = " + AllRequests);
        ArrayList<Requests> CancelReq = new ArrayList<Requests>();

        for (int i = 0; i < CancelledRequests.size(); i++) {
            CancelReq.add(CancelledRequests.get(i));
        }

        while (cnt >= 0) {
            Requests current_req = CancelReq.get(cnt);
//                User  userobj = clgobj.getAllUsersMap().get(current_req.getUser().getName());
//            User userobj = current_req.getUser();
            User userobj = clgobj.getAllUsersMap().get(current_req.getUser().getEmailId());

//            System.out.println("user  check " + userobj);
            for (int i = 0; i < userobj.getRequests().size(); i++) {
                if (userobj.getRequests().get(i).equals(current_req)) {
                    userobj.getRequests().get(i).setStatus("Cancelled");
                }
            }
//            System.out.println("All requests on pressing DisApproved: " + userobj.getRequests());
            clgobj.getAllUsersMap().put(current_req.getUser().getEmailId(), userobj);
//            System.out.println("The actual check " + clgobj.getAllUsersMap().get("ab").getRequests());

//            System.out.println("here will be the error " + (clgobj.getAllUsersMap().get("ab") == current_req.getUser()));
//            System.out.println("one more check when I did jugaad " + current_req.getStatus());
//            current_req.setStatus("Approved");
            for (int i = 0; i < AllRequests.size(); i++) {
//                System.out.println(AllRequests.get(i) + " is not equal to " + ApprovedRequests.get(0));
//                Boolean t = Requests.get(i).getReason().equals(ApprovedRequests.get(0).getReason());
//                System.out.println(t);
//                Requests current_req = AppReq.get(cnt);
////                User  userobj = clgobj.getAllUsersMap().get(current_req.getUser().getName());
//                User userobj = current_req.getUser();
//                System.out.println("user check " + userobj);
//                current_req.setStatus("Approved");

//                main.current_user = current_req.getUser();
                if (AllRequests.get(i).equals(current_req)) {
                    CancelReq.remove(cnt);
                    System.out.println("object found");
                    cnt--;
                    AllRequests.get(i).setStatus("Cancelled");
//                    AllRequests.remove(i);
                }
            }

        }
        ArrayList<Requests> PendingRequests = new ArrayList<Requests>();
        for (int i = 0; i < AllRequests.size(); i++) {
            if (AllRequests.get(i).getStatus().equals("Pending")) {
                PendingRequests.add(AllRequests.get(i));
            }
        }
//            System.out.println("check in admin " + MainPage.clgobj.getAllUsersMap().get("ab").getRequests());
        ObservableList<Requests> display = FXCollections.observableArrayList(PendingRequests);

        Controller.serializeArray(AllRequests);
        PopulateTable();
        College.serialize(clgobj);
//        System.out.println("serialized");

    }

    /**
     * A method to put the entries of all the bookings in the table
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void PopulateTable() throws IOException, ClassNotFoundException {
        ArrayList<Requests> requests = new ArrayList<Requests>();
        requests = Controller.deserializeArray();
        if (requests == null) {
            requests = new ArrayList<Requests>();
        }
//        System.out.println("check in bookings " + requests);
        ObservableList<Requests> obsList = FXCollections.observableArrayList();
        for (int i = 0; i < requests.size(); i++) {
            if (requests.get(i).getStatus().equals("Approved")) {
                obsList.add(requests.get(i));
            }
        }
//        System.out.println("arraylist coming");
//        final ObservableList<Course> data = FXCollections.observableArrayList(new Course("a", "b" , "c", "d", 5, null, null, null, null));
        Date.setCellValueFactory(
                new PropertyValueFactory<Requests, String>("date"));
        start_time.setCellValueFactory(
                new PropertyValueFactory<Requests, String>("startTime"));
        end_time.setCellValueFactory(
                new PropertyValueFactory<Requests, String>("endTime"));
        room.setCellValueFactory(
                new PropertyValueFactory<Requests, String>("roomNumber"));

        UserType.setCellValueFactory(
                new PropertyValueFactory<Requests, String>("userType"));
        Reason.setCellValueFactory(
                new PropertyValueFactory<Requests, String>("reason"));
//        new PropertyValueFactory<Course, Integer>("credits"));

        requestTable.setItems(obsList);
        requestTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        College.serialize(clgobj);
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
}

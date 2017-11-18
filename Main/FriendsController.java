/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import static Main.MainPage.clgobj;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author Lenovo
 */
public class FriendsController implements Initializable {
    
    @FXML
    private TableView<Student> friendsTable;

    @FXML
    private TableColumn<Student, String > name_column;

    @FXML
    private TableColumn<Student, String> email_column;

    @FXML
    private TableColumn<Student, String> branch_column;
    
    @FXML
    private TableColumn<Student, String> info;
    /**
     * Initializes the controller class.
     */
    
    void populateTable(){
        ArrayList<Student> friendList;
        friendList = ((Student)MainPage.current_user).getMyFriends();
        ObservableList<Student> obsList = FXCollections.observableArrayList(friendList);
        name_column.setCellValueFactory(
                new PropertyValueFactory<Student, String>("name"));
        email_column.setCellValueFactory(
                new PropertyValueFactory<Student, String>("emailId"));
        branch_column.setCellValueFactory(
                new PropertyValueFactory<Student, String>("courseType"));


        friendsTable.setItems(obsList);
        friendsTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
//        College.serialize(clgobj);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        populateTable();
    }    
    
}

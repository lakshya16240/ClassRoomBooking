package Main;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import static Main.MainPage.clgobj;
import static Main.MainPage.current_user;

public class SearchFriendsController  implements Initializable{


    private HashMap<String,User> users;


    @FXML
    public TextField searchFriendsTextField;
    @FXML
    public ListView<AnchorPane> listViewFriends;


    public void addFriend(AnchorPane anchorPane){
        Student friend = null ;
        TextFlow textFlow = (TextFlow) anchorPane.getChildren().get(0);
        String name = ((Text)((TextFlow)listViewFriends.getSelectionModel().getSelectedItem().getChildren().get(0)).getChildren().get(0)).getText();
        FriendRequest freq = new FriendRequest((Student) current_user);
        friend.getFriendRequests().add(freq);


    }

    @FXML
    public void viewRequets(ActionEvent actionEvent){
        ObservableList<Requests> requestList = FXCollections.observableArrayList(MainPage.current_user.getRequests());

    }

    @FXML
    public void approveRequets(ActionEvent actionEvent) throws IOException {
        ObservableList<FriendRequest> approve_requests = FXCollections.observableArrayList();;
//        requests = requestTable.getSelectionModel().getSelectedItems();
        for (int i = 0 ; i<approve_requests.size() ; i++){
            Student st = (Student)current_user;
            st.getFriendRequests().remove(approve_requests.get(i));
            approve_requests.get(i).getSender().getMyFriends().add((Student) current_user);
            st.getMyFriends().add(approve_requests.get(i).getSender());
        }
        College.serialize(clgobj);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        users = clgobj.getAllUsersMap();

        searchFriendsTextField.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                TextFlow textFlow = new TextFlow();

                listViewFriends.getItems().clear();

                for(Map.Entry<String,User> x : users.entrySet()){
                    AnchorPane anchorPane = new AnchorPane();

                    if(x.getKey().contains(newValue)){
                        if(x.getValue().getType().equals("Student")) {
                            Text text1 = new Text(x.getValue().getName() + "\n");
                            text1.setStyle("-fx-font-size: 23px");
                            Text text2 = new Text(x.getValue().getEmailId() + "\n");
                            text2.setStyle("-fx-font-size: 18px");
                            Text text3 = new Text(((Student)x.getValue()).getCourseType());
                            text3.setStyle("-fx-font-size: 18px");
                            textFlow.getChildren().addAll(text1, text2, text3);
                            anchorPane.getChildren().add(textFlow);
                            textFlow.setPrefWidth(500);
                            Button button = new Button("+ Add Friend");
                            anchorPane.getChildren().add(button);
                            button.setLayoutX(500);
                            button.setOnAction(new EventHandler<ActionEvent>() {
                                @Override
                                public void handle(ActionEvent event) {


                                    addFriend((AnchorPane) button.getParent());
                                }
                            });

                            listViewFriends.getItems().add(anchorPane);
                        }
                    }
                }





            }

        });


    }
}

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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import static Main.MainPage.clgobj;
import static Main.MainPage.current_user;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Controller which handles the searching of friends, displaying of friend requests and adding/ rejecting them
 */

public class SearchFriendsController  implements Initializable{



    private HashMap<String,User> users;
    private ArrayList<FriendRequest> friendRequests;


    @FXML
    public ListView<AnchorPane> listViewRequests;
    @FXML
    public TextField searchFriendsTextField;
    @FXML
    public ListView<AnchorPane> listViewFriends;


    /**
     * send a friend request to the selected user
     * @param anchorPane pane in which the {add} button is stored.
     * @throws IOException
     */

    public void addFriend(AnchorPane anchorPane) throws IOException, ClassNotFoundException {
        Student friend = null ;
        College clgobj;
        clgobj = College.deserialize("data");
        TextFlow textFlow = (TextFlow) anchorPane.getChildren().get(0);
        Text text= (Text) textFlow.getChildren().get(1);
        String email = text.getText();
        email = email.replace("\n","");

//        System.out.println("NAME : " + name);
        //for(Map.Entry<String,User> x : clgobj.getAllUsersMap())
        friend = (Student) users.get(email);
        FriendRequest freq = new FriendRequest((Student) current_user , friend);
        freq.setStatus("Pending");
        if(!friend.getFriendRequests().contains(freq))
            friend.getFriendRequests().add(freq);
        if(((Student) current_user).getFriendRequests().contains(freq))
            ((Student) current_user).getFriendRequests().add(freq);

        Text text1 = new Text("Pending");
        anchorPane.getChildren().add(text1);
        text1.setLayoutX(270);
        text1.setLayoutY(20);
        text1.setStyle("-fx-font-size: 14px");
        clgobj.getAllUsersMap().put(current_user.getEmailId(), current_user);
        clgobj.getAllUsersMap().put(friend.getEmailId(), friend);
        College.serialize(clgobj);
    }

    /**
     * Views the friend requests for the current user
     * @throws IOException
     */

    public void viewRequests() throws IOException {



        TextFlow textFlow = new TextFlow();
        listViewRequests.getItems().clear();
        System.out.println(friendRequests.size());
        for(int i=0;i<friendRequests.size();i++){
            AnchorPane anchorPane  = new AnchorPane();
            textFlow = new TextFlow();

            Student sender = friendRequests.get(i).getSender();
            System.out.println(sender.getName());


            Text text1 = new Text(sender.getName() + "\n");
            text1.setStyle("-fx-font-size: 20px");
            Text text2 = new Text(sender.getEmailId() + "\n");
            text2.setStyle("-fx-font-size: 15px");
            Text text3 = new Text(sender.getCourseType()  + " - " + sender.getBatch());
            text3.setStyle("-fx-font-size: 15px");
            textFlow.getChildren().addAll(text1, text2, text3);
            anchorPane.getChildren().add(textFlow);
            textFlow.setPrefWidth(290);
            Button acceptbutton = new Button("Accept");
            anchorPane.getChildren().add(acceptbutton);
            Button rejectButton = new Button("Reject");
            anchorPane.getChildren().add(rejectButton);
            acceptbutton.setLayoutX(300);
            rejectButton.setLayoutX(300);
            acceptbutton.setLayoutY(2);
            rejectButton.setLayoutY(30);





            listViewRequests.getItems().add(anchorPane);

            int finalI = i;
            acceptbutton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        approveRequets((AnchorPane) acceptbutton.getParent(),"accept", friendRequests.get(finalI));
                        ((Student)current_user).getFriendRequests().remove(friendRequests.get(finalI));
                        clgobj.getAllUsersMap().put(current_user.getEmailId(), current_user);
                        viewRequests();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            rejectButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    try {
                        approveRequets((AnchorPane) rejectButton.getParent(),"reject",friendRequests.get(finalI));
                        ((Student)current_user).getFriendRequests().remove(friendRequests.get(finalI));
                        clgobj.getAllUsersMap().put(current_user.getEmailId(), current_user);
                        viewRequests();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            });

        }

        College.serialize(clgobj);

    }


    /**
     * Method which accepts/rejects a friend request.
     * @param anchorPane pane in which the {accept/reject} button is situated.
     * @param react String value "accept" if accept button is clicked, "reject" if reject button is clicked.
     * @param friendRequest A FriendRequest type object which is to be accepted/rejected.
     * @throws IOException
     */
    public void approveRequets(AnchorPane anchorPane, String react , FriendRequest friendRequest) throws IOException {

        TextFlow textFlow = (TextFlow) anchorPane.getChildren().get(0);
        Text text= (Text) textFlow.getChildren().get(1);
        String name = text.getText();
        name = name.replace("\n","");
        Student friend = (Student)(users.get(name));
        if(react.equals("accept")){
             ArrayList<FriendRequest> requests = friend.getFriendRequests();
             for(int i=0;i<requests.size();i++){

                 if(requests.get(i).getReceiver().getName().equals(current_user.getName())) {
                     requests.get(i).setStatus("Friend");
                 }
             }

             Student student= (Student) current_user;

             student.getMyFriends().add(friend);
             (friend).getMyFriends().add(student);

         }
         else{
             ArrayList<FriendRequest> requests = (friend).getFriendRequests();
             for(int i=0;i<requests.size();i++){

                 if(requests.get(i).getReceiver().getName().equals(current_user.getName()))
                     requests.get(i).setStatus("Rejected");
             }
         }

//        requests = requestTable.getSelectionModel().getSelectedItems();
//        for (int i = 0 ; i<approve_requests.size() ; i++){
//            Student st = (Student)current_user;
//            st.getFriendRequests().remove(approve_requests.get(i));
//            approve_requests.get(i).getSender().getMyFriends().add((Student) current_user);
//            st.getMyFriends().add(approve_requests.get(i).getSender());
//        }
        clgobj.getAllUsersMap().put(current_user.getEmailId(), current_user);
        clgobj.getAllUsersMap().put(friend.getEmailId(), friend);
        College.serialize(clgobj);
    }


    void populateList(){

        TextFlow textFlow = new TextFlow();

        for(Map.Entry<String,User> x : users.entrySet()){
            AnchorPane anchorPane = new AnchorPane();
            textFlow = new TextFlow();
            int flag=0;

            if(!x.getValue().getEmailId().equals(current_user.getEmailId())){
                if(x.getValue().getType().equals("Student")) {
                    Text text1 = new Text(x.getValue().getName() + "\n");
                    text1.setStyle("-fx-font-size: 20px");
                    Text text2 = new Text(x.getValue().getEmailId() + "\n");
                    text2.setStyle("-fx-font-size: 15px");
                    Text text3 = new Text(((Student)x.getValue()).getCourseType() + " - " + ((Student)x.getValue()).getBatch());
                    text3.setStyle("-fx-font-size: 14px");
                    textFlow.getChildren().addAll(text1, text2, text3);
                    anchorPane.getChildren().add(textFlow);
                    textFlow.setPrefWidth(290);
                    for(int i=0;i<((Student) current_user).getFriendRequests().size();i++){
                        System.out.println(((Student) current_user).getFriendRequests().get(i).getReceiver().getName());
                        if(((Student) current_user).getFriendRequests().get(i).getReceiver().getName().equals(x.getValue().getName())
                                || ((Student) current_user).getFriendRequests().get(i).getSender().getName().equals(x.getValue().getName())){

                            System.out.println("DEBUG");
                            Text textstatus = new Text(((Student) current_user).getFriendRequests().get(i).getStatus());
                            anchorPane.getChildren().add(textstatus);
                            textstatus.setLayoutX(270);
                            textstatus.setLayoutY(20);
                            textstatus.setStyle("-fx-font-size: 14px");
                            flag=1;

                        }
                    }

                    for(int i=0;i<((Student)current_user).getMyFriends().size();i++){
                        if(((Student)current_user).getMyFriends().get(i).getName().equals(x.getKey())){

                            Text textstatus = new Text("Friend");
                            anchorPane.getChildren().add(textstatus);
                            textstatus.setLayoutX(270);
                            textstatus.setLayoutY(20);
                            textstatus.setStyle("-fx-font-size: 14px");
                            textstatus.setTextAlignment(TextAlignment.CENTER);
                            flag=1;
                        }
                    }



                    if(flag==0) {
                        Button button = new Button("+ Add Friend");
                        anchorPane.getChildren().add(button);
                        button.setLayoutX(270);
                        button.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {


                                try {
                                    addFriend((AnchorPane) button.getParent());
                                    viewRequests();
                                    anchorPane.getChildren().remove(button);
                                } catch (IOException e) {
                                    e.printStackTrace();
                                } catch (ClassNotFoundException ex) {
                                    Logger.getLogger(SearchFriendsController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        });
                    }

                    listViewFriends.getItems().add(anchorPane);
                }
            }
        }

    }


    /**
     * display the friends searched for and the friend requests for a user.
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {

            try {
                clgobj = College.deserialize("data");
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            users = clgobj.getAllUsersMap();
            friendRequests = ((Student)current_user).getFriendRequests();
            listViewFriends.getItems().clear();
            populateList();
            viewRequests();
            searchFriendsTextField.textProperty().addListener(new ChangeListener<String>() {


                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                    TextFlow textFlow = new TextFlow();

                    listViewFriends.getItems().clear();
//                    try {
//                        viewRequests();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }

                    for(Map.Entry<String,User> x : users.entrySet()){
                        AnchorPane anchorPane = new AnchorPane();
                        textFlow = new TextFlow();
                        int flag=0;

                        if(x.getKey().contains(newValue) && !x.getValue().getEmailId().equals(current_user.getEmailId())){
                            if(x.getValue().getType().equals("Student")) {
                                Text text1 = new Text(x.getValue().getName() + "\n");
                                text1.setStyle("-fx-font-size: 20px");
                                Text text2 = new Text(x.getValue().getEmailId() + "\n");
                                text2.setStyle("-fx-font-size: 15px");
                                Text text3 = new Text(((Student)x.getValue()).getCourseType() + " - " + ((Student)x.getValue()).getBatch());
                                text3.setStyle("-fx-font-size: 14px");
                                textFlow.getChildren().addAll(text1, text2, text3);
                                anchorPane.getChildren().add(textFlow);
                                textFlow.setPrefWidth(290);
                                for(int i=0;i<((Student) current_user).getFriendRequests().size();i++){
                                    System.out.println(((Student) current_user).getFriendRequests().get(i).getReceiver().getName());
                                    if(((Student) current_user).getFriendRequests().get(i).getReceiver().getName().equals(x.getValue().getName())
                                            || ((Student) current_user).getFriendRequests().get(i).getSender().getName().equals(x.getValue().getName())){

                                        System.out.println("DEBUG");
                                        Text textstatus = new Text(((Student) current_user).getFriendRequests().get(i).getStatus());
                                        anchorPane.getChildren().add(textstatus);
                                        textstatus.setLayoutX(270);
                                        textstatus.setLayoutY(20);
                                        textstatus.setStyle("-fx-font-size: 14px");
                                        flag=1;

                                    }
                                }

                                for(int i=0;i<((Student)current_user).getMyFriends().size();i++){
                                    if(((Student)current_user).getMyFriends().get(i).getName().equals(x.getKey())){

                                        Text textstatus = new Text("Friend");
                                        anchorPane.getChildren().add(textstatus);
                                        textstatus.setLayoutX(270);
                                        textstatus.setLayoutY(20);
                                        textstatus.setStyle("-fx-font-size: 14px");
                                        textstatus.setTextAlignment(TextAlignment.CENTER);
                                        flag=1;
                                    }
                                }



                                if(flag==0) {
                                    Button button = new Button("+ Add Friend");
                                    anchorPane.getChildren().add(button);
                                    button.setLayoutX(270);
                                    button.setOnAction(new EventHandler<ActionEvent>() {
                                        @Override
                                        public void handle(ActionEvent event) {


                                            try {
                                                addFriend((AnchorPane) button.getParent());
                                                viewRequests();
                                                anchorPane.getChildren().remove(button);
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            } catch (ClassNotFoundException ex) {
                                                Logger.getLogger(SearchFriendsController.class.getName()).log(Level.SEVERE, null, ex);
                                            }
                                        }
                                    });
                                }

                                listViewFriends.getItems().add(anchorPane);
                            }
                        }
                    }


                }

            });


        } catch (IOException ex) {
            Logger.getLogger(SearchFriendsController.class.getName()).log(Level.SEVERE, null, ex);
        }


    }
}

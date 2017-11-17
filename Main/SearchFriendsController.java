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
    private ArrayList<FriendRequest> friendRequests;


    @FXML
    public ListView<AnchorPane> listViewRequests;
    @FXML
    public TextField searchFriendsTextField;
    @FXML
    public ListView<AnchorPane> listViewFriends;


    public void addFriend(AnchorPane anchorPane) throws IOException {
        Student friend = null ;
        TextFlow textFlow = (TextFlow) anchorPane.getChildren().get(0);
        Text text= (Text) textFlow.getChildren().get(0);
        String name = text.getText();
        name = name.replace("\n","");
        System.out.println("NAME : " + name);
        //for(Map.Entry<String,User> x : clgobj.getAllUsersMap())
        friend = (Student) users.get(name);
        FriendRequest freq = new FriendRequest((Student) current_user , friend);
        freq.setStatus("Pending");
        if(!friend.getFriendRequests().contains(freq))
            friend.getFriendRequests().add(freq);
        if(((Student) current_user).getFriendRequests().contains(freq))
            ((Student) current_user).getFriendRequests().add(freq);

        Text text1 = new Text("Pending");
        anchorPane.getChildren().add(text1);
        text1.setLayoutX(265);
        College.serialize(clgobj);
    }

    public void viewRequests(){

        TextFlow textFlow = new TextFlow();
        listViewRequests.getItems().clear();
        System.out.println(friendRequests.size());
        for(int i=0;i<friendRequests.size();i++){
            AnchorPane anchorPane  = new AnchorPane();

            Student sender = friendRequests.get(i).getSender();
            System.out.println(sender.getName());


            Text text1 = new Text(sender.getName() + "\n");
            text1.setStyle("-fx-font-size: 23px");
            Text text2 = new Text(sender.getEmailId() + "\n");
            text2.setStyle("-fx-font-size: 18px");
            Text text3 = new Text(sender.getCourseType());
            text3.setStyle("-fx-font-size: 18px");
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
                        approveRequets((AnchorPane) acceptbutton.getParent(),"accept", friendRequests.get(finalI) );
                        ((Student)current_user).getFriendRequests().remove(friendRequests.get(finalI));
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
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            });

        }

    }


    public void approveRequets(AnchorPane anchorPane, String react , FriendRequest friendRequest) throws IOException {

        TextFlow textFlow = (TextFlow) anchorPane.getChildren().get(0);
        Text text= (Text) textFlow.getChildren().get(0);
        String name = text.getText();
        name = name.replace("\n","");
        if(react.equals("accept")){
             ArrayList<FriendRequest> requests = ((Student)users.get(name)).getFriendRequests();
             for(int i=0;i<requests.size();i++){

                 if(requests.get(i).getReceiver().getName().equals(current_user.getName())) {
                     requests.get(i).setStatus("Friend");
                 }
             }

             Student student= (Student) current_user;

             student.getMyFriends().add((Student)users.get(name));
             ((Student)users.get(name)).getMyFriends().add(student);

         }
         else{
             ArrayList<FriendRequest> requests = ((Student)users.get(name)).getFriendRequests();
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
        College.serialize(clgobj);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        users = clgobj.getAllUsersMap();
        friendRequests = ((Student)current_user).getFriendRequests();

        searchFriendsTextField.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                TextFlow textFlow = new TextFlow();

                listViewFriends.getItems().clear();
                viewRequests();

                for(Map.Entry<String,User> x : users.entrySet()){
                    AnchorPane anchorPane = new AnchorPane();
                    int flag=0;

                    if(x.getKey().contains(newValue) && !x.getValue().equals(current_user)){
                        if(x.getValue().getType().equals("Student")) {
                            Text text1 = new Text(x.getValue().getName() + "\n");
                            text1.setStyle("-fx-font-size: 23px");
                            Text text2 = new Text(x.getValue().getEmailId() + "\n");
                            text2.setStyle("-fx-font-size: 18px");
                            Text text3 = new Text(((Student)x.getValue()).getCourseType());
                            text3.setStyle("-fx-font-size: 18px");
                            textFlow.getChildren().addAll(text1, text2, text3);
                            anchorPane.getChildren().add(textFlow);
                            textFlow.setPrefWidth(290);
                            for(int i=0;i<((Student) current_user).getFriendRequests().size();i++){
                                System.out.println(((Student) current_user).getFriendRequests().get(i).getReceiver().getName());
                                if(((Student) current_user).getFriendRequests().get(i).getReceiver().getName().equals(x.getValue().getName())){

                                    System.out.println("DEBUG");
                                    Text textstatus = new Text(((Student) current_user).getFriendRequests().get(i).getStatus());
                                    anchorPane.getChildren().add(textstatus);
                                    textstatus.setLayoutX(265);
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


    }
}
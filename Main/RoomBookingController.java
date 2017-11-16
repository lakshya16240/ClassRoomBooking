/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import static Main.Controller.deserializeArray;
import static Main.Controller.serializeArray;
import static Main.MainPage.clgobj;
import static Main.MainPage.current_user;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.HashMap;

import com.sun.xml.internal.bind.v2.TODO;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

/**
 * @author Lenovo
 */
public class RoomBookingController implements Initializable {
//    MainPage main = new MainPage();

    @FXML
    private JFXDatePicker datePicker;

    @FXML
    private ComboBox<String> roomsComboBox;

    @FXML
    private ListView<TextFlow> listViewBookings;

    @FXML
    private JFXTimePicker timeFrom;

    @FXML
    private JFXTimePicker timeTo;

    @FXML
    private TextArea Reason;

    @FXML
    private JFXTextField roomRequest;

    @FXML
    private JFXTextField capacity;

    private int day;
    private ArrayList<Room> roomArrayList;
    private HashMap<String, Room> roomHashMap;
    private ArrayList<Requests> requests;
    private String date;
    public static final String defaultRoom = "All Rooms";

    @FXML
    void makeRequest(ActionEvent event) throws IOException, ClassNotFoundException {

        HashMap<String, Room> roomBookings = Room.deserializeRoom();

        int flag = 0;

//        System.out.println("req 1 = " + MainPage.main.current_user.getRequests());
        System.out.println("make request pressed");
//        String date1 = date.getAccessibleText();\\
//        LocalDate localDate = date.getValue();
//        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
//        Date date2 = Date.from(instant);
//        System.out.println("day = " + date2);
        String date1 = datePicker.getValue().toString();
        int day = datePicker.getValue().getDayOfWeek().getValue();

//        String requested_date = date1.toString();
        String from_time = timeFrom.getValue().toString();
        String to_time = timeTo.getValue().toString();
        String roomNumber = roomRequest.getText();
        System.out.println("room no. " + roomNumber);
//        roomNumber = roomNumber.toUpperCase();
        int requied_capacity = Integer.parseInt(capacity.getText());
        System.out.println("date =  " + date1);
        System.out.println("from_time = " + from_time);

        String reason = Reason.getText();

        Reason.setText("");

        int startHour = Integer.parseInt((from_time.split(":"))[0]);
        int startMin = Integer.parseInt((from_time.split(":"))[1]);
        int endHour = Integer.parseInt((to_time.split(":"))[0]);
        int endMin = Integer.parseInt((to_time.split(":"))[1]);
        int startIndex;
        int endIndex;

        if (startHour < 8) {
            startIndex = (12 + startHour - 8) * 2;
        } else {
            startIndex = (startHour - 8) * 2;
        }
        if (startMin != 0) {
            startIndex++;
        }

        if (endHour <= 8) {
            endIndex = (12 + endHour - 8) * 2 - 1;
        } else {
            endIndex = (endHour - 8) * 2 - 1;
        }

        if (endMin != 0) {
            endIndex++;
        }

//        Admin.
        ArrayList<Requests> arr = new ArrayList<Requests>();
        arr = deserializeArray();
        if (arr == null) {
            arr = new ArrayList<Requests>();
        }
//        System.out.println( "may be null " + MainPage.main.current_user);
        Requests myreq = new Requests(date1, from_time, to_time, reason, current_user.getType(), roomNumber);
        myreq.setUser(clgobj.getAllUsersMap().get(current_user.getEmailId()));
//        Admin.addRequest(myreq);
        day--;
        if (!roomNumber.equals("")) {

            for (int j = startIndex; j <= endIndex; j++) {

                if (roomBookings.get(roomNumber) == null || (roomBookings.get(roomNumber).getAvailability())[j][day]) {
                    System.out.println("null wala chalaa");
                    myreq.setStatus("Room Already Boooked");
                    flag = 1;
                }
            }
        }
        if (flag == 0) {

            for (int i = 0; i < arr.size(); i++) {

                if (arr.get(i).getDate().compareTo(date1) == 0
                        && arr.get(i).getRoomNumber().compareToIgnoreCase(roomNumber) == 0
                        && arr.get(i).getStartTime().compareTo(from_time) < 0
                        && arr.get(i).getEndTime().compareTo(from_time) > 0
                        && arr.get(i).getStatus().equals("Approved")) {
                    myreq.setStatus("Room Already Boooked");
                    flag = 1;
                } else if (arr.get(i).getDate().compareTo(date1) == 0
                        && arr.get(i).getRoomNumber().compareToIgnoreCase(roomNumber) == 0
                        && arr.get(i).getStartTime().compareTo(to_time) < 0
                        && arr.get(i).getEndTime().compareTo(to_time) > 0
                        && arr.get(i).getStatus().equals("Approved")) {
                    myreq.setStatus("Room Already Boooked");
                    flag = 1;
                }
            }
        }

//        clgobj.getAllUsersMap().put(MainPage.main.current_user.getEmailId(), MainPage.main.current_user);
//        System.out.println("new created abhishek");
//        ArrayList<Requests> allreq = new ArrayList<Requests>();
//        allreq.add(myreq);
        if (flag == 0) {
            if (roomNumber.equals("")) {
                System.out.println("Room Number khaali");
                for (String key : roomBookings.keySet()) {
                    if (roomBookings.get(key).getCapacity() > requied_capacity) {
                        myreq.setRoomNumber(key);

                        break;
                    }
                }
            }
            if (!current_user.getType().equals("Student")) {

                myreq.setStatus("Approved");
            }
//            arr.add(myreq);
        }
        current_user.getRequests().add(myreq);
//        College.serialize();
        arr.add(myreq);
        System.out.println(myreq);
//        System.out.println("check " + MainPage.clgobj.getAllUsersMap().get("ab").getRequests());
        College.serialize(MainPage.clgobj);

        serializeArray(arr);

//        clgobj.
//        College.serialize(clgobj);
        System.out.println("req 2 = " + current_user.getRequests());
    }

    public void roomAvailability() {


        String roomName = roomsComboBox.getValue();
        boolean[][] availability;
        String startHour = "", endHour = "";
        String startMin = "", endMin = "";
        int k = 0, l = 0;

        TextFlow textFlow = new TextFlow();
        if (roomName.equals(defaultRoom)) {

            for (int i = 0; i < roomArrayList.size(); i++) {
                availability = roomArrayList.get(i).getAvailability();
                requests = roomArrayList.get(i).getBookings();

                Text text1 = new Text(roomArrayList.get(i).getName() + "\n");
                textFlow.getChildren().add(text1);
                textFlow.getChildren().addAll(bookingSlotsTimings(availability,requests));
//                for (int j = 0; j < 24; j++) {
//
//                    if (j == 0 && availability[j][day]) {
//                        startHour = "8";
//                        startMin = "00";
//                        k = 1;
//                    } else if (availability[j][day] && !availability[j - 1][day]) {
//                        if (j % 2 == 1) {
//                            startHour = Integer.toString((j - 1) / 2 + 8);
//                            startMin = "30";
//                        } else {
//                            startHour = Integer.toString((j - 1) / 2 + 8);
//                            startMin = "00";
//                        }
//                        k = 1;
//                    }
//
//                    if (j == 23 && availability[j][day]) {
//                        endHour = "7";
//                        endMin = "30";
//                        l = 1;
//                    } else if (availability[j][day] && availability[j + 1][day]) {
//                        if (j % 2 == 1) {
//                            endHour = Integer.toString((j - 1) / 2 + 8);
//                            endMin = "30";
//
//                        } else {
//                            endHour = Integer.toString((j - 1) / 2 + 8);
//                            endMin = "00";
//
//                        }
//                        l = 1;
//                    }
//
//                    if (k == 1 && l == 1) {
//                        Text text2 = new Text(startHour + ":" + startMin + " - " + endHour + ":" + endMin + ",\n");
//                        textFlow.getChildren().add(text2);
//                        k = 0;
//                        l = 0;
//                    }
//                }
//
//
//
//                for (int j = 0; j < requests.size(); j++) {
//                    if (requests.get(i).getStatus().equals("Approved")) {
//                        if (requests.get(i).getDate().equals(date)) {
//
//                            Text text2 = new Text(requests.get(i).getStartTime() + " - " + requests.get(i).getEndTime() + ",\n");
//                            textFlow.getChildren().add(text2);
//                        }
//                    }
//                }
            }
            listViewBookings.getItems().add(textFlow);
        } else {

//            for(int i=0;i<roomArrayList.size();i++){
//                if(roomArrayList.get(i).getName().equals(roomName)){
//
//
//                    availability = roomArrayList.get(i).getAvailability();
//
//                    textFlow = new TextFlow();
//                    Text text1 = new Text(roomName + "\n");
//                    textFlow.getChildren().add(text1);
//                    for (int j = 0; j < 24; j++) {
//
//                        if (j == 0 && availability[j][day]) {
//                            startHour = "8";
//                            startMin = "00";
//                            k = 1;
//                        } else if (availability[j][day] && !availability[j - 1][day]) {
//                            if (j % 2 == 1) {
//                                startHour = Integer.toString((j - 1) / 2 + 8);
//                                startMin = "30";
//                            } else {
//                                startHour = Integer.toString((j - 1) / 2 + 8);
//                                startMin = "00";
//                            }
//                            k = 1;
//                        }
//
//                        if (j == 23 && availability[j][day]) {
//                            endHour = "7";
//                            endMin = "30";
//                            l = 1;
//                        } else if (availability[j][day] && availability[j + 1][day]) {
//                            if (j % 2 == 1) {
//                                endHour = Integer.toString((j - 1) / 2 + 8);
//                                endMin = "30";
//
//                            } else {
//                                endHour = Integer.toString((j - 1) / 2 + 8);
//                                endMin = "00";
//
//                            }
//                            l = 1;
//                        }
//
//                        if (k == 1 && l == 1) {
//                            Text text2 = new Text(startHour + ":" + startMin + " - " + endHour + ":" + endMin + ",\n");
//                            textFlow.getChildren().add(text2);
//                            k = 0;
//                            l = 0;
//                        }
//                    }
//
//
//                    requests = roomArrayList.get(i).getBookings();
//                    for (int j = 0; j < requests.size(); j++) {
//                        if (requests.get(i).getStatus().equals("Approved")) {
//                            if (requests.get(i).getDate().equals(date)) {
//
//                                Text text2 = new Text(requests.get(i).getStartTime() + " - " + requests.get(i).getEndTime() + ",\n");
//                                textFlow.getChildren().add(text2);
//                            }
//                        }
//                    }
//
//                }
//            }


            Room room = roomHashMap.get(roomName);
            availability = room.getAvailability();
            requests = room.getBookings();

            Text text1 = new Text(roomName + "\n");
            textFlow.getChildren().add(text1);
            textFlow.getChildren().addAll(bookingSlotsTimings(availability,requests));
            listViewBookings.getItems().add(textFlow);

        }
    }

    public TextFlow bookingSlotsTimings(boolean[][] availability, ArrayList<Requests> requests) {

        TextFlow textFlow = new TextFlow();
        String startHour = "", endHour = "";
        String startMin = "", endMin = "";
        int k=0,l=0;

        for (int j = 0; j < 24; j++) {

            if (j == 0 && availability[j][day]) {
                startHour = "8";
                startMin = "00";
                k = 1;
            } else if (availability[j][day] && !availability[j - 1][day]) {
                if (j % 2 == 1) {
                    startHour = Integer.toString((j - 1) / 2 + 8);
                    startMin = "30";
                } else {
                    startHour = Integer.toString((j - 1) / 2 + 8);
                    startMin = "00";
                }
                k = 1;
            }

            if (j == 23 && availability[j][day]) {
                endHour = "7";
                endMin = "30";
                l = 1;
            } else if (availability[j][day] && availability[j + 1][day]) {
                if (j % 2 == 1) {
                    endHour = Integer.toString((j - 1) / 2 + 8);
                    endMin = "30";

                } else {
                    endHour = Integer.toString((j - 1) / 2 + 8);
                    endMin = "00";

                }
                l = 1;
            }

            if (k == 1 && l == 1) {
                Text text2 = new Text(startHour + ":" + startMin + " - " + endHour + ":" + endMin + ",\n");
                textFlow.getChildren().add(text2);
                k = 0;
                l = 0;
            }
        }



        for (int j = 0; j < requests.size(); j++) {
            if (requests.get(j).getStatus().equals("Approved")) {
                if (requests.get(j).getDate().equals(date)) {

                    Text text2 = new Text(requests.get(j).getStartTime() + " - " + requests.get(j).getEndTime() + ",\n");
                    textFlow.getChildren().add(text2);
                }
            }
        }

        return textFlow;
    }


    @Override
    public void initialize(URL locationab, ResourceBundle resources) {

        //TODO get rooms arrayList

        try {
            roomHashMap = Room.deserializeRoom();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

//        date.setStyle("-fx-text-fill: white; -fx-font-size: 16;");
//        timeFrom.setStyle("-fx-text-fill: white;");
//        timeTo.setStyle("-fx-text-fill: white;");
//        Reason.setStyle("-fx-text-fill: white;");
//        method();
//        try {
//            Stage primaryStage = new Stage();
//            BorderPane root = new BorderPane();
//            Scene scene = new Scene(root, 400, 400);
//            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
//
//            DatePickerSkin datePickerSkin = new DatePickerSkin(new DatePicker(LocalDate.now()));
//            Node popupContent = datePickerSkin.getPopupContent();
//
//            root.setCenter(popupContent);
//
//            primaryStage.setScene(scene);
//            primaryStage.show();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        roomsComboBox.getItems().add(defaultRoom);
        for (int i = 0; i < roomArrayList.size(); i++)
            roomsComboBox.getItems().add(roomArrayList.get(i).getName());


        roomsComboBox.setValue(defaultRoom);
        datePicker.setOnAction(event -> {
            day = datePicker.getValue().getDayOfWeek().getValue();
            date = datePicker.getValue().toString();
            roomAvailability();
        });

    }
}

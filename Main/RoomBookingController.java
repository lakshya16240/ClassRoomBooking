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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.HashMap;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
    private String date = "";
    public static final String defaultRoom = "All Rooms";
    private ArrayList<Room> availableRooms;

    boolean checkAvailabilty(Room room, int day, String date1, String from_time, String to_time) throws IOException, ClassNotFoundException {

        int startHour = Integer.parseInt((from_time.split(":"))[0]);
        int startMin = Integer.parseInt((from_time.split(":"))[1]);
        int endHour = Integer.parseInt((to_time.split(":"))[0]);
        int endMin = Integer.parseInt((to_time.split(":"))[1]);
        int startIndex;
        int endIndex;
        String timeSetStart = "";
        String timeSetEnd = "";

        if (startHour > 12) {
            startHour -= 12;
            timeSetStart = "PM";
        } else if (startHour == 0) {
            startHour += 12;
            timeSetStart = "AM";
        } else if (startHour == 12) {
            timeSetStart = "PM";
        } else {
            timeSetStart = "AM";
        }

        if (endHour > 12) {
            endHour -= 12;
            timeSetEnd = "PM";
        } else if (endHour == 0) {
            endHour += 12;
            timeSetEnd = "AM";
        } else if (endHour == 12) {
            timeSetEnd = "PM";
        } else {
            timeSetEnd = "AM";
        }

        if (startHour < 8) {
            startIndex = (12 + startHour - 8) * 2;
        } else {
            startIndex = (startHour - 8) * 2;
        }
        if (startMin >= 30) {
            startIndex++;
        }

        if (endHour <= 8) {
            endIndex = (12 + endHour - 8) * 2 - 1;
        } else {
            endIndex = (endHour - 8) * 2 - 1;
        }

        if (endMin >= 30) {
            endIndex++;
        }

        for (int j = startIndex; j <= endIndex; j++) {

            if (room.getAvailability()[j][day]) {
                return false;
            }
        }

        ArrayList<Requests> arr = new ArrayList<Requests>();
        arr = deserializeArray();
        if (arr == null) {
            arr = new ArrayList<Requests>();
        }
        int flag = 0;
//        if (flag == 0) {4
//        if (req)
        String roomNumber = room.getName();
        for (int i = 0; i < arr.size(); i++) {

            if (arr.get(i).getDate().compareTo(date1) == 0
                    && arr.get(i).getRoomNumber().compareToIgnoreCase(roomNumber) == 0
                    && arr.get(i).getStartTime().compareTo(from_time) < 0
                    && arr.get(i).getEndTime().compareTo(from_time) > 0
                    && arr.get(i).getStatus().equals("Approved")) {
//                    myreq.setStatus("Room Already Boooked");
                flag = 1;
            } else if (arr.get(i).getDate().compareTo(date1) == 0
                    && arr.get(i).getRoomNumber().compareToIgnoreCase(roomNumber) == 0
                    && arr.get(i).getStartTime().compareTo(to_time) < 0
                    && arr.get(i).getEndTime().compareTo(to_time) > 0
                    && arr.get(i).getStatus().equals("Approved")) {
//                    myreq.setStatus("Room Already Boooked");
                flag = 1;
            } else if (arr.get(i).getDate().compareTo(date1) == 0
                    && arr.get(i).getRoomNumber().compareToIgnoreCase(roomNumber) == 0
                    && from_time.compareTo(arr.get(i).getStartTime()) < 0
                    && to_time.compareTo(arr.get(i).getStartTime()) > 0
                    && arr.get(i).getStatus().equals("Approved")) {

//                    myreq.setStatus("Room Already Boooked");
                flag = 1;

            } else if (arr.get(i).getDate().compareTo(date1) == 0
                    && arr.get(i).getRoomNumber().compareToIgnoreCase(roomNumber) == 0
                    && from_time.compareTo(arr.get(i).getEndTime()) < 0
                    && to_time.compareTo(arr.get(i).getEndTime()) > 0
                    && arr.get(i).getStatus().equals("Approved")) {

//                    myreq.setStatus("Room Already Boooked");
                flag = 1;

            }
        }

        if (flag == 0) {
            return true;
        } else {
            return false;
        }

//        clgobj.getAllUsersMap().put(MainPage.main.current_user.getEmailId(), MainPage.main.current_user);
//        System.out.println("new created abhishek");
//        ArrayList<Requests> allreq = new ArrayList<Requests>();
//        allreq.add(myreq);
//        clgobj.
//        College.serialize(clgobj);
//        System.out.println("req 2 = " + current_user.getRequests());
    }

    @FXML
    void makeRequest(ActionEvent event) throws IOException, ClassNotFoundException {

        HashMap<String, Room> roomData = Room.deserializeRoom();

        String timeSetStart = "";
        String timeSetEnd = "";

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
        roomNumber = roomNumber.toUpperCase();
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
        System.out.println("oyeyeyyeyeyeyyeyeye :::   "+ startHour);
        int startIndex;
        int endIndex;

        if (startHour > 12) {
            startHour -= 12;
            timeSetStart = "PM";
        } else if (startHour == 0) {
            startHour += 12;
            timeSetStart = "AM";
        } else if (startHour == 12) {
            timeSetStart = "PM";
        } else {
            timeSetStart = "AM";
        }

        if (endHour > 12) {
            endHour -= 12;
            timeSetEnd = "PM";
        } else if (endHour == 0) {
            endHour += 12;
            timeSetEnd = "AM";
        } else if (endHour == 12) {
            timeSetEnd = "PM";
        } else {
            timeSetEnd = "AM";
        }

        if (startHour < 8) {
            startIndex = (12 + startHour - 8) * 2;
        } else {
            startIndex = (startHour - 8) * 2;
        }
        if (startMin >= 30) {
            startIndex++;
        }

        if (endHour <= 8) {
            endIndex = (12 + endHour - 8) * 2 - 1;
        } else {
            endIndex = (endHour - 8) * 2 - 1;
        }

        if (endMin >= 30) {
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
//        myreq.setUser(clgobj.getAllUsersMap().get(current_user.getEmailId()));
        myreq.setUser(current_user);

        LocalDate input_date = datePicker.getValue();
//        input_date.getT
//        LocalDate today =
        Calendar cal = Calendar.getInstance();
//        Date BookingDate = cal.getTime();
//        if (input_date.compareTo((LocalDate)BookingDate)<0){
//
//        }
        System.out.println("room capacity = " + roomData.get(roomNumber).getCapacity());
        if (!roomNumber.equals("") && requied_capacity >= roomData.get(roomNumber).getCapacity()) {
            myreq.setStatus("Invalid Request");
            flag = 1;   
        }
        if (startHour >= 8 && timeSetStart.equals("PM")) {
            myreq.setStatus("Invalid Request");
            flag = 1;
        } else if (endHour == 8 && endMin != 0 && timeSetEnd.equals("PM")) {
            myreq.setStatus("Invalid Request");
            flag = 1;
        } else if (endHour > 8 && timeSetEnd.equals("PM")) {
            myreq.setStatus("Invalid Request");
            flag = 1;
        } else if (startHour < 8 && timeSetStart.equals("AM")) {
            myreq.setStatus("Invalid Request");
            flag = 1;
        } else if (endHour < 8 && timeSetEnd.equals("AM")) {
            myreq.setStatus("Invalid Request");
            flag = 1;
        } else if (timeSetStart.equals("PM") && timeSetEnd.equals("AM")) {
            myreq.setStatus("Invalid Request");
            flag = 1;
        } else if (startHour > endHour) {
            myreq.setStatus("Invalid Request");
            flag = 1;
        } else if (startHour == endHour && startMin < endMin) {
            myreq.setStatus("Invalid Request");
            flag = 1;
        }

        if (flag == 0) {
//        Admin.addRequest(myreq);
            day--;
            if (!roomNumber.equals("")) {

                for (int j = startIndex; j <= endIndex; j++) {

                    if (roomData.get(roomNumber) == null || (roomData.get(roomNumber).getAvailability())[j][day]) {
                        System.out.println("null wala chalaa");
                        myreq.setStatus("Room Unavailable");
                        flag = 1;

                    }
                }
            }

            if (flag == 0 && !roomNumber.equals("")) {

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
                    } else if (arr.get(i).getDate().compareTo(date1) == 0
                            && arr.get(i).getRoomNumber().compareToIgnoreCase(roomNumber) == 0
                            && from_time.compareTo(arr.get(i).getStartTime()) < 0
                            && to_time.compareTo(arr.get(i).getStartTime()) > 0
                            && arr.get(i).getStatus().equals("Approved")) {

                        myreq.setStatus("Room Already Boooked");
                        flag = 1;

                    } else if (arr.get(i).getDate().compareTo(date1) == 0
                            && arr.get(i).getRoomNumber().compareToIgnoreCase(roomNumber) == 0
                            && from_time.compareTo(arr.get(i).getEndTime()) < 0
                            && to_time.compareTo(arr.get(i).getEndTime()) > 0
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
            if (flag == 0 && roomNumber.equals("")) {
//                if (roomNumber.equals("")) {
                System.out.println("Room Number khaali");
                for (String key : roomData.keySet()) {
                    if (roomData.get(key).getCapacity() > requied_capacity) {
//                            myreq.setRoomNumber(key);
                        boolean b = checkAvailabilty(roomData.get(key), day, date1, from_time, to_time);
                        if (b) {
                            availableRooms.add(roomData.get(key));
                        }
                    }
                }
                if (availableRooms.size()>0){

                myreq.setRoomNumber(availableRooms.get(0).getName());
                }
                else{
                    myreq.setStatus("No room available");
                }

                if (!current_user.getType().equals("Student") && !myreq.getRoomNumber().equals("") ) {
                    myreq.setStatus("Approved");
                }
//            arr.add(myreq);
            }

//        clgobj.
//        College.serialize(clgobj);
            System.out.println("req 2 = " + current_user.getRequests());
        }
        current_user.getRequests().add(myreq);
//        College.serialize();
        arr.add(myreq);
        System.out.println(myreq);
//        System.out.println("check " + MainPage.clgobj.getAllUsersMap().get("ab").getRequests());
        College.serialize(MainPage.clgobj);

        serializeArray(arr);

    }

    public void roomAvailability() {

        String roomName = roomsComboBox.getValue();
        boolean[][] availability;
        String startHour = "", endHour = "";
        String startMin = "", endMin = "";
        int k = 0, l = 0;

        TextFlow textFlow = new TextFlow();
        if (roomName.equals(defaultRoom)) {

            listViewBookings.getItems().clear();
            System.out.println("DEFAULT ROOM");

            for(Map.Entry<String, Room> x: roomHashMap.entrySet())
            {
                textFlow = new TextFlow();
                availability = x.getValue().getAvailability();
                requests = x.getValue().getBookings();

                Text text1 = new Text(x.getKey() + "\n");
                textFlow.getChildren().add(text1);
                textFlow.getChildren().addAll(bookingSlotsTimings(availability, requests));
                listViewBookings.getItems().add(textFlow);
                System.out.println("hashmap running");

            }


//            for (int i = 0; i < roomArrayList.size(); i++) {
//                availability = roomArrayList.get(i).getAvailability();
//                requests = roomArrayList.get(i).getBookings();
//
//                Text text1 = new Text(roomArrayList.get(i).getName() + "\n");
//                textFlow.getChildren().add(text1);
//                textFlow.getChildren().addAll(bookingSlotsTimings(availability, requests));
////                for (int j = 0; j < 24; j++) {
////
////                    if (j == 0 && availability[j][day]) {
////                        startHour = "8";
////                        startMin = "00";
////                        k = 1;
////                    } else if (availability[j][day] && !availability[j - 1][day]) {
////                        if (j % 2 == 1) {
////                            startHour = Integer.toString((j - 1) / 2 + 8);
////                            startMin = "30";
////                        } else {
////                            startHour = Integer.toString((j - 1) / 2 + 8);
////                            startMin = "00";
////                        }
////                        k = 1;
////                    }
////
////                    if (j == 23 && availability[j][day]) {
////                        endHour = "7";
////                        endMin = "30";
////                        l = 1;
////                    } else if (availability[j][day] && availability[j + 1][day]) {
////                        if (j % 2 == 1) {
////                            endHour = Integer.toString((j - 1) / 2 + 8);
////                            endMin = "30";
////
////                        } else {
////                            endHour = Integer.toString((j - 1) / 2 + 8);
////                            endMin = "00";
////
////                        }
////                        l = 1;
////                    }
////
////                    if (k == 1 && l == 1) {
////                        Text text2 = new Text(startHour + ":" + startMin + " - " + endHour + ":" + endMin + ",\n");
////                        textFlow.getChildren().add(text2);
////                        k = 0;
////                        l = 0;
////                    }
////                }
////
////
////
////                for (int j = 0; j < requests.size(); j++) {
////                    if (requests.get(i).getStatus().equals("Approved")) {
////                        if (requests.get(i).getDate().equals(date)) {
////
////                            Text text2 = new Text(requests.get(i).getStartTime() + " - " + requests.get(i).getEndTime() + ",\n");
////                            textFlow.getChildren().add(text2);
////                        }
////                    }
////                }
//                listViewBookings.getItems().add(textFlow);
//
//            }
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
            textFlow.getChildren().addAll(bookingSlotsTimings(availability, requests));
            listViewBookings.getItems().clear();
            listViewBookings.getItems().add(textFlow);

        }
    }

    public TextFlow bookingSlotsTimings(boolean[][] availability, ArrayList<Requests> requests) {

        TextFlow textFlow = new TextFlow();
        String startHour = "", endHour = "";
        String startMin = "", endMin = "";
        int k = 0, l = 0;

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
                    startHour = Integer.toString((j) / 2 + 8);
                    startMin = "00";
                }
                k = 1;
            }

            if (j == 23 && availability[j][day]) {
                endHour = "7";
                endMin = "30";
                l = 1;
            } else if (availability[j][day] && !availability[j + 1][day]) {
                if (j % 2 == 1) {
                    endHour = Integer.toString((j + 1) / 2 + 8);
                    endMin = "00";

                } else {
                    endHour = Integer.toString((j/ 2 + 8));
                    endMin = "30";

                }
                l = 1;
            }

            if (k == 1 && l == 1) {
                Text text2 = new Text(startHour + ":" + startMin + " - " + endHour + ":" + endMin + "\n");
                textFlow.getChildren().add(text2);
                k = 0;
                l = 0;
            }
        }

        for (int j = 0; j < requests.size(); j++) {
            if (requests.get(j).getStatus().equals("Approved")) {
                if (requests.get(j).getDate().equals(date)) {

                    Text text2 = new Text(requests.get(j).getStartTime() + " - " + requests.get(j).getEndTime() + "\n");
                    textFlow.getChildren().add(text2);
                }
            }
        }

        if(textFlow.getChildren().size()==0){
            textFlow.getChildren().add(new Text("No Bookings on this day"));
        }
        return textFlow;
    }

    @Override
    public void initialize(URL locationab, ResourceBundle resources) {

//        datePicker.
        timeFrom.setIs24HourView(true);
        timeTo.setIs24HourView(true);
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
//        for (int i = 0; i < roomArrayList.size(); i++)
//            roomsComboBox.getItems().add(roomArrayList.get(i).getName());
        for(Map.Entry<String, Room> x: roomHashMap.entrySet()){
            roomsComboBox.getItems().add(x.getKey());
        }

        roomsComboBox.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if(!date.equals(""))
                    roomAvailability();
            }
        });

        roomsComboBox.setValue(defaultRoom);
        datePicker.setOnAction(event -> {
            day = datePicker.getValue().getDayOfWeek().getValue();
            day--;
            date = datePicker.getValue().toString();
            roomAvailability();
        });

    }
}

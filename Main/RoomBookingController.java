/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import static Main.Controller.deserializeArray;
import static Main.Controller.serializeArray;
import static Main.MainPage.clgobj;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;
import com.sun.javafx.scene.control.skin.DatePickerSkin;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.HashMap;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author Lenovo
 */
public class RoomBookingController implements Initializable {
//    MainPage main = new MainPage();

    @FXML
    private JFXDatePicker date;

    @FXML
    private JFXTimePicker timeFrom;

    @FXML
    private JFXTimePicker timeTo;

    @FXML
    private TextArea Reason;

    @FXML
    private JFXTextField roomRequest;

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
        String date1 = date.getValue().toString();
        int day = date.getValue().getDayOfWeek().getValue();

//        String requested_date = date1.toString();
        String from_time = timeFrom.getValue().toString();
        String to_time = timeTo.getValue().toString();
        String roomNumber = roomRequest.getText();
        roomNumber = roomNumber.toUpperCase();

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
        Requests myreq = new Requests(date1, from_time, to_time, reason, MainPage.main.current_user.getType(), roomNumber);
        myreq.setUser(clgobj.getAllUsersMap().get(MainPage.main.current_user.getEmailId()));
//        Admin.addRequest(myreq);

        for (int j = startIndex; j <= endIndex; j++) {

            if (roomBookings.get(roomNumber) == null || (roomBookings.get(roomNumber).getAvailability())[j][day]) {
                myreq.setStatus("Invalid Request");
                flag = 1;
            }
        }
        if (flag == 0) {

            for (int i = 0; i < arr.size(); i++) {

                if (arr.get(i).getDate().compareTo(date1) == 0
                        && arr.get(i).getRoomNumber().compareToIgnoreCase(roomNumber) == 0
                        && arr.get(i).getStartTime().compareTo(from_time) < 0
                        && arr.get(i).getEndTime().compareTo(from_time) > 0
                        && arr.get(i).getStatus().equals("Approved")) {
                    myreq.setStatus("Invalid Request");
                    flag = 1;
                } else if (arr.get(i).getDate().compareTo(date1) == 0
                        && arr.get(i).getRoomNumber().compareToIgnoreCase(roomNumber) == 0
                        && arr.get(i).getStartTime().compareTo(to_time) < 0
                        && arr.get(i).getEndTime().compareTo(to_time) > 0
                        && arr.get(i).getStatus().equals("Approved")) {
                    myreq.setStatus("Invalid Request");
                    flag = 1;
                }
            }
        }

        MainPage.main.current_user.getRequests().add(myreq);
//        clgobj.getAllUsersMap().put(MainPage.main.current_user.getEmailId(), MainPage.main.current_user);
//        System.out.println("new created abhishek");
//        ArrayList<Requests> allreq = new ArrayList<Requests>();
//        allreq.add(myreq);
        if (flag == 0) {
            arr.add(myreq);
        }
//        College.serialize();

        System.out.println(myreq);
//        System.out.println("check " + MainPage.clgobj.getAllUsersMap().get("ab").getRequests());
        College.serialize(MainPage.clgobj);

        serializeArray(arr);

//        clgobj.
//        College.serialize(clgobj);
        System.out.println("req 2 = " + MainPage.main.current_user.getRequests());
    }

    @Override
    public void initialize(URL locationab, ResourceBundle resources) {

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
    }
}

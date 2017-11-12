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
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author Lenovo
 */


public class RoomBookingController {
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

        int flag=0;

        System.out.println("req 1 = "  + MainPage.main.current_user.getRequests());
        System.out.println("make request pressed");
//        String date1 = date.getAccessibleText();\\
//        LocalDate localDate = date.getValue();
//        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
//        Date date2 = Date.from(instant);
//        System.out.println("day = " + date2);
        String date1 = date.getValue().toString();
        
//        String requested_date = date1.toString();
        String from_time = timeFrom.getValue().toString();
        String to_time = timeTo.getValue().toString();
        String roomNumber = roomRequest.getText();



        System.out.println("date =  " + date1);
        System.out.println("from_time = " + from_time );
//        String date1 = date.getText();
//        String mnth = month.getText();
//        String year1 = year.getText();
//        from_time = timeFrom.getText();
//        to_time = timeTo.getText();
        String reason = Reason.getText();
//        month.setText(null);
//        date.setText(null);
//        year.setText(null);
//        timeFrom.setText(null);
//        timeTo.setText(null);
        Reason.setText("");
//        Admin.
        ArrayList<Requests> arr = new ArrayList<Requests>();
        arr = deserializeArray();
        if (arr==null){
            arr = new ArrayList<Requests>();
        }
//        System.out.println( "may be null " + MainPage.main.current_user);
        Requests myreq = new Requests(date1, from_time, to_time, reason, MainPage.main.current_user.getType(),roomNumber);
        myreq.setUser(MainPage.main.current_user);
//        Admin.addRequest(myreq);


        for(int i=0;i<arr.size();i++){

            if(arr.get(i).getDate().compareTo(date1)==0 &&
                    arr.get(i).getRoomNumber().compareToIgnoreCase(roomNumber)==0 &&
                    arr.get(i).getStartTime().compareTo(from_time)<0 &&
                    arr.get(i).getEndTime().compareTo(from_time)>0 &&
                    arr.get(i).getStatus().equals("Approved"))
            {
                myreq.setStatus("Invalid Request");
                flag=1;
            }

            else if(arr.get(i).getDate().compareTo(date1)==0 &&
                    arr.get(i).getRoomNumber().compareToIgnoreCase(roomNumber)==0 &&
                    arr.get(i).getStartTime().compareTo(to_time)<0 &&
                    arr.get(i).getEndTime().compareTo(to_time)>0 &&
                    arr.get(i).getStatus().equals("Approved"))
            {
                myreq.setStatus("Invalid Request");
                flag=1;
            }
        }

        MainPage.main.current_user.getRequests().add(myreq);
//        System.out.println("new created abhishek");
//        ArrayList<Requests> allreq = new ArrayList<Requests>();
//        allreq.add(myreq);
        if(flag==0)
            arr.add(myreq);
//        College.serialize();
        System.out.println(myreq);
//        System.out.println("check " + MainPage.clgobj.getAllUsersMap().get("ab").getRequests());
        College.serialize(MainPage.clgobj);
        
        serializeArray(arr);
//        clgobj.
//        College.serialize(clgobj);
        System.out.println("req 2 = "  + MainPage.main.current_user.getRequests());
    }

}

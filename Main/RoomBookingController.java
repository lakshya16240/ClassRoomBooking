/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import static Main.Controller.deserializeArray;
import static Main.Controller.serializeArray;
import java.io.IOException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 *
 * @author Lenovo
 */


public class RoomBookingController {
    
    
    @FXML
    private TextField date;

    @FXML
    private TextField year;

    @FXML
    private TextField month;

    @FXML
    private TextField timeFrom;

    @FXML
    private TextField timeTo;

    @FXML
    private TextArea Reason;
    
    @FXML
    void makeRequest(ActionEvent event) throws IOException, ClassNotFoundException {
        System.out.println("make request pressed");
        String date1 = date.getText();
        String mnth = month.getText();
        String year1 = year.getText();
        String from_time = timeFrom.getText();
        String to_time = timeTo.getText();
        String reason = Reason.getText();
        month.setText(null);
        date.setText(null);
        year.setText(null);
        timeFrom.setText(null);
        timeTo.setText(null);
        Reason.setText(null);
//        Admin.

        ArrayList<Requests> arr = new ArrayList<Requests>();
        arr = deserializeArray();
        Admin.setList(arr);
        Requests myreq = new Requests(date1, from_time, to_time, reason, "Student");
        Admin.addRequest(myreq);
//        System.out.println("new created abhishek");
       
        ArrayList<Requests> allreq = new ArrayList<Requests>();
        allreq.add(myreq);
//        College.serialize();
        System.out.println(myreq);

        serializeArray(arr);
//        clgobj.
    }

}

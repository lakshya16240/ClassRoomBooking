package Main;

import com.jfoenix.controls.JFXDatePicker;
import com.sun.javafx.scene.control.skin.DatePickerSkin;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class TimeTableController implements Initializable{

    @FXML
    private DatePicker datePicker;
    
    public void method(){
        try {
            Stage primaryStage = new Stage();
            BorderPane root = new BorderPane();
            Scene scene = new Scene(root, 400, 400);
//            scene.getStylesheets().add()
//            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

            DatePickerSkin datePickerSkin = new DatePickerSkin(new DatePicker(LocalDate.now()));
            Node popupContent = datePickerSkin.getPopupContent();

            root.setCenter(popupContent);

            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        method();
        //datePicker.show();

    }
}

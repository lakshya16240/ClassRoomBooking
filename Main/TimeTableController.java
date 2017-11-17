package Main;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.skins.JFXDatePickerSkin;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class TimeTableController implements Initializable {

    @FXML
    public ListView<TextFlow> listViewBookings;

    @FXML
    private ListView<TextFlow> listViewCourses;

    @FXML
    private AnchorPane timeTableAnchor;

    @FXML
    private TabPane calendarTab;

    private JFXDatePicker datePicker = new JFXDatePicker(LocalDate.now());
    private int day;
    private List<Course> courseList;
    private ArrayList<Requests> requestsList;

    private void coursesListView(int day) throws IOException {
        //int day = datePicker.getValue().getDayOfWeek().getValue() - 1;
//        MainPage.current_user = (Student) MainPage.current_user;
        System.out.println("day ::::" + day);

        listViewCourses.getItems().clear();
        //List<Course> displayList = new ArrayList<>();
        for (int i = 0; i < courseList.size(); i++) {
            String[] timings = courseList.get(i).getTime();
            String[] rooms = courseList.get(i).getRoom();
            if (!timings[day].equals("-")) {
                TextFlow textFlow = new TextFlow();
                Text text1 = new Text(courseList.get(i).getName() + "\n");
                text1.setStyle("-fx-font-size: 20px");
                Text text2 = new Text(courseList.get(i).getInstructor() + "\n");
                text2.setStyle("-fx-font-size: 15px");
                Text text3 = new Text(timings[day] + "    " + rooms[day]);
                text3.setStyle("-fx-font-size: 15px");
                textFlow.getChildren().addAll(text1, text2, text3);
                //displayList.add(courseList.get(i));

                listViewCourses.getItems().add(textFlow);
            }
        }
//        ObservableList<Course> observableList = FXCollections.observableList(displayList);
//        listViewTable.getItems().addAll(courseList.get(0).getName());
//        listViewTable.setItems(observableList);
    }

    private void bookingsListView(String date) {

        listViewBookings.getItems().clear();
        for (int i = 0; i < requestsList.size(); i++) {
            if (requestsList.get(i).getDate().equals(date) && requestsList.get(i).getStatus().equals("Approved")) {
                TextFlow textFlow = new TextFlow();
                Text text1 = new Text(requestsList.get(i).getReason() + "\n");
                text1.setStyle("-fx-font-size: 20px");
                Text text2 = new Text("From : " + requestsList.get(i).getStartTime() + "\n");
                text2.setStyle("-fx-font-size: 15px");
                Text text3 = new Text("To   : " + requestsList.get(i).getEndTime() + "\n");
                text3.setStyle("-fx-font-size: 15px");
                Text text4 = new Text("Room : " + requestsList.get(i).getRoomNumber());
                text4.setStyle("-fx-font-size: 15px");
                textFlow.getChildren().addAll(text1, text2, text3, text4);

                listViewBookings.getItems().add(textFlow);

            }
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        courseList = ((Student) MainPage.current_user).viewCourses();
        requestsList = MainPage.current_user.getRequests();

        JFXDatePickerSkin datePickerSkin = new JFXDatePickerSkin((datePicker));
        Node popupContent = datePickerSkin.getPopupContent();
        popupContent.setLayoutX(50.0);
        popupContent.setLayoutY(195.0);
        popupContent.setScaleX(1.30);
        popupContent.setScaleY(1.30);
        timeTableAnchor.getChildren().add(popupContent);
        try {
            coursesListView(LocalDate.now().getDayOfWeek().getValue() - 1);
            bookingsListView(LocalDate.now().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        datePicker.setOnAction(event -> {
            day = datePicker.getValue().getDayOfWeek().getValue();
            try {
                coursesListView(day - 1);
                bookingsListView(datePicker.getValue().toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }
}

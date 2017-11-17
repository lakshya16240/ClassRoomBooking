package Main;

import static Main.MainPage.clgobj;
import static Main.MainPage.current_user;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import org.controlsfx.control.textfield.TextFields;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;

public class SearchCoursesController implements Initializable {

    @FXML
    private TableView<Course> searchCoursesTable;

    @FXML
    private ListView<String> listViewPostConditions;

    @FXML
    private TableColumn<Course, String> tc5;
    @FXML

    private TableColumn<Course, String> tc6;
    @FXML
    private TableColumn<Course, String> tc7;

    @FXML
    private TableColumn<Course, Integer> tc8;

    @FXML
    private TableColumn<Course, String> tc9;

    @FXML
    private TextField searchCoursesTextField;

    @FXML
    private AnchorPane searchCoursePane;

    @FXML
    private Button AddCourse;

    @FXML
    void addCourse(ActionEvent event) throws IOException, ClassNotFoundException {
        ObservableList<Course> SelectedCourses = FXCollections.observableArrayList();;
        SelectedCourses = searchCoursesTable.getSelectionModel().getSelectedItems();
//        System.out.println("req = " + ApprovedRequests);

//        System.out.println("dis - approved requests = " + DisApprovedRequests);
//        System.out.println("requests = " + AllRequests);
//        current_user = (Student)current_user;
        for (int i = 0; i < SelectedCourses.size(); i++) {
            ((Student) current_user).addCourses(SelectedCourses.get(i));
        }
        clgobj = College.deserialize("data");
        ArrayList<Course> courseList = clgobj.getAllCourses();
//        List<Course> courseList = MainPage.readCourseCSV();
        //System.out.println("ullahhaaa : " + courseList.size());
        ObservableList<Course> courseObservableList = FXCollections.observableArrayList();
        ArrayList<Course> registeredCourses = ((Student)MainPage.current_user).viewCourses();
//            course.convertToString(courseList);'
        for (int i = 0 ; i<courseList.size() ;i++){
            if(!registeredCourses.contains(courseList.get(i)) && checkTimingsCourses(registeredCourses,courseList.get(i))) {
                courseList.get(i).convertToString(courseList.get(i).getPostConditions());
                courseObservableList.add(courseList.get(i));
            }
        }
        tc5.setCellValueFactory(
                new PropertyValueFactory<Course, String>("name"));
        tc6.setCellValueFactory(
                new PropertyValueFactory<Course, String>("type"));
        tc7.setCellValueFactory(
                new PropertyValueFactory<Course, String>("code"));
        tc8.setCellValueFactory(
                new PropertyValueFactory<Course, Integer>("credits"));

        tc9.setCellValueFactory(
                new PropertyValueFactory<Course, String>("postConditionsString"));

        searchCoursesTable.setItems(courseObservableList);


//        clgobj.getAllUsersMap().put(user.getEmailId(),user);
//        System.out.println("Courses are : " + user.viewCourses() );
        College.serialize(clgobj);
    }

    boolean checkTimingsCourses(ArrayList<Course> courses,Course toBeCheckedCourse){
        for(int i=0;i<courses.size();i++){
            if(!courses.get(i).checkTime(toBeCheckedCourse)){

                return false;
            }
        }
        return true;

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        searchCoursesTable.setSelectionModel(value);
        ArrayList<Course> courseList = new ArrayList<>();
        ArrayList<String> postConditionsList = new ArrayList<>();
        try {
            clgobj = College.deserialize("data");
        } catch (IOException ex) {
            Logger.getLogger(SearchCoursesController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(SearchCoursesController.class.getName()).log(Level.SEVERE, null, ex);
        }
        courseList = clgobj.getAllCourses();
        System.out.println("debugging : " + courseList.size());
        ObservableList<Course> courseObservableList = FXCollections.observableArrayList();
        ArrayList<Course> registeredCourses = ((Student)MainPage.current_user).viewCourses();
        //            course.convertToString(courseList);'
        for (int i = 0 ; i<courseList.size() ;i++){
            if(!registeredCourses.contains(courseList.get(i)) && checkTimingsCourses(registeredCourses,courseList.get(i))) {
                courseList.get(i).convertToString(courseList.get(i).getPostConditions());
                courseObservableList.add(courseList.get(i));
            }
        }
        tc5.setCellValueFactory(
                new PropertyValueFactory<Course, String>("name"));
        tc6.setCellValueFactory(
                new PropertyValueFactory<Course, String>("type"));
        tc7.setCellValueFactory(
                new PropertyValueFactory<Course, String>("code"));
        tc8.setCellValueFactory(
                new PropertyValueFactory<Course, Integer>("credits"));
        tc9.setCellValueFactory(
                new PropertyValueFactory<Course, String>("postConditionsString"));
        searchCoursesTable.setItems(courseObservableList);
        for (int i = 0; i < courseList.size(); i++) {
            
            Course course = courseList.get(i);
            postConditionsList.addAll(course.getPostConditions());
        }
        String[] suggestedPostConditions = new String[postConditionsList.size()];
        for (int i = 0; i < postConditionsList.size(); i++) {
            suggestedPostConditions[i] = postConditionsList.get(i);
            //System.out.println("suggested pc =  " + suggestedPostConditions[i]);
        }

        String postConditionTyped = "";

        List<Course> finalCourseList = courseList;


        searchCoursesTextField.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                ObservableList<Course> courseObservableList = FXCollections.observableArrayList();
                ArrayList<Course> registeredCourses = ((Student)MainPage.current_user).viewCourses();
//                ObservableList<String> postConditionsObservableList = FXCollections.observableArrayList();

                System.out.println("hello ::::::::::::::::::::" + finalCourseList.size());
                for (int i = 0; i < finalCourseList.size(); i++) {
                    //System.out.println("loop :::");
                    Course course = finalCourseList.get(i);
                    //System.out.println(course.getName());
                    ArrayList<String> postConditions = course.getPostConditions();
                    for (int j = 0; j < postConditions.size(); j++) {

                        System.out.println(course.getName() + " " + postConditions.get(j).contains(newValue));
                        if (postConditions.get(j).contains(newValue) && !registeredCourses.contains(course) && checkTimingsCourses(registeredCourses,course) ) {
                            course.convertToString(postConditions);
                            courseObservableList.add(course);
//                            postConditionsObservableList.addAll(postConditions);

                            break;
                        }
                    }

                }

                tc5.setCellValueFactory(
                        new PropertyValueFactory<Course, String>("name"));
                tc6.setCellValueFactory(
                        new PropertyValueFactory<Course, String>("type"));
                tc7.setCellValueFactory(
                        new PropertyValueFactory<Course, String>("code"));
                tc8.setCellValueFactory(
                        new PropertyValueFactory<Course, Integer>("credits"));

                tc9.setCellValueFactory(
                        new PropertyValueFactory<Course, String>("postConditionsString"));
//        new PropertyValueFactory<Course, Integer>("credits"));

                searchCoursesTable.setItems(courseObservableList);
//                listViewPostConditions.setItems(postConditionsObservableList);

            }
        });

    }
}

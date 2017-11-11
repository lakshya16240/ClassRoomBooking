package Main;

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


public class SearchCoursesController implements Initializable{

    @FXML
    private TableView<Course> searchCoursesTable;

    @FXML
    private ListView<String> listViewPostConditions;

    @FXML
    private TableColumn<Course,String> tc5;
    @FXML

    private TableColumn<Course,String> tc6;
    @FXML
    private TableColumn<Course,String> tc7;

    @FXML
    private TableColumn<Course, Integer> tc8;

    @FXML
    private TableColumn<Course, String> tc9;



    @FXML
    private TextField searchCoursesTextField;

    @FXML
    private AnchorPane searchCoursePane;


    @Override
    public void initialize(URL location, ResourceBundle resources) {


        List<Course> courseList = new ArrayList<>();
        ArrayList<String> postConditionsList = new ArrayList<>();
        try {
            courseList = MainPage.readCourseCSV();
            for (int i = 0; i < courseList.size(); i++) {

                Course course = courseList.get(i);
                postConditionsList.addAll(course.getPostConditions());
            }
            String[] suggestedPostConditions = new String[postConditionsList.size()];
            for (int i = 0; i < postConditionsList.size(); i++) {
                suggestedPostConditions[i] = postConditionsList.get(i);
                //System.out.println("suggested pc =  " + suggestedPostConditions[i]);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        String postConditionTyped = "";


        List<Course> finalCourseList = courseList;

        searchCoursesTextField.textProperty().addListener(new ChangeListener<String>() {


            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                ObservableList<Course> courseObservableList = FXCollections.observableArrayList();
//                ObservableList<String> postConditionsObservableList = FXCollections.observableArrayList();

                //System.out.println("hello ::::::::::::::::::::");
                for (int i = 0; i < finalCourseList.size(); i++) {

                    Course course = finalCourseList.get(i);
                    ArrayList<String> postConditions = course.getPostConditions();
                    for(int j=0;j<postConditions.size();j++){

                        if(postConditions.get(j).contains(newValue)){
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




//        List<Course> courseList;
//        ArrayList<String> postConditionsList = new ArrayList<>();
//        try {
//            courseList = MainPage.readCourseCSV();
//            for(int i=0;i<courseList.size();i++){
//
//                Course course = courseList.get(i);
//                postConditionsList.addAll(course.getPostConditions());
//            }
//            String[] suggestedPostConditions = new String[postConditionsList.size()];
//            for(int i=0;i<postConditionsList.size();i++){
//                suggestedPostConditions[i] = postConditionsList.get(i);
//                //System.out.println("suggested pc =  " + suggestedPostConditions[i]);
//            }
//
//            TextFields.bindAutoCompletion(searchCoursesTextField, suggestedPostConditions);
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


//

    }
}

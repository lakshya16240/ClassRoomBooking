package Main;

//import Main.MainPage.clgobj;
import static Main.MainPage.clgobj;
import static Main.MainPage.current_user;
import static Main.MainPage.test_user;
import static Main.initializer.serialize;
import java.io.*;
import java.net.URL;
import java.util.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * The main controller class for fxml files
 *
 * @author Lenovo
 */
public class Controller implements Initializable {

    @FXML
    public ComboBox<String> batchComboBox;

    @FXML
    private TableView<Course> mytable;

    @FXML
    private ComboBox<String> courseComboBox;

    @FXML
    private TextField username;

    @FXML
    private AnchorPane studentPane;

    @FXML
    private AnchorPane facultyPane;

    @FXML
    private AnchorPane adminPane;

    @FXML
    private TextField pass;

    @FXML
    private Button requestbtn;

    @FXML
    private Button LogIn;

    @FXML
    private TableColumn<Course, String> tc1;
    @FXML

    private TableColumn<Course, String> tc2;
    @FXML

    private TableColumn<Course, String> tc3;

    @FXML
    private TableColumn<Course, String> tc4;

    @FXML
    private TextField name;

    @FXML
    private TextField emailId;

    @FXML
    private PasswordField password;

    @FXML
    private ToggleGroup Users;

    @FXML
    private Button SignUp;

    @FXML
    private Label loginlabel;

    @FXML
    private Label signuplabel;

    @FXML
    private TableView tableView;

    @FXML
    private RadioButton studentToggle;

    @FXML
    private RadioButton facultyToggle;

    @FXML
    private RadioButton adminToggle;
//
//    @FXML
//    private TextField date;
//
//    @FXML
//    private TextField year;
//
//    @FXML
//    private TextField month;
//
//    @FXML
//    private TextField timeFrom;
//
//    @FXML
//    private TextField timeTo;
//
//    @FXML
//    private TextArea Reason;

    /**
     * Authenticates sign up of a new user
     *
     * @param event
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @FXML
    void SignUp(ActionEvent event) throws IOException, ClassNotFoundException {
//        College clgobj = deserialize("data");
        int flag = 0;
        clgobj = College.deserialize("data");
        String user_name = name.getText();
        String email = emailId.getText();
        String passwd = password.getText();
        String course = courseComboBox.getValue();
        String batch = batchComboBox.getValue();
        RadioButton selectedRadioButton = (RadioButton) Users.getSelectedToggle();
        String type = "";
        if (selectedRadioButton != null) {
            type = selectedRadioButton.getText();
        }
        System.out.println(type + " " + course);
        if (!type.equals("Student")) {
            if (user_name.equals("") || email.equals("") || passwd.equals("") || type.equals("")) {
                signuplabel.setText("All Fields are Required");
                flag = 1;
            }
        } else {
            if (user_name.equals("") || email.equals("") || passwd.equals("") || course == null || course.equals("Course") || batch == null || batch.equals("Batch") || type.equals("") ){
                signuplabel.setText("All Fields are Required");
                flag = 1;
            }
        }
//        String type = Users.getSelectedToggle().toString();
//        System.out.println("tt= "+ toogleGroupValue);
        name.setText(null);
        emailId.setText(null);
        password.setText(null);
        if (selectedRadioButton != null) {
            selectedRadioButton.setSelected(false);
        }
        courseComboBox.setValue("Course");
        courseComboBox.setDisable(true);
        batchComboBox.setValue("Batch");
        batchComboBox.setDisable(true);
//        System.out.println(u);
        if (flag == 0) {
            int val = clgobj.SignUp(user_name, email, passwd, type, course, batch);
            if (val == 0) {
                signuplabel.setText("User already exists");
            }
            if (val == 2) {
                signuplabel.setText("Invalid E-mail Id");
            } else {
                signuplabel.setText("Sign up succesful");

            }
            System.out.println(type);
            College.serialize(clgobj);
        }
    }

    /**
     * Method to serialize the ArrayList of all requests
     *
     * @param al represents the arrayList of all requests
     * @throws IOException
     */
    public static void serializeArray(ArrayList<Requests> al) throws IOException {
        ObjectOutputStream out = null;
        try {
//			System.out.println(p.getName());x` 
            out = new ObjectOutputStream(new FileOutputStream("./src/" + "list" + ".txt"));
            System.out.println("created");
            out.writeObject(al);
        } finally {
            out.close();
        }
    }

    /**
     * To deserialize the arrayList of booking requests
     *
     * @return the ArrayList obtained after deserializing
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static ArrayList<Requests> deserializeArray() throws IOException, ClassNotFoundException {
        System.out.println("deserializing");
        ObjectInputStream in = null;
        try {

            in = new ObjectInputStream(new FileInputStream("./src/" + "list" + ".txt"));
//            if (in==null){
//                in = new ArrayList<Re>
//            }
            ArrayList<Requests> obj = (ArrayList<Requests>) in.readObject();
            //			in.readObject();
            return obj;
        } finally {
            in.close();
        }
    }

    /**
     * Method to authenticate user login
     */
    @FXML
    void userlogin(ActionEvent event) throws IOException, ClassNotFoundException {
        clgobj = College.deserialize("data");

        String email = username.getText();
        String passwd = pass.getText();
        username.setText(null);
        pass.setText(null);
//        College clgobj = new College();
        System.out.println("hello");

        Parent root2;
//        current_user
        int num = clgobj.Login(email, passwd);

        if (num == 1) {
            loginlabel.setText("successful");
//            loginlabel.setText("hello1");
            User obj = clgobj.getAllUsersMap().get(email);

            if (clgobj.getAllUsersMap().get(email).getType().equals("Student")) {
                current_user = (Student) obj;
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StdntPage.fxml"));
                root2 = (Parent) fxmlLoader.load();
//                root2 = FXMLLoader.load(getClass().getResource("StdntPage.fxml"));
                StudentController newcontroller = fxmlLoader.getController();
//                newcontroller.Test("Super");
                newcontroller.Start((Student) obj);
                current_user = (Student) obj;
                System.out.println("current user has been set to " + current_user);
//                System.out.println("main.current user has been set " + MainPage.main.current_user);
//                test_user = obj ;
//                test_user.setName("Abhi");
//                System.out.println("test name " + clgobj.getAllUsersMap().get(obj.getEmailId()).getName());
//                MainPage main = new MainPage();
            } else if (clgobj.getAllUsersMap().get(email).getType().equals("Admin")) {
//                root2 = FXMLLoader.load(getClass().getResource("AdminPage.fxml"));
                current_user = (Admin) obj;
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AdminPage.fxml"));
                root2 = (Parent) fxmlLoader.load();
//                root2 = FXMLLoader.load(getClass().getResource("StdntPage.fxml"));
                AdminController newcontroller = fxmlLoader.<AdminController>getController();
//                newcontroller.Test("Super");
                newcontroller.Start((Admin) obj);
                current_user = (Admin) obj;

            } else {
                current_user = (Faculty) obj;
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FacultyPage.fxml"));
                root2 = (Parent) fxmlLoader.load();
//                root2 = FXMLLoader.load(getClass().getResource("StdntPage.fxml"));
                FacultyController newcontroller = fxmlLoader.<FacultyController>getController();
//                newcontroller.Test("Super");
                newcontroller.Start((Faculty) obj);
                current_user = (Faculty) obj;
//                root2 = FXMLLoader.load(getClass().getResource("FacultyPage.fxml"));
            }
            System.out.println("now the current user has been set to " + current_user);
            Scene scene = new Scene(root2);
            Stage stage = (Stage) LogIn.getScene().getWindow();
            stage.setScene(scene);

            stage.show();
//            loginlabel.setText("hello");
        } else {
            loginlabel.setText("unsuccessful");
        }
    }

//    @FXML
//    void displayCourses(ActionEvent event) throws IOException{
//        Pane newstudentPane = FXMLLoader.load(getClass().getResource("Table.fxml"));
//        studentPane.getChildren().add(newstudentPane);
//        tableView.getColumns().add(0,"monday");
//    }
    /**
     * Method enabling user to view the TimeTable
     *
     * @param event
     * @throws IOException
     */
    @FXML
    void viewTimeTable(ActionEvent event) throws IOException {
        Pane newstudentPane = FXMLLoader.load(getClass().getResource("TimeTable.fxml"));
        studentPane.getChildren().clear();
        studentPane.getChildren().add(newstudentPane);
    }

//     @FXML
//    void roomBooking(ActionEvent event) throws IOException{
//        Pane newstudentPane = FXMLLoader.load(getClass().getResource("RoomBooking.fxml"));
//        studentPane.getChildren().clear();
//        studentPane.getChildren().add(newstudentPane);
//    }
//    @FXML
//    void makeRequest(ActionEvent event) throws IOException, ClassNotFoundException {
//        System.out.println("make request pressed");
//        String date1 = date.getText();
//        String mnth = month.getText();
//        String year1 = year.getText();
//        String from_time = timeFrom.getText();
//        String to_time = timeTo.getText();
//        String reason = Reason.getText();
//        month.setText(null);
//        date.setText(null);
//        year.setText(null);
//        timeFrom.setText(null);
//        timeTo.setText(null);
//        Reason.setText(null);
////        Admin.
//
//        ArrayList<Requests> arr = new ArrayList<Requests>();
//        arr = deserializeArray();
//        Admin.setList(arr);
//        Requests myreq = new Requests(date1, from_time, to_time, reason, "Student");
//        Admin.addRequest(myreq);
////        System.out.println("new created abhishek");
//
//        ArrayList<Requests> allreq = new ArrayList<Requests>();
//        allreq.add(myreq);
////        College.serialize();
//        System.out.println(myreq);
//
//        serializeArray(arr);
////        clgobj.
//    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        courseComboBox.getItems().addAll("CSE", "ECE", "CSAM");
        batchComboBox.getItems().addAll("1st Year", "2nd Year","3rd Year","4th Year");

        username.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    LogIn.fire();
                }
            }
        });
        pass.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    LogIn.fire();
                }
            }
        });

        password.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    SignUp.fire();
                }
            }
        });

        name.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    SignUp.fire();
                }
            }
        });
        emailId.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    SignUp.fire();
                }
            }
        });

        LogIn.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    LogIn.fire();
                }
            }
        });

        SignUp.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    SignUp.fire();
                }
            }
        });

        studentToggle.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    studentToggle.setSelected(true);
                    courseComboBox.setDisable(false);
                    batchComboBox.setDisable(false);
                }
            }
        });

        facultyToggle.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    facultyToggle.setSelected(true);
                }
            }
        });

        adminToggle.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    adminToggle.setSelected(true);
                }
            }
        });

        studentToggle.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(studentToggle.isSelected()){
                    courseComboBox.setDisable(false);
                    batchComboBox.setDisable(false);
                }

            }
        });

    }

}

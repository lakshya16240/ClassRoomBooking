package Main;

//import Main.MainPage.clgobj;
import static Main.MainPage.clgobj;
import static Main.initializer.serialize;
import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import javafx.stage.Stage;

/**
 *
 * @author Lenovo
 */
public class Controller implements Initializable {
//    @FXML 
//    ToggleGroup Users;
//  

    @FXML
    private TableView<Course> mytable;

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

//    @FXML javafx.scene.control.TableColumn tc1;
//    @FXML javafx.scene.control.TableColumn tc2;
//    @FXML javafx.scene.control.TableColumn tc3;
//    @FXML javafx.scene.control.TableColumn tc4;
//    @FXML
//    private TableColumn<?, ?> tc1;
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
//
//    public static void serialize(College p) throws IOException {
//
//        ObjectOutputStream out = null;
//        try {
////			System.out.println(p.getName());x` 
//            out = new ObjectOutputStream(new FileOutputStream("./src/" + "data" + ".txt"));
//            System.out.println("created");
//            out.writeObject(p);
//        } finally {
//            out.close();
//        }
//    }
//
//    public static College deserialize(String filename) throws IOException, ClassNotFoundException {
//        System.out.println("deserializing");
//        ObjectInputStream in = null;
//        try {
//
//            in = new ObjectInputStream(new FileInputStream("./src/" + filename + ".txt"));
//            College obj = (College) in.readObject();
//            //			in.readObject();
//            return obj;
//        } finally {
//            in.close();
//        }
//    }
//    College clgobj = deserialize("data"); 

//    public static void serializeReq(ArrayList<Requests> req) throws IOException {
//
//        ObjectOutputStream out = null;
//        try {
////			System.out.println(p.getName());` 
//            out = new ObjectOutputStream(new FileOutputStream("./src/" + "requests" + ".txt"));
////			System.out.println("created");
//            out.writeObject(req);
////			out.writeObject(p);
//        } finally {
//            out.close();
//        }
//    }
//
//    public static ArrayList<Requests> deserializeReq(String filename) throws IOException, ClassNotFoundException {
//        System.out.println("deserializing");
//        ObjectInputStream in = null;
//        try {
//            ArrayList<Requests> arr = new ArrayList<Requests>();
//            in = new ObjectInputStream(new FileInputStream("./src/" + filename + ".txt"));
//            arr = (ArrayList<Requests>) in.readObject();
//            //			in.readObject();
//            return arr;
//        } finally {
//            in.close();
//        }
//    }
    @FXML
    void SignUp(ActionEvent event) throws IOException, ClassNotFoundException {
//        College clgobj = deserialize("data");
//        clgobj = College.deserialize("data");
        String user_name = name.getText();
        String email = emailId.getText();
        String passwd = password.getText();
        RadioButton selectedRadioButton = (RadioButton) Users.getSelectedToggle();
        String type = selectedRadioButton.getText();
//        String type = Users.getSelectedToggle().toString();
//        System.out.println("tt= "+ toogleGroupValue);
        name.setText(null);
        emailId.setText(null);
        password.setText(null);
//        System.out.println(u);
        int val = clgobj.SignUp(user_name, email, passwd, type);
        if (val == 0) {
            signuplabel.setText("User already exists");
        } else {
            signuplabel.setText("Sign up succesful");

        }
        System.out.println(type);
        College.serialize(clgobj);
    }

    void serializeArray(ArrayList<Requests> al) throws IOException {
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

    public static ArrayList<Requests> deserializeArray() throws IOException, ClassNotFoundException {
        System.out.println("deserializing");
        ObjectInputStream in = null;
        try {

            in = new ObjectInputStream(new FileInputStream("./src/" + "list" + ".txt"));
            ArrayList<Requests> obj = (ArrayList<Requests>) in.readObject();
            //			in.readObject();
            return obj;
        } finally {
            in.close();
        }
    }

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

        int num = clgobj.Login(email, passwd);
        if (num == 1) {
            loginlabel.setText("successful");
//            loginlabel.setText("hello1");
            User obj = clgobj.getAllUsersMap().get(email);

            if (clgobj.getAllUsersMap().get(email).getType().equals("Student")) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("StdntPage.fxml"));
                root2 = (Parent) fxmlLoader.load();
//                root2 = FXMLLoader.load(getClass().getResource("StdntPage.fxml"));
                StudentController newcontroller = fxmlLoader.<StudentController>getController();
                newcontroller.Test("Super");
                newcontroller.Start((Student) obj);

            } else if (clgobj.getAllUsersMap().get(email).getType().equals("Admin")) {
//                root2 = FXMLLoader.load(getClass().getResource("AdminPage.fxml"));
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AdminPage.fxml"));
                root2 = (Parent) fxmlLoader.load();
//                root2 = FXMLLoader.load(getClass().getResource("StdntPage.fxml"));
                AdminController newcontroller = fxmlLoader.<AdminController>getController();
//                newcontroller.Test("Super");
                newcontroller.Start((Admin) obj);

            } else {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FacultyPage.fxml"));
                root2 = (Parent) fxmlLoader.load();
//                root2 = FXMLLoader.load(getClass().getResource("StdntPage.fxml"));
                FacultyController newcontroller = fxmlLoader.<FacultyController>getController();
//                newcontroller.Test("Super");
                newcontroller.Start((Faculty) obj);

//                root2 = FXMLLoader.load(getClass().getResource("FacultyPage.fxml"));
            }

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
        System.out.println("new created abhishek");
        System.out.println(Admin.getRequests());
        ArrayList<Requests> allreq = new ArrayList<Requests>();

//        allreq = deserializeReq("requests");
        System.out.println("new created naaaaaa");

//        if (allreq==null){
//            System.out.println("new created");
//        }
        allreq.add(myreq);
//        College.serialize();
        System.out.println(myreq);
        System.out.println("inside function");
        System.out.println(Admin.getRequests());
        serialize(clgobj);
        clgobj = College.deserialize("data");
        Admin obj = (Admin) clgobj.getAllUsersMap().get("abhishek16126");
        System.out.println("after deserializing");

        System.out.println(obj.getRequests());
        ArrayList<Integer> myarray = new ArrayList<Integer>();
        myarray.add(5);
        myarray.add(10);
        System.out.println(myarray);
        serializeArray(arr);
//        clgobj.
    }

//    @FXML
//    void myMethod(ActionEvent event) throws IOException{
//        Pane newadminPane = FXMLLoader.load(getClass().getResource("CourseTable.fxml"));
//        adminPane.getChildren().clear();
//        adminPane.getChildren().add(newadminPane);
//        
////        MainPage obj = new MainPage();
////        List<Course> myList = new ArrayList<Course>();
////        myList = obj.readCSV();
////        ObservableList<Course> obsList = FXCollections.observableArrayList();
////        for (int i=0; i<myList.size() ;i++){
////            obsList.add(myList.get(i));
////            System.out.println(myList.get(i) + " VAL");
////            System.out.println(obsList.get(i) + " VAL");
////
////        }
//////        final ObservableList<Course> data = FXCollections.observableArrayList(new Course("a", "b" , "c", "d", 5, null, null, null, null));
////        tc1.setCellValueFactory(
////        new PropertyValueFactory<Course, String>("name"));
////        tc2.setCellValueFactory(
////        new PropertyValueFactory<Course, String>("type"));
////        tc3.setCellValueFactory(
////        new PropertyValueFactory<Course, String>("code"));
////        tc4.setCellValueFactory(
////        new PropertyValueFactory<Course, String>("code"));
//
////        new PropertyValueFactory<Course, Integer>("credits"));
//        
////        mytable.setItems(obsList);
//        System.out.println("ENDDDDD");
//    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

//        List<String[]> courseinfo = new ArrayList<>(); 
//            try {
//                courseinfo = readCSV("pc");
//            } catch (IOException ex) {
//                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        for (int i = 0 ; i<courseinfo.size() ; i++){
//            String[] s = courseinfo.get(i);
//            for (int j=0; j <s.length;j++){
//                System.out.println(s[j]);
//            }
//        }
//        System.out.println("hello22");
    }

//   public static List<Course> readCSV(String fileName) throws IOException{
//       List<Course> courseinfo = new ArrayList<>(); 
//       String pathToFile = "/Users/pc.csv"; 
//       System.out.println(pathToFile + " path ");
//        try (BufferedReader br = new BufferedReader(new FileReader(pathToFile))) {
//            String line = br.readLine();
//            while (line != null) { 
//                String[] attributes = line.split(",");
////                Book book = createBook(attributes); // adding book into ArrayList books.add(book); // read next line before looping // if end of file reached, line would be null line = br.readLine(); } } catch (IOException ioe) { ioe.printStackTrace(); }
//                courseinfo.add(new Course(attributes[0], attributes[1], attributes[2], null, null, null, null));
//                line = br.readLine();
//            }
//        }
//        catch (IOException ioe) { 
//                ioe.printStackTrace(); 
//                } 
//        return courseinfo;
//   }
//Read more: http://www.java67.com/2015/08/how-to-load-data-from-csv-file-in-java.html#ixzz4xAZp1hki
}

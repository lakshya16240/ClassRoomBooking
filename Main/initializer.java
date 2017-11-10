/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

//import static Main.MainPage.clgobj;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Lenovo
 */
public class initializer {
    private static HashMap<String, Room> roomBookings;

    public static void addBookings(String[] timeAndVenue, int i) throws IOException {                      // i REPRESENTS DAY

        Room room;
        String[] time = timeAndVenue[0].split("-");
        System.out.println("TIMEEEEEEE ::::: " + timeAndVenue[0]);
        String startTime = time[0];
        String endTime = time[1];
        int startHour = Integer.parseInt((startTime.split(":"))[0]);
        int startMin = Integer.parseInt((startTime.split(":"))[1]);
        int endHour = Integer.parseInt((endTime.split(":"))[0]);
        int endMin = Integer.parseInt((endTime.split(":"))[1]);
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

        if (roomBookings == null) {
            roomBookings = new HashMap<>();
        }
        if (roomBookings.get(timeAndVenue[1]) == null) {

            room = new Room(timeAndVenue[1], 50);

            for (int j = startIndex; j <= endIndex; j++) {
                (room.getAvailability())[j][i] = true;
            }
        } else {
            room = roomBookings.get(timeAndVenue[1]);
            for (int j = startIndex; j <= endIndex; j++) {
                (room.getAvailability())[j][i] = true;
            }
        }
        roomBookings.put(timeAndVenue[1], room);
        Room.serializeRoom(roomBookings);
    }
    
    
//    public static void serializeRoom(HashMap<String, Room> rb) throws IOException {
//
//        ObjectOutputStream out = null;
//        try {
////			System.out.println(p.getName());x` 
//            out = new ObjectOutputStream(new FileOutputStream("./src/" + "RoomData" + ".txt"));
////			System.out.println("created");
//            out.writeObject(rb);
//        } finally {
//            out.close();
//        }
//    }
    
    public static void serialize(College p) throws IOException {

        ObjectOutputStream out = null;
        try {
//			System.out.println(p.getName());x` 
            out = new ObjectOutputStream(new FileOutputStream("./src/" + "data" + ".txt"));
//			System.out.println("created");
            out.writeObject(p);
        } finally {
            out.close();
        }
    }

    public static void serializeReq(ArrayList<Requests> p) throws IOException {

        ObjectOutputStream out = null;
        try {
//			System.out.println(p.getName());x` 
            out = new ObjectOutputStream(new FileOutputStream("./src/" + "requests" + ".txt"));
//			System.out.println("created");
            out.writeObject(p);
        } finally {
            out.close();
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        College clgobj = new College();
//        clgobj = College.deserialize("data");
//        serializeReq(null);
        User admin1 = new Admin("Abhishek", "abhishek16126", "abhi", "Admin");
        User admin2 = new Admin("Lakshya", "lakshya16240", "chowki", "Admin");
        clgobj.getAllUsers().add(admin1);
        clgobj.getAllUsers().add(admin2);
        clgobj.getAllUsersMap().put("abhishek16126", admin1);
        clgobj.getAllUsersMap().put("lakshya16240", admin2);
        clgobj.getCredentials().put("abhishek16126", "abhi");
        clgobj.getCredentials().put("lakshya16240", "chowki");
        System.out.println("added wadded");
//        System.out.println(admin1.getType());
        System.out.println("hello");

        serialize(clgobj);
    }
}

package Main;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class to store information about a romm
 */

public class Room implements Serializable {

    private String name;
    private int capacity;
    private boolean[][] availability;
    private ArrayList<Requests> bookings;

    public Room(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        availability = new boolean[24][7];
//        this.bookings = bookings;
        this.bookings = new ArrayList<Requests>();
    }

    /**
     * To serialize an object of Class Room
     * @param rb hashmap to be serialized
     * @throws IOException
     */

    public static void serializeRoom(HashMap<String, Room> rb) throws IOException {

        ObjectOutputStream out = null;
        try {
//			System.out.println(p.getName());x` 
            out = new ObjectOutputStream(new FileOutputStream("./src/" + "RoomData" + ".txt"));
//			System.out.println("created");
            out.writeObject(rb);
        } finally {
            out.close();
        }
    }

    /**
     * To deserialize an object of Class Room
     * @return a hashmap of key type 'String' , value type 'Room'
     * @throws IOException
     * @throws ClassNotFoundException
     */

    public static HashMap<String, Room> deserializeRoom() throws IOException, ClassNotFoundException {
        System.out.println("deserializing");
        ObjectInputStream in = null;
        HashMap<String, Room> rb;
        try {

            in = new ObjectInputStream(new FileInputStream("./src/" + "RoomData" + ".txt"));
            rb = (HashMap<String, Room>) in.readObject();
            //			in.readObject();
            return rb;
        } finally {
            in.close();
        }
    }
    
    public  void printAvailability(){
        for (int i= 0 ; i < 7 ; i++){
            for (int j=0;j<24;j++){
                System.out.print(getAvailability()[j][i]+ " " );
            }
            System.out.println();
        }
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }
    public boolean[][] getAvailability() {
        return availability;
    }

    public ArrayList<Requests> getBookings() {
        return bookings;
    }

    public boolean IsAvailable(String time) {
        return true;
    }
}

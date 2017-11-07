package Main;


import java.io.Serializable;
import java.util.ArrayList;

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

    public boolean[][] getAvailability() {
        return availability;
    }

    public ArrayList<Requests> getBookings() {
        return bookings;
    }

    public boolean IsAvailable(String time){
        return true;
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import static Main.MainPage.clgobj;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public class initializer {
    	public static void serialize(College p) throws IOException{

		ObjectOutputStream out = null;
		try {
//			System.out.println(p.getName());x` 
			out = new ObjectOutputStream(new FileOutputStream("./src/" + "data" + ".txt"));
//			System.out.println("created");
			out.writeObject(p);
		}
		finally {
			out.close();
		}
	}   
        
        
	public static void serializeReq(ArrayList<Requests> p) throws IOException{

		ObjectOutputStream out = null;
		try {
//			System.out.println(p.getName());x` 
			out = new ObjectOutputStream(new FileOutputStream("./src/" + "requests" + ".txt"));
//			System.out.println("created");
			out.writeObject(p);
		}
		finally {
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
        clgobj.getAllUsersMap().put("abhishek16126",admin1);
        clgobj.getAllUsersMap().put("lakshya16240",admin2);
        clgobj.getCredentials().put("abhishek16126", "abhi");
        clgobj.getCredentials().put("lakshya16240", "chowki");
        System.out.println("added wadded");
//        System.out.println(admin1.getType());
        System.out.println("hello");

        serialize(clgobj);
    }
}

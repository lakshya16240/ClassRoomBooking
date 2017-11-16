/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.io.Serializable;

/**
 *
 * @author Lenovo
 */
public class FriendRequest implements Serializable{
    Student sender;
    public FriendRequest(Student s){
        sender = s;
    }

    public Student getSender() {
        return sender;
 
    }
    
//    @ 
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.io.Serializable;

/**
 *A class which stores the sender, receiver and status of the friend request
 * @author Lenovo
 */
public class FriendRequest implements Serializable{
    Student sender , receiver;
    String status = "";

    public FriendRequest(Student sender, Student receiver){
        this.sender = sender;
        this.receiver = receiver;
    }

    public Student getSender() {
        return sender;
 
    }

    public Student getReceiver() {
        return receiver;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object obj) {
        return ((FriendRequest)obj).getSender().equals(sender);
    }

    //    @
}
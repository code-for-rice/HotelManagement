package controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dell
 */
public class Hotel {
    private String id;
    private String name;
    private int roomAvailable;
    private String address;
    private String phone;
    private String rating;

    public Hotel() {
    }

    public Hotel(String id, String name, int roomAvailable, String address, String phone, String rating) {
        this.id = id;
        this.name = name;
        this.roomAvailable = roomAvailable;
        this.address = address;
        this.phone = phone;
        this.rating = rating;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRoomAvailable() {
        return roomAvailable;
    }

    public void setRoomAvailable(int roomAvailable) {
        this.roomAvailable = roomAvailable;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %d, %s, %s, %s", id, name, roomAvailable, address, phone, rating) ;
    }

    public String display(){
        return String.format("%3s|%-20s|%8d|%-6s|%-70s|%-12s|%-7s", id, name, roomAvailable, " ", address, phone, rating);
    }
}

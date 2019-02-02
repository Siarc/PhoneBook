package com.example.aminu.phonebook.Models;

public class PhoneBooks {

    private int id;
    private String name;
    private double number;
    private String email;
    private String address;
    private String note;

    public PhoneBooks(int id, String name, double number, String email, String address, String note) {
        this.id = id;
        this.name = name;
        this.number = number;
        this.email = email;
        this.address = address;
        this.note = note;
    }

    public PhoneBooks(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getNumber() {
        return number;
    }

    public void setNumber(double number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "PhoneBooks{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", number=" + number +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}

package com.example.aminu.phonebook.Models;

/**
 * Created by aminu on 10/16/2017.
 */

public class PhoneBook {

    private int id;
    private String contact_name;
    private String contact_number;

    public PhoneBook(int id, String contact_name, String contact_number) {
        this.id = id;
        this.contact_name = contact_name;
        this.contact_number = contact_number;
    }

    public PhoneBook(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContact_name() {
        return contact_name;
    }

    public void setContact_name(String contact_name) {
        this.contact_name = contact_name;
    }

    public String getContact_number() {
        return contact_number;
    }

    public void setContact_number(String contact_number) {
        this.contact_number = contact_number;
    }

    @Override
    public String toString() {
        return "PhoneBook{" +
                "id=" + id +
                ", contact_name='" + contact_name + '\'' +
                ", contact_number=" + contact_number +
                '}';
    }
}

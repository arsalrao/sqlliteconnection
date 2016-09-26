package com.boost.entertainment.sqliteapplication;

/**
 * Created by waqas on 18/09/2016.
 */
public class Contact {

    private String name ;

    private int id ;
    private String email ;
    private String address ;

    public Contact(String name, String email, String address) {
        this.name = name;
        this.email = email;
        this.address = address;
    }
        //default constructor
    public Contact(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}

package com.example.GAURAV.myapplication.jokebackend;

/** The object model for the data we are sending through endpoints */
public class MyBean {

    private String myData;

    MyBean(){
        myData = "TEST";
    }

    public String getData() {
        return myData;
    }

    public void setData(String data) {
        myData = data;
    }
}
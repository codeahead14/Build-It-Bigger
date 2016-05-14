package com.example.GAURAV.myapplication.jokebackend;

import com.example.JokeLib;

import sun.rmi.runtime.Log;

/** The object model for the data we are sending through endpoints */
public class MyBean {
    private String myData;

    public String getData() {
        return myData;
    }

    public void setData(String data) {
        myData = data;
    }
}
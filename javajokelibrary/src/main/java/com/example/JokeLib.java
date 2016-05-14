package com.example;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;

import javafx.util.Pair;
import sun.rmi.runtime.Log;

public class JokeLib {
    private final static Logger logger = Logger.getLogger(JokeLib.class.getName());

    public static String getJoke(){
        logger.setLevel(Level.INFO);
        logger.info("You are a masterpiece!");
        return "You are a masterPiece!!";
    }
}


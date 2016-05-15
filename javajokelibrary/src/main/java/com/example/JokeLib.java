package com.example;

import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;

import javafx.util.Pair;
import sun.rmi.runtime.Log;

public class JokeLib {
    private final static Logger logger = Logger.getLogger(JokeLib.class.getName());
    private String[] jokes = {"Why do cows wear bells? Because their horns don't work",
                            "Why did the bald man paint rabbits on his head?  Because from a distance they looked like hares.",
                            "Last night a man fell into a barrel of beer and drowned - he came to a bitter end.",
                            "A Funny British Pub Name:  The Quiet Woman"};

    private Random random;

    public JokeLib(){
        random = new Random();
    }

    public String getJoke(){
        return jokes[random.nextInt(4)];
    }
}


package com.example.dell.test.Game;

import java.sql.Timestamp;

/**
 * Created by DELL on 2017/12/7.
 */

public class Game {

    private String name;
    private String address;
    private int start;
    private int end;

    public Game(String name, String address, int start, int end){
        this.name = name;
        this.address = address;
        this.start = start;
        this.end = end;
    }

    public String getName(){
        return name;
    }

    public String getAddress(){
        return address;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
}

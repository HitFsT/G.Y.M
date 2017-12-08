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
    private boolean selected;

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setStart(int start) {

        this.start = start;
    }


    public void setEnd(int end) {
        this.end = end;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
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

    public boolean isSelected() {
        return selected;
    }
}

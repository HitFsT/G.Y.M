package com.example.dell.test.Game;

import java.sql.Timestamp;

/**
 * Created by DELL on 2017/12/7.
 */

public class Game {

    private String name;
    private String address;
    private String start;
    private String end;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    private int user_id;
    private int game_id;
    private boolean selected;

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setStart(String start) {

        this.start = start;
    }


    public void setEnd(String end) {
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

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }

    public boolean isSelected() {
        return selected;
    }
}

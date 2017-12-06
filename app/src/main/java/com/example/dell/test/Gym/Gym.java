package com.example.dell.test.Gym;

/**
 * Created by DELL on 2017/12/6.
 */

public class Gym {
    private String name;
    private String address;
    private int imageID;

    public Gym(String name, String address, int imageID){
        this.name = name;
        this.address = address;
        this.imageID = imageID;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getImageID() {
        return imageID;
    }
}

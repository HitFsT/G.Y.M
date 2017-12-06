package com.example.dell.test.Gym;

import android.graphics.Bitmap;

/**
 * Created by DELL on 2017/12/6.
 */

public class Gym {
    private String name;
    private String address;
    private Bitmap image;

    public Gym(String name, String address, Bitmap image){
        this.name = name;
        this.address = address;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public Bitmap getImage() {
        return image;
    }
}

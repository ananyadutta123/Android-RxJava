package com.example.rxjava;

import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


public class Item {
    //list of items for each element
    int id;

    public static final String ITEM_TAG = "Item";


    public Item( int id){

        this.id = id;
    }
    public void handle() throws InterruptedException {

        Log.i(ITEM_TAG,"Inside handle of Item");
        //Toast.makeText(this,"Inside handle of item",Toast.LENGTH_SHORT).show();
        Random random = new Random();
        int min = 10;
        int max = 30;
        int randomNum = random.nextInt((max - min) + 1) + min;
        Log.i(ITEM_TAG,id +"writing for " + randomNum + "Seconds");
        Thread.sleep(randomNum);
        Log.i(ITEM_TAG,id + "Processed Item");
    }
}

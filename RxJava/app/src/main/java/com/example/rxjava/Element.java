package com.example.rxjava;

import java.util.ArrayList;
import java.util.List;

public class Element{

    public int getId() {
        return id;
    }

    public List<Item> getItems() {
        return items;
    }

    private int id;

    private List<Item> items;

    public Element(int id, List<Item> items){
        this.id = id;
//        items = new ArrayList<>();
        this.items = items;
    }



}

package com.example.rxandroidexample2.data.model;

public class Note {
    private int mId;
    private String node;

    public Note() {
    }

    public Note(int id, String node) {
        mId = id;
        this.node = node;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }
}

package com.example.progressChecker.teachOwnWay;



public class Image {
    private int id;
    private String name;
    private String desc;
    private byte[] image;

    public Image(String name, String desc, byte[] image, int id) {
        this.name = name;
        this.desc = desc;
        this.image = image;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}

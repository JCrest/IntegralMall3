package com.csii.integralmall.bean;

public class ActBean {
    private int image;
    private int title;
    private int describe;

    public ActBean(int image, int title, int describe) {
        this.image = image;
        this.title = title;
        this.describe = describe;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public int getDescribe() {
        return describe;
    }

    public void setDescribe(int describe) {
        this.describe = describe;
    }

    @Override
    public String toString() {
        return "ActBean{" +
                "image=" + image +
                ", title=" + title +
                ", describe=" + describe +
                '}';
    }
}

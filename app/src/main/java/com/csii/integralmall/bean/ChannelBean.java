package com.csii.integralmall.bean;

public class ChannelBean {
    private int imageView;
    private int channelText;

    public ChannelBean(int imageView, int channelText) {
        this.imageView = imageView;
        this.channelText = channelText;
    }

    public int getImageView() {
        return imageView;
    }

    public void setImageView(int imageView) {
        this.imageView = imageView;
    }

    public int getChannelText() {
        return channelText;
    }

    public void setChannelText(int channelText) {
        this.channelText = channelText;
    }

    @Override
    public String toString() {
        return "ChannelBean{" +
                "imageView=" + imageView +
                ", channelText=" + channelText +
                '}';
    }
}

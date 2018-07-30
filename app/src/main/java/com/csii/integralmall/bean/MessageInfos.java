package com.csii.integralmall.bean;

public class MessageInfos {
    private String messageTitle;
    private String messageDes;
    private String messageData;
    private int messageImage;

    public MessageInfos(String messageTitle, String messageDes, String messageData, int messageImage) {
        this.messageTitle = messageTitle;
        this.messageDes = messageDes;
        this.messageData = messageData;
        this.messageImage = messageImage;
    }

    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }

    public String getMessageDes() {
        return messageDes;
    }

    public void setMessageDes(String messageDes) {
        this.messageDes = messageDes;
    }

    public String getMessageData() {
        return messageData;
    }

    public void setMessageData(String messageData) {
        this.messageData = messageData;
    }


    public int getMessageImage() {
        return messageImage;
    }

    public void setMessageImage(int messageImage) {
        this.messageImage = messageImage;
    }

    @Override
    public String toString() {
        return "MessageInfos{" +
                "messageTitle='" + messageTitle + '\'' +
                ", messageDes='" + messageDes + '\'' +
                ", messageData='" + messageData + '\'' +
                ", messageImage=" + messageImage +
                '}';
    }
}

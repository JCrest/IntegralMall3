package com.csii.integralmall.bean;

public class EarnIntegralBean {
    private int icon;
    private String title;
    private String desc;

    public EarnIntegralBean(int icon, String title, String desc) {
        this.icon = icon;
        this.title = title;
        this.desc = desc;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "EarnIntegralBean{" +
                "icon=" + icon +
                ", title=" + title +
                ", desc=" + desc +
                '}';
    }
}

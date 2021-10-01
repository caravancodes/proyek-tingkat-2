package com.example.jasontood.halloffameindo;

/**
 * Created by Jason Tood on 2/23/2018.
 */

public class MenuItem {
    private  String text;
    private int  icon;

    public MenuItem(String text, int icon) {
        this.text = text;
        this.icon = icon;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}

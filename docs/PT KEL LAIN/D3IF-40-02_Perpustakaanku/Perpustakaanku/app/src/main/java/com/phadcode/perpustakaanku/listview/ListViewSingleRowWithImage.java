package com.phadcode.perpustakaanku.listview;

/**
 * Created by Hudio Hizari on 4/21/2018.
 */

public class ListViewSingleRowWithImage {
    private static final int NO_IMAGE_PROVIDED = -1;
    private String ID, Text;
    private int imageResourceID = NO_IMAGE_PROVIDED;

    public ListViewSingleRowWithImage(String ID, String Text, int imageResourceID) {
        this.ID = ID;
        this.Text = Text;
        this.imageResourceID = imageResourceID;
    }

    public String getID() {
        return ID;
    }
    public String getText() {
        return Text;
    }
    public int getImageResourceID() {
        return imageResourceID;
    }
    public boolean isImageProvided(){
        return !(imageResourceID == NO_IMAGE_PROVIDED);
    }
}

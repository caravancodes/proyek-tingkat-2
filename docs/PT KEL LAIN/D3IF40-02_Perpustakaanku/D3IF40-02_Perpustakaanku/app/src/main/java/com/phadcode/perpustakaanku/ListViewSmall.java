package com.phadcode.perpustakaanku;

public class ListViewSmall {
    private static final int NO_IMAGE_PROVIDED = -1;
    private String Name, Desc;
    private int imageResourceID = NO_IMAGE_PROVIDED;

    public ListViewSmall(String Name, String Desc, int imageResourceID) {
        this.Name = Name;
        this.Desc = Desc;
        this.imageResourceID = imageResourceID;
    }

    public int getImageResourceID() {
        return imageResourceID;
    }
    public String getName() {
        return Name;
    }
    public String getDesc() {
        return Desc;
    }
    public boolean isImageProvided(){
        return !(imageResourceID == NO_IMAGE_PROVIDED);
    }
}

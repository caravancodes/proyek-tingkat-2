package com.phadcode.perpustakaanku.listview;

public class ListViewSmall {
    private static final int NO_IMAGE_PROVIDED = -1;
    private String ID, Name, Desc;
    private int imageResourceID = NO_IMAGE_PROVIDED;

    public ListViewSmall(String ID, String Name, String Desc, int imageResourceID) {
        this.ID = ID;

        this.Name = Name;
        this.Desc = Desc;
        this.imageResourceID = imageResourceID;
    }

    public String getID() {
        return ID;
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

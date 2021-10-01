package org.d3ifcool.counterpulsa;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by AKU CINTA DIA on 2/25/2018.
 */

public class ControllerBeranda implements Serializable{

    // DECLARATION VARIABLE
    private ArrayList<BerandaClass> berandaClasses;

    // CONSTRUCTOR
    public ControllerBeranda() {
        this.berandaClasses = new ArrayList<>();
        this.addBeranda(new BerandaClass(1, "Title Example 1", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."));
        this.addBeranda(new BerandaClass(2, "Title Example 2", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book."));
    }

    // GETTER AND SETTER METHO
    public ArrayList<BerandaClass> getBerandaClasses() {
        return berandaClasses;
    }

    public boolean addBeranda(BerandaClass data){
        this.berandaClasses.add(data);
        return true;
    }

    public void setBerandaClasses(ArrayList<BerandaClass> berandaClasses) {
        this.berandaClasses = berandaClasses;
    }
}

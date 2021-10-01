package org.d3ifcool.counterpulsa;

import java.io.Serializable;

/**
 * Created by AKU CINTA DIA on 2/25/2018.
 */

public class BerandaClass implements Serializable {

    // DECLARATION VARIABLE
    private int id_beranda;
    private String beranda_title, beranda_description;

    // CONSTRUCTOR
    public BerandaClass(int id_beranda, String beranda_title, String beranda_description) {
        this.id_beranda = id_beranda;
        this.beranda_title = beranda_title;
        this.beranda_description = beranda_description;
    }

    // GETTER AND SETTER METHOD
    public int getId_beranda() {
        return id_beranda;
    }

    public void setId_beranda(int id_beranda) {
        this.id_beranda = id_beranda;
    }

    public String getBeranda_title() {
        return beranda_title;
    }

    public void setBeranda_title(String beranda_title) {
        this.beranda_title = beranda_title;
    }

    public String getBeranda_description() {
        return beranda_description;
    }

    public void setBeranda_description(String beranda_description) {
        this.beranda_description = beranda_description;
    }
}

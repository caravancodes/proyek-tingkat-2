package org.d3ifcool.counterpulsa;

import java.io.Serializable;

/**
 * Created by AKU CINTA DIA on 2/25/2018.
 */

public class ServiceClass implements Serializable{

    // DECLARATION VARIABLE
    private int id_service, harga_service, status_service;
    private String nama_service;

    // CONSTRUCTOR
    public ServiceClass(int id_service, int harga_service, int status_service, String nama_service) {
        this.id_service = id_service;
        this.harga_service = harga_service;
        this.status_service = status_service;
        this.nama_service = nama_service;
    }

    // GETTER AND SETTER METHOD
    public int getId_service() {
        return id_service;
    }

    public void setId_service(int id_service) {
        this.id_service = id_service;
    }

    public int getHarga_service() {
        return harga_service;
    }

    public void setHarga_service(int harga_service) {
        this.harga_service = harga_service;
    }

    public int getStatus_service() {
        return status_service;
    }

    public void setStatus_service(int status_service) {
        this.status_service = status_service;
    }

    public String getNama_service() {
        return nama_service;
    }

    public void setNama_service(String nama_service) {
        this.nama_service = nama_service;
    }
}

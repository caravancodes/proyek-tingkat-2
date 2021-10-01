package com.phadcode.perpustakaanku.object;

import java.io.Serializable;

/**
 * Created by Hudio Hizari on 4/17/2018.
 */

public class PenggunaObject implements Serializable {
    private String email, phoneNumber, profilPicture, backgroundPicture, name, password, dot, gender, address;

    public PenggunaObject(){}

    public PenggunaObject(String email, String phoneNumber, String profilPicture, String backgroundPicture, String name, String password, String dot, String gender, String address) {
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.profilPicture = profilPicture;
        this.backgroundPicture = backgroundPicture;
        this.name = name;
        this.password = password;
        this.dot = dot;
        this.gender = gender;
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getProfilPicture() {
        return profilPicture;
    }

    public String getBackgroundPicture() {
        return backgroundPicture;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getDot() {
        return dot;
    }

    public String getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "PenggunaObject{" +
                "email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", profilPicture='" + profilPicture + '\'' +
                ", backgroundPicture='" + backgroundPicture + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", dot='" + dot + '\'' +
                ", gender='" + gender + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}

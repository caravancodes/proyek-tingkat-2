package com.phadcode.perpustakaanku.object;

import java.io.Serializable;

/**
 * Created by Hudio Hizari on 4/17/2018.
 */

public class PerpusObject implements Serializable {
    private String id, email, phoneNumber, profilPicture, backgroundPicture, name, gps, address, owner, admin, members, pendingMembers, bookCategory, status;

    public PerpusObject() {}

    public PerpusObject(String id, String email, String phoneNumber, String profilPicture, String backgroundPicture, String name, String gps, String address, String owner, String admin, String members, String pendingMembers, String bookCategory, String status) {
        this.id = id;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.profilPicture = profilPicture;
        this.backgroundPicture = backgroundPicture;
        this.name = name;
        this.gps = gps;
        this.address = address;
        this.owner = owner;
        this.admin = admin;
        this.members = members;
        this.pendingMembers = pendingMembers;
        this.bookCategory = bookCategory;
        this.status = status;
    }

    public String getId() {
        return id;
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

    public String getGps() {
        return gps;
    }

    public String getAddress() {
        return address;
    }

    public String getOwner() {
        return owner;
    }

    public String getAdmin() {
        return admin;
    }

    public String getMembers() {
        return members;
    }

    public String getPendingMembers() {
        return pendingMembers;
    }

    public String getBookCategory() {
        return bookCategory;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "PerpusObject{" +
                "ID='" + id + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", profilPicture='" + profilPicture + '\'' +
                ", backgroundPicture='" + backgroundPicture + '\'' +
                ", name='" + name + '\'' +
                ", gps='" + gps + '\'' +
                ", address='" + address + '\'' +
                ", owner='" + owner + '\'' +
                ", admin='" + admin + '\'' +
                ", members='" + members + '\'' +
                ", pendingMembers='" + pendingMembers + '\'' +
                ", bookCategory='" + bookCategory + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

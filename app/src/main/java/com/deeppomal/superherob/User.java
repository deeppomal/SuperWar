package com.deeppomal.superherob;

import java.util.HashMap;

public class User {

    private String fullName;
    private String photo;
    private String email;
    private int balance;
    private int totalBattles;
    private HashMap<String,Object> timestampJoined;
    public User() {
    }

    public User(String mFullName, String mPhoto, String mEmail,int balance, int totalBattles,HashMap<String, Object> timestampJoined) {
        this.fullName = mFullName;
        this.photo = mPhoto;
        this.email = mEmail;
        this.timestampJoined = timestampJoined;
        this.balance=balance;
        this.totalBattles=totalBattles;


    }

    public String getFullName() {
        return fullName;
    }

    public String getPhoto() {
        return photo;
    }

    public String getEmail() {
        return email;
    }

    public HashMap<String, Object> getTimestampJoined() {
        return timestampJoined;
    }

    public int getBalance() { return balance; }

    public int getTotalBattles() {
        return totalBattles;
    }

    public void setTotalBattles(int totalBattles) {
        this.totalBattles = totalBattles;
    }

    public void setBalance(int balance)
    {
        this.balance=balance;
    }
}
package com.example.stuntingapps;

import com.google.gson.annotations.SerializedName;

public class ObjectIbu {

    //@SerializedName("ibu_id")
    private int ibu_id;
    //@SerializedName("user_id")
    private int user_id;
    //@SerializedName("nama_ibu")
    private String nama_ibu;

    public ObjectIbu(int ibu_id, int user_id, String nama_ibu) {
        this.ibu_id = ibu_id;
        this.user_id = user_id;
        this.nama_ibu = nama_ibu;
    }

    public int getIbuId() {
        return ibu_id;
    }

    public int getUserId() {
        return user_id;
    }

    public String getNamaIbu() {
        return nama_ibu;
    }
}

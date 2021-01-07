package com.example.myapplication;

import java.io.IOException;
import java.io.Serializable;

/**
 *
 * @author WanHar
 */
public class Ibu implements Serializable {
    String nama;
    char jenisKel, tempatTinggal;
    int umurTahun;
    double tinggiBadanCm;
    double beratBadanKg;

    public Ibu(String nama, int umurTahun, double tinggiBadanCm, double beratBadanKg, char tempatTinggal){
        this.nama = nama;
        this.umurTahun = umurTahun;
        this.tinggiBadanCm = tinggiBadanCm;
        this.beratBadanKg = beratBadanKg;
        this.tempatTinggal = tempatTinggal;
    }

    public int getUmur(){
        return this.umurTahun;
    }

    public double getTinggi(){
        return this.tinggiBadanCm;
    }


    //WAZ
    public double getMothBMI (){
        double tinggiM = convertCmToM(this.tinggiBadanCm);
        return this.beratBadanKg/(tinggiM*tinggiM);
    }

    public double convertCmToM (double cm){
        return cm*1/100;
    }


}

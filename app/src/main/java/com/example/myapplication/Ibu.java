package com.example.myapplication;

import java.io.IOException;

/**
 *
 * @author WanHar
 */
public class Ibu {
    String nama;
    char jenisKel;
    int umurBulan;
    double tinggiBadanCm;
    double beratBadanKg;

    public Ibu(
            String nama
    ){
        this.nama = nama;
        this.jenisKel = jenisKel;
        this.umurBulan = umurBulan;
        this.tinggiBadanCm = tinggiBadanCm;
        this.beratBadanKg = beratBadanKg;
    }

    public int getUmur(){
        return this.umurBulan;
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

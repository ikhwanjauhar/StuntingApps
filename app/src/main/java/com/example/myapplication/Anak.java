package com.example.myapplication;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

/**
 *
 * @author WanHar
 */
public class Anak implements Serializable {
    String nama;
    char jenisKel;
    int umurBulan;
    double tinggiBadanCm;
    double beratBadanKg;
    int indexArray, indexWAZ;

    public Anak(
            String nama,
            char jenisKel,
            int umurBulan,
            double tinggiBadanCm,
            double beratBadanKg
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

//    public int getHAZ (){
//        return getZScore();
//    }
//    public int getWAZ (){
//
//    }
//    public int getWHZ (){
//
//    }


    //WAZ
    public int getWAZScore (InputStream inDataBBUL){
        try {
            //WAZ
            //String pathDataBBUL = "C:\\Users\\WanHar\\Documents\\Kuliah Semester 7\\TA\\Data\\CSV\\BBUL060.csv";

            ReadDataAntropometri dataBBUL060 = new ReadDataAntropometri();
            dataBBUL060.readBBUL(inDataBBUL);
            indexArray = dataBBUL060.findIndex(umurBulan, beratBadanKg);
            indexWAZ = convertToZScore(indexArray);
        } catch (IOException e) {
        }
        return this.indexWAZ;
    }

    //konversi Index array ke satuan ZScore
    public int convertToZScore(int indexZScore){
        switch (indexZScore){
            case 1 : return -3;
            case 2 : return -2;
            case 3 : return -1;
            case 4 : return 0;
            case 5 : return 1;
            case 6 : return 2;
            case 7 : return 3;
        }
        return -99;
    }


}


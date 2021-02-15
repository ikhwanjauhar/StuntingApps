package com.example.stuntingapps;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import java.util.Date;

/**
 *
 * @author WanHar
 */
public class Anak implements Serializable {
    String nama;
    char jenisKel, beratLahir, tingkatPenyakitAnemiaAnak;
    int umurBulan;
    double tinggiBadanCm;
    double beratBadanKg;
    int indexArray, indexWAZ, indexHAZ, indexWHZ;
    Date tanggalLahirAnak;

    public Anak(
            String nama,
            char jenisKel,
            int umurBulan,
            char beratLahir,
            double tinggiBadanCm,
            double beratBadanKg,
            Date tanggalLahirAnak,
            char tingkatPenyakitAnemiaAnak
    ){
        this.nama = nama;
        this.jenisKel = jenisKel;
        this.umurBulan = umurBulan;
        this.beratLahir = beratLahir;
        this.tinggiBadanCm = tinggiBadanCm;
        this.beratBadanKg = beratBadanKg;
        this.tanggalLahirAnak = tanggalLahirAnak;
        this.tingkatPenyakitAnemiaAnak = tingkatPenyakitAnemiaAnak;
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


    //HAZ
    public int getHAZScore (InputStream inDataTBU){
        try {
            //HAZ
            //String pathDataBBUL = "C:\\Users\\WanHar\\Documents\\Kuliah Semester 7\\TA\\Data\\CSV\\BBUL060.csv";

            ReadDataAntropometri dataTBU = new ReadDataAntropometri();
            indexArray = dataTBU.findIndex(umurBulan, beratBadanKg, dataTBU.readData060(inDataTBU));
            indexHAZ = convertToZScore(indexArray);
        } catch (IOException e) {
        }
        return this.indexHAZ;
    }

    //WAZ
    public int getWAZScore (InputStream inDataBBU){
        try {
            //WAZ
            //String pathDataBBUL = "C:\\Users\\WanHar\\Documents\\Kuliah Semester 7\\TA\\Data\\CSV\\BBUL060.csv";

            ReadDataAntropometri dataBBU = new ReadDataAntropometri();
            indexArray = dataBBU.findIndex(umurBulan, beratBadanKg, dataBBU.readData060(inDataBBU));
            indexWAZ = convertToZScore(indexArray);
        } catch (IOException e) {
        }
        return this.indexWAZ;
    }

    //WHZ Umur 0-24
    public int getWHZScore024 (InputStream inDataBBTB){
        try {

            ReadDataAntropometri dataBBTB = new ReadDataAntropometri();
            indexArray = dataBBTB.findIndexWHZ024(tinggiBadanCm, beratBadanKg, dataBBTB.readDataWHZ024(inDataBBTB));
            indexWHZ = convertToZScore(indexArray);
        } catch (IOException e) {
        }
        return this.indexWHZ;
    }

    //WHZ Umur 24-60
    public int getWHZScore2460 (InputStream inDataBBTB){
        try {

            ReadDataAntropometri dataBBTB = new ReadDataAntropometri();
            indexArray = dataBBTB.findIndexWHZ2460(tinggiBadanCm, beratBadanKg, dataBBTB.readDataWHZ2460(inDataBBTB));
            indexWHZ = convertToZScore(indexArray);
        } catch (IOException e) {
        }
        return this.indexWHZ;
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


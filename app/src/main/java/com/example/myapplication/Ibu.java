package com.example.myapplication;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author WanHar
 */
public class Ibu implements Serializable {
    String nama, wealthIndex;
    char tempatTinggal, tingkatPendidikan, statusPekerjaanIbu;
    int umurTahun;
    double tinggiBadanCm;
    double beratBadanKg;
    Date tanggalLahirIbu;

    public Ibu(String nama, int umurTahun, double tinggiBadanCm, double beratBadanKg,
               char tempatTinggal, char tingkatPendidikan, char statusPekerjaanIbu,
               String wealthIndex, Date tanggalLahirIbu) {
        this.nama = nama;
        this.umurTahun = umurTahun;
        this.tinggiBadanCm = tinggiBadanCm;
        this.beratBadanKg = beratBadanKg;
        this.tempatTinggal = tempatTinggal;
        this.tingkatPendidikan = tingkatPendidikan;
        this.statusPekerjaanIbu = statusPekerjaanIbu;
        this.wealthIndex = wealthIndex;
        this.tanggalLahirIbu = tanggalLahirIbu;
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

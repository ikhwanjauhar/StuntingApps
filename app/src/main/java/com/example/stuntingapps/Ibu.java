package com.example.stuntingapps;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author WanHar
 */
public class Ibu implements Serializable {
    String nama, wealthIndex, email;
    String pekerjaanIbuDesc = "";
    char tempatTinggal, tingkatPendidikan, statusPekerjaanIbu;
    int umurTahun, jumlahAnak;
    double tinggiBadanCm;
    double beratBadanKg;
    Date tanggalLahirIbu;

    public Ibu(String email, String nama, int umurTahun, double tinggiBadanCm, double beratBadanKg, int jumlahAnak,
               char tempatTinggal, char tingkatPendidikan, char statusPekerjaanIbu, String pekerjaanIbuDesc,
               String wealthIndex, Date tanggalLahirIbu) {
        this.email = email;
        this.nama = nama;
        this.umurTahun = umurTahun;
        this.tinggiBadanCm = tinggiBadanCm;
        this.beratBadanKg = beratBadanKg;
        this.jumlahAnak = jumlahAnak;
        this.tempatTinggal = tempatTinggal;
        this.tingkatPendidikan = tingkatPendidikan;
        this.statusPekerjaanIbu = statusPekerjaanIbu;
        this.pekerjaanIbuDesc = pekerjaanIbuDesc;
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

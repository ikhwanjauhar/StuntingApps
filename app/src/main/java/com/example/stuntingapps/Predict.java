package com.example.stuntingapps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Predict extends AppCompatActivity {

    //Variables
    private TextView tvHasilPrediksi, tvNamaAnak, tvHAZScore, tvWAZScore, tvWHZScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_predict);

        //Retreive objIbu
        Ibu ibu = (Ibu)getIntent().getSerializableExtra("objIbu");
        //Retreive objAnak
        Anak anak = (Anak)getIntent().getSerializableExtra("objAnak");

        tvNamaAnak = (TextView) findViewById(R.id.nama_anak);
        tvNamaAnak.setText(anak.nama);

        //HAZ
        tvHAZScore = (TextView) findViewById(R.id.indeks_haz);
        tvHAZScore.setText(String.valueOf(anak.indexHAZ));

        //WAZ
        tvWAZScore = (TextView) findViewById(R.id.indeks_waz);
        tvWAZScore.setText(String.valueOf(anak.indexWAZ));

        //HAZ
        tvWHZScore = (TextView) findViewById(R.id.indeks_whz);
        tvWHZScore.setText(String.valueOf(anak.indexWHZ));

        //Button Predict
        Button prediksi = (Button)findViewById(R.id.lihat_hasil);
        prediksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Main Function

                DecisionTree proses = new DecisionTree ();

                //haz < -2 && waz >= 2 && waz <= 2
                DecisionTree dt = new DecisionTree();

                //Test sRule1
                dt.mothBMI = ibu.getMothBMI();
                dt.haz = anak.indexHAZ;
                dt.waz = anak.indexWAZ;
                dt.whz = anak.indexWHZ;
                dt.totalNumberOfChildren = ibu.jumlahAnak;
                dt.mothAge = ibu.umurTahun;
                dt.childAge = anak.umurBulan;
                dt.residence = ibu.tempatTinggal;
                dt.childAge = anak.tingkatPenyakitAnemiaAnak;
                dt.mothEdu = ibu.tingkatPendidikan;
                dt.mothOccup = ibu.tempatTinggal;
                dt.sexOfChild = anak.jenisKel;
                dt.sizeAtBirth = anak.beratLahir;
                dt.wealthIndex = ibu.wealthIndex;

                String results [] = new String [4];

                //Class Stunted
                if (dt.sRule1() == true) {
                    results[0]="Stunted";
                }
                else if (dt.sRule2() == true) {
                    results[0]="Stunted";
                }
                else if (dt.sRule3() == true) {
                    results[0]="Stunted";
                }
                else if (dt.sRule4() == true) {
                    results[0]="Stunted";
                }
                else if (dt.sRule7() == true) {
                    results[0]="Stunted";
                }
                else {
                    results[0]="Not Stunted";
                }

                //Class Underweight
                if (dt.uRule5() == true) {
                    results[1]="Underweight";
                }
                else {
                    results[1]="Not Underweight";
                }

                //Class Wasted
                if (dt.wRule1() == true) {
                    results[2]="Wasted";
                }
                else if (dt.wRule2() == true) {
                    results[2]="Wasted";
                }
                else if (dt.wRule3() == true) {
                    results[2]="Wasted";
                }
                else if (dt.wRule4() == true) {
                    results[2]="Wasted";
                }
                else {
                    results[2]="Not Wasted";
                }

                //Class Normal
                if (dt.nRule1() == true) {
                    results[3]="Normal";
                }
                else {
                    results[3]="Not Normal";
                }

                //System.out.println("Test Rule 1");
                for (String result : results)
                {
                    System.out.println(result);
                }
                String hasilPrediksi="";

                for (int i = 0; i < results.length; i++) {
                    if (results[i].contains("Not") == false) {
                        hasilPrediksi = results[i];
                    }
                }
                tvHasilPrediksi = (TextView) findViewById(R.id.hasil_prediksi);
                tvHasilPrediksi.setText(hasilPrediksi);
            }
        });

    }
}
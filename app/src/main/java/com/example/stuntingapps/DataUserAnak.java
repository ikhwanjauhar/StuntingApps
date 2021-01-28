package com.example.stuntingapps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.InputStream;

public class DataUserAnak extends AppCompatActivity {

    private TextView tvNamaAnak, tvUsiaAnak, tvWAZScore, tvHAZScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_user_anak);

        //Retreive objIbu
        Ibu ibu = (Ibu)getIntent().getSerializableExtra("objIbu");
        //Retreive objAnak
        Anak anak = (Anak)getIntent().getSerializableExtra("objAnak");

        tvNamaAnak = (TextView) findViewById(R.id.nama_ibu);
        tvUsiaAnak = (TextView) findViewById(R.id.usia_anak);
        tvNamaAnak.setText(getIntent().getStringExtra("NamaAnak"));
        tvUsiaAnak.setText(Integer.toString(getIntent().getIntExtra("UsiaAnak",0)));

        //Indeks HAZ
        //Laki-Laki
        if (anak.jenisKel == 'L') {
            //Usia 0-24
            if (anak.umurBulan<=24) {
                InputStream pbul024 = getResources().openRawResource(R.raw.pbul_024);
                anak.indexHAZ = anak.getHAZScore(pbul024);
                tvHAZScore = (TextView) findViewById(R.id.indeks_haz);
                tvHAZScore.setText(String.valueOf(anak.indexHAZ));
            }
            //Usia 24-60
            else if (anak.umurBulan>24) {
                InputStream pbul2460 = getResources().openRawResource(R.raw.pbup_024);
                anak.indexHAZ = anak.getHAZScore(pbul2460);
                tvHAZScore = (TextView) findViewById(R.id.indeks_haz);
                tvHAZScore.setText(String.valueOf(anak.indexHAZ));
            }
        }
        //Perempuan
        else {
            //Usia 0-24
            if (anak.umurBulan<=24) {
                InputStream pbup024 = getResources().openRawResource(R.raw.pbup_024);
                anak.indexHAZ = anak.getHAZScore(pbup024);
                tvHAZScore = (TextView) findViewById(R.id.indeks_haz);
                tvHAZScore.setText(String.valueOf(anak.indexHAZ));
            }
            //Usia 24-60
            else if (anak.umurBulan>24) {
                InputStream pbup2460 = getResources().openRawResource(R.raw.pbup_024);
                anak.indexHAZ = anak.getHAZScore(pbup2460);
                tvHAZScore = (TextView) findViewById(R.id.indeks_haz);
                tvHAZScore.setText(String.valueOf(anak.indexHAZ));
            }
        }

        //Indeks WAZ
        //Laki-Laki
        if (anak.jenisKel == 'L') {
            InputStream bbul060 = getResources().openRawResource(R.raw.bbul_060);
            anak.indexWAZ = anak.getWAZScore(bbul060);
            tvWAZScore = (TextView) findViewById(R.id.indeks_waz);
            tvWAZScore.setText(String.valueOf(anak.indexWAZ));
        }
        //Perempuan
        else {
            InputStream bbup060 = getResources().openRawResource(R.raw.bbul_060);
            anak.indexWAZ = anak.getWAZScore(bbup060);
            tvWAZScore = (TextView) findViewById(R.id.indeks_waz);
            tvWAZScore.setText(String.valueOf(anak.indexWAZ));
        }

        //Button Next
        Button nextAnak = (Button)findViewById(R.id.next_simpan);
        nextAnak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tempIbu;
                tempIbu = getIntent().getStringExtra("NamaIbu");
                int tempUsia = getIntent().getIntExtra("UsiaIbu",0);
                Intent i = new Intent(DataUserAnak.this, Predict.class);
                i.putExtra("NamaIbu", tempIbu);
                i.putExtra("UsiaIbu", tempUsia);

                //Region Object
                i.putExtra("objIbu", ibu);
                i.putExtra("objAnak", anak);
                startActivity(i);
            }
        });
    }
}
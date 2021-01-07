package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.InputStream;

public class DataUserAnak extends AppCompatActivity {

    private TextView tvNamaAnak, tvUsiaAnak, tvWAZScore;

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

        //Indeks
        InputStream bbul060 = getResources().openRawResource(R.raw.bbul_060);
        tvWAZScore = (TextView) findViewById(R.id.indeks_waz);
        tvWAZScore.setText(String.valueOf(anak.getWAZScore(bbul060)));

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
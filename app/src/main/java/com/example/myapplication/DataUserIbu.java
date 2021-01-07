package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DataUserIbu extends AppCompatActivity {

    private TextView tvUsiaIbu, tvNamaIbu, tvMothBMI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_user_ibu);

        //Retreive objIbu
        Ibu ibu = (Ibu)getIntent().getSerializableExtra("objIbu");

        tvNamaIbu = (TextView) findViewById(R.id.nama_ibu);
        tvUsiaIbu = (TextView) findViewById(R.id.usia_ibu);
        tvMothBMI = (TextView) findViewById(R.id.moth_bmi);

        //tvNamaIbu.setText(getIntent().getStringExtra("NamaIbu"));
        tvNamaIbu.setText(ibu.nama);
        tvUsiaIbu.setText(Integer.toString(getIntent().getIntExtra("UsiaIbu",0)));
        tvMothBMI.setText(String.format("%.2f", getIntent().getDoubleExtra("MothBMI",0)));


        //Button Next
        Button nextAnak = (Button)findViewById(R.id.next_anak);
        nextAnak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tempIbu;
                tempIbu = getIntent().getStringExtra("NamaIbu");
                int tempUsia = getIntent().getIntExtra("UsiaIbu",0);
                Intent i = new Intent(DataUserIbu.this, UserAnak.class);
                i.putExtra("NamaIbu", tempIbu);
                i.putExtra("UsiaIbu", tempUsia);
                i.putExtra("objIbu", ibu);
                startActivity(i);
            }
        });
    }


    public double convertCmToM (double cm){
        return cm*1/100;
    }

    public double getMothBMI (double tinggiBadanCm, double beratBadanKg) {
        double tinggiM = convertCmToM(tinggiBadanCm);
        return beratBadanKg / (tinggiM * tinggiM);
    }

}
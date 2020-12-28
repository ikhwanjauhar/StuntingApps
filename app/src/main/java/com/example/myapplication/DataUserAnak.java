package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DataUserAnak extends AppCompatActivity {

    private TextView tvNamaAnak, tvUsiaAnak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_user_anak);

        tvNamaAnak = (TextView) findViewById(R.id.nama_ibu);
        tvUsiaAnak = (TextView) findViewById(R.id.usia_ibu);
        tvNamaAnak.setText(getIntent().getStringExtra("NamaAnak"));
        tvUsiaAnak.setText(Integer.toString(getIntent().getIntExtra("UsiaAnak",0)));
    }
}
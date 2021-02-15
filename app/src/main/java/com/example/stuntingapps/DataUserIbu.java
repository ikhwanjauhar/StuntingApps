package com.example.stuntingapps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;

public class DataUserIbu extends AppCompatActivity {
//public class DataUserIbu extends AppCompatActivity implements View.OnClickListener {
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

                String newDate = new SimpleDateFormat("yyyy-MM-dd").format(ibu.tanggalLahirIbu);

/*                simpanDataIbu("8", ibu.nama, ibu.tanggalLahirIbu.toString(), String.valueOf(ibu.tinggiBadanCm),
                        String.valueOf(ibu.beratBadanKg), String.valueOf(ibu.jumlahAnak), String.valueOf(ibu.tempatTinggal),
                        String.valueOf(ibu.tingkatPendidikan), String.valueOf(ibu.statusPekerjaanIbu),
                        ibu.pekerjaanIbuDesc, ibu.wealthIndex);*/
                int userId = SharedPrefManager.getInstance(DataUserIbu.this).getUser().getUserId();
                simpanDataIbu(userId, ibu.nama, newDate, ibu.tinggiBadanCm,
                        ibu.beratBadanKg, ibu.jumlahAnak, String.valueOf(ibu.tempatTinggal),
                        String.valueOf(ibu.tingkatPendidikan), String.valueOf(ibu.statusPekerjaanIbu),
                        ibu.pekerjaanIbuDesc, ibu.wealthIndex);
                /*simpanDataIbu("1", "123", "2000-01-01",
                        "22", "60", "2",
                        "S", "N", "Y", "asd",
                        "SS");*/
                startActivity(i);
            }
        });
    }

    /*private void simpanDataIbu(String user_id, String nama_ibu, String tanggal_lahir_ibu,
                               String tinggi_badan_ibu, String berat_badan_ibu,
                               String jumlah_anak_ibu, String tempat_tinggal, String tingkat_pendidikan, String pekerjaan,
                               String pekerjaan_desc, String wealth_index){*/
    private void simpanDataIbu(int user_id, String nama_ibu, String tanggal_lahir_ibu,
                               double tinggi_badan_ibu, double berat_badan_ibu,
                               int jumlah_anak_ibu, String tempat_tinggal, String tingkat_pendidikan,
                               String pekerjaan, String pekerjaan_desc, String wealth_index){
    //private void simpanDataIbu(){
        /*Ibu ibu = (Ibu)getIntent().getSerializableExtra("objIbu");

        int user_id = 5;
        String nama_ibu = ibu.nama;
        String tanggal_lahir_ibu = ibu.tanggalLahirIbu.toString();
        double tinggi_badan_ibu = ibu.tinggiBadanCm;
        double berat_badan_ibu = ibu.beratBadanKg;
        int jumlah_anak_ibu = ibu.jumlahAnak;
        String tempat_tinggal = String.valueOf(ibu.tempatTinggal);
        String tingkat_pendidikan = String.valueOf(ibu.tingkatPendidikan);
        String pekerjaan = String.valueOf(ibu.statusPekerjaanIbu);
        String pekerjaan_desc = ibu.pekerjaanIbuDesc;
        String wealth_index = ibu.wealthIndex;*/

        //Validations

        //Save Data Ibu via API
        Call<DefaultResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .createDataIbu(user_id, nama_ibu, tanggal_lahir_ibu, tinggi_badan_ibu, berat_badan_ibu,
                        jumlah_anak_ibu, tempat_tinggal, tingkat_pendidikan, pekerjaan,
                        pekerjaan_desc, wealth_index);

        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                if (response.code() == 201) {

                    DefaultResponse dr2 = response.body();
                    Toast.makeText(DataUserIbu.this, dr2.getMsg(), Toast.LENGTH_LONG).show();

                } else if (response.code() == 422) {
                    Toast.makeText(DataUserIbu.this, "User already exist", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                Toast.makeText(DataUserIbu.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

/*    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.next_anak:
                simpanDataIbu();
                break;
        }
    }*/


    public double convertCmToM (double cm){
        return cm*1/100;
    }

    public double getMothBMI (double tinggiBadanCm, double beratBadanKg) {
        double tinggiM = convertCmToM(tinggiBadanCm);
        return beratBadanKg / (tinggiM * tinggiM);
    }

}
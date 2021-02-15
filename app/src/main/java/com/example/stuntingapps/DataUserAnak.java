package com.example.stuntingapps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataUserAnak extends AppCompatActivity {

    private TextView tvNamaAnak, tvUsiaAnak, tvWAZScore, tvHAZScore, tvWHZScore;

    List<User> userList;
    List<ObjectIbu> ibuList;

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

        //Indeks WHZ
        //Laki-Laki
        if (anak.jenisKel == 'L') {
            //Usia 0-24
            if (anak.umurBulan<=24) {
                InputStream bbtbl024 = getResources().openRawResource(R.raw.bbtbl_024);
                anak.indexWHZ = anak.getWHZScore024(bbtbl024);
                tvWHZScore = (TextView) findViewById(R.id.indeks_whz);
                tvWHZScore.setText(String.valueOf(anak.indexWHZ));
            }
            //Usia 24-60
            else if (anak.umurBulan>24) {
                InputStream bbtbl2460 = getResources().openRawResource(R.raw.bbtbl_2460);
                anak.indexWHZ = anak.getWHZScore2460(bbtbl2460);
                tvWHZScore = (TextView) findViewById(R.id.indeks_whz);
                tvWHZScore.setText(String.valueOf(anak.indexWHZ));
            }
        }
        //Perempuan
        else {
            //Usia 0-24
            if (anak.umurBulan<=24) {
                InputStream bbtbp024 = getResources().openRawResource(R.raw.bbtbp_024);
                anak.indexWHZ = anak.getWHZScore024(bbtbp024);
                tvWHZScore = (TextView) findViewById(R.id.indeks_whz);
                tvWHZScore.setText(String.valueOf(anak.indexWHZ));
            }
            //Usia 24-60
            else if (anak.umurBulan>24) {
                InputStream bbtbp2460 = getResources().openRawResource(R.raw.bbtbp_2460);
                anak.indexWHZ = anak.getWHZScore2460(bbtbp2460);
                tvWHZScore = (TextView) findViewById(R.id.indeks_whz);
                tvWHZScore.setText(String.valueOf(anak.indexWHZ));
            }
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

                //Get User Id
                //getUserIdByEmail(ibu.email);
                //int userId = userList.get(0).getUserId();

                int userId = SharedPrefManager.getInstance(DataUserAnak.this).getUser().getUserId();

                //Get Ibu Id
                //getIbuByUserId(userId);

                //List<ObjectIbu> ibus = getIbuByUserId(userId);
                //int ibuId = ibus.get(0).getIbuId();

                //Change Date Format
                String newDate = new SimpleDateFormat("yyyy-MM-dd").format(anak.tanggalLahirAnak);

                //Save Data Via API
/*                simpanDataAnak(9, "Joni", newDate, "S",
                        45, 3, "L",
                        "N");*/
                simpanDataAnak(userId, anak.nama, newDate, String.valueOf(anak.beratLahir),
                        anak.tinggiBadanCm, anak.beratBadanKg, String.valueOf(anak.jenisKel),
                        String.valueOf(anak.tingkatPenyakitAnemiaAnak));

                startActivity(i);
            }
        });
    }

    //ibu_id, nama_anak, tanggal_lahir_anak, berat_lahir_anak, tinggi_badan_anak, berat_badan_anak, jenis_kelamin_anak, penyakit_anemia_anak
    private void simpanDataAnak(int ibu_id, String nama_anak, String tanggal_lahir_anak,
                                String berat_lahir_anak, double tinggi_badan_anak,
                                double berat_badan_anak, String jenis_kelamin_anak, String penyakit_anemia_anak){

        //Validations

        //Save Data Ibu via API
        Call<DefaultResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .createDataAnak(ibu_id, nama_anak, tanggal_lahir_anak, berat_lahir_anak,
                        tinggi_badan_anak, berat_badan_anak, jenis_kelamin_anak, penyakit_anemia_anak);

        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                if (response.code() == 201) {

                    DefaultResponse dr3 = response.body();
                    Toast.makeText(DataUserAnak.this, dr3.getMsg(), Toast.LENGTH_LONG).show();

                } else if (response.code() == 422) {
                    Toast.makeText(DataUserAnak.this, "User already exist", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                Toast.makeText(DataUserAnak.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void getUserIdByEmail(String email){

        //Validations

        //Save Data Ibu via API
        Call<UsersResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .getUserByEmail(email);

        call.enqueue(new Callback<UsersResponse>() {
            @Override
            public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {
                userList = response.body().getUsers();
            }

            @Override
            public void onFailure(Call<UsersResponse> call, Throwable t) {

            }
        });
    }

    private List<ObjectIbu> getIbuByUserId(int userId){

        //Validations

        //Save Data Ibu via API
        Call<IbusResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .getIbuByUserId(userId);

        call.enqueue(new Callback<IbusResponse>() {
            @Override
            public void onResponse(Call<IbusResponse> call, Response<IbusResponse> response) {
                ibuList = response.body().getIbus();
            }

            @Override
            public void onFailure(Call<IbusResponse> call, Throwable t) {

            }
        });
        return ibuList;
    }

}
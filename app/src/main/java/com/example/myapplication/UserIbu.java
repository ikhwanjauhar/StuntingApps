package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;

public class UserIbu extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private TextView mDisplayDate;
    int mothAge;
    char moth_occup;

    EditText tinggiBadanIbu, beratBadanIbu;
    EditText namaIbu, jumlahAnak;

    private DatePickerDialog.OnDateSetListener mDateSetListener;

    RadioGroup radioGroupTempatTinggal;
    RadioButton radioButton;
    RadioButton radioButtonPekerjaan;

    //Radio Group Tingkat Pendidikan
    RadioGroup rgTingkatPendidikan;

    //Radio Group Wealth Index
    RadioGroup rgWealthIndex;

    //Pekerjaan
    private EditText editTextPekerjaan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_ibu);


        //Tanggal Lahir
        mDisplayDate = (TextView) findViewById(R.id.tanggal_lahir);

        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(view.getContext(),
                        mDateSetListener,
                        year,month,day);
                dialog.getDatePicker().setMaxDate(new Date().getTime());
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                Calendar c = Calendar.getInstance();
                c.set(Calendar.YEAR, year);
                c.set(Calendar.MONTH, month);
                c.set(Calendar.DAY_OF_MONTH, day);

                String format = new SimpleDateFormat("dd/MM/yyyy").format(c.getTime());
                mDisplayDate.setText(format);
                mothAge = calculateAge(c.getTimeInMillis());
            }
        };

        //Pekerjaan
        radioButtonPekerjaan = findViewById(R.id.mothOccup_Yes);
        editTextPekerjaan = findViewById(R.id.mothOccup);
        RadioGroup rg = (RadioGroup) findViewById(R.id.radio_group_pekerjaan);

        //Status Pekerjaan Ibu Working (Y) / Not Working (N)
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.mothOccup_Yes){
                    editTextPekerjaan.setEnabled(true);
                    moth_occup = 'Y';
                }
                else{
                    editTextPekerjaan.setEnabled(false);
                    moth_occup = 'N';
                }
            }
        });

        namaIbu = (EditText) findViewById(R.id.nama_ibu);
        tinggiBadanIbu = (EditText) findViewById(R.id.tinggi_badan);
        beratBadanIbu = (EditText) findViewById(R.id.berat_badan);

        //Jumlah Anak
        jumlahAnak = (EditText) findViewById(R.id.jumlah_anak);

        //Tempat Tinggal
        radioGroupTempatTinggal = findViewById(R.id.radio_group_tempat_tinggal);

        //Tingkat Pendidikan
        rgTingkatPendidikan = findViewById(R.id.radio_group_tingkat_pendidikan);

        //Tingkat Pendidikan
        rgWealthIndex = findViewById(R.id.radio_group_pendapatan);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.mothOccup_Yes){
                    editTextPekerjaan.setEnabled(true);
                }
                else{
                    editTextPekerjaan.setEnabled(false);
                }
            }
        });

        //Button Next
        Button nextAnak = (Button)findViewById(R.id.next_anak);
        nextAnak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tempNamaIbu;
                tempNamaIbu = namaIbu.getText().toString();
                int tempUsia = mothAge;

                //Tanggal Lahir
                String tanggalLahirIbu = mDisplayDate.getText().toString();
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                Date tempTanggalLahirIbu = null;
                try {
                    tempTanggalLahirIbu = formatter.parse(tanggalLahirIbu);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                if (tinggiBadanIbu.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Tinggi Badan Belum Diisi", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (beratBadanIbu.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Berat Badan Belum Diisi", Toast.LENGTH_SHORT).show();
                    return;
                }

                double tempTinggiBadan = Double.parseDouble(tinggiBadanIbu.getText().toString());
                double tempBeratBadan = Double.parseDouble(beratBadanIbu.getText().toString());;
                double mothBMI = getMothBMI(tempTinggiBadan, tempBeratBadan);

                //Tempat Tinggal
                char tempTempatTinggal = 'R';
                int selectedTempatTinggal = radioGroupTempatTinggal.getCheckedRadioButtonId();
                if (selectedTempatTinggal == R.id.tempat_kota) {
                    tempTempatTinggal = 'U';
                }

                //Tingkat Pendidikan
                char tempTingkatPendidikan;
                int selectedTingkatPendidikan = rgTingkatPendidikan.getCheckedRadioButtonId();
                if (selectedTingkatPendidikan == R.id.moth_edu_primary) {
                    tempTingkatPendidikan = 'P';
                }
                else if (selectedTingkatPendidikan == R.id.moth_edu_secondary) {
                    tempTingkatPendidikan = 'S';
                }
                else if (selectedTingkatPendidikan == R.id.moth_edu_higher) {
                    tempTingkatPendidikan = 'H';
                }
                else {
                    tempTingkatPendidikan = 'N';
                }

                //Status Pekerjaan Ibu Working / Not Working
                char tempStatusPekerjaanIbu = moth_occup;

                //Wealth Index
                String tempWealthIndex;
                int selectedWealthIndex = rgWealthIndex.getCheckedRadioButtonId();
                if (selectedWealthIndex == R.id.wealth_index_PR) {
                    tempWealthIndex = "PR";
                }
                else if (selectedWealthIndex == R.id.wealth_index_M) {
                    tempWealthIndex = "M";
                }
                else if (selectedWealthIndex == R.id.wealth_index_RR) {
                    tempWealthIndex = "RR";
                }
                else if (selectedWealthIndex == R.id.wealth_index_RS) {
                    tempWealthIndex = "RS";
                }
                else {
                    tempWealthIndex = "PS";
                }

                //Make objIbu
                Ibu ibu = new Ibu(tempNamaIbu, tempUsia, tempTinggiBadan, tempBeratBadan,
                        tempTempatTinggal, tempTingkatPendidikan, tempStatusPekerjaanIbu,
                        tempWealthIndex, tempTanggalLahirIbu);

                Intent i = new Intent(UserIbu.this, DataUserIbu.class);
                i.putExtra("NamaIbu", tempNamaIbu);
                i.putExtra("UsiaIbu", tempUsia);
                i.putExtra("MothBMI", mothBMI);
                i.putExtra("objIbu", ibu);
                startActivity(i);
            }
        });


    }
    public void checkButton(View v) {
        int radioId = radioGroupTempatTinggal.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
    }

    int calculateAge (long date) {
        Calendar dob = Calendar.getInstance();
        dob.setTimeInMillis(date);

        Calendar today = Calendar.getInstance();
        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_MONTH)<dob.get(Calendar.DAY_OF_MONTH)){
            age--;
        }
        return age;
    }

    public double convertCmToM (double cm){
        return cm*1/100;
    }

    public double getMothBMI (double tinggiBadanCm, double beratBadanKg){
        double tinggiM = convertCmToM(tinggiBadanCm);
        return beratBadanKg/(tinggiM*tinggiM);
    }
}
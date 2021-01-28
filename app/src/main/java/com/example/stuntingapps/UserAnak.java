package com.example.stuntingapps;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
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
import java.util.Calendar;
import java.util.Date;

public class UserAnak extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    int childAge;

    private TextView mDisplayNamaIbu;
    private TextView tvTanggalLahirAnak;

    EditText namaAnak, beratLahirAnak, tinggiBadanAnak, beratBadanAnak;

    private DatePickerDialog.OnDateSetListener mDateSetListener;
    RadioGroup radioGroupJenisKel;
    RadioButton radioButton;

    //Radio Group Tingkat Anemia Anak
    RadioGroup rgTingkatPenyakitAnemiaAnak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_anak);

        //Retreive objIbu
        Ibu ibu = (Ibu)getIntent().getSerializableExtra("objIbu");

        mDisplayNamaIbu = (TextView) findViewById(R.id.nama_ibu);
        mDisplayNamaIbu.setText("Anak dari Ibu "+getIntent().getStringExtra("NamaIbu"));

        //Tanggal Lahir
        tvTanggalLahirAnak = (TextView) findViewById(R.id.tanggal_lahir_anak);

        tvTanggalLahirAnak.setOnClickListener(new View.OnClickListener() {
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
                tvTanggalLahirAnak.setText(format);
                childAge = calculateAge(c.getTimeInMillis());
            }
        };

        //Jenis Kelamin
        radioGroupJenisKel = findViewById(R.id.radio_group_jenis_kel);

        //Berat Lahir Anak
        beratLahirAnak = findViewById(R.id.berat_lahir);

        //Tinggi Badan Anak
        tinggiBadanAnak = (EditText) findViewById(R.id.tinggi_badan);

        //Berat Badan Anak
        beratBadanAnak = (EditText) findViewById(R.id.berat_badan);

        //Tingkat Penyakit Anemia
        rgTingkatPenyakitAnemiaAnak = findViewById(R.id.radio_group_penyakit_anemia);

        //Button Next
        Button nextAnak = (Button)findViewById(R.id.next_anak);
        namaAnak = (EditText) findViewById(R.id.nama_anak);

        nextAnak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tempNamaAnak = namaAnak.getText().toString();
                int tempUsia = childAge;

                //Tanggal Lahir
                String tanggalLahirAnak = tvTanggalLahirAnak.getText().toString();
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                Date temptanggalLahirAnak = null;
                try {
                    temptanggalLahirAnak = formatter.parse(tanggalLahirAnak);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                //Berat Lahir Anak
                if (beratLahirAnak.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Berat Lahir Anak Belum Diisi", Toast.LENGTH_SHORT).show();
                    return;
                }
                char tempBeratLahir = 'N';
                double inBeratLahir =  Double.parseDouble(beratLahirAnak.getText().toString());
                //large (>4 kg), normal (2.5-4 kg) and small (<2.5 kg)
                if (inBeratLahir>4) {
                    tempBeratLahir = 'L';
                }
                else if (inBeratLahir>=2.5 && inBeratLahir<=4) {
                    tempBeratLahir = 'N';
                }
                else if (inBeratLahir<2.5) {
                    tempBeratLahir = 'S';
                }

                //Tinggi Badan Anak
                if (tinggiBadanAnak.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Tinggi Badan Belum Diisi", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (beratBadanAnak.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Berat Badan Belum Diisi", Toast.LENGTH_SHORT).show();
                    return;
                }
                double tempTinggiBadanAnak = Double.parseDouble(tinggiBadanAnak.getText().toString());
                double tempBeratBadanAnak = Double.parseDouble(beratBadanAnak.getText().toString());

                //Jenis Kelamin Anak
                char tempJenisKelamin = 'L';
                int selectedJenisKelamin = radioGroupJenisKel.getCheckedRadioButtonId();
                if (selectedJenisKelamin == R.id.jenis_kelamin_p) {
                    tempJenisKelamin = 'P';
                }

                //Tingkat Penyakit Anemia
                char tempTingkatPenyakitAnemiaAnak;
                int selectedTempatTinggal = rgTingkatPenyakitAnemiaAnak.getCheckedRadioButtonId();
                switch (selectedTempatTinggal) {
                    //Severe
                    case R.id.penyakit_anemia_S:
                        tempTingkatPenyakitAnemiaAnak = 'S';
                    //Moderate
                    case R.id.penyakit_anemia_M:
                        tempTingkatPenyakitAnemiaAnak = 'M';
                    //Mild
                    case R.id.penyakit_anemia_I:
                        tempTingkatPenyakitAnemiaAnak = 'I';
                    //Not Anemic
                    default:
                        tempTingkatPenyakitAnemiaAnak = 'N';
                }

                //Make objAnak
                Anak anak = new Anak(
                        tempNamaAnak, tempJenisKelamin, tempUsia, tempBeratLahir,
                        tempTinggiBadanAnak, tempBeratBadanAnak, temptanggalLahirAnak,
                        tempTingkatPenyakitAnemiaAnak);

                Intent i = new Intent(UserAnak.this, DataUserAnak.class);
                i.putExtra("NamaAnak", tempNamaAnak);
                i.putExtra("UsiaAnak", tempUsia);

                //Region Object
                i.putExtra("objAnak", anak);
                i.putExtra("objIbu", ibu);
                startActivity(i);
            }
        });
    }

    public void checkButton(View v) {
        int radioId = radioGroupJenisKel.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
    }

    int calculateAge (long date) {
        Calendar dob = Calendar.getInstance();
        dob.setTimeInMillis(date);

        Calendar today = Calendar.getInstance();
        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
        int ageMonth = today.get(Calendar.MONTH) - dob.get(Calendar.MONTH);

        if (today.get(Calendar.DAY_OF_MONTH)<dob.get(Calendar.DAY_OF_MONTH)){
            age--;
        }

        if (age==0) {
            return ageMonth;
        }
        else {
            return (age*12)+ageMonth;
        }
    }
}
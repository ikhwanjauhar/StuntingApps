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
    EditText namaIbu;

    private DatePickerDialog.OnDateSetListener mDateSetListener;
    RadioGroup radioGroup;
    RadioButton radioButton;
    RadioButton radioButtonPekerjaan;

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

        //Tempat Tinggal
        radioGroup = findViewById(R.id.radioGroup);

        //Pekerjaan
        radioButtonPekerjaan = findViewById(R.id.mothOccup_Yes);
        editTextPekerjaan = findViewById(R.id.mothOccup);
        RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroupPekerjaan);

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

        namaIbu = (EditText) findViewById(R.id.nama_ibu);
        //Button Next
        Button nextAnak = (Button)findViewById(R.id.next_anak);
        nextAnak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tempIbu;
                tempIbu = namaIbu.getText().toString();
                int tempUsia = mothAge;
                Intent i = new Intent(UserIbu.this, DataUserIbu.class);
                i.putExtra("NamaIbu", tempIbu);
                i.putExtra("UsiaIbu", tempUsia);
                startActivity(i);
            }
        });


    }
    public void checkButton(View v) {
        int radioId = radioGroup.getCheckedRadioButtonId();
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
}
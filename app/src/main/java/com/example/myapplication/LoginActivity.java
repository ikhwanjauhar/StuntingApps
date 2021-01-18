package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.util.regex.Pattern;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etEmail, etPassword, etName, etSchool;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.email);
        etPassword = findViewById(R.id.password);
        etName = findViewById(R.id.password);
        etSchool = findViewById(R.id.school);

        findViewById(R.id.sign_up).setOnClickListener(this);

    }

    private void userSignUp(){

        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String name = etName.getText().toString().trim();
        String school = etSchool.getText().toString().trim();

        //Validations

        //email
        if (email.isEmpty()){
            etEmail.setError("Masukkan alamat E-mail");
            etEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            etEmail.setError("Format alamat E-mail salah");
            etEmail.requestFocus();
            return;
        }

        //password
        if (password.isEmpty()){
            etPassword.setError("Masukkan password");
            etPassword.requestFocus();
            return;
        }
        if (password.length() < 8){
            etPassword.setError("Panjang minimal password 8 karakter");
            etPassword.requestFocus();
            return;
        }

        //name
        if (name.isEmpty()){
            etName.setError("Masukkan username");
            etName.requestFocus();
            return;
        }

        //School
        if (name.isEmpty()){
            etSchool.setError("Masukkan school");
            etSchool.requestFocus();
            return;
        }

        //User Registration via API
        Call<ResponseBody> call = RetrofitClient
                .getInstance()
                .getApi()
                .createUser(email, password, name, school);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String s = response.body().string();
                    Toast.makeText(LoginActivity.this, s, Toast.LENGTH_LONG).show();
                } catch(IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_up:
                userSignUp();
            break;
        }
    }
}
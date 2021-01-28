package com.example.stuntingapps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etEmail, etPassword, etUserName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        etEmail = findViewById(R.id.email);
        etPassword = findViewById(R.id.password);
        etUserName = findViewById(R.id.user_name);

        findViewById(R.id.sign_up).setOnClickListener(this);
        findViewById(R.id.text_view_login).setOnClickListener(this);

    }

    private void userSignUp(){

        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String user_name = etUserName.getText().toString().trim();

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

        //username
        if (user_name.isEmpty()){
            etUserName.setError("Masukkan username");
            etUserName.requestFocus();
            return;
        }

        //User Registration via API
        Call<DefaultResponse> call = RetrofitClient
                .getInstance()
                .getApi()
                .createUser(email, user_name, password);

        call.enqueue(new Callback<DefaultResponse>() {
            @Override
            public void onResponse(Call<DefaultResponse> call, Response<DefaultResponse> response) {
                if (response.code() == 201) {

                    DefaultResponse dr = response.body();
                    Toast.makeText(SignUpActivity.this, dr.getMsg(), Toast.LENGTH_LONG).show();

                } else if (response.code() == 422) {
                    Toast.makeText(SignUpActivity.this, "User already exist", Toast.LENGTH_LONG).show();
                }
            }


            @Override
            public void onFailure(Call<DefaultResponse> call, Throwable t) {
                Toast.makeText(SignUpActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_up:
                userSignUp();
            break;
            case R.id.text_view_login:
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }
    }
}
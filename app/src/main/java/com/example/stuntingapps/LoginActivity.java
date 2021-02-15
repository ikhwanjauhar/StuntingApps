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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etEmail;
    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.email);
        etPassword = findViewById(R.id.password);

        findViewById(R.id.button_login).setOnClickListener(this);
        findViewById(R.id.text_view_sign_up).setOnClickListener(this);
    }

    /*
    @Override
    protected void onStart() {
        super.onStart();

        if (SharedPrefManager.getInstance(this).isLoggedIn()) {
            Intent intent = new Intent(this, UserIbu.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }ibu
     */

    private void userLogin() {

        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString().trim();

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

        Call<LoginResponse> call = RetrofitClient
                .getInstance().getApi().userLogin(email, password);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();

                if (!loginResponse.isError()) {

                    SharedPrefManager.getInstance(LoginActivity.this)
                            .saveUser(loginResponse.getUser());

                    //Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
                    Intent intent = new Intent(LoginActivity.this, UserIbu.class);
                    intent.putExtra("email", email);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);


                } else {
                    Toast.makeText(LoginActivity.this, loginResponse.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_login:
                userLogin();
                break;
            case R.id.text_view_sign_up:
                startActivity(new Intent(this, SignUpActivity.class));
                break;
        }
    }
}
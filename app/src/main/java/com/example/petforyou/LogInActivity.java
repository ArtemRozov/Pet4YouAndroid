package com.example.petforyou;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LogInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        EditText login = findViewById(R.id.editTextEmailAddress);
        EditText password = findViewById(R.id.editTextPassword);

        // if log in button clicked
        Button button = findViewById(R.id.button2);
        button.setOnClickListener(view -> {

            ApiService apiService = Api.getInsecureClient().create(ApiService.class);

            Call<LoginResponse> call =
                    apiService.loginUser(new LoginCredentials(login.getText().toString(),
                            password.getText().toString()));

            System.out.println("login = " + login.getText().toString() + ";\npsw = " + password.getText().toString());
            call.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    if (response.isSuccessful()) {
                        // Обработка успешного ответа
                        LoginResponse loginResponse = response.body();
                        String token = loginResponse.getToken();
                        UserModel user = loginResponse.getUser();

                        System.out.println("555555555555555555555");
                        // Действия после успешного входа пользователя
                        Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                        intent.putExtra("userModel", user); // "userModel" - ключ, по которому объект будет передан

                        startActivity(intent);
                    } else {
                        // Обработка ошибки
                        System.out.println("88888888888888888888");
                        Toast.makeText(getApplicationContext(),
                                "Неправильный логин или пароль", Toast.LENGTH_SHORT).show();
                    }
                }


                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    // Обработка ошибки при выполнении запроса
                    System.out.println("6666666666");
                }
            });

        });

        // if registration button clicked
        TextView textView = findViewById(R.id.textView3);
        textView.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), RegistrationActivity.class);
            startActivity(intent);
        });
    }
}
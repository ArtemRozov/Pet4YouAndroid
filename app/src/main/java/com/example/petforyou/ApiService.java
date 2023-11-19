package com.example.petforyou;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiService {
    @GET("api/User/id/{userId}") // Указываем путь к вашему API endpoint с переменной в пути
    Call<UserModel> getData(@Path("userId") int userId); // Передаем переменную userId как параметр

    @POST("api/Auth/login")
    Call<LoginResponse> loginUser(@Body LoginCredentials credentials);
}

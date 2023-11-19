package com.example.petforyou;

public class LoginResponse {
    private String token;
    private UserModel user; // Предполагается, что есть класс User с информацией о пользователе

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    // Геттеры и сеттеры для token и user
    // Можно также добавить дополнительные методы и логику, если необходимо
}

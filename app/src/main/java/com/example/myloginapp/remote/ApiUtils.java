package com.example.myloginapp.remote;

public class ApiUtils {

    public static final String BASE_URL = "https://cbt.sekolahmodern.com/api/login/";

    public static UserService getUserService(){
        return RetrofitClient.getClient(BASE_URL).create(UserService.class);
    }
}
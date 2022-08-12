package com.example.myloginapp.remote;
import com.example.myloginapp.model.ResObj;
import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {
    @POST("login/{nisn}/{token}")
    Call<Void> login(@Path("nisn") String nisn, @Path("token") String token);
}

package com.hopop.hopop.communicators.services;

import com.hopop.hopop.communicators.builder.LoginUser;
import com.hopop.hopop.communicators.builder.RegisterUser;
import com.hopop.hopop.response.Registerresponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RegisterClass {
    public static final String ACCEPT_JSON = "Accept: application/json";

    public static final String CONTENT_TYPE_JSON = "Content-Type: application/json";
    @POST("register_user.php")
    @Headers({ACCEPT_JSON, CONTENT_TYPE_JSON})
    Call<Registerresponse> groupListReg(@Body RegisterUser registerUser);
    @POST("user_details.php")
    Call<Registerresponse> groupListLogin(@Body LoginUser loginUser);
}

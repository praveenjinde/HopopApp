package com.hopop.hopopv10.communicators.services;

import com.hopop.hopopv10.communicators.builder.LoginUser;
import com.hopop.hopopv10.communicators.builder.RegisterUser;
import com.hopop.hopopv10.response.Registerresponse;

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
    //Call<ResponseBody> groupListReg(@Body RegisterUser registerClass);
    @POST("user_details.php")
    Call<Registerresponse> groupListLogin(@Body LoginUser loginUser);
}

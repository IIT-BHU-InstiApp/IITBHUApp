package com.example.anant.iitbhuvaranasi.BackendCalls;

import com.example.anant.iitbhuvaranasi.NewModels.BuiltProfilePost;
import com.example.anant.iitbhuvaranasi.NewModels.BuiltProfileSearchPost;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface ProfileAPIs {

    //   ----------------------------------------------------- GET -----------------------------------------------------
    //    --------------------------------------------------------------------------------------------------------------

    @GET("profile/")
    Call<BuiltProfilePost> getProfile(
            @Header("Authorization") String token);

    //    --------------------------------------------------------------------------------------------------------------
    //   ----------------------------------------------------- POST -----------------------------------------------------
    //    --------------------------------------------------------------------------------------------------------------

    @POST("profile/search/")
    Call<List<BuiltProfilePost>> searchProfile(
            @Header("Authorization") String token,
            @Body BuiltProfileSearchPost body);


    //    --------------------------------------------------------------------------------------------------------------
    //   ----------------------------------------------------- PUT -----------------------------------------------------
    //    --------------------------------------------------------------------------------------------------------------

    @PUT("profile/")
    Call<BuiltProfilePost> updateProfileByPut(
            @Header("Authorization") String token,
            @Body BuiltProfilePost body);

    //    --------------------------------------------------------------------------------------------------------------
    //   ----------------------------------------------------- PATCH -----------------------------------------------------
    //    --------------------------------------------------------------------------------------------------------------

    @PATCH("profile/")
    Call<BuiltProfilePost> updateProfileByPatch(
            @Header("Authorization") String token,
            @Body BuiltProfilePost body);

}

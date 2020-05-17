package com.example.anant.iitbhuvaranasi.BackendCalls;

import com.example.anant.iitbhuvaranasi.NewModels.BuiltAllWorkshopsPost;
import com.example.anant.iitbhuvaranasi.NewModels.BuiltClubPost;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ClubAPIs {

    //   ----------------------------------------------------- GET -----------------------------------------------------
    //    --------------------------------------------------------------------------------------------------------------

    @GET("clubs/{id}/")
    Call<BuiltClubPost> getClubSummary(
            @Header("Authorization") String token,
            @Path("id") int id);

    @GET("clubs/{id}/toggle-subscribed/")
    Call<Void> toggleClubSubscription(
            @Header("Authorization") String token,
            @Path("id") int id);

    @GET("clubs/{id}/workshops/")
    Call<BuiltAllWorkshopsPost> getClubWorkshops(
            @Header("Authorization") String token,
            @Path("id") int id);

    //    --------------------------------------------------------------------------------------------------------------
    //   ----------------------------------------------------- PUT -----------------------------------------------------
    //    --------------------------------------------------------------------------------------------------------------

    @PUT("clubs/{id}/")
    Call<Void> updateClubByPut(
            @Header("Authorization") String token,
            @Path("id") int id,
            @Body BuiltClubPost body);

    //    --------------------------------------------------------------------------------------------------------------
    //   ----------------------------------------------------- PATCH -----------------------------------------------------
    //    --------------------------------------------------------------------------------------------------------------

    @PATCH("clubs/{id}/")
    Call<Void> updateClubByPatch(
            @Header("Authorization") String token,
            @Path("id") int id,
            @Body BuiltClubPost body);



}

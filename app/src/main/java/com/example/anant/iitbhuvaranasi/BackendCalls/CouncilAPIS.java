package com.example.anant.iitbhuvaranasi.BackendCalls;

import com.example.anant.iitbhuvaranasi.NewModels.BuiltAllCouncilsPost;
import com.example.anant.iitbhuvaranasi.NewModels.BuiltCouncilPost;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CouncilAPIS {

    //   ----------------------------------------------------- GET -----------------------------------------------------
    //    --------------------------------------------------------------------------------------------------------------

    @GET("councils/")
    Call<List<BuiltAllCouncilsPost>> getAllCouncils();

    @GET("councils/{id}/")
    Call<BuiltCouncilPost> getCouncilDetails(
            @Header("Authorization") String token,
            @Path("id") int id);

    //    --------------------------------------------------------------------------------------------------------------
    //   ----------------------------------------------------- PUT -----------------------------------------------------
    //    --------------------------------------------------------------------------------------------------------------

    @PUT("councils/{id}/")
    Call<Void> updateCouncilByPut(
            @Header("Authorization") String token,
            @Path("id") int id,
            @Body BuiltCouncilPost body);

    //    --------------------------------------------------------------------------------------------------------------
    //   ----------------------------------------------------- PATCH -----------------------------------------------------
    //    --------------------------------------------------------------------------------------------------------------

    @PATCH("councils/{id}/")
    Call<Void> updateCouncilByPatch(
            @Header("Authorization") String token,
            @Path("id") int id,
            @Body BuiltCouncilPost body);


}

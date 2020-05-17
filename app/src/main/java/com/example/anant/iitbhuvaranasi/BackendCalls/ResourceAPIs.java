package com.example.anant.iitbhuvaranasi.BackendCalls;

import com.example.anant.iitbhuvaranasi.NewModels.WorkshopResources;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ResourceAPIs {


    //   ----------------------------------------------------- GET -----------------------------------------------------
    //    --------------------------------------------------------------------------------------------------------------

    @GET("resources/{id}/")
    Call<WorkshopResources> getWorkshopResources(
            @Path("id") int id);

    //    --------------------------------------------------------------------------------------------------------------
    //   ----------------------------------------------------- PUT -----------------------------------------------------
    //    --------------------------------------------------------------------------------------------------------------

    @PUT("resources/{id}/")
    Call<Void> updateWorkshopResourcesByPut(
            @Header("Authorization") String token,
            @Path("id") int id,
            @Body WorkshopResources body);


    //    --------------------------------------------------------------------------------------------------------------
    //   ----------------------------------------------------- PATCH -----------------------------------------------------
    //    --------------------------------------------------------------------------------------------------------------

    @PATCH("resources/{id}/")
    Call<Void> updateWorkshopResourcesByPatch(
            @Header("Authorization") String token,
            @Path("id") int id,
            @Body WorkshopResources body);


    //    --------------------------------------------------------------------------------------------------------------
    //   ----------------------------------------------------- DELETE -----------------------------------------------------
    //    --------------------------------------------------------------------------------------------------------------

    @DELETE("resources/{id}/")
    Call<Void> removeWorkshopResources(
            @Header("Authorization") String token,
            @Path("id") int id);

}

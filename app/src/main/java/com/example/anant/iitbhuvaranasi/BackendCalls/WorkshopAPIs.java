package com.example.anant.iitbhuvaranasi.BackendCalls;

import com.example.anant.iitbhuvaranasi.NewModels.BuiltAllWorkshopsPost;
import com.example.anant.iitbhuvaranasi.NewModels.BuiltContacts;
import com.example.anant.iitbhuvaranasi.NewModels.BuiltTags;
import com.example.anant.iitbhuvaranasi.NewModels.BuiltWorkshopCreatePost;
import com.example.anant.iitbhuvaranasi.NewModels.BuiltWorkshopDetailPost;
import com.example.anant.iitbhuvaranasi.NewModels.BuiltWorkshopSearchByStringPost;
import com.example.anant.iitbhuvaranasi.NewModels.BuiltWorkshopSummaryPost;
import com.example.anant.iitbhuvaranasi.NewModels.ContactPost;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface WorkshopAPIs {

    //   ----------------------------------------------------- GET -----------------------------------------------------
    //    --------------------------------------------------------------------------------------------------------------

    @GET("workshops/")
    Call<BuiltAllWorkshopsPost> getAllWorkshops();

    @GET("workshops/active/")
    Call<List<BuiltWorkshopSummaryPost>> getActiveWorkshops();

    @GET("workshops/interested/")
    Call<List<BuiltWorkshopSummaryPost>> getInterestedWorkshops(
            @Header("Authorization") String token);

    @GET("workshops/past/")
    Call<List<BuiltWorkshopSummaryPost>> getPastWorkshops();

    @GET("workshops/{id}/")
    Call<BuiltWorkshopDetailPost> getWorkshopDetailsPost(
            @Header("Authorization") String token,
            @Path("id") int id);

    @GET("workshops/{id}/toggle-interested/")
    Call<Void> toggleInterestedWorkshop(
            @Header("Authorization") String token,
            @Path("id") int id);

    //    --------------------------------------------------------------------------------------------------------------
    //   ----------------------------------------------------- POST -----------------------------------------------------
    //    --------------------------------------------------------------------------------------------------------------

    @POST("workshops/create/")
    Call<Void> postNewWorkshop(
            @Header("Authorization") String token,
            @Body BuiltWorkshopCreatePost body);

    @POST("workshops/search/")
    Call<BuiltAllWorkshopsPost> searchWorkshop(
            @Body BuiltWorkshopSearchByStringPost body);

    //    --------------------------------------------------------------------------------------------------------------
    //   ----------------------------------------------------- PUT -----------------------------------------------------
    //    --------------------------------------------------------------------------------------------------------------

    @PUT("workshops/{id}/update-tags/")
    Call<List<BuiltTags>> updateTags(
            @Header("Authorization") String token,
            @Path("id") int id,
            @Body BuiltTags body);

    @PUT("workshops/{id}/update-contacts/")
    Call<List<ContactPost>> updateContacts(
            @Header("Authorization") String token,
            @Path("id") int id,
            @Body BuiltContacts body);

    @PUT("workshops/{id}/")
    Call<Void> updateWorkshopByPut(
            @Header("Authorization") String token,
            @Path("id") int id,
            @Body BuiltWorkshopDetailPost body);

    //    --------------------------------------------------------------------------------------------------------------
    //   ----------------------------------------------------- PATCH -----------------------------------------------------
    //    --------------------------------------------------------------------------------------------------------------

    @PATCH("workshops/{id}/")
    Call<Void> updateWorkshopByPatch(
            @Header("Authorization") String token,
            @Path("id") int id,
            @Body BuiltWorkshopDetailPost body);

    //    --------------------------------------------------------------------------------------------------------------
    //   ----------------------------------------------------- DELETE -----------------------------------------------------
    //    --------------------------------------------------------------------------------------------------------------

    @DELETE("workshops/{id}/")
    Call<Void> removeWorkshop(
            @Header("Authorization") String token,
            @Path("id") int id);
}

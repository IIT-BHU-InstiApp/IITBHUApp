package com.example.anant.iitbhuvaranasi.BackendCalls;

import com.example.anant.iitbhuvaranasi.NewModels.BuiltTeamMemberPost;
import com.example.anant.iitbhuvaranasi.NewModels.LoginPost;
import com.example.anant.iitbhuvaranasi.NewModels.TagCreate;
import com.example.anant.iitbhuvaranasi.NewModels.TagDetail;
import com.example.anant.iitbhuvaranasi.NewModels.TagSearch;
import com.example.anant.iitbhuvaranasi.NewModels.Token;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface OtherAPIs {


    //   ----------------------------------------------------- GET -----------------------------------------------------
    //    --------------------------------------------------------------------------------------------------------------

//    @GET("team/")
//    Call<List<BuiltTeamMemberPost>> getTeam();


    //    --------------------------------------------------------------------------------------------------------------
    //   ----------------------------------------------------- POST -----------------------------------------------------
    //    --------------------------------------------------------------------------------------------------------------

    @POST("tags/create/")
    Call<Void> createTag(
            @Header("Authorization") String token,
            @Body TagCreate body);

    @POST("tags/search/")
    Call<List<TagDetail>> searchTags(
            @Header("Authorization") String token,
            @Body TagSearch body);

    @POST("login/")
    Call<Token> logInPost(@Body LoginPost body);
}

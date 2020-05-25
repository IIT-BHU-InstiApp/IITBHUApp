package com.example.anant.iitbhuvaranasi.NewBackendResponses;

import com.example.anant.iitbhuvaranasi.BackendCalls.WorkshopAPIs;
import com.example.anant.iitbhuvaranasi.Constants;
import com.example.anant.iitbhuvaranasi.NewModels.BuiltAllWorkshopsPost;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Workshop_Responses {
    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Constants.herokuBaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static void allWorkshopsMethod(){

         WorkshopAPIs allWorkshopsAPI = retrofit.create(WorkshopAPIs.class);

        Call<BuiltAllWorkshopsPost> call = allWorkshopsAPI.getAllWorkshops();

        call.enqueue(new Callback<BuiltAllWorkshopsPost>() {
            @Override
            public void onResponse(Call<BuiltAllWorkshopsPost> call, Response<BuiltAllWorkshopsPost> response) {
                if(!response.isSuccessful()){
                    System.out.println("all workshops api call is not successful");
                    return;
                }
                Constants.allWorkshopsPost = response.body();

            }
            @Override
            public void onFailure(Call<BuiltAllWorkshopsPost> call, Throwable t) {
                System.out.println("all workshops api call resulted in failure");
            }
        });

    }

}

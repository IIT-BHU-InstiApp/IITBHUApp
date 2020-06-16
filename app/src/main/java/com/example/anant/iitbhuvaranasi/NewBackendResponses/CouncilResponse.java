package com.example.anant.iitbhuvaranasi.NewBackendResponses;

import android.util.Log;

import com.example.anant.iitbhuvaranasi.BackendCalls.CouncilAPIS;
import com.example.anant.iitbhuvaranasi.Constants;
import com.example.anant.iitbhuvaranasi.Interfaces.RetrofitResponseListener;
import com.example.anant.iitbhuvaranasi.NewModels.BuiltAllCouncilsPost;
import com.example.anant.iitbhuvaranasi.NewModels.BuiltCouncilPost;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static app.AppController.TAG;

public class CouncilResponse {

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(Constants.herokuBaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public static void responseAllCouncils(RetrofitResponseListener<List<BuiltAllCouncilsPost>> retrofitResponseListener) {
        retrofit.create(CouncilAPIS.class)
                .getAllCouncils()
                .enqueue(new Callback<List<BuiltAllCouncilsPost>>() {
                    @Override
                    public void onResponse(Call<List<BuiltAllCouncilsPost>> call, Response<List<BuiltAllCouncilsPost>> response) {
                        if (response.isSuccessful()) {
                            retrofitResponseListener.onSuccess(response);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<BuiltAllCouncilsPost>> call, Throwable t) {
                        Log.e(TAG, "Unable to fetch councils");
                        retrofitResponseListener.onFailure();
                    }
                });

    }

    public static void responseCouncilDetails(int id, RetrofitResponseListener<BuiltCouncilPost> retrofitResponseListener) {
        retrofit.create(CouncilAPIS.class)
                .getCouncilDetails(Constants.djangoToken, id)
                .enqueue(new Callback<BuiltCouncilPost>() {
                    @Override
                    public void onResponse(Call<BuiltCouncilPost> call, Response<BuiltCouncilPost> response) {
                        retrofitResponseListener.onSuccess(response);

                    }

                    @Override
                    public void onFailure(Call<BuiltCouncilPost> call, Throwable t) {
                        retrofitResponseListener.onFailure();
                    }
                });
    }
}

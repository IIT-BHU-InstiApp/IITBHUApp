package com.example.anant.iitbhuvaranasi.Interfaces;

import retrofit2.Response;

public interface RetrofitResponseListener<T> {

    void onSuccess(Response<T> response);

    void onFailure();
}

package com.example.anant.iitbhuvaranasi.Interfaces;

import org.json.JSONObject;

public interface ServerCallback {
    void onSuccess();
    void onError();

    void onSuccess(JSONObject response);
}

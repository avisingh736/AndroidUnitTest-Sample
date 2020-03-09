package com.testingex.data.remote.api;

import androidx.annotation.NonNull;

import retrofit2.Call;
import retrofit2.Response;

public abstract class Callback<T> implements retrofit2.Callback<T> {
    @Override
    public void onResponse(@NonNull Call<T> call, Response<T> response) {
        if (response.code() == 200) {
            if (response.body() != null) {
                onSuccess(response.body());
            } else {
                onError(new Throwable("Request onSuccess with null response"));
            }
        } else if (response.code() == 400) {
            //ToDo: Need to handle this onError code and remove error code
            onError(new Throwable("Handle logout error: " + response.toString()));
        } else {
            if (response.errorBody() != null) {
                onError(new Throwable("Request onSuccess with an error: " + response.errorBody().charStream()));
            } else {
                onError(new Throwable("Request onSuccess with an unknown error: " + response.toString()));
            }
        }

    }

    @Override
    public void onFailure(@NonNull Call<T> call, @NonNull Throwable t) {
        onError(t);
    }

    abstract void onSuccess(T response);

    abstract void onError(Throwable t);
}

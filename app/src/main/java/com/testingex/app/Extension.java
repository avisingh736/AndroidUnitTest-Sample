package com.testingex.app;

import android.app.Application;

import com.testingex.app.prefs.Constants;
import com.testingex.app.prefs.PrefHelper;
import com.testingex.data.remote.api.Apis;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Extension extends Application {
    private static Retrofit retrofit;
    private static OkHttpClient client;

    @Override
    public void onCreate() {
        super.onCreate();
        PrefHelper.init(this);
    }

    /**
     *  Retrofit configuration
     * */
    public static Apis getApis() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("BASE_URL")
                    .client(client())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(Apis.class);
    }

    /**
     *  OkHttpClient configuration
     * */
    private static OkHttpClient client() {
        if (client == null) {
            client = new OkHttpClient.Builder()
                    .connectTimeout(Constants.CONNECTION_TIMEOUT, TimeUnit.SECONDS)
                    .readTimeout(Constants.READ_TIMEOUT, TimeUnit.SECONDS)
                    .build();
        }
        return client;
    }
}

package com.akhbulatov.githubsample.data.global.factories;

import android.support.annotation.NonNull;

import com.akhbulatov.githubsample.BuildConfig;
import com.akhbulatov.githubsample.data.network.GitHubApi;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class NetworkFactory {

    private static GitHubApi api;

    private NetworkFactory() {
        throw new AssertionError("No instance!");
    }

    public static GitHubApi getApi() {
        if (api == null) {
            api = createGitHubApi();
        }
        return api;
    }

    private static @NonNull HttpLoggingInterceptor createLoggingInterceptor() {
        return new HttpLoggingInterceptor()
                .setLevel(
                        BuildConfig.DEBUG
                                ? HttpLoggingInterceptor.Level.BODY
                                : HttpLoggingInterceptor.Level.BASIC
                );
    }

    private static @NonNull OkHttpClient createOkhttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(createLoggingInterceptor())
                .build();
    }

    private static @NonNull Retrofit createRetrofit() {
        return new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .client(createOkhttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private static @NonNull GitHubApi createGitHubApi() {
        return createRetrofit().create(GitHubApi.class);
    }
}

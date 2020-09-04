package ir.hosseini.shalimanagment.app;

import android.content.Context;

import ir.hosseini.shalimanagment.retrofit.ShaliAPI;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Application extends android.app.Application {

    private static Context context;
    private static Retrofit retrofit;
    private static ShaliAPI shaliAPI;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this ;

        retrofit = new Retrofit.Builder()
                .baseUrl(app.main.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        shaliAPI = retrofit.create(ShaliAPI.class);

    }

    public static Context getContext() {
        return context ;
    }

    public static Retrofit getRetrofit() {
        return retrofit;
    }

    public static ShaliAPI getShaliAPI() {
        return shaliAPI;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}

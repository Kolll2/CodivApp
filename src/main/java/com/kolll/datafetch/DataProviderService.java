package com.kolll.datafetch;

import com.kolll.datafetch.model.CountryData;
import com.kolll.datafetch.model.CovidDataModel;
import com.kolll.datafetch.model.GlobalData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.CompletableFuture;

public class DataProviderService {
    public CovidDataModel getData (String countryName){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://coronavirus-19-api.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        CovidApi covidApi = retrofit.create(CovidApi.class);
        CompletableFuture<GlobalData> globalDataCompletableFuture = new CompletableFuture<>();
        covidApi.getGlobalData()
                .enqueue(new Callback<GlobalData>() {
                    @Override
                    public void onResponse(Call<GlobalData> call, Response<GlobalData> response) {
                        globalDataCompletableFuture.complete(response.body());
                    }

                    @Override
                    public void onFailure(Call<GlobalData> call, Throwable t) {
                        globalDataCompletableFuture.completeExceptionally(t);
                    }
                });

        CompletableFuture<CountryData> countryDataCompletableFuture = new CompletableFuture<>();
        covidApi.getCountryData(countryName)
                .enqueue(new Callback<CountryData>() {
                    @Override
                    public void onResponse(Call<CountryData> call, Response<CountryData> response) {
                        countryDataCompletableFuture.complete(response.body());
                    }

                    @Override
                    public void onFailure(Call<CountryData> call, Throwable t) {
                        countryDataCompletableFuture.completeExceptionally(t);
                    }
                });

        GlobalData globalData = globalDataCompletableFuture.join();
        CountryData countryData = countryDataCompletableFuture.join();

        return new CovidDataModel(globalData, countryData);
    }
}

package com.kolll.datafetch;

import com.google.gson.JsonObject;
import com.kolll.datafetch.model.CountryData;
import com.kolll.datafetch.model.GlobalData;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CovidApi {
    @GET(" https://coronavirus-19-api.herokuapp.com/all")
    Call<GlobalData> getGlobalData();

    @GET(" https://coronavirus-19-api.herokuapp.com/countries/{countryName}")
    Call<CountryData> getCountryData(@Path(value = "countryName") String countryName);
}

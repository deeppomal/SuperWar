package com.deeppomal.superherob;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GetData {

    @GET("api/APIKEY/{id}")
    Call<BattleModel> getAllStats(@Path("id") String id);
}

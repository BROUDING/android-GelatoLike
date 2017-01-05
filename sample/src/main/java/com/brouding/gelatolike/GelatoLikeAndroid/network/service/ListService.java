package com.brouding.gelatolike.GelatoLikeAndroid.network.service;

import com.brouding.gelatolike.GelatoLikeAndroid.network.dataModel.ModelInsta;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by John Lee on 2017-01-04.
 */

public class ListService extends BaseService {
    public static ListAPI api()
    {
        return (ListAPI) retrofit(ListAPI.class);
    }

    public interface ListAPI
    {
        @GET("{user_id}/media/?max_id=")
        Call<ModelInsta> getInstaSampleData(@Path("user_id") String userId, @Query("max_id") String maxId);
    }
}

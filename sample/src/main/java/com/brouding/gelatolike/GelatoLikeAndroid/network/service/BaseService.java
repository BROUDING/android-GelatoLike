package com.brouding.gelatolike.GelatoLikeAndroid.network.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by John Lee on 2017-01-04.
 */

public class BaseService {
    protected static Object retrofit(Class<?> className)
    {
        String host = "https://www.instagram.com/";
        Retrofit retrofit = new Retrofit.Builder().baseUrl( host ).addConverterFactory( GsonConverterFactory.create() ).build();
        return retrofit.create(className);
    }
}

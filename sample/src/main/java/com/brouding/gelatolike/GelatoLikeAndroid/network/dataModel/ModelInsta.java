package com.brouding.gelatolike.GelatoLikeAndroid.network.dataModel;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * Created by John Lee on 2017-01-04.
 */

public class ModelInsta {
    @SerializedName("items")
    @Expose
    public List<Item> items = null;
    @SerializedName("more_available")
    @Expose
    public boolean isThereMoreData;
}
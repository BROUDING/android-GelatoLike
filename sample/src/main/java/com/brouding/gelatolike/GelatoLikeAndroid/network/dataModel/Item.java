package com.brouding.gelatolike.GelatoLikeAndroid.network.dataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


/**
 * Created by John Lee on 2017-01-05.
 */

public class Item {

    @SerializedName("created_time")
    @Expose
    public String createdTime;
    @SerializedName("images")
    @Expose
    public Images images;
    @SerializedName("link")
    @Expose
    public String link;
    @SerializedName("id")
    @Expose
    public String id;

}

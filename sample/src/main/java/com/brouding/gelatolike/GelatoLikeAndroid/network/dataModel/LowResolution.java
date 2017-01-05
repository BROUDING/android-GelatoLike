package com.brouding.gelatolike.GelatoLikeAndroid.network.dataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by John Lee on 2017-01-05.
 */

public class LowResolution {
    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("width")
    @Expose
    public Integer width;
    @SerializedName("height")
    @Expose
    public Integer height;
}

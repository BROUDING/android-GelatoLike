package com.brouding.gelatolike.GelatoLikeAndroid.network.dataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by John Lee on 2017-01-05.
 */

public class Images {
    @SerializedName("thumbnail")
    @Expose
    public Thumbnail thumbnail;
    @SerializedName("low_resolution")
    @Expose
    public LowResolution lowResolution;
    @SerializedName("standard_resolution")
    @Expose
    public StandardResolution standardResolution;

}

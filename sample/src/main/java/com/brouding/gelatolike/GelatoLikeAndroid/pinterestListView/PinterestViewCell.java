package com.brouding.gelatolike.GelatoLikeAndroid.pinterestListView;

import java.io.Serializable;

public class PinterestViewCell implements Serializable {
    private String lowResImageUri,
                   imageUri,
                   productId;
    private int    lowResImageWidth,
                   lowResImageHeight,
                   imageWidth,
                   imageHeight;

    public PinterestViewCell(String productId, String lowResImageUri, int lowResImageWidth, int lowResImageHeight, String imageUri, int imageWidth, int imageHeight) {
        this.productId         = productId;
        this.lowResImageUri    = lowResImageUri;
        this.lowResImageWidth  = lowResImageWidth;
        this.lowResImageHeight = lowResImageHeight;
        this.imageUri          = imageUri;
        this.imageWidth        = imageWidth;
        this.imageHeight       = imageHeight;
    }

    public String  getProductId()         { return productId; }
    public String  getLowResImageUri()    { return lowResImageUri; }
    public int     getLowResImageWidth()  { return lowResImageWidth; }
    public int     getLowResImageHeight() { return lowResImageHeight; }
    public String  getImageUri()          { return imageUri; }
    public int     getImageWidth()        { return imageWidth; }
    public int     getImageHeight()       { return imageHeight; }
}

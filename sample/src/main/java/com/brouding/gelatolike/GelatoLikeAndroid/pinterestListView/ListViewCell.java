package com.brouding.gelatolike.GelatoLikeAndroid.pinterestListView;

/**
 * Created by root on 25/10/16.
 */

public class ListViewCell {
    private String imageUri,
                   productId;
    private int    imageWidth,
                   imageHeight;

    public ListViewCell(String productId, String imageUri, int imageWidth, int imageHeight) {
        this.productId   = productId;
        this.imageUri    = imageUri;
        this.imageWidth  = imageWidth;
        this.imageHeight = imageHeight;
    }

    public String  getProductId()   { return productId; }
    public String  getImageUri()    { return imageUri; }
    public int     getImageWidth()  { return imageWidth; }
    public int     getImageHeight() { return imageHeight; }
}

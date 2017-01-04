package com.brouding.gelatolike.GelatoLikeAndroid.pinterestListView;

/**
 * Created by root on 25/10/16.
 */

public class ListViewCell {
    private String imageUri;
    private int    productId,
                   imageWidth,
                   imageHeight;

    public ListViewCell(int productId, String imageUri, int imageWidth, int imageHeight) {
        this.productId   = productId;
        this.imageUri    = imageUri;
        this.imageWidth  = imageWidth;
        this.imageHeight = imageHeight;
    }

    public int     getProductId()   { return productId; }
    public String  getImageUri()    { return imageUri; }
    public int     getImageWidth()  { return imageWidth; }
    public int     getImageHeight() { return imageHeight; }
}

package com.brouding.gelatolike.GelatoLikeAndroid;

import android.net.Uri;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.brouding.gelatolike.GelatoLikeAndroid.pinterestListView.PinterestViewCell;
import com.brouding.gelatolike.sample.R;
import com.facebook.drawee.view.SimpleDraweeView;
import com.huewu.pla.lib.internal.Utils;

import java.util.ArrayList;


/**
 * Created by John on 12/8/16.
 */

public class DetailViewPagerAdapter extends PagerAdapter {
    private LayoutInflater inflater;
    private ArrayList<PinterestViewCell> listContent;
    private int currentIndex, deviceWidth;

    public DetailViewPagerAdapter(LayoutInflater inflater, ArrayList<PinterestViewCell> listContent, int currentIndex, int deviceWidth) {
        super();
        this.inflater     = inflater;
        this.listContent  = listContent;
        this.currentIndex = currentIndex;
        this.deviceWidth  = deviceWidth;
    }

    @Override
    public int getCount() {
        return listContent.size() -currentIndex;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = inflater.inflate(R.layout.layout_sample_detail_pager, null);
        SimpleDraweeView mainImage = (SimpleDraweeView)view.findViewById(R.id.main_image);

        int listIndex = currentIndex +position;
        if ( listIndex >= listContent.size()) {
            listIndex = listContent.size() -1;
        }
        PinterestViewCell pinterestViewCell = listContent.get(listIndex);

        // set mainImage Size by images
        ViewGroup.LayoutParams params = mainImage.getLayoutParams();
        params.width                  = deviceWidth;
        params.height                 = Utils.calculateHeight(deviceWidth, pinterestViewCell.getImageWidth(), pinterestViewCell.getImageHeight());
        mainImage.setLayoutParams(params);
        mainImage.setImageURI(Uri.parse(pinterestViewCell.getImageUri()));

        container.addView(view);
        return view;
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }

    @Override
    public boolean isViewFromObject(View v, Object obj) {
        return v == ((View) obj);
    }

}

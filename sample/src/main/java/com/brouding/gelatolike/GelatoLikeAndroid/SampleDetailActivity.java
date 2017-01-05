package com.brouding.gelatolike.GelatoLikeAndroid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;

import com.brouding.gelatolike.GelatoLikeAndroid.pinterestListView.ListViewCell;
import com.brouding.gelatolike.sample.R;

import java.util.ArrayList;

/**
 * Created by John Lee on 2017-01-05.
 */

public class SampleDetailActivity extends FragmentActivity {
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private Context mContext;

    private ArrayList<ListViewCell> listContent;
    private int currentIndex;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_sample_detail);

        mContext = getApplicationContext();

        Intent intent = getIntent();
        listContent = (ArrayList<ListViewCell>)intent.getSerializableExtra("listData");
        currentIndex = intent.getIntExtra("currentIndex", 0);

        Log.e("@@# currentIndex = ", "" +currentIndex);
//        Log.e("@@# listContent = " , "" +listContent.toString());

        // viewPager
        viewPager = (ViewPager)findViewById(R.id.viewPager);
        viewPager.setOffscreenPageLimit(1);
        DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
        viewPagerAdapter = new ViewPagerAdapter(LayoutInflater.from(mContext), listContent, currentIndex, dm.widthPixels);
        viewPager.setAdapter(viewPagerAdapter);
    }
}

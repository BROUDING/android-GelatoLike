package com.brouding.gelatolike.GelatoLikeAndroid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;

import com.brouding.gelatolike.GelatoLikeAndroid.pinterestListView.PinterestViewCell;
import com.brouding.gelatolike.sample.R;

import java.util.ArrayList;

/**
 * Created by John Lee on 2017-01-05.
 */

public class DetailActivity extends AppCompatActivity {
    private ViewPager   viewPager;
    private DetailViewPagerAdapter detailViewPagerAdapter;
    private Context mContext;

    private ArrayList<PinterestViewCell> listContent;
    private int currentIndex, pagePosition;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_sample_detail);

        mContext = getApplicationContext();

        Intent intent = getIntent();
        listContent   = (ArrayList<PinterestViewCell>)intent.getSerializableExtra("listData");
        currentIndex   = intent.getIntExtra("currentIndex", 0);

        // viewPager
        viewPager = (ViewPager)findViewById(R.id.view_pager);
        viewPager.setOffscreenPageLimit(1);
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                pagePosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                if( state == 0 && pagePosition == listContent.size() -currentIndex) {
                    // TODO: LoadMore Data

                }
            }
        });

        DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
        detailViewPagerAdapter = new DetailViewPagerAdapter(LayoutInflater.from(mContext), listContent, currentIndex, dm.widthPixels);
        viewPager.setAdapter(detailViewPagerAdapter);
    }

    public void closeActivity(View v) {
        closeActivity();
    }

    public void closeActivity() {
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        // TODO: setResult << ListData and then finish.

    }
}

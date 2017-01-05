package com.brouding.gelatolike.GelatoLikeAndroid.pinterestListView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.brouding.gelatolike.GelatoLikeAndroid.SampleActivity;
import com.brouding.gelatolike.GelatoLikeAndroid.SampleDetailActivity;
import com.brouding.gelatolike.sample.R;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

public class PinterestViewHolder {
    private Context mContext;
    public RelativeLayout    mainCellLayout;
    public SimpleDraweeView  mainImage;

    private final ArrayList<ListViewCell> listContent;
    public String            productId;
    public int               currentIndex;

    public PinterestViewHolder(final Context mContext, View convertView, ArrayList<ListViewCell> mList) {
        this.mContext     = mContext;
        this.listContent  = mList;

        mainCellLayout    = (RelativeLayout)   convertView.findViewById(R.id.mainCellLayout);
        mainImage         = (SimpleDraweeView) convertView.findViewById(R.id.mainImage);

        convertView.setTag(this);
        convertView.setBackgroundResource(R.drawable.theme_pinterestview_list_row);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent newIntent = new Intent(mContext, SampleDetailActivity.class);
                newIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                Bundle bundle = new Bundle();
                bundle.putInt("currentIndex", ((PinterestViewHolder) v.getTag()).currentIndex);
                bundle.putSerializable("listData", listContent);
                newIntent.putExtras(bundle);

                mContext.startActivity(newIntent);
            }
        });
    }
}
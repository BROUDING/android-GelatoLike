package com.brouding.gelatolike.GelatoLikeAndroid.pinterestListView;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;

import com.brouding.gelatolike.GelatoLikeAndroid.SampleActivity;
import com.brouding.gelatolike.sample.R;
import com.facebook.drawee.view.SimpleDraweeView;

public class PinterestViewHolder {
    public Context mContext;
    public RelativeLayout mainCellLayout;
    public SimpleDraweeView     mainImage;

    public String               productId;

    public PinterestViewHolder(Context mContext, final SampleActivity mainActivity, View convertView) {
        this.mContext     = mContext;

        mainCellLayout    = (RelativeLayout)   convertView.findViewById(R.id.mainCellLayout);
        mainImage         = (SimpleDraweeView) convertView.findViewById(R.id.mainImage);

        convertView.setTag(this);
        convertView.setBackgroundResource(R.drawable.theme_pinterestview_list_row);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("@@# view.getTag = ", "" + ((PinterestViewHolder) v.getTag()).productId);

                if( mainActivity instanceof IMethodCaller ) {
//                    mainActivity.sendEvent("Product CLICK !!!");
                }
            }
        });
    }
}
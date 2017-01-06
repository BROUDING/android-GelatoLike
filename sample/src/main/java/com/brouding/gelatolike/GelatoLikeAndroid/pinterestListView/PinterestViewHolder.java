package com.brouding.gelatolike.GelatoLikeAndroid.pinterestListView;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;

import com.brouding.gelatolike.GelatoLikeAndroid.MainActivity;
import com.brouding.gelatolike.sample.R;
import com.facebook.drawee.view.SimpleDraweeView;

public class PinterestViewHolder {
    private final MainActivity mainActivity;
    private Context mContext;
    public RelativeLayout mainCellLayout;
    public SimpleDraweeView mainImage;

    public String            productId;
    public int               currentIndex;

    public PinterestViewHolder(final Context mContext, final MainActivity mainActivity, View convertView) {
        this.mContext     = mContext;
        this.mainActivity = mainActivity;

        mainCellLayout    = (RelativeLayout)   convertView.findViewById(R.id.mainCellLayout);
        mainImage         = (SimpleDraweeView) convertView.findViewById(R.id.mainImage);

        convertView.setTag(this);
        convertView.setBackgroundResource(R.drawable.theme_pinterest_cell);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if( mainActivity instanceof IMethodCaller ) {
                    mainActivity.showDetailActivity( ((PinterestViewHolder) v.getTag()).currentIndex );
                }
            }
        });
    }
}
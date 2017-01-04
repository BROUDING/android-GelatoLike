package com.brouding.gelatolike.GelatoLikeAndroid.pinterestListView;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import com.brouding.gelatolike.GelatoLikeAndroid.SampleActivity;
import com.brouding.gelatolike.sample.R;
import com.huewu.pla.lib.MultiColumnListView;

import java.util.List;

public class PLAdapter extends BaseAdapter {
    private int SDK_INT;
    private Context mContext;
    private LayoutInflater mInflater;
    private MultiColumnListView  mainListView;
    private List<ListViewCell> listContent;
    private SampleActivity mainActivity;

    // Styles
    private int
            cellBackgroundColor,
            cellPadding;

//    Float fontSize;
//    int padding, width;
//    String fontColor;

    public PLAdapter(int SDK_INT, Context mContext, SampleActivity activity, LayoutInflater mInflater, MultiColumnListView mainListView, List<ListViewCell> objects) {
        this.SDK_INT      = SDK_INT;
        this.mContext     = mContext;
        this.mInflater    = mInflater;
        this.mainListView = mainListView;
        this.listContent  = objects;
        this.mainActivity = activity;
    }

//    public void setStyles(ReadableMap src) {
//        fontColor = src.getString("fontColor");
//        fontSize = Float.parseFloat(src.getString("fontSize"));
//        padding =  Integer.parseInt(src.getString("padding"));
//    }

    public void setBackgroundColor(int color) {
        cellBackgroundColor = color;
    }

    public void setPadding(int padding) {
        cellPadding = padding;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        PinterestViewHolder mViewHolder;
        int defaultColumnWidth  = mainListView.mdefaultColumnWidth;
        int defaultColumnHeight = mainListView.mdefaultColumnHeight;

        if (convertView == null) {
            convertView   = mInflater.inflate(R.layout.pinterest_cell_tab_style, parent, false);
            mViewHolder   = new PinterestViewHolder(mContext, mainActivity, convertView);
        } else { // 캐시된 뷰가 있을 경우 저장된 뷰홀더를 사용한다
            mViewHolder = (PinterestViewHolder) convertView.getTag();
        }

        // TODO: 이미지 아래 LinearLayout도 id로 박아놓고, isAd이면 속성 변경하는 형태? 우선 데이터 형태를 정해야 하지 않나...

        ListViewCell listViewCell = listContent.get(position);
        mViewHolder.mainCellLayout.setPadding(cellPadding, cellPadding*2, cellPadding, 0);

        convertView.setBackgroundColor(cellBackgroundColor);
//        if(SDK_INT >= Build.VERSION_CODES.M) {
//            convertView.setBackgroundColor(mContext.getColor(R.color.black));
//        } else {
//            convertView.setBackgroundColor(mContext.getResources().getColor(R.color.black));
//        }

        // set mainImage Size by images
        ViewGroup.LayoutParams params = mViewHolder.mainImage.getLayoutParams();
        params.width                  = defaultColumnWidth;
        params.height                 = calculateHeight(defaultColumnWidth, defaultColumnHeight, listViewCell.getImageWidth(), listViewCell.getImageHeight());
        mViewHolder.mainImage   .setLayoutParams(params);
        mViewHolder.mainImage   .setImageURI(Uri.parse(listViewCell.getImageUri()));
        mViewHolder.productId    = listViewCell.getProductId();

        return convertView;
    }

    LinearLayout.OnClickListener favoriteListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.e("@@# getTag = ", "" + v.getTag() +"  ^ " +( mainActivity instanceof IMethodCaller));

            if( mainActivity instanceof IMethodCaller) {
//                mainActivity.sendEvent("Favorite CLICK !!!");
            }
        }
    };

    private int calculateHeight(int defaultColumnWidth, int defaultColumnHeight, int realWidth, int realHeight) {
        if( realWidth == realHeight ) {
            return defaultColumnWidth;
        } else {
            return (defaultColumnWidth *realHeight) /realWidth;
        }
    }

    public int getCount() {
        return listContent.size();
    }

    public Object getItem(int position) {
        return listContent.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
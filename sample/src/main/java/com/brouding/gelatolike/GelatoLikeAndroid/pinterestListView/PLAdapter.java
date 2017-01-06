package com.brouding.gelatolike.GelatoLikeAndroid.pinterestListView;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.brouding.gelatolike.GelatoLikeAndroid.MainActivity;
import com.brouding.gelatolike.sample.R;
import com.huewu.pla.lib.MultiColumnListView;
import com.huewu.pla.lib.internal.Utils;

import java.util.ArrayList;

public class PLAdapter extends BaseAdapter {
    private int SDK_INT;
    private Context mContext;
    private LayoutInflater mInflater;
    private MultiColumnListView  mainListView;
    private ArrayList<ListViewCell> listContent;
    private MainActivity mainActivity;

    // Styles
    private int
            cellBackgroundColor,
            cellPadding;

    public PLAdapter(int SDK_INT, Context mContext, MainActivity mainActivity, LayoutInflater mInflater, MultiColumnListView mainListView, ArrayList<ListViewCell> objects) {
        this.SDK_INT      = SDK_INT;
        this.mContext     = mContext;
        this.mainActivity = mainActivity;
        this.mInflater    = mInflater;
        this.mainListView = mainListView;
        this.listContent  = objects;
    }

    public void setBackgroundColor(int color) {
        cellBackgroundColor = color;
    }

    public void setPadding(int padding) {
        cellPadding = padding;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        PinterestViewHolder mViewHolder;
        int defaultColumnWidth  = mainListView.mdefaultColumnWidth;

        if (convertView == null) {
            convertView   = mInflater.inflate(R.layout.pinterest_cell, parent, false);
            mViewHolder   = new PinterestViewHolder(mContext, mainActivity, convertView);
        } else { // 캐시된 뷰가 있을 경우 저장된 뷰홀더를 사용한다
            mViewHolder = (PinterestViewHolder) convertView.getTag();
        }

        ListViewCell listViewCell = listContent.get(position);
        mViewHolder.mainCellLayout.setPadding(cellPadding, cellPadding*2, cellPadding, 0);
        convertView.setBackgroundColor(cellBackgroundColor);

        // set mainImage Size by images
        ViewGroup.LayoutParams params = mViewHolder.mainImage.getLayoutParams();
        params.width                  = defaultColumnWidth;
        params.height                 = Utils.calculateHeight(defaultColumnWidth, listViewCell.getLowResImageWidth(), listViewCell.getLowResImageHeight());
        mViewHolder.mainImage   .setLayoutParams(params);
        mViewHolder.mainImage   .setImageURI(Uri.parse(listViewCell.getLowResImageUri()));
        mViewHolder.currentIndex = position;
        mViewHolder.productId    = listViewCell.getProductId();

        return convertView;
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
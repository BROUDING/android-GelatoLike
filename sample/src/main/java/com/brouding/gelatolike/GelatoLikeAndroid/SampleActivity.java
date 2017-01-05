package com.brouding.gelatolike.GelatoLikeAndroid;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.brouding.gelatolike.GelatoLikeAndroid.network.dataModel.Images;
import com.brouding.gelatolike.GelatoLikeAndroid.network.dataModel.Item;
import com.brouding.gelatolike.GelatoLikeAndroid.network.dataModel.LowResolution;
import com.brouding.gelatolike.GelatoLikeAndroid.network.dataModel.ModelInsta;
import com.brouding.gelatolike.GelatoLikeAndroid.network.dataModel.StandardResolution;
import com.brouding.gelatolike.GelatoLikeAndroid.network.service.ListService;
import com.brouding.gelatolike.GelatoLikeAndroid.pinterestListView.ListViewCell;
import com.brouding.gelatolike.GelatoLikeAndroid.pinterestListView.PLAdapter;
import com.brouding.gelatolike.sample.R;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.huewu.pla.lib.MultiColumnListView;
import com.huewu.pla.lib.internal.Utils;

import java.util.ArrayList;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SampleActivity extends AppCompatActivity {
    private int SDK_INT = 0;
    private Context mContext;
    private RelativeLayout mainView;
    private MultiColumnListView mainListView;
    private LayoutInflater mInflater;
    private View loadingView;
    private LinearLayout searchView, noSearchResultView, scrollTopButton;
    private SwipeRefreshLayout swipeRefreshLayout;

    private ArrayList<ListViewCell> mList   = new ArrayList<>();
    private PLAdapter mAdapter;

    private String searchUserId = "";
    private boolean isThereMoreData = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = getApplicationContext();
        mainView = new RelativeLayout(mContext);
        Fresco.initialize(mContext);

        setContentView( createView(mainView) );
    }

    private RelativeLayout createView(RelativeLayout mainView) {
        SDK_INT     = Build.VERSION.SDK_INT;
        mInflater   = LayoutInflater.from(mContext);
        loadingView = mInflater.inflate(R.layout.footer_loading_circle, null);

        swipeRefreshLayout = new SwipeRefreshLayout(mContext);
        RelativeLayout.LayoutParams swipeRefreshLayoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        swipeRefreshLayoutParams.addRule(RelativeLayout.BELOW, R.id.search_view);
        swipeRefreshLayout.setLayoutParams(swipeRefreshLayoutParams);
        swipeRefreshLayout.setColorSchemeResources(R.color.gelato_top_note, R.color.gelato_base_note);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                getInstaSampleData(isSearchUserIdValid(searchUserId) ? searchUserId : "design", "0");
            }
        });

        // PinterestView CORE
        mainListView = new MultiColumnListView(mContext);

        // ScrollTopButton
        scrollTopButton = new LinearLayout(mContext);
        scrollTopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 상속받은 뷰에서 isAutoScrolling을 보고 getTop값을 갱신하며 결국 끝까지 올라간다.
                mainListView.setAutoScrolling();
                mainListView.smoothScrollToPosition( mainListView.getChildAt(0).getTop() );
            }
        });

        searchView = (LinearLayout) mInflater.inflate(R.layout.header_search_view, null);
        RelativeLayout.LayoutParams searchViewParams = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, Utils.getDpFromPx(mContext, 80));
        searchViewParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        searchViewParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        searchView.setLayoutParams(searchViewParams);

        final EditText searchText = (EditText)searchView.findViewById(R.id.search_text);
        searchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                switch (actionId) {
                    case EditorInfo.IME_ACTION_SEARCH:
                        searchByUserId(searchText);
                        return true;
                }
                return false;
            }
        });

        ImageButton searchBtn = (ImageButton)searchView.findViewById(R.id.search_btn);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchByUserId(searchText);
            }
        });

        noSearchResultView = (LinearLayout) mInflater.inflate(R.layout.layout_no_search_result, null);
        RelativeLayout.LayoutParams noResultViewParams = new RelativeLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        noResultViewParams.addRule(RelativeLayout.BELOW, R.id.search_view);
        noSearchResultView.setLayoutParams(noResultViewParams);

        RelativeLayout.LayoutParams scrollTopButtonParams = new RelativeLayout.LayoutParams(200, 200);
        scrollTopButtonParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        scrollTopButtonParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        scrollTopButtonParams.setMargins(0, 0, 40, 40);
        scrollTopButton.setTag("ScrollTopButton");
        scrollTopButton.setLayoutParams(scrollTopButtonParams);
        mAdapter = new PLAdapter(SDK_INT, mContext, mInflater, mainListView, mList);
        mainListView.addHeaderView(new View(mContext),  null, false);   // 구글에서 swipeRefreshLayout bug fix하기 전까지 이런 꼼수를 쓰라는 글 읽음.
        mainListView.addFooterView(loadingView, null, false);
        mainListView.setOnCustomScrollListener(customScrollListener);
        mainListView.setAdapter(mAdapter);
        swipeRefreshLayout.addView(mainListView);
        mainView.addView(searchView);
        mainView.addView(swipeRefreshLayout);
        mainView.addView(scrollTopButton);
        mainView.addView(noSearchResultView);

        initView();

        return mainView;
    }

    private void searchByUserId(EditText searchText) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(searchText.getWindowToken(), 0);

        getInstaSampleData("" +searchText.getText(), "0");
    }

    MultiColumnListView.OnCustomScrollListener customScrollListener = new MultiColumnListView.OnCustomScrollListener() {

        @Override
        public void onFirstLineForScrollTopButton() {
            if( scrollTopButton.getVisibility() == View.VISIBLE ) {
                scrollTopButton.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void onNotFirstLineForScrollTopButton() {
            if( scrollTopButton.getVisibility() == View.INVISIBLE ) {
                scrollTopButton.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onLoadMore() {
            if( isThereMoreData ) {
                int lastPosition = mainListView.getLastVisiblePosition() - 2;
                getInstaSampleData(isSearchUserIdValid(searchUserId) ? searchUserId : "design", mList.get(lastPosition).getProductId());
                mainListView.onLoadMoreComplete();
            }
        }
    };

    private void initView() {
        setPaddings(Utils.getDpFromPx(mContext, 2));
        setBackgroundColors("#d8d8d8"); // listViewBackground && cellBackground
        mainListView.init(null, 2);
        getInstaSampleData("design", "0");
    }

    private void getInstaSampleData(String userId, String dataFrom)
    {
        searchUserId = userId;
        final boolean isInitData = dataFrom.equals("0");
        ListService.api().getInstaSampleData(searchUserId, dataFrom).enqueue(new Callback<ModelInsta>()
        {
            @Override
            public void onResponse(Call<ModelInsta> call, Response<ModelInsta> response)
            {
                if (response != null && response.isSuccess() && response.body() != null)
                {
                    if( isInitData ) {
                        mList.clear();
                    }
                    ModelInsta data = response.body();
                    Random randomGen = new Random();
                    boolean isGeneralSize;

                    isThereMoreData = data.isThereMoreData;
                    if( isThereMoreData ) {
                        loadingView.setVisibility(View.VISIBLE);
                        noSearchResultView.setVisibility(View.GONE);

                        for (Item type : data.items) {
                            Images images = type.images;
                            LowResolution lowResolution = images.lowResolution;
                            StandardResolution standardResolution = images.standardResolution;

                            isGeneralSize = (randomGen.nextInt(10) + 1) % 2 == 1;

                            ListViewCell cell = new ListViewCell(
                                    type.id,
                                    lowResolution.url,
                                    lowResolution.width,
                                    isGeneralSize ? 320 : 200,
                                    standardResolution.url,
                                    standardResolution.width,
                                    isGeneralSize ? 640 : 520
                            );
                            mList.add(cell);
                        }
                    } else {
                        loadingView.setVisibility(View.GONE);
                        noSearchResultView.setVisibility(View.VISIBLE);
                    }

                    if( swipeRefreshLayout.isRefreshing() ) {
                        swipeRefreshLayout.setRefreshing(false);
                    }

                    mAdapter.notifyDataSetChanged();
                    mainListView.onTouchModeChanged(true);  // 이놈이 layoutChildren을 한다....
                }
            }

            @Override
            public void onFailure(Call<ModelInsta> call, Throwable t)
            {
                t.printStackTrace();
            }
        });
    }

    private void setPaddings(int padding) {
        mainListView.setClipToPadding(false);
        mainListView.setPadding(padding, 0, padding, 0);
        mAdapter.setPadding(padding);
    }

    private void setBackgroundColors(String htmlColor) {
        int color = Color.parseColor(htmlColor);
        mainListView.setPaintColor(color); // PLA libary버그로 인해.. headerView가 더해지면 배경색이 바뀌는 버그..
        mainView    .setBackgroundColor(color);
        mAdapter    .setBackgroundColor(color);
    }

    private boolean isSearchUserIdValid(String userId) {
        return !userId.equals("") && !userId.isEmpty();
    }
}

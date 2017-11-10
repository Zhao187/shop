package com.zhao.shopapp.fragment;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.http.RequestParams;
import com.zhao.shopapp.R;
import com.zhao.shopapp.fragment.base.BaseFragment;

import butterknife.Bind;

/**
 * 分类
 */
public class ClazzFragment extends BaseFragment {

    @Bind(R.id.title_left)
    ImageView titleLeft;
    @Bind(R.id.title_tv)
    TextView titleTv;
    @Bind(R.id.title_right)
    ImageView titleRight;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_clazz;
    }

    @Override
    public void initData(String content) {

    }

    @Override
    public void initTitle() {
        titleLeft.setVisibility(View.INVISIBLE);
        titleTv.setText("分类");
        titleRight.setVisibility(View.INVISIBLE);
    }

    @Override
    protected String getUrl() {
        return "";
    }

    @Override
    protected RequestParams getParams() {
        return new RequestParams();
    }

}

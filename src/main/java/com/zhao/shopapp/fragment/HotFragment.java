package com.zhao.shopapp.fragment;


import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.loopj.android.http.RequestParams;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.zhao.shopapp.R;
import com.zhao.shopapp.adapter.HotFragmentAdapter;
import com.zhao.shopapp.fragment.base.BaseFragment;
import com.zhao.shopapp.fragment.hot.ProductHotFragment;
import com.zhao.shopapp.fragment.hot.ProductListFragment;
import com.zhao.shopapp.fragment.hot.ProductRecommendFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * 热门
 */
public class HotFragment extends BaseFragment {
    @Bind(R.id.title_left)
    ImageView titleLeft;
    @Bind(R.id.title_tv)
    TextView titleTv;
    @Bind(R.id.title_right)
    ImageView titleRight;
    @Bind(R.id.tab_indictor)
    SmartTabLayout tabIndictor;
    @Bind(R.id.pager)
    ViewPager pager;

    private List<BaseFragment> fragmentList = new ArrayList<>();

    @Override
    public int getLayoutId() {
        return R.layout.fragment_hot;
    }

    @Override
    public void initData(String content) {
        initFragment();
        HotFragmentAdapter hotFragmentAdapter=new HotFragmentAdapter(getFragmentManager(),fragmentList);
        pager.setAdapter(hotFragmentAdapter);
        tabIndictor.setViewPager(pager);
    }

    /**
     * 初始化Fragment
     */
    public void initFragment()
    {
        fragmentList.add(new ProductListFragment());
        fragmentList.add(new ProductHotFragment());
        fragmentList.add(new ProductRecommendFragment());
    }
    @Override
    public void initTitle() {
        titleLeft.setVisibility(View.INVISIBLE);
        titleTv.setText("热门");
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

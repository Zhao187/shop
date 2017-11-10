package com.zhao.shopapp.fragment;


import android.os.SystemClock;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.loopj.android.http.RequestParams;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.zhao.shopapp.R;
import com.zhao.shopapp.adapter.ImagePageAdapter;
import com.zhao.shopapp.bean.Index;
import com.zhao.shopapp.commont.AppNetConfig;
import com.zhao.shopapp.fragment.base.BaseFragment;
import com.zhao.shopapp.view.CircleProgress;

import butterknife.Bind;

/**
 * 主页
 */
public class HomeFragment extends BaseFragment {

    @Bind(R.id.title_left)
    ImageView titleLeft;
    @Bind(R.id.title_tv)
    TextView titleTv;
    @Bind(R.id.title_right)
    ImageView titleRight;
    @Bind(R.id.vp_barner)
    ViewPager vpBarner;
    @Bind(R.id.viewpagertab)
    SmartTabLayout viewpagertab;
    @Bind(R.id.p_yearlv)
    TextView pYearlv;
    @Bind(R.id.p_progresss)
    CircleProgress pProgresss;
    private ImagePageAdapter imagePageAdapter;

    private Index index;

    private int totalProgress = 0;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public void initData(String content) {
        if (!TextUtils.isEmpty(content)) {
            index = new Index();
            JSONObject jsonObject = JSON.parseObject(content);
            String imageArr = jsonObject.getString("imageArr");
            index.setImageArr(JSON.parseArray(imageArr, Index.ImageArrBean.class));

            String product = jsonObject.getString("proInfo");
            index.setProInfo(JSON.parseObject(product, Index.ProInfoBean.class));

            imagePageAdapter = new ImagePageAdapter(index.getImageArr());
            vpBarner.setAdapter(imagePageAdapter);
            viewpagertab.setViewPager(vpBarner);

            pYearlv.setText(index.getProInfo().getYearLv());

            totalProgress = Integer.parseInt(index.getProInfo().getProgress());

            new Thread(runnable).start();
        }
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            int templeProgress = 0;
            while (templeProgress <= totalProgress) {
                pProgresss.setProgress(templeProgress);
                templeProgress++;
                SystemClock.sleep(100);
            }
        }
    };

    @Override
    public void initTitle() {
        titleLeft.setVisibility(View.INVISIBLE);
        titleTv.setText("主页");
        titleRight.setVisibility(View.INVISIBLE);
    }

    @Override
    protected String getUrl() {
        return AppNetConfig.INDEX;
    }

    @Override
    protected RequestParams getParams() {
        return new RequestParams();
    }

}

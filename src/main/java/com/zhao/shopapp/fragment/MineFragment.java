package com.zhao.shopapp.fragment;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.loopj.android.http.RequestParams;
import com.zhao.shopapp.R;
import com.zhao.shopapp.activity.BarChartActivity;
import com.zhao.shopapp.activity.LineChartActivity;
import com.zhao.shopapp.activity.LockActivity;
import com.zhao.shopapp.activity.PieCharActivity;
import com.zhao.shopapp.activity.base.BaseActivity;
import com.zhao.shopapp.fragment.base.BaseFragment;
import com.zhao.shopapp.util.BtimapUtil;
import com.zhao.shopapp.util.UIUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 我的
 */
public class MineFragment extends BaseFragment {
    @Bind(R.id.title_left)
    ImageView titleLeft;
    @Bind(R.id.title_tv)
    TextView titleTv;
    @Bind(R.id.iv_icon)
    ImageView ivIcon;
    @Bind(R.id.title_right)
    ImageView titleRight;
    @Bind(R.id.icon_time)
    RelativeLayout iconTime;
    @Bind(R.id.textView11)
    TextView textView11;
    @Bind(R.id.relativeLayout1)
    RelativeLayout relativeLayout1;
    @Bind(R.id.chongzhi)
    ImageView chongzhi;
    @Bind(R.id.tixian)
    ImageView tixian;
    @Bind(R.id.ll_touzi)
    TextView llTouzi;
    @Bind(R.id.ll_touzi_zhiguan)
    TextView llTouziZhiguan;
    @Bind(R.id.ll_zichang)
    TextView llZichang;
    @Bind(R.id.ll_zhanquan)
    TextView llZhanquan;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initData(String content) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.timg);
        Bitmap scaleBitmap = BtimapUtil.zoom(bitmap, UIUtils.px2dp(400), UIUtils.px2dp(400));
        ivIcon.setImageBitmap(BtimapUtil.circleBitmap(scaleBitmap));
    }

    @Override
    public void initTitle() {
        titleLeft.setVisibility(View.INVISIBLE);
        titleTv.setText("我的");
    }

    @Override
    protected String getUrl() {
        return "";
    }

    @Override
    protected RequestParams getParams() {
        return new RequestParams();
    }

   @OnClick(R.id.ll_touzi_zhiguan)
   public void gotoTouziZhiguan()
   {
       ((BaseActivity) getActivity()).startActivity(LineChartActivity.class,null);
   }

   @OnClick(R.id.ll_touzi)
    public void gotoTouzi()
   {
       ((BaseActivity) getActivity()).startActivity(BarChartActivity.class,null);
   }

    @OnClick(R.id.ll_zhanquan)
    public void gotoZhanquan()
    {
        ((BaseActivity) getActivity()).startActivity(LockActivity.class,null);
    }

    @OnClick(R.id.ll_zichang)
    public void gotoZichang()
    {
        ((BaseActivity) getActivity()).startActivity(PieCharActivity.class,null);
    }
}

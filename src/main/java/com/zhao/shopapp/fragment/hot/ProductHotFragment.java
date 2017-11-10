package com.zhao.shopapp.fragment.hot;


import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.loopj.android.http.RequestParams;
import com.zhao.shopapp.R;
import com.zhao.shopapp.fragment.base.BaseFragment;
import com.zhao.shopapp.util.DrawableUtil;
import com.zhao.shopapp.util.UIUtils;
import com.zhao.shopapp.view.FlowLayout;

import java.util.Random;

import butterknife.Bind;

/**
 * 热门商品
 */
public class ProductHotFragment extends BaseFragment {
    @Bind(R.id.flow)
    FlowLayout flow;

    private String[] datas = new String[]{"新手计划", "乐享活系列90天计划", "钱包", "30天理财计划(加息2%)",
            "林业局投资商业经营与大捞一笔", "中学老师购买车辆", "屌丝下海经商计划", "新西游影视拍", "Java培训老师自己周转", "HelloWorld", "C++-C-ObjectC-java", "Android vs ios", "算法与数据结构", "JNI与NDK", "team working"};
    private Random random;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_product_hot;
    }

    @Override
    protected void initData(String content) {
        random = new Random();
        for (String data : datas) {
            TextView tv = new TextView(getActivity());
            ViewGroup.MarginLayoutParams mp = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            mp.leftMargin = UIUtils.dp2px(10);
            mp.rightMargin = UIUtils.dp2px(10);
            mp.topMargin = UIUtils.dp2px(10);
            mp.bottomMargin = UIUtils.dp2px(10);
            tv.setLayoutParams(mp);
            int r=random.nextInt(210);
            int g=random.nextInt(210);
            int b=random.nextInt(210);
            tv.setBackground(DrawableUtil.getSelector(DrawableUtil.getDrawable(Color.rgb(r,g,b),UIUtils.px2dp(5)),DrawableUtil.getDrawable(Color.rgb(g,r,b),UIUtils.px2dp(5))));
            tv.setText(data);
            int padding = UIUtils.dp2px(5);
            tv.setPadding(padding, padding, padding, padding);
            tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            flow.addView(tv);
        }
    }

    @Override
    protected void initTitle() {

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

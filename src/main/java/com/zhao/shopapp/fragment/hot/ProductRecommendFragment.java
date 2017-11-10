package com.zhao.shopapp.fragment.hot;


import com.loopj.android.http.RequestParams;
import com.zhao.shopapp.R;
import com.zhao.shopapp.fragment.base.BaseFragment;

/**
 * 推荐商品
 */
public class ProductRecommendFragment extends BaseFragment {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_product_recommend;
    }

    @Override
    protected void initData(String content) {

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

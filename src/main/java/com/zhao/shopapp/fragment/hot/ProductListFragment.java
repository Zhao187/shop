package com.zhao.shopapp.fragment.hot;


import android.text.TextUtils;
import android.widget.ListView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.loopj.android.http.RequestParams;
import com.zhao.shopapp.R;
import com.zhao.shopapp.adapter.product.adapter2.ListAdapter1;
import com.zhao.shopapp.bean.Product;
import com.zhao.shopapp.commont.AppNetConfig;
import com.zhao.shopapp.fragment.base.BaseFragment;

import butterknife.Bind;

/**
 * 商品列表
 */
public class ProductListFragment extends BaseFragment {
    @Bind(R.id.lv)
    ListView lv;
    private Product product;

    private ListAdapter1 listAdapter;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_product_list;
    }

    @Override
    protected void initData(String content) {
        if (!TextUtils.isEmpty(content)) {
            product = new Product();
            JSONObject jsonObject = JSON.parseObject(content);
            String data = jsonObject.getString("data");
            product.setData(JSON.parseArray(data, Product.DataBean.class));

            listAdapter=new ListAdapter1(product.getData());
            lv.setAdapter(listAdapter);
        }
    }

    @Override
    protected void initTitle() {

    }

    @Override
    protected String getUrl() {
        return AppNetConfig.PRODUCT;
    }

    @Override
    protected RequestParams getParams() {
        return new RequestParams();
    }
}

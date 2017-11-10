package com.zhao.shopapp.adapter.product.adapter2;

import android.view.View;
import android.widget.TextView;

import com.zhao.shopapp.R;
import com.zhao.shopapp.adapter.product.adapter2.base.BaseHolder;
import com.zhao.shopapp.adapter.product.adapter2.base.BaseListAdapter1;
import com.zhao.shopapp.bean.Product;
import com.zhao.shopapp.util.UIUtils;
import com.zhao.shopapp.view.CircleProgress;

import java.util.List;

import butterknife.Bind;

/**
 * 商品列表适配器
 */

public class ListAdapter1 extends BaseListAdapter1<Product.DataBean> {
    public ListAdapter1(List<Product.DataBean> list) {
        super(list);
    }

    @Override
    public BaseHolder getHoler() {
        return new ViewHolder();
    }

    static class ViewHolder extends BaseHolder<Product.DataBean> {
        @Bind(R.id.p_name)
        TextView pName;
        @Bind(R.id.p_money)
        TextView pMoney;
        @Bind(R.id.p_yearlv)
        TextView pYearlv;
        @Bind(R.id.p_suodingdays)
        TextView pSuodingdays;
        @Bind(R.id.p_minzouzi)
        TextView pMinzouzi;
        @Bind(R.id.p_progresss)
        CircleProgress pProgresss;

        @Override
        public void refreshView() {
            Product.DataBean dataBean = getmData();
            pMinzouzi.setText(dataBean.getMinTouMoney());
            pMoney.setText(dataBean.getMoney());
            pName.setText(dataBean.getName());
            pSuodingdays.setText(dataBean.getSuodingDays());
            pYearlv.setText(dataBean.getYearLv());
            pProgresss.setProgress(Integer.parseInt(dataBean.getProgress()));
        }

        @Override
        public View initView() {
            return UIUtils.getXMLView(R.layout.item_product_list);
        }
    }
}

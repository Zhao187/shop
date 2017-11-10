package com.zhao.shopapp.adapter;

import android.view.View;
import android.widget.TextView;

import com.zhao.shopapp.R;
import com.zhao.shopapp.adapter.product.adapter1.base.BaseListAdapter;
import com.zhao.shopapp.bean.Product;
import com.zhao.shopapp.util.UIUtils;
import com.zhao.shopapp.view.CircleProgress;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 列表适配器
 */

public class ListAdapter extends BaseListAdapter<Product.DataBean> {

    public ListAdapter(List<Product.DataBean> list) {
        super(list);
    }

    @Override
    public View getConvertView() {
        return UIUtils.getXMLView(R.layout.item_product_list);
    }

    @Override
    public void setData(View converView, Product.DataBean dataBean) {
        ViewHolder viewHolder=new ViewHolder(converView);
        viewHolder.pMinzouzi.setText(dataBean.getMinTouMoney());
        viewHolder.pMoney.setText(dataBean.getMoney());
        viewHolder.pName.setText(dataBean.getName());
        viewHolder.pSuodingdays.setText(dataBean.getSuodingDays());
        viewHolder.pYearlv.setText(dataBean.getYearLv());
        viewHolder.pProgresss.setProgress(Integer.parseInt(dataBean.getProgress()));
    }

    static class ViewHolder {
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

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}

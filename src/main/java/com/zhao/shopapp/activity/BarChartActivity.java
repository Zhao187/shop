package com.zhao.shopapp.activity;

import android.graphics.Typeface;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.zhao.shopapp.R;
import com.zhao.shopapp.activity.base.BaseActivity;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.OnClick;

public class BarChartActivity extends BaseActivity {
    @Bind(R.id.title_tv)
    TextView titleTv;
    @Bind(R.id.title_right)
    ImageView titleRight;
    @Bind(R.id.chart)
    BarChart chart;
    private Typeface mTf;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_bar_chart;
    }

    @Override
    protected void initData() {
        // apply styling
        mTf = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");
        chart.getDescription().setEnabled(false);
        chart.setDrawGridBackground(false);
        chart.setDrawBarShadow(false);

        IAxisValueFormatter xAxisFormatter = new MonthAxisValueFormatter();
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTypeface(mTf);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(true);
        xAxis.setLabelCount(11);
        xAxis.setValueFormatter(xAxisFormatter);

        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setLabelCount(5, false);
        leftAxis.setTypeface(mTf);
        //绘制Y轴方向上最高的顶部的值距离整个图表的top距离
        leftAxis.setSpaceTop(30f);

        chart.getAxisRight().setEnabled(false);

        BarData mChartData = generateDataBar();
        // set data
        chart.setData(mChartData);
        // do not forget to refresh the chart
//        holder.chart.invalidate();
        chart.animateY(700);

    }

    /**
     * generates a random ChartData object with just one DataSet
     *
     * @return
     */
    private BarData generateDataBar() {

        ArrayList<BarEntry> entries = new ArrayList<BarEntry>();

        for (int i = 0; i < 12; i++) {
            entries.add(new BarEntry(i,(int) (Math.random() * 70) + 30));
        }
        BarDataSet d = new BarDataSet(entries, "柱状图");
        //绘制数据集合时指定属性
        d.setColors(ColorTemplate.VORDIPLOM_COLORS);
        d.setHighLightAlpha(255);
        BarData cd = new BarData(d);
        return cd;
    }


    @Override
    protected void initTitle() {
        titleTv.setText("投资直观柱状图");
        titleRight.setVisibility(View.INVISIBLE);
    }

    @OnClick(R.id.title_left)
    public void closeActivity() {
        closeCurrentActivity();
    }
}

package com.zhao.shopapp.activity;

import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.zhao.shopapp.R;
import com.zhao.shopapp.activity.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class PieCharActivity extends BaseActivity {

    @Bind(R.id.title_tv)
    TextView titleTv;
    @Bind(R.id.title_right)
    ImageView titleRight;
    @Bind(R.id.chart)
    PieChart chart;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pie_char;
    }

    @Override
    protected void initData() {
        chart.setUsePercentValues(false);
        chart.getDescription().setEnabled(false);

        chart.setDrawHoleEnabled(true);
        chart.setHoleColor(Color.WHITE);
        chart.setHoleRadius(42f);
        //绘制包裹最内层圆的外层圆的半径
        chart.setTransparentCircleRadius(57f);
        chart.setCenterText("资产分配");
        chart.setCenterTextSize(14f);

        PieData mChartData = generateDataPie();
        mChartData.setValueTextSize(14f);
        mChartData.setValueTextColor(Color.RED);
        // set data
        chart.setData((PieData) mChartData);

        //饼图指示器绘制
        Legend l = chart.getLegend();
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        l.setYEntrySpace(0f);
        l.setYOffset(20f);

        // do not forget to refresh the chart
        // holder.chart.invalidate();
        chart.animateXY(900, 900);
    }

    /**
     * generates a random ChartData object with just one DataSet
     *
     * @return
     */
    private PieData generateDataPie() {

        List<PieEntry> entries = new ArrayList<PieEntry>();

        for (int i = 0; i < 4; i++) {
            entries.add(new PieEntry((float) (Math.random() * 70) + 30,getQuarters().get(i)));
        }

        PieDataSet d = new PieDataSet(entries, "");

        // space between slices
        //绘制图表数据集合的时候指定属性
        d.setSliceSpace(1f);
        d.setColors(ColorTemplate.VORDIPLOM_COLORS);

        PieData cd = new PieData(d);
        return cd;
    }

    private ArrayList<String> getQuarters() {

        ArrayList<String> q = new ArrayList<String>();
        q.add("1st Quarter");
        q.add("2nd Quarter");
        q.add("3rd Quarter");
        q.add("4th Quarter");

        return q;
    }

    @Override
    protected void initTitle() {
        titleTv.setText("投资饼状图");
        titleRight.setVisibility(View.INVISIBLE);
    }
    @OnClick(R.id.title_left)
    public void closeActivity() {
        closeCurrentActivity();
    }
}

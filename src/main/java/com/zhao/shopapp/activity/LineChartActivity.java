package com.zhao.shopapp.activity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.zhao.shopapp.R;
import com.zhao.shopapp.activity.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

public class LineChartActivity extends BaseActivity {
    @Bind(R.id.title_tv)
    TextView titleTv;
    @Bind(R.id.title_right)
    ImageView titleRight;
    @Bind(R.id.chart)
    LineChart chart;

    private Typeface mTf;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_line_chart;
    }

    @Override
    protected void initData() {
        Toast.makeText(this, "添加了一个新功能", Toast.LENGTH_SHORT).show();
        mTf = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");
        chart.getDescription().setEnabled(false);
        chart.setDrawGridBackground(true);

        //绘制图表的X轴
        IAxisValueFormatter xAxisFormatter = new MonthAxisValueFormatter();
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTypeface(mTf);
        xAxis.setDrawGridLines(false);
        xAxis.setDrawAxisLine(true);
        xAxis.setLabelCount(11);
        xAxis.setValueFormatter(xAxisFormatter);

        //绘制图表的Y轴
        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setTypeface(mTf);
        //false:代表值是平均分配的;
        leftAxis.setLabelCount(7, false);

        chart.getAxisRight().setEnabled(false);

        // set data
        chart.setData(generateDataLine());
        // do not forget to refresh the chart
        // holder.chart.invalidate();
        chart.animateX(750);
    }

    private LineData generateDataLine() {

        ArrayList<Entry> e1 = new ArrayList<Entry>();
        for (int i = 0; i < 12; i++) {
            e1.add(new Entry(i,(int) (Math.random() * 65) + 40));
        }
        LineDataSet d1 = new LineDataSet(e1, "折线图");
        //指定数据集合绘制时候的属性
        d1.setLineWidth(5f);
        d1.setCircleSize(7.5f);
        d1.setHighLightColor(Color.BLACK);
        d1.setDrawValues(true);

        List<ILineDataSet> sets = new ArrayList<ILineDataSet>();
        sets.add(d1);

        LineData cd = new LineData(sets);
        return cd;
    }
    @Override
    protected void initTitle() {
        titleTv.setText("投资直观线性图");
        titleRight.setVisibility(View.INVISIBLE);
    }
    @OnClick(R.id.title_left)
    public void closeActivity() {
        closeCurrentActivity();
    }
}

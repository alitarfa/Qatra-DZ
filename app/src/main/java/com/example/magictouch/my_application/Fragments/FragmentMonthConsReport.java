package com.example.magictouch.my_application.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.magictouch.my_application.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tarfa on 6/8/18.
 */

public class FragmentMonthConsReport extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.month_consomation_fragment_report, container, false);

        BarChart chart = (BarChart) view.findViewById(R.id.chart);
        chart.getAxisRight().setEnabled(false);

        List<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(0f, 1200f));
        entries.add(new BarEntry(1f, 1000f));
        entries.add(new BarEntry(2f, -800f));
        entries.add(new BarEntry(3f, 1500f));


        BarDataSet set = new BarDataSet(entries, "");
        BarData data = new BarData(set);
        data.setBarWidth(0.9f); // set custom bar width

        final String[] quarters = new String[] { "Semain 1", "Semain 2", "Semain 3", "Semain 4"};

        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(10f);
        xAxis.setTextColor(Color.RED);
        xAxis.setDrawAxisLine(false);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularityEnabled(true);
        xAxis.setGranularity(1f);



        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
               /* return mapX.get((int)value);*/
                return quarters[(int) value];
            }
        });

        chart.setData(data);
        chart.setFitBars(true); // make the x-axis fit exactly all bars
        chart.invalidate(); // refresh

        return  view;

    }


}
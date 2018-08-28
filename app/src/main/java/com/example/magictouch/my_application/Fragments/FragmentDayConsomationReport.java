package com.example.magictouch.my_application.Fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.magictouch.my_application.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by tarfa on 6/7/18.
 */

public class FragmentDayConsomationReport extends Fragment {

    HashMap<Integer,String> mapX ;
    HashMap<Integer,String> mapY ;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.day_consomation_report_fragment, container, false);


        LineChart chart = (LineChart)view.findViewById(R.id.chart);
        //chart.getAxisRight().setEnabled(false);

        List<Entry> entries = new ArrayList<Entry>();
        mapX=new HashMap<>();
        mapY=new HashMap<>();




        entries.add(new Entry(0,0f));
        entries.add(new Entry(1,40f));
        entries.add(new Entry(2,30f));
        entries.add(new Entry(3,20f));
        entries.add(new Entry(4,10f));
        entries.add(new Entry(5,0f));
        entries.add(new Entry(6,-10f));
        entries.add(new Entry(7,-10f));
        entries.add(new Entry(8,-50f));
        entries.add(new Entry(9,30f));
        entries.add(new Entry(10,10f));
        entries.add(new Entry(11,60f));


        // for the x and y line



        mapX.put(0,"0 h");
        mapX.put(1,"2 h");
        mapX.put(2,"4 h");
        mapX.put(3,"6 h");
        mapX.put(4,"8 h");
        mapX.put(5,"10 h");
        mapX.put(6,"12 h");
        mapX.put(7,"14 h");
        mapX.put(8,"16 h");
        mapX.put(9,"18 h");
        mapX.put(10,"20 h");
        mapX.put(12,"22 h");



        mapY.put(0,"10 L");
        mapY.put(1,"20 L");
        mapY.put(2,"30 L");
        mapY.put(3,"40 L");
        mapY.put(4,"50 L");
        mapY.put(5,"60 L");
        mapY.put(6,"60 L");
        mapY.put(7,"60 L");
        mapY.put(8,"60 L");
        mapY.put(9,"60 L");
        mapY.put(10,"60 L");
        mapY.put(11,"60 L");



        final String[] quarters = new String[] { "2H", "4H", "6H", "8H" ,"10H", "12H", "14H", "16H" ,"18H", "20H", "22H", "24H"};
        final String[] cost = new String[] { "10", "20", "30", "40" ,"50", "60", "70", "80" ,"90", "100", "110", "120"};

        LineDataSet lineDataSet=new LineDataSet(entries,"");
        LineData lineData = new LineData(lineDataSet);
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextSize(10f);
        xAxis.setTextColor(Color.RED);
        xAxis.setDrawAxisLine(false);
        xAxis.setDrawGridLines(true);
        xAxis.setLabelCount(mapX.size());
        xAxis.setGranularityEnabled(true);
        xAxis.setGranularity(1f);

        YAxis left = chart.getAxisLeft();
        YAxis right=chart.getAxisRight();

        left.setDrawLabels(false); // no axis labels
        left.setDrawAxisLine(false); // no axis line
        left.setDrawGridLines(false); // no grid lines
        left.setDrawZeroLine(true); // draw a zero line

/*


        right.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return cost[(int) value];
            }
        });
*/


        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return quarters[(int) value];
            }
        });



        left.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return mapY.get((int)value);
            }
        });

         chart.setData(lineData);
         chart.invalidate(); // refresh








        return view;
    }
}

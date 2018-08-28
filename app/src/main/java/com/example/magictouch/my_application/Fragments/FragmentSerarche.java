package com.example.magictouch.my_application.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.magictouch.my_application.AdapterPayment;
import com.example.magictouch.my_application.ModelPayment;
import com.example.magictouch.my_application.R;
import com.example.magictouch.my_application.database.DataBase;
import com.twinkle94.monthyearpicker.picker.YearMonthPickerDialog;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ArrayList;


/**
 * Created by tarfa on 6/9/18.
 */

public class FragmentSerarche extends Fragment {


    RecyclerView recyclerView;
    FragmentTransaction ft;
    FloatingActionButton searchBtn;
    Button yearDate;
    TextView textView;
    DataBase dataBase;
    public String date;

    public   ArrayList<ModelPayment> list;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.search_fragment,container,false);
        recyclerView =view.findViewById(R.id.recycle_facture);

        FloatingActionButton search=view.findViewById(R.id.floatingActionButton);
        yearDate=view.findViewById(R.id.year_picker);
        dataBase=new DataBase(getContext());
        list=new ArrayList<>();
        //list.add(new ModelPayment(1,"1520","2000/10/10","2001/01/01"));

        AdapterPayment adapterPayment= new AdapterPayment(getContext(),getFragmentManager());
        recyclerView.setAdapter(adapterPayment);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        /**
         * time picker
         */


        Calendar calendar = Calendar.getInstance();
        calendar.set(2010,01,01);

        YearMonthPickerDialog yearMonthPickerDialog = new YearMonthPickerDialog(getContext(), new YearMonthPickerDialog.OnDateSetListener() {
            @Override
            public void onYearMonthSet(int year, int month) {
                Toast.makeText(getContext(), ""+year, Toast.LENGTH_SHORT).show();
                yearDate.setText(String.valueOf(year));
                date=String.valueOf(year);
            }
        });



        yearDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yearMonthPickerDialog.show();
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list = dataBase.searchByDate(date);
                adapterPayment.setList(list);
                adapterPayment.notifyDataSetChanged();
            }
        });


        return view;
    }
}

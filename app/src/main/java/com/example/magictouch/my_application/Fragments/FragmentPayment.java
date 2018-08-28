package com.example.magictouch.my_application.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.magictouch.my_application.AdapterPayment;
import com.example.magictouch.my_application.ModelPayment;
import com.example.magictouch.my_application.R;

import java.util.ArrayList;

/**
 * Created by tarfa on 6/6/18.
 */

public class FragmentPayment extends Fragment {


    RecyclerView recyclerView;
    FragmentTransaction ft;
    FloatingActionButton searchBtn;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.payment_fragment, container, false);
        recyclerView =view.findViewById(R.id.recycle_payment);
        searchBtn =view.findViewById(R.id.searche_btn);

        ft= getFragmentManager().beginTransaction();

        ArrayList<ModelPayment> list=new ArrayList<>();
        list.add(new ModelPayment(1,"1520","2000/10/10","2001/01/01"));
        list.add(new ModelPayment(1,"1520","2000/10/10","2001/01/01"));
        list.add(new ModelPayment(1,"1520","2000/10/10","2001/01/01"));
        list.add(new ModelPayment(1,"1520","2000/10/10","2001/01/01"));
        list.add(new ModelPayment(1,"1520","2000/10/10","2001/01/01"));


        AdapterPayment adapterPayment= new AdapterPayment(getContext(),getFragmentManager());
        adapterPayment.setList(list);
        recyclerView.setAdapter(adapterPayment);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentSerarche fragment=new FragmentSerarche();
                ft.replace(R.id.container, fragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        return view;
    }

}
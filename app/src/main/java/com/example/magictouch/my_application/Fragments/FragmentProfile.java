package com.example.magictouch.my_application.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.magictouch.my_application.Authentication.Login;
import com.example.magictouch.my_application.R;

/**
 * Created by tarfa on 6/6/18.
 */

public class FragmentProfile extends Fragment {

    FragmentTransaction ft;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_fragment, container, false);
        ft=getFragmentManager().beginTransaction();

        CardView cardView=view.findViewById(R.id.info_btn);
        CardView setting=view.findViewById(R.id.setting_card);
        CardView singOut=view.findViewById(R.id.sing_out_btn);
        CardView aboutUs=view.findViewById(R.id.aboutUs);


        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentPersonalInfo fragment=new FragmentPersonalInfo();
                ft.replace(R.id.container, fragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentSetting fragmentSetting=new FragmentSetting();
                fragmentSetting.show(getFragmentManager(),"dialouge");
            }
        });

        singOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), Login.class));
                getActivity().finish();
            }
        });


        aboutUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentAboutUs fragmentAboutUs=new FragmentAboutUs();
                fragmentAboutUs.show(getFragmentManager(),"dialog");
            }
        });



        return view;
    }

}
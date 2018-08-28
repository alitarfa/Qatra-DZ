package com.example.magictouch.my_application.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.magictouch.my_application.R;
import com.example.magictouch.my_application.Utility;
import com.example.magictouch.my_application.notification.AdapterNotification;
import com.example.magictouch.my_application.notification.InformationNotification;


import java.util.ArrayList;

/**
 * Created by tarfa on 6/6/18.
 */

public class FragmentNotification extends Fragment {

    RecyclerView recyclerView;
    ArrayList<InformationNotification> list =new ArrayList<>();
    AdapterNotification adapterNotification;
    FragmentTransaction ft;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.notification_fragment,container,false);

        recyclerView=view.findViewById(R.id.recycle_notification);
        ft= getFragmentManager().beginTransaction();

        list.add(new InformationNotification("moité de consommation","2017-10-25", Utility.CONSOMATION_ALL));
        list.add(new InformationNotification("Vous avez consommé la quantité d'aujourd'hui","2017-10-25",Utility.CONSOMATION_SOME));
        list.add(new InformationNotification("vous n'avez pas payer la facture","2017-10-25",Utility.FACTURE_NOT_PAYED_YET));
        list.add(new InformationNotification("Attention ! vous avez un gaspillage d'eau","2017-10-25",Utility.WASTE_WATER));



        adapterNotification = new AdapterNotification(this,getContext(),ft,getFragmentManager());
        adapterNotification.setList(list);
        recyclerView.setAdapter(adapterNotification);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }
}

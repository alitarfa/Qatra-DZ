package com.example.magictouch.my_application.Dialog;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.magictouch.my_application.R;
import com.example.magictouch.my_application.Utility;

/**
 * Created by tarfa on 6/7/18.
 */

@SuppressLint("ValidFragment")
public class DialogInfoNotification extends DialogFragment {

    String content;
    String type;
    @SuppressLint("ValidFragment")
    public DialogInfoNotification(String title, String content) {
        this.content=content;
        this.type=title;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.dialog_info_notification,container,false);
        TextView textView=view.findViewById(R.id.type_notification);
        TextView typeIcon=view.findViewById(R.id.image_type_notification);

        textView.setText(content);

        if (type.equals(Utility.CONSOMATION_ALL)){
            typeIcon.setText("S");
            typeIcon.setBackground(getContext().getResources().getDrawable(R.drawable.circle_shape_consoma_all));
        }

        if (type.equals(Utility.CONSOMATION_SOME)){
            typeIcon.setText("A");
            typeIcon.setBackground(getContext().getResources().getDrawable(R.drawable.circle_shape_consoma_some));

        }

        if (type.equals(Utility.FACTURE_NOT_PAYED_YET)){
             typeIcon.setText("F");
             typeIcon.setBackground(getContext().getResources().getDrawable(R.drawable.circle_shape_facture));
        }

        if (type.equals(Utility.WASTE_WATER)){
            typeIcon.setText("W");
            typeIcon.setBackground(getContext().getResources().getDrawable(R.drawable.wasting_water));
        }


        return view;
    }
}

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
public class DialogInfoPayment extends DialogFragment {

    @SuppressLint("ValidFragment")
    public DialogInfoPayment() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.dialog_info_payment,container,false);

        return view;
    }
}

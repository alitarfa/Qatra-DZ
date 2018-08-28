package com.example.magictouch.my_application.Dialog;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.magictouch.my_application.R;

/**
 * Created by tarfa on 6/9/18.
 */

@SuppressLint("ValidFragment")
public class DialogDeleteFragment extends DialogFragment {

    public OnDeleteNotification onDeleteNotification;
    public int position;

    public DialogDeleteFragment(OnDeleteNotification onDeleteNotification, int position) {
        this.onDeleteNotification = onDeleteNotification;
        this.position = position;
    }


    public interface OnDeleteNotification{
        public void onDelete(int id);
        public void onCancel();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.delete_notification_dialog,container,false);
        TextView cancelBtn=view.findViewById(R.id.no_btn);
        TextView ouiBtn=view.findViewById(R.id.oui_btn);

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDeleteNotification.onCancel();
            }
        });

        ouiBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onDeleteNotification.onDelete(position);
            }
        });
        return view;
    }
}

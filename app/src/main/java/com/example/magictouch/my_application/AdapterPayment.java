package com.example.magictouch.my_application;

import android.content.Context;
import android.graphics.drawable.RippleDrawable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.magictouch.my_application.Dialog.DialogInfoPayment;

import java.util.ArrayList;

/**
 * Created by tarfa on 6/9/18.
 */

public class AdapterPayment extends RecyclerView.Adapter<AdapterPayment.ViewHoderClass> {

    LayoutInflater inflater;
    Context context;
    FragmentManager fragmentManager;
    ArrayList<ModelPayment>  list=new ArrayList<>();

    public AdapterPayment(Context context, FragmentManager fragmentManager) {
        inflater= LayoutInflater.from(context);
        this.context=context;
        this.fragmentManager=fragmentManager;
    }

    public void setList(ArrayList<ModelPayment> list) {
        this.list = list;
    }

    @Override
    public ViewHoderClass onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.item_payment,parent,false);
        return new ViewHoderClass(view);
    }

    @Override
    public void onBindViewHolder(ViewHoderClass holder, int position) {

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogInfoPayment dialogInfoPayment=new DialogInfoPayment();
                dialogInfoPayment.show(fragmentManager,"dialog");
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    private void openDialog(View view) {

    }


    // my be static
    public class ViewHoderClass extends RecyclerView.ViewHolder {

        CardView cardView;

        public ViewHoderClass(View itemView) {
            super(itemView);
            cardView=itemView.findViewById(R.id.card);

        }


    }
}

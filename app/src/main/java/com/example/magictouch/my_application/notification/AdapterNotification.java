package com.example.magictouch.my_application.notification;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.magictouch.my_application.Dialog.DialogDeleteFragment;
import com.example.magictouch.my_application.Dialog.DialogInfoNotification;
import com.example.magictouch.my_application.Fragments.FragmentNotification;
import com.example.magictouch.my_application.R;
import com.example.magictouch.my_application.Utility;

import java.util.ArrayList;


/**
 * Created by tarfa on 6/6/18.
 */

public class AdapterNotification extends RecyclerView.Adapter<AdapterNotification.ViewHoderClass> implements DialogDeleteFragment.OnDeleteNotification {

    ArrayList<InformationNotification> list= new ArrayList<>();
    LayoutInflater inflater;
    Context context;
    FragmentTransaction fragmentTransaction;
    FragmentManager fragmentManager;
    FragmentNotification notification;
    public AdapterNotification adapterNotification=this;
    DialogDeleteFragment dialogDeleteFragment;



    public AdapterNotification(FragmentNotification notification,Context context, FragmentTransaction fragmentTransactions,FragmentManager fragmentManager) {
       inflater= LayoutInflater.from(context);
       this.context=context;
        fragmentTransaction =fragmentManager.beginTransaction();
        this.fragmentManager=fragmentManager;
        this.notification=notification;
    }

    public void setList(ArrayList<InformationNotification> list) {
        this.list=list;
    }

    public void addItemToList(InformationNotification notification) {
        this.list.add(notification);
    }



    @Override
    public ViewHoderClass onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=inflater.inflate(R.layout.item_notification,parent,false);
        return new ViewHoderClass(view);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onBindViewHolder(ViewHoderClass holder, int position) {

        if (list.get(position).getType().equals(Utility.CONSOMATION_ALL)){
            holder.typeIcon.setText("S");
            holder.typeIcon.setBackground(context.getResources().getDrawable(R.drawable.circle_shape_consoma_all));
        }

        if (list.get(position).getType().equals(Utility.CONSOMATION_SOME)){
            holder.typeIcon.setText("A");
            holder.typeIcon.setBackground(context.getResources().getDrawable(R.drawable.circle_shape_consoma_some));
        }

        if (list.get(position).getType().equals(Utility.FACTURE_NOT_PAYED_YET)){
            holder.typeIcon.setText("F");
            holder.typeIcon.setBackground(context.getResources().getDrawable(R.drawable.circle_shape_facture));
        }

        if (list.get(position).getType().equals(Utility.WASTE_WATER)){
            holder.typeIcon.setText("W");
            holder.typeIcon.setBackground(context.getResources().getDrawable(R.drawable.wasting_water));
        }


        /**
         **for the click of the item
         **/
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogInfoNotification dialogInfoNotification=new DialogInfoNotification(list.get(position).getType(),list.get(position).getTitle());
                Fragment prev = fragmentManager.findFragmentByTag("dialog");
                if (prev != null) {

                    if (prev instanceof DialogInfoNotification){
                        ((DialogInfoNotification) prev).dismiss();
                    }
                }
                 fragmentTransaction.addToBackStack(null);
                 dialogInfoNotification.show(fragmentManager,"dialog");
            }
        });

        holder.typeName.setText(list.get(position).getTitle());
        holder.cardView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                dialogDeleteFragment =new DialogDeleteFragment(adapterNotification,position);
                Fragment prev = fragmentManager.findFragmentByTag("dialog");
                if (prev != null) {
                    fragmentTransaction.remove(prev);
                }
                fragmentTransaction.addToBackStack(null);
                dialogDeleteFragment.show(fragmentManager,"dialog");
                return true;
            }
        });


    }


    @Override
    public void onDelete(int id) {

        Log.e("onDelete: ","dddddddd");
        list.remove(id);
        adapterNotification.notifyDataSetChanged();
        dialogDeleteFragment.dismiss();
    }

    @Override
    public void onCancel() {
        dialogDeleteFragment.dismiss();
    }


    // my be static
    public  class ViewHoderClass extends RecyclerView.ViewHolder {

        TextView typeIcon;
        TextView typeName;
        TextView dataNoti;
        CardView cardView;

        public ViewHoderClass(View itemView) {
            super(itemView);
            typeIcon=itemView.findViewById(R.id.image_type_notification);
            typeName=itemView.findViewById(R.id.type_notification);
            dataNoti=itemView.findViewById(R.id.data_notification);
            cardView=itemView.findViewById(R.id.card_view);
        }


    }
}

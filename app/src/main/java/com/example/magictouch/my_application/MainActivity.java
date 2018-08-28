package com.example.magictouch.my_application;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.example.magictouch.my_application.Fragments.FragmentDashboard;
import com.example.magictouch.my_application.Fragments.FragmentNotification;
import com.example.magictouch.my_application.Fragments.FragmentPayment;
import com.example.magictouch.my_application.Fragments.FragmentProfile;
import com.example.magictouch.my_application.Fragments.FragmetnReport;
import com.example.magictouch.my_application.database.DataBase;
import com.example.magictouch.my_application.notification.NewMessageNotification;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;
import androidx.work.Worker;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    //pour tester la base
    String value_test = "20";
    String time_test = "2019";
    String marks = "kk";
    String test = "test";
    DataBase database ;
    BottomNavigationView menuButtom;
   public static Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = new DataBase(this);
        AHBottomNavigation bottomNavigation = (AHBottomNavigation) findViewById(R.id.bottom_navigation);

        navigationToFragment(new FragmentDashboard(getSupportFragmentManager()));
        context=this;

// Create items
        AHBottomNavigationItem dashboard = new AHBottomNavigationItem(R.string.title_dashboard, R.drawable.ic_dashboard_black_24dp,R.color.colorTopBar);
        AHBottomNavigationItem notification = new AHBottomNavigationItem(R.string.title_notifications, R.drawable.ic_notifications_black_24dp, R.color.colorTopBar);
        AHBottomNavigationItem payment = new AHBottomNavigationItem(R.string.title_payment, R.drawable.ic_payment_black_24dp, R.color.colorTopBar);
        AHBottomNavigationItem report = new AHBottomNavigationItem(R.string.title_report, R.drawable.ic_reports, R.color.colorTopBar);
        AHBottomNavigationItem profile = new AHBottomNavigationItem(R.string.title_profile, R.drawable.profile_account, R.color.colorTopBar);

        bottomNavigation.setBehaviorTranslationEnabled(false);
        bottomNavigation.setTranslucentNavigationEnabled(false);
       // bottomNavigation.setNotification("1", 3);


// Add items
        bottomNavigation.addItem(dashboard);
        bottomNavigation.addItem(notification);
        bottomNavigation.addItem(payment);
        bottomNavigation.addItem(report);
        bottomNavigation.addItem(profile);


        // Set listeners
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {

                switch(position) {
                    case 0 :
                        navigationToFragment(new FragmentDashboard(getSupportFragmentManager()));
                        break;
                    case 1 :
                        navigationToFragment(new FragmentNotification());
                        break;
                    case 2 :
                        navigationToFragment(new FragmentPayment());
                        break;
                    case 3 :
                        navigationToFragment(new FragmetnReport(getSupportFragmentManager()));
                        break;
                    case 4 :
                        navigationToFragment(new FragmentProfile());
                        break;
                }

                return true;
            }
        });


      /*  *//**
         * related with the notification ^_^
         *//*
          NewMessageNotification.notify(getBaseContext(),"moité de consommation",1);
          NewMessageNotification.notify(getBaseContext(),"Vous avez consommé la quantité d'aujourd'hui",2);
          NewMessageNotification.notify(getBaseContext(),"vous n'avez pas payer la facture",3);
          NewMessageNotification.notify(getBaseContext(),"Attention ! vous avez un gaspillage d'eau",4);
*/


        /**
         * insert tarifation
         */
       boolean re =database.insertTarification(152.2f,"2018/10/10","2018/12/31");
        if (re){
            Log.e("onCreate: ", "tarifation inserted");
        }

        Date currentTime = Calendar.getInstance().getTime();
        int t=currentTime.getHours();

        /**
         * display the notification of payment
         */
        if (t==15){
            NewMessageNotification.notify(getBaseContext(),"vous n'avez pas payer la facture",3);
        }

        /**
         * the Worker
         */

        //init the data base
        ServiceWorker.dataBase=database;

    /*    PeriodicWorkRequest.Builder webService = new PeriodicWorkRequest.Builder(
                             ServiceWorker.class, 2,
                             TimeUnit.HOURS);
        PeriodicWorkRequest photoCheckWork = webService.build();
        WorkManager.getInstance().enqueue(photoCheckWork);
    */

        OneTimeWorkRequest compressionWork =
                new OneTimeWorkRequest.Builder(ServiceWorker.class)
                        .build();
        WorkManager.getInstance().enqueue(compressionWork);


        /**
         * notification
         */

        Observer<String> observer= new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {

                 if (s.equals("notify_half")){
                     NewMessageNotification.notify(getBaseContext(),"moité de consommation",1);
                 }

                 if (s.equals("notify_full")){
                     NewMessageNotification.notify(getBaseContext(),"Vous avez consommé la quantité d'aujourd'hui",2);
                 }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };

        // passing it
        ServiceWorker.observer=observer;




    }



    public void navigationToFragment(android.support.v4.app.Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    /**
     * *************************************************** API Web Service *************************************
     */

    /**
     * every thing related with request to service
     */


}


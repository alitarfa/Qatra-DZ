package com.example.magictouch.my_application;

import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.magictouch.my_application.database.DataBase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;

import androidx.work.Worker;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by tarfa on 6/17/18.
 */

public class ServiceWorker extends Worker {

    public static DataBase dataBase;
    public int fullValue=6;
    public static Observer<String> observer;
    public static Observer<Float> observerOfCircle;
    public Observable<String> observable;
    public float allowedQuantity=10;

    @NonNull
    @Override
    public WorkerResult doWork() {
        Log.e("doWork: ", "it work");
        doTheRequest();
        return WorkerResult.SUCCESS;

    }

    public void doTheRequest() {
        final RequestQueue queue = Volley.newRequestQueue(MainActivity.context);
        String url = "http://192.168.43.79:8080/SmartHome/home1/";
        final StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject object = jsonArray.getJSONObject(i);
                                JSONObject object1 = jsonArray.getJSONObject(i);
                                String time =  object.getString("time").toString();
                                String value = object1.getString("value").toString();
                                Log.e( "onResponse Time :", time);
                                Log.e("onResponse value :", value);

                                boolean res=ServiceWorker.dataBase.insertConsumption(time,Float.parseFloat(value));
                                if (res)Log.e( "Comsumption inserted :","Ok");
                                else Log.e( "not inserted", "no");




                                /**
                                 * case notification half value
                                 */

                                if (Float.parseFloat(value)==fullValue/2){

                                    // TODO: 6/17/18 generate the notification
                                 observable=Observable.just("notify_half");
                                    observable.subscribeOn(Schedulers.io())
                                            .observeOn(AndroidSchedulers.mainThread())
                                            .subscribe(observer);
                                }

                                /**
                                 * case all quantity
                                 */
                                if (Float.parseFloat(value)==fullValue){

                                    // TODO: 6/17/18 generate the notification
                                    observable=Observable.just("notify_full");
                                    observable.subscribeOn(Schedulers.io())
                                            .observeOn(AndroidSchedulers.mainThread())
                                            .subscribe(observer);
                                }


                                /**
                                 * for the circle change value
                                 */


                                float curretValue=allowedQuantity-Float.parseFloat(value);
                                Observable<Float> floatObservable=Observable.just(curretValue);
                                                floatObservable.subscribeOn(Schedulers.io())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(observerOfCircle);


                            }

                        } catch (
                                JSONException e)

                        {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("onErrorResp Volley",error.getMessage() );
            }
        });
        queue.add(stringRequest);
    }
}

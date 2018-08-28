package com.example.magictouch.my_application.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.magictouch.my_application.R;
import com.example.magictouch.my_application.ServiceWorker;
import com.github.mikephil.charting.charts.PieChart;

import at.grabner.circleprogress.CircleProgressView;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by tarfa on 6/7/18.
 */

public class FragmentCircleCounter extends Fragment {
    CircleProgressView mCircleView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.circle_counter_fragment, container, false);
        mCircleView = (CircleProgressView)view.findViewById(R.id.circleView);
        mCircleView.setOnProgressChangedListener(new CircleProgressView.OnProgressChangedListener() {
            @Override
            public void onProgressChanged(float value) {
                Log.d("ddd", "Progress Changed: " + value);
            }
        });

        TextView tarifation=view.findViewById(R.id.tarifation);

        Observer<Float>  observerForCircle = new Observer<Float>() {

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Float aFloat) {

                mCircleView.setValue(aFloat);
                tarifation.setText(String.valueOf(aFloat*0.25f));
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };

        ServiceWorker.observerOfCircle=observerForCircle;


        return view;
    }
}

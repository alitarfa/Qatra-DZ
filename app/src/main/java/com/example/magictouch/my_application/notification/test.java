package com.example.magictouch.my_application.notification;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.magictouch.my_application.R;

/**
 * Created by Magic touch on 28/03/2018.
 */

public class test extends Activity {
    public TextView idType,body;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_laouyt);
        TextView close=findViewById(R.id.closeBtn);
        idType=findViewById(R.id.id_type);
        body=findViewById(R.id.body);

        String typeValue=getIntent().getExtras().getString("type");
        String bodyValue=getIntent().getExtras().getString("body");

        idType.setText(typeValue);
        body.setText(bodyValue);


        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}

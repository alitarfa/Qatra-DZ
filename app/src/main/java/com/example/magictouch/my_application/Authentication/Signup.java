package com.example.magictouch.my_application.Authentication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.magictouch.my_application.R;
import com.example.magictouch.my_application.database.DataBase;

/**
 * Created by Magic touch on 02/06/2018.
 */

public class Signup extends Activity {

    TextView textView1;
    View singUpBtn;
    EditText first_name, last_name, familly_number, address, email, password;
    String firstname, lastname, famillynumber, address1, e_mail, password1;

    DataBase dataBase;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_layout);

        dataBase=new DataBase(this);

        first_name = (EditText) findViewById(R.id.input_name_first);
        last_name = (EditText) findViewById(R.id.input_last_name);
        familly_number = (EditText) findViewById(R.id.input_familly_number);
        address = (EditText) findViewById(R.id.input_address);
        email = (EditText) findViewById(R.id.input_email);
        password = (EditText) findViewById(R.id.input_password);


        singUpBtn = (View) findViewById(R.id.btn_signup);
        textView1 = (TextView) findViewById(R.id.link_login);

        singUpBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                firstname = first_name.getText().toString();
                lastname = last_name.getText().toString();
                famillynumber = familly_number.getText().toString().trim();
                address1 = address.getText().toString();
                e_mail = email.getText().toString();
                password1 = password.getText().toString();

                boolean result=dataBase.insertUser(firstname,lastname,215,address1,e_mail,password1);
                if (result) {
                    Intent intent = new Intent(getApplicationContext(), Login.class);
                    Toast.makeText(Signup.this, "vous abez bien inscrevez !", Toast.LENGTH_LONG).show();
                    startActivity(intent);

                }else {
                    Toast.makeText(Signup.this, "Problem !!!", Toast.LENGTH_SHORT).show();
                }


            }

        });


        textView1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);


            }

        });


    }
}

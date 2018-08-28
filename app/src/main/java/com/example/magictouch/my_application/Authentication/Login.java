package com.example.magictouch.my_application.Authentication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.magictouch.my_application.MainActivity;
import com.example.magictouch.my_application.Model.UserModel;
import com.example.magictouch.my_application.R;
import com.example.magictouch.my_application.database.DataBase;

/**
 * Created by Magic touch on 02/06/2018.
 */

public class Login extends Activity {

    View view ;
    TextView textView;
    EditText email,password;
    DataBase dataBase;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.longin_layout);
        view = (View) findViewById(R.id.btn_login);
        textView = (TextView) findViewById(R.id.link_signup);

        email=findViewById(R.id.input_email);
        password=findViewById(R.id.input_password);

        dataBase=new DataBase(this);

        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                UserModel  userModel=dataBase.getUserByEmail(email.getText().toString().trim(),
                                                             password.getText().toString().trim());
                if (userModel!=null) {
                    
                    getBaseContext().getSharedPreferences("lamin",MODE_PRIVATE).edit().putInt("id_user",userModel.getId()).apply();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);

                }else {
                    Toast.makeText(Login.this, "Authentication Problem", Toast.LENGTH_SHORT).show();
                }

            }

        });


        textView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), Signup.class);
                startActivity(intent);


            }

        });


    }
}

package com.example.juancho.scrpasystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button registro;
    Button logear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logear =(Button)findViewById(R.id.btnlogear);

        logear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent siguiente= new Intent(MainActivity.this, Inicio.class);
                startActivity(siguiente);

            }
        });


        registro =(Button)findViewById(R.id.btnregistro);

        registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent siguiente= new Intent(MainActivity.this, RegistroLogin.class);
                startActivity(siguiente);

            }
        });


    }
}

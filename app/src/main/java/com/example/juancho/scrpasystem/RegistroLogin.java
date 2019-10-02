package com.example.juancho.scrpasystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class RegistroLogin extends AppCompatActivity {

    Button siguiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_login);

        siguiente =(Button)findViewById(R.id.btnrl);

        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent siguiente= new Intent(RegistroLogin.this, RegistroLocalidad.class);
                startActivity(siguiente);

            }
        });
    }
}

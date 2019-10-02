package com.example.juancho.scrpasystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegistroLocalidad extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    Button siguiente;
    private Spinner spinner1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_localidad);
         spinner1= (Spinner)findViewById(R.id.btnspinner0);

        String [] opciones = {"San Ignacio","Progreso","Kanazin","Flamboyanes"};

        //aqui se llama al spinner personalizado
        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item_personalizado, opciones);
        spinner1.setAdapter(adapter);

        siguiente =(Button)findViewById(R.id.btnrdl);

        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent siguiente= new Intent(RegistroLocalidad.this, Servicios.class);
                startActivity(siguiente);

            }
        });
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        String text= parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(),text,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}

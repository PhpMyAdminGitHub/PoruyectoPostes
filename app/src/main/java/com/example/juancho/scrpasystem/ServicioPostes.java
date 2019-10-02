package com.example.juancho.scrpasystem;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class ServicioPostes extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private Spinner spinner0;
    private Spinner spinner1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicio_postes);


        spinner0= (Spinner)findViewById(R.id.spinner_dialog);

        String [] opciones0 = {"Normal","Critico"};

        //aqui se llama al spinner personalizado
        ArrayAdapter<String> adapter0 = new ArrayAdapter<String>(this, R.layout.spinner_item_personalizado, opciones0);
        spinner0.setAdapter(adapter0);


        //SPPINER 1
        spinner1= (Spinner)findViewById(R.id.spinner_dropdown);

        //contenido del spinner
        String [] opciones1 = {"1","2","3","4"};

        //aqui se llama al spinner personalizado
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, R.layout.spinner_item_personalizado, opciones1);
        spinner1.setAdapter(adapter1);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        String text= adapterView.getItemAtPosition(position).toString();
        Toast.makeText(adapterView.getContext(),text,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}

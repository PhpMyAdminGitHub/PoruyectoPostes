package com.example.juancho.scrpasystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class RegistroLocalidad extends AppCompatActivity{

        private ArrayList<CountryItem> mCountryList;
        private CountryAdapter mAdapter;
        Button siguiente;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_registro_localidad);

            initList();
            Spinner spinnerCountries = findViewById(R.id.spinner_countries);

            mAdapter = new CountryAdapter(this, mCountryList);
            spinnerCountries.setAdapter(mAdapter);

            spinnerCountries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    CountryItem clickedItem = (CountryItem) parent.getItemAtPosition(position);
                    String clickedCountryName = clickedItem.getCountryName();
                    Toast.makeText(RegistroLocalidad.this, clickedCountryName + " selected", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
            siguiente =(Button)findViewById(R.id.btnregistrar);

            siguiente.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent siguiente= new Intent(RegistroLocalidad.this, Inicio.class);
                    startActivity(siguiente);

                }
            });

        }



        private void initList() {
            mCountryList = new ArrayList<>();
            mCountryList.add(new CountryItem("San Ignacio", R.drawable.comisaria));
            mCountryList.add(new CountryItem("Progreso", R.drawable.faro));

        }
    }

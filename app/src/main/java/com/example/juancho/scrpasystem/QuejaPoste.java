package com.example.juancho.scrpasystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class QuejaPoste extends AppCompatActivity {
    private ArrayList<CountryItem> mCountryList;
    private CountryAdapter mAdapter;
    Button siguiente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queja_poste);
        initList();
    Spinner spinnerCountries = findViewById(R.id.spinner_countries);

    mAdapter = new CountryAdapter(this, mCountryList);
            spinnerCountries.setAdapter(mAdapter);

            spinnerCountries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            CountryItem clickedItem = (CountryItem) parent.getItemAtPosition(position);
            String clickedCountryName = clickedItem.getCountryName();
            Toast.makeText(QuejaPoste.this, clickedCountryName + " selected", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    });
    siguiente =(Button)findViewById(R.id.btnregistrar);

            siguiente.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent siguiente= new Intent(QuejaPoste.this, Inicio.class);
            startActivity(siguiente);

        }
    });

}



    private void initList() {
        mCountryList = new ArrayList<>();
        mCountryList.add(new CountryItem("Regular", R.drawable.regular));
        mCountryList.add(new CountryItem("Critico", R.drawable.critico));

    }
}
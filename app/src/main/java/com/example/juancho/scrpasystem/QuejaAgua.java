package com.example.juancho.scrpasystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class QuejaAgua extends AppCompatActivity {
    private ArrayList<CountryItem> mCountryList;
    private CountryAdapter mAdapter;
    Button siguiente;
    TextView Num_Toma,Num_Toma0;
    EditText txtquejat;
    int estado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queja_agua);

        Num_Toma=(TextView)findViewById(R.id.Num_Toma);
        Num_Toma0=(TextView)findViewById(R.id.Num_Toma0);
txtquejat=(EditText)findViewById(R.id.txtquejat);
        //Spinner
        initList();
        Spinner spinnerCountries = findViewById(R.id.spinner_countries);
        mAdapter = new CountryAdapter(this, mCountryList);
        spinnerCountries.setAdapter(mAdapter);
        recibirDatos();

        spinnerCountries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                CountryItem clickedItem = (CountryItem) parent.getItemAtPosition(position);
                String clickedCountryName = clickedItem.getCountryName();
                //Toast.makeText(QuejaAgua.this, clickedCountryName + " selected", Toast.LENGTH_SHORT).show();
                estado=((int) (position+1)); //Obtenemos valor del estado por medio de la posicion
                Num_Toma0.setText((int) estado+""); //lo asignamos a un txt para obtenerlo de nuevo
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        siguiente =(Button)findViewById(R.id.btnregistrar);
//funcion click del boton siguiente
        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validarUsuario("https://scrpaprueba.000webhostapp.com/Conect/insertar_toma.php");


            }
        });

    }



    private void initList() {
        mCountryList = new ArrayList<>();
        mCountryList.add(new CountryItem("Regular", R.drawable.regular));
        mCountryList.add(new CountryItem("Critico", R.drawable.critico));

    }

    private void recibirDatos(){
        Bundle extras= getIntent().getExtras();
        int num=extras.getInt("pasar");
        Num_Toma=(TextView)findViewById(R.id.Num_Toma);
        Num_Toma.setText((int) num+"");
    }

    //select usuario con nombre y contraseña en la base de datos
    private void validarUsuario(String URL){
        //se envian los 3 parametros
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(QuejaAgua.this,"Reporte enviado",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(getApplicationContext(), Inicio.class);
                startActivity(intent);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();//captura de rror


            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                //obtendra los valres y se los dara a los parametros para ser ingresados en el php
                Map<String,String> parametros=new HashMap<String,String>();

                String var1,var2,var3;
                //obtenemos los valores de los txt
                var1=txtquejat.getText().toString();
                var2=Num_Toma.getText().toString();
                var3=Num_Toma0.getText().toString();


//pasamos los valores obtenidos y los valores encriptados u

                //se manda cada uno a los valores del php que envian a base de datos
                parametros.put("Descripcion_A",var1);
                parametros.put("Num_agua",var2);
                parametros.put("Id_estado_a",var3);
                return parametros;

            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this); //comunica con php
        requestQueue.add(stringRequest);
    }//fin private

}
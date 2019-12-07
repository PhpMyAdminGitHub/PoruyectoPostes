package com.example.juancho.scrpasystem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
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
import com.example.juancho.scrpasystem.ui.home.HomeViewModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ServicioPostes extends AppCompatActivity {
    private ListView listViewPostes;

    TextView text_home1;
    ListView lista;
    String[] Numero;
    String[] Calle;
    String[] datosimg;
    private HomeViewModel homeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicio_postes);
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        lista = (ListView) findViewById(R.id.lista_postes);
        //final TextView textView = findViewById(R.id.text_home1);

        validarUsuario("https://scrpaprueba.000webhostapp.com/Conect/listapostes.php");

    }

    private void validarUsuario(String URL){
        //se envian los 3 parametros
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONArray ja=new JSONArray(response);
                    JSONObject jo=null;
                    Calle=new String[ja.length()];
                    Numero=new String[ja.length()];
                    datosimg=new String[ja.length()];

                    for(int i=0;i<ja.length();i++){
                        jo=ja.getJSONObject(i);
                        Numero[i]=jo.getString("Num_Poste");
                        Calle[i]=jo.getString("Calle");
                        datosimg[i]=jo.getString("img");
                    }
                    lista.setAdapter(new AdaptadorP(getApplicationContext(), Numero,Calle, datosimg));
                }catch (JSONException e){
                    e.getMessage();
                }


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
                return parametros;

            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this); //comunica con php
        requestQueue.add(stringRequest);
    }//fin private

}


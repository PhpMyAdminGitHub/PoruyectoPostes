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

public class QuejaPoste extends AppCompatActivity {
    private ArrayList<CountryItem> mCountryList;
    private CountryAdapter mAdapter;
    Button siguiente;
    TextView Num_Poste,Num_Poste0;
    EditText txtqueja;
    int estado;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_queja_poste);

        Num_Poste=(TextView)findViewById(R.id.Num_Poste);
        Num_Poste0=(TextView)findViewById(R.id.Num_Poste0);
        txtqueja=(EditText)findViewById(R.id.txtqueja);


        initList();
            Spinner spinnerCountries = findViewById(R.id.spinner_countries);
            mAdapter = new CountryAdapter(this, mCountryList);
            spinnerCountries.setAdapter(mAdapter);
            recibirDatos();
            //recibimos los datos del activiti de lista


        spinnerCountries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            CountryItem clickedItem = (CountryItem) parent.getItemAtPosition(position);
            String clickedCountryName = clickedItem.getCountryName();

            //Toast.makeText(QuejaPoste.this, clickedCountryName + " selected", Toast.LENGTH_SHORT).show();
            estado=((int) (position+1)); //Obtenemos valor del estado por medio de la posicion
            Num_Poste0.setText((int) estado+""); //lo asignamos a un txt para obtenerlo de nuevo

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    });
    siguiente =(Button)findViewById(R.id.btnregistrar);

            siguiente.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //llamamos a validar que contendra los datos enviados a la bd
            validarUsuario("https://scrpaprueba.000webhostapp.com/Conect/insertar_reporte.php");



        }
    });

}
    private void initList() {
        mCountryList = new ArrayList<>();
        //tipos de estados disponibles
        mCountryList.add(new CountryItem("Regular", R.drawable.regular));
        mCountryList.add(new CountryItem("Critico", R.drawable.critico));

    }

    private void recibirDatos(){
        Bundle extras= getIntent().getExtras();
        int num=extras.getInt("pasar");
        Num_Poste=(TextView)findViewById(R.id.Num_Poste);
        Num_Poste.setText((int) num+"");
    }

    //select usuario con nombre y contraseña en la base de datos
    private void validarUsuario(String URL){
        //se envian los 3 parametros
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(QuejaPoste.this,"Reporte enviado",Toast.LENGTH_SHORT).show();
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

                        int n1=0,n2=0;
                        n1=Integer.parseInt(Num_Poste0.getText().toString());
                    n2=Integer.parseInt(Num_Poste.getText().toString());
                    String var1,var2,var3;
                    //obtenemos los valores de los txt
                    var1=txtqueja.getText().toString();
                    var2=Num_Poste.getText().toString();
                    var3=Num_Poste0.getText().toString();

//pasamos los valores obtenidos y los valores encriptados u

                    //se manda cada uno a los valores del php que envian a base de datos
                    parametros.put("Descripcion_P",var1);
                    parametros.put("Num_Poste",var2);
                    parametros.put("Id_estado",var3);





                return parametros;

            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this); //comunica con php
        requestQueue.add(stringRequest);
    }//fin private
}
package com.example.juancho.scrpasystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class MainActivity extends AppCompatActivity {
    Button registro,btnlogear;
    EditText txtusuario,txtcontrasena;
    String textosalida1,textosalida2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtusuario=(EditText)findViewById(R.id.txtusuario);
        txtcontrasena=(EditText)findViewById(R.id.txtcontrasena);
        btnlogear=(Button) findViewById(R.id.btnlogear);

        btnlogear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {


                    // validarUsuario("https://scrpaprueba.000webhostapp.com/Conect/insertar_producto.php",textosalida1,textosalida2);
                    validarUsuario("https://scrpaprueba.000webhostapp.com/Conect/select_usuario.php");
                    //url del servidor cojn los datos parametros obtenidos de la encriptacion

                }catch (Exception e){

                }
                //ejecutarServicio("http://192.168.0.7:8080/Conect/select_usuario.php");

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

    private String encriptar(String datos, String password)throws Exception{
        //pasa los datos de la contrasea y el usuario y cada uno depende del otro para que sea exitoso
        SecretKeySpec secretKey=generateKey(password);//primer tipo de encriptacion
        Cipher cipher=Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE,secretKey);
        byte[] datosEncriptadosBytes=cipher.doFinal(datos.getBytes());
        //transforma el dato a string y lo pasa a base 64
        String datosDesencriptadosString= Base64.encodeToString(datosEncriptadosBytes,Base64.DEFAULT);
        return datosDesencriptadosString;//retorna valores encriptados

    }
    private SecretKeySpec generateKey(String password)throws Exception{
        MessageDigest sha=MessageDigest.getInstance("SHA-256");//tipo de encriptacion y extension
        byte[] key=password.getBytes("UTF-8");//tipo escritura
        key=sha.digest(key);
        SecretKeySpec secretKey=new SecretKeySpec(key,"AES");
        //se obtiene la llave
        return secretKey;

    }
    //final encriptado desencriptado

    //select usuario con nombre y contraseña en la base de datos
    private void validarUsuario(String URL){
        //se envian los 3 parametros
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(!response.isEmpty()){ //si la conexion es exitosa manda al egundo layout sino al toast
                    Intent intent=new Intent(getApplicationContext(), Inicio.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this,"Usuario o Contraeña incorrecta",Toast.LENGTH_SHORT).show();

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
                try {


                    textosalida1 = encriptar(txtusuario.getText().toString(), txtcontrasena.getText().toString());
                    textosalida2 = encriptar(txtcontrasena.getText().toString(), txtusuario.getText().toString());
                    parametros.put("Username",textosalida1);

                    parametros.put("Contrasena",textosalida2);
                }catch(Exception e){}

                return parametros;

            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(this); //comunica con php
        requestQueue.add(stringRequest);
    }//fin private
}

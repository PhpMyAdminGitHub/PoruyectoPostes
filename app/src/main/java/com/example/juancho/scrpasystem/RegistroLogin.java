package com.example.juancho.scrpasystem;

import android.content.Intent;
import android.os.Bundle;
import android.util.Base64;
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

import androidx.appcompat.app.AppCompatActivity;

public class RegistroLogin extends AppCompatActivity {
    EditText txtnombre,txtusuario,txtcontrasena,txtapellidop,txtapellidom;
    Button siguiente;
    String textosalida1,textosalida2,nombre,apellidop,apellidom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_login);
        txtnombre=(EditText)findViewById(R.id.txtnombre);
        txtapellidop=(EditText)findViewById(R.id.txtapellidop);
        txtapellidom=(EditText)findViewById(R.id.txtapellidom);
        txtusuario=(EditText)findViewById(R.id.txtusuario);
        txtcontrasena=(EditText)findViewById(R.id.txtcontrasena);

        siguiente =(Button)findViewById(R.id.btnregistro);

        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {


                    validarUsuario("https://scrpaprueba.000webhostapp.com/Conect/insertar_producto.php");
                    //validarUsuario("https://scrpaprueba.000webhostapp.com/Conect/select_usuario.php");
                    //url del servidor cojn los datos parametros obtenidos de la encriptacion

                }catch (Exception e){

                }

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
                Intent intent=new Intent(getApplicationContext(),RegistroLocalidad.class);
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
                try {


                    textosalida1 = encriptar(txtusuario.getText().toString(), txtcontrasena.getText().toString());
                    textosalida2 = encriptar(txtcontrasena.getText().toString(), txtusuario.getText().toString());
                    nombre=txtnombre.getText().toString();
                    apellidop=txtapellidop.getText().toString();
                    apellidom=txtapellidom.getText().toString();
                    parametros.put("Nombre",nombre);
                    parametros.put("Apellido_p",apellidop);
                    parametros.put("Apellido_m",apellidom);
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

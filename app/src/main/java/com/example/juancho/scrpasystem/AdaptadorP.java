package com.example.juancho.scrpasystem;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;

public class AdaptadorP extends BaseAdapter {

    private static LayoutInflater inflater = null;
    Context contexto;
    String[] Calle;
    String[] Numero;

    String[] datosimg;
    Bitmap bitmap;

    public AdaptadorP (Context contextos, String[] Callee,String[] Numeroo, String[] imagenes ){
        this.contexto=contextos;
        this.Numero = Numeroo;
        this.Calle = Callee;
        this.datosimg = imagenes;
        inflater=(LayoutInflater) contextos.getSystemService(contextos.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        final View vista = inflater.inflate(R.layout.postes_row, null);
        TextView Num=vista.findViewById(R.id.txtNom);
        TextView Dir=vista.findViewById(R.id.txtDir);
        ImageView imagen=vista.findViewById(R.id.imgPoste);
        Num.setText(Numero[i]);
        Dir.setText(Calle[i]);
        new AdaptadorP.GetImageFromURL(imagen).execute(datosimg[i]);

        imagen.setTag(i);

        imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent visorImagen = new Intent(contexto, VisorImagen.class);
                // visorImagen.putExtra("IMG", datosimg[(Integer)v.getTag()]);
                //contexto.startActivity(visorImagen);

                Intent intent=new Intent(contexto,QuejaPoste .class);
                intent.putExtra("pasar",i+1);
                contexto.startActivity(intent);


            }
        });

        return vista;

    }

    public class GetImageFromURL extends AsyncTask<String,Void, Bitmap>
    {

        ImageView imgView;
        public GetImageFromURL(ImageView imgv)
        {
            this.imgView=imgv;
        }
        @Override
        protected Bitmap doInBackground(String... url) {
            String urldisplay=url[0];
            bitmap=null;

            try{

                InputStream ist=new java.net.URL(urldisplay).openStream();
                bitmap= BitmapFactory.decodeStream(ist);
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }

            return bitmap;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap){

            super.onPostExecute(bitmap);
            imgView.setImageBitmap(bitmap);
        }
    }

    @Override
    public int getCount() {
        return datosimg.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
}

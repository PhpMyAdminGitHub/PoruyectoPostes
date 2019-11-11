package com.example.juancho.scrpasystem;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class ServicioAguna extends AppCompatActivity {
    private ListView listViewAgua;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicio_aguna);
        listViewAgua=(ListView) findViewById(R.id.lista_agua);
        ArrayList<Tomas> tomas=new ArrayList<>();

        tomas.add(new Tomas("Numero de toma de agua: 1","Dirección: Calle 3 por 4 y 6",R.drawable.toma_verde));
        tomas.add(new Tomas("Numero de toma de agua: 2","Dirección: Calle 5 por 6 y 8",R.drawable.toma_verde));
        tomas.add(new Tomas("Numero de toma de agua: 3","Dirección: Calle 7 por 8 y 10",R.drawable.toma_roja));
        tomas.add(new Tomas("Numero de toma de agua: 4","Dirección: Calle 9 por 10 y 12",R.drawable.toma_verde));
        tomas.add(new Tomas("Numero de toma de agua: 5","Dirección: Calle 11 por 12 y 14",R.drawable.toma_amarilla));
        tomas.add(new Tomas("Numero de toma de agua: 6","Dirección: Calle 13 por 14 y 16",R.drawable.toma_amarilla));
        tomas.add(new Tomas("Numero de toma de agua: 7","Dirección: Calle 15 por 16 y 18",R.drawable.toma_verde));
        tomas.add(new Tomas("Numero de toma de agua: 8","Dirección: Calle 17 por 18 y 20",R.drawable.toma_roja));
        tomas.add(new Tomas("Numero de toma de agua: 9","Dirección:  Calle 19 por 20 y 22",R.drawable.toma_verde));
        listViewAgua.setAdapter(new ListAguaAdapter(this,tomas));

        listViewAgua.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Toast.makeText(ServicioPostes.this,position,Toast.LENGTH_SHORT).show();
                int p=position;

                Toast.makeText(ServicioAguna.this,"Toma de agua numero "+(position+1)+" seleccionado",Toast.LENGTH_SHORT).show();
                Intent siguiente= new Intent(ServicioAguna.this,QuejaPoste.class);
                startActivity(siguiente);
                //Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();//captura de rror


            }
        });
    }

    //postes
    public  class Tomas{
        String nombre;
        String direccion;
        int image;
        public Tomas(String nombre,String direccion,int image){
            this.nombre=nombre;
            this.direccion=direccion;
            this.image=image;
        }
    }

    static class ListAguaAdapter extends BaseAdapter {
        private  final Context context;
        private final ArrayList<Tomas> tomas;

        public  ListAguaAdapter(Context context,ArrayList<Tomas> tomas){
            this.context=context;
            this.tomas=tomas;
        }

        @Override
        public int getCount() {
            return tomas.size();
            //devuelve el entero que coresponde al numero de items a enseñar en la lista
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            //devuelve el view a enseñar para el item
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);

            Tomas toma=tomas.get(position);
            ListAguaAdapter.ListViewHolder holder;
            if(view==null){
                view=inflater.inflate(R.layout.tomas_row,viewGroup,false);
                holder=new ListAguaAdapter.ListViewHolder();
                holder.txtNom=(TextView)view.findViewById(R.id.txtNom);
                holder.txtDir=(TextView)view.findViewById(R.id.txtDir);
                holder.imgTomas=(ImageView)view.findViewById(R.id.imgTomas);

                view.setTag(holder);

            }else{
                Log.d("ListView", "RECYCLED");
                holder=(ListAguaAdapter.ListViewHolder) view.getTag();

            }
            holder.txtNom.setText(toma.nombre);
            holder.txtDir.setText(toma.direccion);
            holder.imgTomas.setImageResource(toma.image);
            return view;


        }
        static class ListViewHolder{
            TextView txtNom;
            TextView txtDir;
            ImageView imgTomas;
        }
    }

}



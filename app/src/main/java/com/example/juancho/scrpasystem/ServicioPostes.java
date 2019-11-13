package com.example.juancho.scrpasystem;

import androidx.appcompat.app.AppCompatActivity;

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

import java.util.ArrayList;

public class ServicioPostes extends AppCompatActivity {
    private ListView listViewPostes;
    //Postes[] items = new Postes[]{(new Postes("Numero de poste: 1","Dirección: Calle 3 por 4 y 6",R.drawable.poste_verde)),(new Postes("Numero de poste: 2","Dirección: Calle 5 por 6 y 8",R.drawable.poste_verde)),(new Postes("Numero de poste: 3","Dirección: Calle 7 por 8 y 10",R.drawable.poste_rojo))};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicio_postes);

        listViewPostes=(ListView) findViewById(R.id.lista_postes);
        //ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,items);
        //listViewPostes.setAdapter(adapter);
        final ArrayList<Postes> postes=new ArrayList<>();

         postes.add(new Postes("Numero de poste: 1","Dirección: Calle 3 por 4 y 6",R.drawable.poste_verde));
        postes.add(new Postes("Numero de poste: 2","Dirección: Calle 5 por 6 y 8",R.drawable.poste_verde));
        postes.add(new Postes("Numero de poste: 3","Dirección: Calle 7 por 8 y 10",R.drawable.poste_rojo));
        postes.add(new Postes("Numero de poste: 4","Dirección: Calle 9 por 10 y 12",R.drawable.poste_verde));
        postes.add(new Postes("Numero de poste: 5","Dirección: Calle 11 por 12 y 14",R.drawable.poste_amarillo));
        postes.add(new Postes("Numero de poste: 6","Dirección: Calle 13 por 14 y 16",R.drawable.poste_amarillo));
        postes.add(new Postes("Numero de poste: 7","Dirección: Calle 15 por 16 y 18",R.drawable.poste_verde));
        postes.add(new Postes("Numero de poste: 8","Dirección: Calle 17 por 18 y 20",R.drawable.poste_rojo));
        postes.add(new Postes("Numero de poste: 9","Dirección:  Calle 19 por 20 y 22",R.drawable.poste_verde));
        listViewPostes.setAdapter(new ListPostesAdapter(this,postes));
        listViewPostes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               // Toast.makeText(ServicioPostes.this,position,Toast.LENGTH_SHORT).show();

                Toast.makeText(ServicioPostes.this,"Poste numero "+(position+1)+" seleccionado",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(ServicioPostes.this,QuejaPoste.class);
                intent.putExtra("pasar",position+1);
                startActivity(intent);

                //Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();//captura de rror


            }
        });




    }

    public  class Postes{
        String nombre;
        String direccion;
        int image;
        public Postes(String nombre,String direccion,int image){
            this.nombre=nombre;
            this.direccion=direccion;
            this.image=image;
        }
    }
    static class ListPostesAdapter extends BaseAdapter {
        private  final Context context;
        private final ArrayList<Postes> postes;

        public  ListPostesAdapter(Context context,ArrayList<Postes> postes){
            this.context=context;
            this.postes=postes;
        }

        @Override
        public int getCount() {
            return postes.size();
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

            Postes poste=postes.get(position);
            ListViewHolder holder;
            if(view==null){
                view=inflater.inflate(R.layout.postes_row,viewGroup,false);
                holder=new ListViewHolder();
                holder.txtNom=(TextView)view.findViewById(R.id.txtNom);
                holder.txtDir=(TextView)view.findViewById(R.id.txtDir);
                holder.imgPoste=(ImageView)view.findViewById(R.id.imgPoste);

                view.setTag(holder);

            }else{
                Log.d("ListView", "RECYCLED");
                holder=(ListPostesAdapter.ListViewHolder) view.getTag();

            }
            holder.txtNom.setText(poste.nombre);
            holder.txtDir.setText(poste.direccion);
            holder.imgPoste.setImageResource(poste.image);
            return view;


        }
        static class ListViewHolder{
            TextView txtNom;
            TextView txtDir;
            ImageView imgPoste;
        }
    }


}


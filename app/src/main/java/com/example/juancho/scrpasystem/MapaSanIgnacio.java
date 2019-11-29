package com.example.juancho.scrpasystem;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapaSanIgnacio extends FragmentActivity implements OnMapReadyCallback,GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private Marker poste1;
    private Marker poste2;
    private Marker poste3;
    private Marker poste4;
    private Marker poste5;
    private Marker poste6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa_san_ignacio);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }



    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng p1=new LatLng(21.160559207348967, -89.65024338421769);
        LatLng p2 = new LatLng(21.16024519935273, -89.65150605133151);
        LatLng p3 = new LatLng(21.160433604234015, -89.6523309938607);
        LatLng p4 = new LatLng(21.160119595990892, -89.6538461943777);
        LatLng p5 = new LatLng(21.160308000982738, -89.65596747510992);
        LatLng p6 = new LatLng(21.1600253933846, -89.65699444436697);


        poste1=googleMap.addMarker(new MarkerOptions()
                .position(p1)
                .title("San Ignacio,Progreso,México")
                .snippet("Poste 1")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.poste))
        );
        poste2=googleMap.addMarker(new MarkerOptions()
                .position(p2)
                .title("San Ignacio,Progreso,México")
                .snippet("Poste 2")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.poste))
        );
        poste3=googleMap.addMarker(new MarkerOptions()
                .position(p3)
                .title("San Ignacio,Progreso,México")
                .snippet("Poste 3")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.poste))
        );
        poste4=googleMap.addMarker(new MarkerOptions()
                .position(p4)
                .title("San Ignacio,Progreso,México")
                .snippet("Poste 4")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.poste))
        );
        poste5=googleMap.addMarker(new MarkerOptions()
                .position(p5)
                .title("San Ignacio,Progreso,México")
                .snippet("Poste 5")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.poste))
        );
        poste6=googleMap.addMarker(new MarkerOptions()
                .position(p6)
                .title("San Ignacio,Progreso,México")
                .snippet("Poste 6")
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.poste))
        );

//permisos para ubicacion real
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true); //posicion propia

        } else {
            Toast.makeText(MapaSanIgnacio.this,"Habilite permisos de ubicacion en su dispositivo",Toast.LENGTH_SHORT).show();

        }

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(p4,15));
        mMap.getUiSettings().setZoomControlsEnabled(true);//habilita boton zoom
        googleMap.setOnMarkerClickListener(this);




    }

    @Override
    public boolean onMarkerClick(Marker marker) {

        if(marker.equals(poste1)){
            Toast.makeText(MapaSanIgnacio.this,"Eligio el poste 1",Toast.LENGTH_SHORT).show();
        }
        else if(marker.equals(poste2)){
            Toast.makeText(MapaSanIgnacio.this,"Eligio el poste 2",Toast.LENGTH_SHORT).show();
        }
        else if(marker.equals(poste3)){
            Toast.makeText(MapaSanIgnacio.this,"Eligio el poste 3",Toast.LENGTH_SHORT).show();
        }
        else if(marker.equals(poste4)){
            Toast.makeText(MapaSanIgnacio.this,"Eligio el poste 4",Toast.LENGTH_SHORT).show();
        }
        else if(marker.equals(poste5)){
            Toast.makeText(MapaSanIgnacio.this,"Eligio el porte 5",Toast.LENGTH_SHORT).show();
        }
        else if(marker.equals(poste6)){
            Toast.makeText(MapaSanIgnacio.this,"Eligio el porte 6",Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}

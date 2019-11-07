package com.example.juancho.scrpasystem;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapaSanIgnacio extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa_san_ignacio);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //agregar marca
        LatLng poste1 = new LatLng(21.160559207348967, -89.65024338421769);
        mMap.addMarker(new MarkerOptions().position(poste1).title("San Ignacio,Progreso,México").snippet("Poste #1").icon(BitmapDescriptorFactory.fromResource(R.drawable.poste)));

        LatLng poste2 = new LatLng(21.16024519935273, -89.65150605133151);
        mMap.addMarker(new MarkerOptions().position(poste2).title("San Ignacio,Progreso,México").snippet("Poste #2").icon(BitmapDescriptorFactory.fromResource(R.drawable.poste)));

        LatLng poste3 = new LatLng(21.160433604234015, -89.6523309938607);
        mMap.addMarker(new MarkerOptions().position(poste3).title("San Ignacio,Progreso,México").snippet("Poste #3").icon(BitmapDescriptorFactory.fromResource(R.drawable.poste)));

        LatLng poste4 = new LatLng(21.160119595990892, -89.6538461943777);
        mMap.addMarker(new MarkerOptions().position(poste4).title("San Ignacio,Progreso,México").snippet("Poste #4").icon(BitmapDescriptorFactory.fromResource(R.drawable.poste)));

        LatLng poste5 = new LatLng(21.160308000982738, -89.65596747510992);
        mMap.addMarker(new MarkerOptions().position(poste5).title("San Ignacio,Progreso,México").snippet("Poste #5").icon(BitmapDescriptorFactory.fromResource(R.drawable.poste)));

        LatLng poste6 = new LatLng(21.1600253933846, -89.65699444436697);
        mMap.addMarker(new MarkerOptions().position(poste6).title("San Ignacio,Progreso,México").snippet("Poste #6").icon(BitmapDescriptorFactory.fromResource(R.drawable.poste)));

        LatLng poste7 = new LatLng(21.160027065641387, -89.65923357404908);
        mMap.addMarker(new MarkerOptions().position(poste7).title("San Ignacio,Progreso,México").snippet("Poste #7").icon(BitmapDescriptorFactory.fromResource(R.drawable.poste)));

        LatLng poste8 = new LatLng(21.160215470805525, -89.66041206335176);
        mMap.addMarker(new MarkerOptions().position(poste8).title("San Ignacio,Progreso,México").snippet("Poste #8").icon(BitmapDescriptorFactory.fromResource(R.drawable.poste)));

        LatLng agua1 = new LatLng(21.16031189288492, -89.65008166878098);
        mMap.addMarker(new MarkerOptions().position(agua1).title("San Ignacio,Progreso,México").snippet("Toma de agua #1").icon(BitmapDescriptorFactory.fromResource(R.drawable.agua)));

        LatLng agua2 = new LatLng(21.160197878840037, -89.65266653148846);
        mMap.addMarker(new MarkerOptions().position(agua2).title("San Ignacio,Progreso,México").snippet("Toma de agua #2").icon(BitmapDescriptorFactory.fromResource(R.drawable.agua)));

        LatLng agua3 = new LatLng(21.16015173190216, -89.65618286865211);
        mMap.addMarker(new MarkerOptions().position(agua3).title("San Ignacio,Progreso,México").snippet("Toma de agua #3").icon(BitmapDescriptorFactory.fromResource(R.drawable.agua)));


        LatLng agua4 = new LatLng(21.160083864712334, -89.65996701953898);
        mMap.addMarker(new MarkerOptions().position(agua4).title("San Ignacio,Progreso,México").snippet("Toma de agua #4").icon(BitmapDescriptorFactory.fromResource(R.drawable.agua)));


        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(poste4,12));
    }
}

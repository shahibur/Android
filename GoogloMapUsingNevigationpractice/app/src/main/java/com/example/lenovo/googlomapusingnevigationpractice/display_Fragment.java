package com.example.lenovo.googlomapusingnevigationpractice;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class display_Fragment extends Fragment implements OnMapReadyCallback {

    GoogleMap googleMap;

    public display_Fragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View view=inflater.inflate(R.layout.fragment_display_, container, false);

        SupportMapFragment supportMapFragment= (SupportMapFragment) getFragmentManager().findFragmentById(R.id.mapFragment);

        try {
            supportMapFragment.getMapAsync( this);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("error:", String.valueOf(e));
        }

        return  view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SupportMapFragment supportMapFragment= (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapFragment);
            supportMapFragment.getMapAsync( this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {


        this.googleMap=googleMap;

        LatLng latLng=new LatLng(24.8949294,91.8687063);

        MarkerOptions markerOptions=new MarkerOptions();

        markerOptions.position(latLng).title("sylhet");

        googleMap.addMarker(markerOptions);

        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));


    }
}

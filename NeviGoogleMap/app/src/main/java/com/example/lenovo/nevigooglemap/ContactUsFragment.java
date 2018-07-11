package com.example.lenovo.nevigooglemap;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

public class ContactUsFragment extends android.support.v4.app.Fragment implements OnMapReadyCallback {

   GoogleMap googleMap;

    public ContactUsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v= inflater.inflate(R.layout.fragment_contact_us, container, false);

        SupportMapFragment supportMapFragment= (SupportMapFragment) getFragmentManager().findFragmentById(R.id.mapCopy);
        try{
            supportMapFragment.getMapAsync(this);
        }catch (Exception ex)
        {
           Log.e("error", String.valueOf(ex));
        }


       return  v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment supportMapFragment= (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapCopy);
        supportMapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap=googleMap;

        LatLng latLng=new LatLng(24.8949294,91.8687063);
        MarkerOptions markerOptions=new MarkerOptions();

        markerOptions.position(latLng).title("Sylhet");

        googleMap.addMarker(markerOptions);

        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));


    }
}

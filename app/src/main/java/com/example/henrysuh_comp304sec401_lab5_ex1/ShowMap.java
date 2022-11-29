package com.example.henrysuh_comp304sec401_lab5_ex1;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static androidx.core.content.PermissionChecker.PERMISSION_GRANTED;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.henrysuh_comp304sec401_lab5_ex1.entity.Restaurant;
import com.example.henrysuh_comp304sec401_lab5_ex1.service.DatabaseService;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ShowMap extends AppCompatActivity implements OnMapReadyCallback {

    private final DatabaseService databaseService = new DatabaseService();
    TextView restName, restDetail;
    CheckBox mSatellite;
    Restaurant restaurant;
    GoogleMap mGoogleMap;
    int restaurantId;

    private static final int LOCATION_PERMISSION_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_map);

        Intent intent = getIntent();
        restaurantId = intent.getIntExtra("restaurantId", 0);
        restaurant = databaseService.getRestaurantDetail(this, restaurantId);

        restName = findViewById(R.id.textRestName);
        restName.setText(restaurant.getName());
        restDetail = findViewById(R.id.textRestDetail);
        restDetail.setText(restaurant.getDetail());

        mSatellite = findViewById(R.id.cBoxSatellite);
        mSatellite.setOnClickListener(v -> setSatellite());

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapsFragment);
        if(mapFragment != null) {
            mapFragment.getMapAsync(this::onMapReady);
        }
    }

    private void setSatellite() {
        if (mSatellite.isChecked()) {
            mGoogleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        } else {
            mGoogleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        }
    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        int permission = ActivityCompat.checkSelfPermission(this,
                ACCESS_FINE_LOCATION);

        if (permission != PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST);
        }

        mGoogleMap = googleMap;

        LatLng loc = new LatLng(restaurant.getLatitude(), restaurant.getLongitude());

        mGoogleMap.addMarker(new MarkerOptions()
                .position(loc)
                .title(restaurant.getName())
                .snippet(restaurant.getAddress()
                ));

        mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, 13));
        mGoogleMap.getUiSettings().setZoomControlsEnabled(true);
        mGoogleMap.getUiSettings().setMapToolbarEnabled(true);
    }
}
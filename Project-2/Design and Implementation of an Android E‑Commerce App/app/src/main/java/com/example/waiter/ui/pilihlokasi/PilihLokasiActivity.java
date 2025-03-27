package com.example.waiter.ui.pilihlokasi;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.example.waiter.R;
import com.example.waiter.model.ResponsePostTransaksi;
import com.example.waiter.network.Api;
import com.example.waiter.network.InitRetrofit;
import com.example.waiter.utils.GPSTracker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PilihLokasiActivity extends FragmentActivity implements OnMapReadyCallback {

    private SupportMapFragment mapFragment;
    private GoogleMap mMap;
    private LatLng latLng, latLngusr;
    private GPSTracker gps;
    private double latuser, lonuser;
    private LatLng user;
    private Button btnKirim;
    private String idUser;
    private ProgressBar pb;
    private String pembayaran;
    private ImageView backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_lokasi);

        SharedPreferences pref = this.getSharedPreferences("login", MODE_PRIVATE);
        idUser = (pref.getString("code", null));

        if (getIntent().getExtras() != null) {
            Bundle bundle = getIntent().getExtras();
            pembayaran = bundle.getString("pembayaran");
        }

        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        btnKirim = findViewById(R.id.btnKirim);
        pb = findViewById(R.id.progressBar);
        backBtn = findViewById(R.id.backBtn);
        backBtn.setOnClickListener(view -> {
            finish();
        });
    }

    @Override
    public void onMapReady(final GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        gps = new GPSTracker(PilihLokasiActivity.this);
        // check if GPS enabled
        if (gps.canGetLocation()) {
            latuser = (gps.getLatitude());
            lonuser = (gps.getLongitude());
            user = new LatLng(latuser, lonuser);
            posisiuser();
        } else {
            gps.showSettingsAlert();
        }

        // TODO: Consider calling
        mMap.getUiSettings().setMyLocationButtonEnabled(true);
        //mMap.setMyLocationEnabled(true);
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        googleMap.setMyLocationEnabled(false);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.getUiSettings().setMyLocationButtonEnabled(false);
        googleMap.getUiSettings().setCompassEnabled(false);
        googleMap.getUiSettings().setRotateGesturesEnabled(true);
        googleMap.getUiSettings().setZoomGesturesEnabled(true);

        mMap.setOnCameraMoveListener(new GoogleMap.OnCameraMoveListener() {
            @Override
            public void onCameraMove() {

            }
        });

        mMap.setOnCameraIdleListener(new GoogleMap.OnCameraIdleListener() {
            @Override
            public void onCameraIdle() {
                latLng = mMap.getCameraPosition().target;
                googleMap.clear();
                try {
                    Log.e("Latitude", latLng.latitude + "");
                    Log.e("Longitude", latLng.longitude + "");
                    latLngusr = new LatLng(latLng.latitude, latLng.longitude);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btnKirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnKirim.setVisibility(View.GONE);
                pb.setVisibility(View.VISIBLE);
                postTransaksi();
            }
        });

    }


    public void posisiuser() {
        mMap.addMarker(new MarkerOptions().position(user));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(user, 16));
    }

    private void postTransaksi () {
        Api api = new InitRetrofit().getInitInstance();
        Call<ResponsePostTransaksi> call = api.postTransaksitake(idUser, "", String.valueOf(latLng.latitude), String.valueOf(latLng.longitude), "take");
        call.enqueue(new Callback<ResponsePostTransaksi>() {
            @Override
            public void onResponse(Call<ResponsePostTransaksi> call, Response<ResponsePostTransaksi> response) {
                Log.d("resadada", response.body().toString());
                if (response.isSuccessful()) {
                    pb.setVisibility(View.GONE);
                    finish();
//                    Toast.makeText(getApplicationContext(), "Pesanan Berhasil terkirim", Toast.LENGTH_SHORT).show();
                }else{
                    pb.setVisibility(View.GONE);
                    btnKirim.setVisibility(View.VISIBLE);
                    Toast.makeText(getApplicationContext(), "Terjadi Kesalahan", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ResponsePostTransaksi> call, Throwable t) {

            }
        });
    }
}
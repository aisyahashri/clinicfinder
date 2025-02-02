package com.example.mapapplication;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.mapapplication.AboutUsActivity;
import com.example.mapapplication.MapsActivity;
import com.example.mapapplication.R;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private static final int LOCATION_REQUEST_CODE = 1001;
    private TextView greetingText;
    private Button button;
    private FirebaseAuth auth;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private Button aboutUsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        greetingText = findViewById(R.id.greetingText);
        button = findViewById(R.id.button);
        aboutUsButton = findViewById(R.id.aboutUsButton);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        button.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MapsActivity.class);
            startActivity(intent);
        });
        aboutUsButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AboutUsActivity.class);
            startActivity(intent);
        });


        showUserInfo();


        getLocation();
    }

    private void showUserInfo() {
        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
            String name = user.getDisplayName();
            String email = user.getEmail();

            if (name != null && !name.isEmpty()) {
                greetingText.setText("Hello, " + name + "!\nFetching your location...");
            } else {
                greetingText.setText("Hello, " + email + "!\nFetching your location...");
            }
        } else {
            greetingText.setText("Welcome, Guest!\nFetching your location...");
        }
    }

    private void getLocation() {
        // Check if location services are enabled
        if (!isLocationEnabled()) {
            greetingText.setText("Please enable location services.");
            return;
        }

        // Check if location permissions are granted
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // Request location permissions
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    LOCATION_REQUEST_CODE);
            return;
        }

        // Use location updates for accurate results
        requestLocationUpdates();
    }

    private void requestLocationUpdates() {
        LocationRequest locationRequest = LocationRequest.create()
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
                .setInterval(10000) // 10-second interval
                .setFastestInterval(5000); // 5 seconds minimum

        LocationCallback locationCallback = new LocationCallback() {
            @Override
            public void onLocationResult(LocationResult locationResult) {
                if (locationResult == null) {
                    greetingText.setText("Unable to fetch location.");
                    return;
                }
                for (Location location : locationResult.getLocations()) {
                    if (location != null) {
                        updateGreetingWithLocation(location.getLatitude(), location.getLongitude());
                    }
                }
            }
        };

        // Check permissions before requesting updates
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, Looper.getMainLooper());
        }
    }

    private void updateGreetingWithLocation(double latitude, double longitude) {
        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
            String name = user.getDisplayName();
            String email = user.getEmail();
            String username = email.split("@")[0]; // Extract username before '@'
            String greeting;
            if (name != null && !name.isEmpty() && email.contains("@")) {
                greeting = "Hello, " + name + "!\nYour location:\nLatitude: " + latitude + "\nLongitude: " + longitude;
            } else {
                greeting = "Hello, " + username + "!\nYour location:\nLatitude: " + latitude + "\nLongitude: " + longitude;
            }
            greetingText.setText(greeting);
        }
    }

    private boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == LOCATION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, fetch location
                getLocation();
            } else {
                // Permission denied
                Toast.makeText(this, "Location permission is required to fetch your location.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void showToast(String s) {
    }
}
package com.example.mapapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.mapapplication.MainActivity;
import com.example.mapapplication.R;
import com.example.mapapplication.RegisterActivity;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private EditText etEmail, etPassword;
    private TextView tvRegister;
    private Button btnLogin;

    private FusedLocationProviderClient fusedLocationProviderClient;
    private static final int LOCATION_REQUEST_CODE = 1001;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvRegister = findViewById(R.id.tvRegister);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString();
                String pass = etPassword.getText().toString();

                if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    if (!pass.isEmpty()) {
                        loginUser(email, pass);
                    } else {
                        etPassword.setError("Password cannot be empty");
                    }
                } else if (email.isEmpty()) {
                    etEmail.setError("Email cannot be empty");
                } else {
                    etEmail.setError("Please enter a valid email");
                }
            }
        });

        tvRegister.setOnClickListener(v -> {
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        });
    }

    private void loginUser(String email, String password) {
        auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(authResult -> {
                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    fetchLocationAndSendData();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                });
    }

    private void fetchLocationAndSendData() {
        // Check for location permissions
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_REQUEST_CODE);
        } else {
            // Fetch the location
            fusedLocationProviderClient.getLastLocation().addOnCompleteListener(task -> {
                if (task.isSuccessful() && task.getResult() != null) {
                    Location location = task.getResult();
                    double latitude = location.getLatitude();
                    double longitude = location.getLongitude();
                    sendUserDataToServer(latitude, longitude);
                } else {
                    Toast.makeText(this, "Unable to fetch location", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(e -> {
                Toast.makeText(this, "Location fetch failed: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("LoginActivity", "Location fetch error", e);
            });
        }
    }


    private void sendUserDataToServer(double latitude, double longitude) {
        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
            String name = (user.getDisplayName() != null && !user.getDisplayName().isEmpty()) ? user.getDisplayName() : "Anonymous";
            String email = (user.getEmail() != null) ? user.getEmail() : "No Email";
            String location = "User logged in from Lat: " + latitude + ", Long: " + longitude;

            // Use PostComment class to send data
            PostComment postComment = new PostComment();
            postComment.sendPost(name, email, location, getApplicationContext(), new MainActivity());
        } else {
            Toast.makeText(this, "User authentication failed", Toast.LENGTH_SHORT).show();
        }

        // Redirect to MainActivity
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == LOCATION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                fetchLocationAndSendData();
            } else {
                Toast.makeText(this, "Location permission is required to proceed.", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
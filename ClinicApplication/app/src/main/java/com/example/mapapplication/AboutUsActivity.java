package com.example.mapapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;

public class AboutUsActivity extends AppCompatActivity {

    private Button gitHubButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        // Finding the GitHub button
        Button gitHubButton = findViewById(R.id.gitHubButton);

        // Setting an OnClickListener
        gitHubButton.setOnClickListener(v -> {
            // URL to GitHub
            String url = "https://github.com";

            // Creating an Intent to open a browser
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

            // Starting the activity
            startActivity(intent);
        });

    }
}

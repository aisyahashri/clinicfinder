package com.example.mapapplication;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class PostComment {

    public void sendPost(String name, String email, String location, Context ctx, MainActivity main) {
        String url = "http://10.0.2.2/comments/api.php"; // Ensure your server is running

        RequestQueue queue = Volley.newRequestQueue(ctx);

        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                response -> {
                    Log.d("PostComment", "Response: " + response);
                    if (main != null) {
                        main.showToast("Server Response: " + response);
                    }
                },
                error -> {
                    Log.e("PostComment", "Error: " + error.toString());
                    if (main != null) {
                        main.showToast("Error: " + error.toString());
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("name", name);
                params.put("email", email);
                params.put("location", location);
                return params;
            }
        };

        queue.add(postRequest);
    }
}

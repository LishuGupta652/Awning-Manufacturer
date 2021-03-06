package com.example.awningmanufacturer;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Videos extends AppCompatActivity {


    private static final String URL = "http://awningmanufacturer.org/videos.php";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<VideoItem> videoItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);


        // Recycler View
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        videoItems = new ArrayList<>();

        loadRecyclerViewData();

        adapter = new VideoAdapter(videoItems, this);

        recyclerView.setAdapter(adapter);
    }

    private void loadRecyclerViewData() {

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Videos ... ");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    progressDialog.dismiss();
                    JSONObject jsonObject = new JSONObject(response);
                    jsonObject = jsonObject.getJSONObject("data");
                    JSONArray offersArray = jsonObject.getJSONArray("content");



                    for(int i = 0; i< offersArray.length(); i++){
                        JSONObject o = offersArray.getJSONObject(i);

                        // Parsing The video Url
                        String content = o.getString("url");
                        String patternString = "=";
                        Pattern pattern = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
                        String[] myString = pattern.split(content);
                        String videoUrl = myString[1];

                        VideoItem item = new VideoItem(
                                o.getString("title"),
                                videoUrl,
                                o.getString("image")
                        );
                        videoItems.add(item);
                    }

                    adapter = new VideoAdapter(videoItems,getApplicationContext());
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(Videos.this, "Please Check your Internet Connection or try again later.", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}

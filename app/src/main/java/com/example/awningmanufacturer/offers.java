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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class offers extends AppCompatActivity {

    private static final String URL = "http://awningmanufacturer.org/offer.php";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<OfferItem> offerItems;
    private String offerEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offers);

        // Recycler View
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        offerItems = new ArrayList<>();

        loadRecyclerViewData();
        
        adapter = new OfferAdapter(offerItems, this);

        recyclerView.setAdapter(adapter);
    }

    private void loadRecyclerViewData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Offers ... ");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    progressDialog.dismiss();
                    System.out.println(jsonObject.toString());
                    jsonObject = jsonObject.getJSONObject("data");
                    JSONArray offersArray = jsonObject.getJSONArray("content");

                    for(int i = 0; i< offersArray.length(); i++){
                        JSONObject o = offersArray.getJSONObject(i);
                        OfferItem item = new OfferItem(
                                o.getString("offerName"),
                                o.getString("off"),
                                o.getString("valid"),
                                o.getString("background")
                        );
                        offerItems.add(item);
                    }

                    adapter = new OfferAdapter(offerItems,getApplicationContext());
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(offers.this, "Please Check your Internet Connection or try again later.", Toast.LENGTH_SHORT).show();
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<String, String>();
                Bundle extras = getIntent().getExtras();

                String email = getIntent().getStringExtra("EMAIL");
                    //The key argument here must match that used in the other activity
                MyData.put("name", email);

                 //Add the data you'd like to send to the server.
                return MyData;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

}

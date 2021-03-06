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


/*
Developed by lishu gupta
web: https://www.pakkabaniya.ml
 */

public class Contacts extends AppCompatActivity {


    private static final String URL = "http://awningmanufacturer.org/contacts.php";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<ContactItem> contactItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        // Recycler View
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        contactItems = new ArrayList<>();

        loadRecyclerViewData();

        adapter = new ContactAdapter(contactItems, this);

        recyclerView.setAdapter(adapter);
    }

    private void loadRecyclerViewData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Contacts ... ");
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
                        ContactItem item = new ContactItem(
                                o.getString("address"),
                                o.getString("email"),
                                o.getString("mobile")
                        );
                        contactItems.add(item);
                    }

                    adapter = new ContactAdapter(contactItems,getApplicationContext());
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(Contacts.this, "Please Check your Internet Connection or try again later.", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}

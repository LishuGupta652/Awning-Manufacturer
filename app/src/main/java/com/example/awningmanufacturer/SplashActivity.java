package com.example.awningmanufacturer;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.HashMap;
import java.util.Map;


/*
Developed by lishu gupta
web: https://www.pakkabaniya.ml
 */

public class SplashActivity extends AppCompatActivity {
    private Button btnOverview,btnOffers, btnWebsite, btnSccRegistrationBuyer, btnSccRegistrationSeller, btnGroupCompanies, btnContacts, btnVideos, btnFeedback;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        requestQueue = Volley.newRequestQueue(this);


        btnOverview = findViewById(R.id.btnOverview);
        btnSccRegistrationBuyer = findViewById(R.id.btnSccRegistrationBuyer);
        btnSccRegistrationSeller = findViewById(R.id.btnSccRegistrationSeller);
        btnGroupCompanies = findViewById(R.id.btnGroupCompanies);
        btnOffers = findViewById(R.id.btnOffers);
        btnWebsite = findViewById(R.id.btnWebsite);
        btnContacts = findViewById(R.id.btnContacts);
        btnVideos = findViewById(R.id.btnVideos);
        btnFeedback = findViewById(R.id.btnFeedback);

        // On click listners for Main Activity
        btnOverview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOverview();
            }
        });
        btnSccRegistrationBuyer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSccRegistrationBuyer();
            }
        });

        btnSccRegistrationSeller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSccRegistrationSeller();
            }
        });
        btnGroupCompanies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGroupCompanies();
            }
        });
        btnOffers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openOffers();
            }
        });
        btnWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openWebsite();
            }
        });
        btnContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openContacts();
            }
        });
        btnVideos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openVideos();
            }
        });
        btnFeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFeedback();
            }
        });

    }

    private void openSccRegistrationSeller() {
        Intent intent = new Intent(this, SccRegistrationSeller.class);
        startActivity(intent);
    }

    private void openSccRegistrationBuyer() {
        Intent intent = new Intent(this, SccRegistrationBuyer.class);
        startActivity(intent);
    }

    private void openGroupCompanies() {
        Intent intent = new Intent(this, Group.class);
        startActivity(intent);
    }


    private void openWebsite() {
//        Intent intent = new Intent(ACTION_VIEW);
//        intent.setData(Uri.parse("http://www.shrayanscoatings.com/"));
//        startActivity(intent);
        Intent intent = new Intent(this, Website.class);
        startActivity(intent);
    }

    private void openFeedback() {
//        Intent intent = new Intent(this, Feedback.class);
//        startActivity(intent);

        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Feedback form ");
        dialog.setMessage("Provide us with your valuable Feedback ");

        LayoutInflater inflater = LayoutInflater.from(this);

        View layout = inflater.inflate(R.layout.send_feedback, null);

        final MaterialEditText edtName = layout.findViewById(R.id.edtName);
        final MaterialEditText edtEmail= layout.findViewById(R.id.edtEmail);
        final MaterialEditText edtMobile = layout.findViewById(R.id.edtMobile);
        final MaterialEditText edtFeedback = layout.findViewById(R.id.edtFeedback);

        dialog.setView(layout);

        // Set button
        dialog.setPositiveButton("SEND", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Check Validation


                if(TextUtils.isEmpty(edtName.getText().toString())){
                    Toast.makeText(SplashActivity.this, "Please enter your name... ", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(edtEmail.getText().toString())){
                    Toast.makeText(SplashActivity.this, "Please enter your email...", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(edtMobile.getText().toString())){
                    Toast.makeText(SplashActivity.this, "Please enter your Mobile no...", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(edtFeedback.getText().toString())){
                    Toast.makeText(SplashActivity.this, "Please submit Feedback...", Toast.LENGTH_SHORT).show();
                    return;
                }

                final ProgressDialog progressDialog = new ProgressDialog(SplashActivity.this);
                progressDialog.setMessage("Sending Feedback ... ");
                progressDialog.setCanceledOnTouchOutside(false);
                progressDialog.show();


                // input fields from Feedback form
                final String name = edtName.getText().toString();
                final String email = edtEmail.getText().toString();
                final String mobile = edtMobile.getText().toString();
                final String feedback = edtFeedback.getText().toString();

                System.out.println(name + email+ mobile+ feedback);
                //Posting Data Here
                String url = "http://awningmanufacturer.org/feedback.php";

                StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Stopping progress Dialog
                        progressDialog.dismiss();

                        //This code is executed if the server responds, whether or not the response contains data.
                        //The String 'response' contains the server's response.
                        System.out.println(response.toString());
                        Toast.makeText(SplashActivity.this, "Feedback Send ....", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        //This code is executed if there is an error.
                        Toast.makeText(SplashActivity.this, "Please Check your Internet Connection or try again later.", Toast.LENGTH_SHORT).show();
                        System.out.println("--------Errror generated while response ---------");
                    }
                }) {
                    protected Map<String, String> getParams() {
                        Map<String, String> MyData = new HashMap<String, String>();
                        MyData.put("name", name); //Add the data you'd like to send to the server.
                        MyData.put("email", email);
                        MyData.put("mobileno", mobile);
                        MyData.put("message", feedback);
                        return MyData;
                    }
                };

                requestQueue.add(MyStringRequest);

            }

        });

        dialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        dialog.show();


    }

    private void openVideos() {
        Intent intent = new Intent(this, Videos.class);
        startActivity(intent);
    }

    private void openContacts() {
        Intent intent = new Intent(this, Contacts.class);
        startActivity(intent);
    }

    private void openOffers() {
//        Intent intent = new Intent(this, offers.class);
//        startActivity(intent);
        Intent intent = new Intent(this, OfferEmail.class);
        startActivity(intent);

    }

    private void openOverview() {
        Intent intent = new Intent(this, Overview.class);
        startActivity(intent);
    }

}

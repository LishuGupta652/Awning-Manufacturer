package com.example.awningmanufacturer;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.weiwangcn.betterspinner.library.BetterSpinner;

import java.util.HashMap;
import java.util.Map;

public class SccRegistration extends AppCompatActivity {

    String [] indianStates = { "Andhra Pradesh","Arunachal Pradesh","Assam","Bihar","Chhattisgarh","Goa","Gujarat","Haryana","Himachal Pradesh","Jammu and Kashmir","Jharkhand","Karnataka","Kerala","Madhya Pradesh","Maharashtra","Manipur","Meghalaya","Mizoram","Nagaland","Odisha","Punjab","Rajasthan","Sikkim","Tamil Nadu","Telangana","Tripura","Uttarakhand","Uttar Pradesh","West Bengal","Andaman and Nicobar Islands","Chandigarh","Dadra and Nagar Haveli","Daman and Diu","Delhi","Lakshadweep","Puducherry"};
    String [] buyerTypes = { "Buyer", "Consumer"};

    MaterialEditText edtName, edtEmail, edtMobile, edtAddress, edtCity, edtPincode, edtCompanyName, edtGstin;
    BetterSpinner bsStates, bsBType;
    Button btnRegister;

    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scc_registration);


        requestQueue = Volley.newRequestQueue(this);


        // States Adapter
        ArrayAdapter<String> adapterCity = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, indianStates);
        BetterSpinner states = findViewById(R.id.bsStates);
        states.setAdapter(adapterCity);


        // BType Adapter
        ArrayAdapter<String> adapterBType = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, buyerTypes);
        BetterSpinner btype = findViewById(R.id.bsBType);
        btype.setAdapter(adapterBType);


       // Initializing input fields
       edtName = findViewById(R.id.edtName);
       edtEmail = findViewById(R.id.edtEmail);
       edtMobile = findViewById(R.id.edtMobile);
       edtAddress = findViewById(R.id.edtAddress);
       edtCity = findViewById(R.id.edtCity);
       edtPincode = findViewById(R.id.edtPincode);
       edtCompanyName = findViewById(R.id.edtCompanyName);
       edtGstin = findViewById(R.id.edtGstin);

       bsStates = findViewById(R.id.bsStates);
       bsBType = findViewById(R.id.bsBType);

       btnRegister = findViewById(R.id.btnRegister);

       btnRegister.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               sendRegistrationData();
           }
       });


    }

    private void sendRegistrationData() {


        if(TextUtils.isEmpty(edtName.getText().toString())){
            Toast.makeText(this, "Please enter your name... ", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(edtEmail.getText().toString())){
            Toast.makeText(this, "Please enter your email...", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(edtMobile.getText().toString())){
            Toast.makeText(this, "Please enter your Mobile no...", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(edtAddress.getText().toString())){
            Toast.makeText(this, "Please enter your Address...", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(edtCity.getText().toString())){
            Toast.makeText(this, "Please enter your City ...", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(edtPincode.getText().toString())){
            Toast.makeText(this, "Please enter your Pincode ...", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(bsStates.getText().toString())){
            Toast.makeText(this, "Please Choose your state...", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(bsBType.getText().toString())){
            Toast.makeText(this, "Please Choose Consumer type correctly...", Toast.LENGTH_SHORT).show();
            return;
        }


        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Registering  ... ");
        progressDialog.show();



        // input fields from Feedback form
        final String name = edtName.getText().toString();
        final String email = edtEmail.getText().toString();
        final String mobile = edtMobile.getText().toString();
        final String address = edtAddress.getText().toString();
        final String city= edtCity.getText().toString();
        final String pincode = edtPincode.getText().toString();
        final String companyName = edtCompanyName.getText().toString();
        final String gstin = edtGstin.getText().toString();
        final String state = bsStates.getText().toString();
//        final String btype = bsBType.getText().toString();
        final String btype = "buyer";

        System.out.println("Enter data is ----------------------------------");
        System.out.println(name + email + mobile+ address + city + pincode + companyName + gstin + state + btype );


        //Posting Data Here
        String url = "http://awningmanufacturer.org/sccregistration.php";

        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Stopping progress Dialog
                progressDialog.dismiss();

                //This code is executed if the server responds, whether or not the response contains data.
                //The String 'response' contains the server's response.
                System.out.println(response.toString());
                Toast.makeText(SccRegistration.this, "\"Thanks for registration, We contact shortly.", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(SccRegistration.this, SplashActivity.class);
                startActivity(intent);
                finish(); // Destroy activity A and not exist in Back stack

            }

        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                //This code is executed if there is an error.
                System.out.println("--------Error generated while response ---------");
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<String, String>();
                MyData.put("name", name); //Add the data you'd like to send to the server.
                MyData.put("email", email);
                MyData.put("mobileno", mobile);
                MyData.put("address", address);
                MyData.put("state", state);
                MyData.put("city", city);
                MyData.put("pincode", pincode);
                MyData.put("companyName", companyName);
                MyData.put("gstin", gstin);
                MyData.put("btype", btype);
                return MyData;
            }
        };

        requestQueue.add(MyStringRequest);



    }

}

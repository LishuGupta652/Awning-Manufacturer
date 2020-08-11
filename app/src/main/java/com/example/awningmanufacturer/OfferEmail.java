package com.example.awningmanufacturer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class OfferEmail extends AppCompatActivity {

    EditText edtEmail;
    Button btnSubmitEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offer_email);

        edtEmail = findViewById(R.id.edtEmail);
        btnSubmitEmail = findViewById(R.id.btnSubmitEmail);

        btnSubmitEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = edtEmail.getText().toString();

                if(TextUtils.isEmpty(edtEmail.getText().toString()) ){
                    Toast.makeText(OfferEmail.this, "Enter your email...", Toast.LENGTH_SHORT).show();
                    return;
                }
                if( !isValid(edtEmail.getText().toString())){
                    Toast.makeText(OfferEmail.this, "Enter your email correctly ...", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(OfferEmail.this, offers.class);
                intent.putExtra("EMAIL", email);
                startActivity(intent);
                finish();
            }
        });
    }

    private boolean isValid(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
}

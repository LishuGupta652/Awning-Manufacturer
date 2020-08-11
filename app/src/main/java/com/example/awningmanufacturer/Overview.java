package com.example.awningmanufacturer;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Overview extends AppCompatActivity {

    RequestQueue rq;
    TextView tvAboutUs, tvAboutUsContent, tvWhatWeDo, tvWhatWeDoContent, tvWhoAreYou, tvWhoAreYouContent;

    String url = "http://awningmanufacturer.org/overview.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview);

        rq = Volley.newRequestQueue(this);


        tvAboutUs = findViewById(R.id.tvAboutUs);
        tvAboutUsContent = findViewById(R.id.tvAboutUsContent);

        tvWhatWeDo = findViewById(R.id.tvWhatWeDo);
        tvWhatWeDoContent = findViewById(R.id.tvWhatWeDoContent);

        tvWhoAreYou = findViewById(R.id.tvWhoAreYou);
        tvWhoAreYouContent = findViewById(R.id.tvWhoAreYouContent);

        sendjsonrequest();
    }

    public void sendjsonrequest(){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading... ");
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


                try {
                    // getting data
                    progressDialog.dismiss();
                    JSONObject jo = response.getJSONObject("data");

                    // getting About us
                    jo = jo.getJSONObject("About Us");

                    String title,content;

                    title = jo.getString("title");
                    content = jo.getString("content");

                    tvAboutUs.setText(title);
                    tvAboutUsContent.setText(content);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        tvAboutUsContent.setText(Html.fromHtml(content, Html.FROM_HTML_MODE_LEGACY));
                    } else
                        tvAboutUsContent.setText(Html.fromHtml(content));

                    // Getting What we do contents
                    jo = response.getJSONObject("data");
                    jo = jo.getJSONObject("What We Do");

                    title = jo.getString("title");
                    content = jo.getString("content");

                    tvWhatWeDo.setText(title);
                    tvWhatWeDoContent.setText(content);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        tvWhatWeDoContent.setText(Html.fromHtml(content, Html.FROM_HTML_MODE_LEGACY));
                    } else
                        tvWhatWeDoContent.setText(Html.fromHtml(content));



                    // Getting Who are you contents
                    jo = response.getJSONObject("data");
                    jo = jo.getJSONObject("Who are you");

                    title = jo.getString("title");
                    content = jo.getString("content");

                    tvWhoAreYou.setText(title);
                    tvWhoAreYouContent.setText(content);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                        tvWhoAreYouContent.setText(Html.fromHtml(content, Html.FROM_HTML_MODE_LEGACY));
                    } else
                        tvWhoAreYouContent.setText(Html.fromHtml(content));


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(Overview.this, "Please Check your Internet Connection or try again later.", Toast.LENGTH_SHORT).show();
            }
        });

        rq.add(jsonObjectRequest);
    }
}

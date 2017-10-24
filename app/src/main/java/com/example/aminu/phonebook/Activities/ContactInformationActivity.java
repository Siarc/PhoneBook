package com.example.aminu.phonebook.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.aminu.phonebook.Constants.Constants;
import com.example.aminu.phonebook.R;
import com.example.aminu.phonebook.Utils.RequestHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by aminu on 10/24/2017.
 */

public class ContactInformationActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView update_nameText;
    private TextView update_numberText;
    private EditText update_contact_name;
    private EditText update_contact_number;
    private Button btn_back;
    private Button btn_update;
    private Button btn_delete;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_information);

        iniWidgets();



    }

    private void iniWidgets() {

        update_nameText = (TextView) findViewById(R.id.update_nameText);
        update_numberText = (TextView) findViewById(R.id.update_numberText);
        update_contact_name = (EditText) findViewById(R.id.update_contact_name);
        update_contact_number = (EditText) findViewById(R.id.update_contact_number);
        btn_back = (Button) findViewById(R.id.btn_back);
        btn_update = (Button) findViewById(R.id.btn_update);
        btn_delete = (Button) findViewById(R.id.btn_delete);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        progressBar.setVisibility(View.GONE);

        update_contact_name.setText(HomeActivity.name);
        update_contact_number.setText(HomeActivity.number);

        btn_back.setOnClickListener(this);
        btn_update.setOnClickListener(this);
        btn_delete.setOnClickListener(this);

    }

    private void updateContact() {

        final String id = String.valueOf(HomeActivity.id);
        final String contact_name = update_contact_name.getText().toString().trim();
        final String contact_number = update_contact_number.getText().toString().trim();

        if(!contact_name.isEmpty()){

            if(!contact_number.isEmpty()){

                progressBar.setVisibility(View.VISIBLE);

                StringRequest stringRequest = new StringRequest(Request.Method.POST,
                        Constants.URL_updateContact,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                progressBar.setVisibility(View.GONE);
                                update_contact_name.getText().clear();
                                update_contact_number.getText().clear();


                                try {
                                    JSONObject jsonObject = new JSONObject(response);

                                    Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_SHORT).show();

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                                progressBar.setVisibility(View.GONE);
                            }
                        }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        Map<String,String> params = new HashMap<>();
                        params.put("contact_id",id);
                        params.put("contact_name",contact_name);
                        params.put("contact_number",contact_number);

                        return params;
                    }
                };

                RequestHandler.getInstance(this).addToRequestQueue(stringRequest);
            }else{

                Toast.makeText(getApplicationContext(),"Needs a Contact Number",Toast.LENGTH_SHORT).show();

            }
        }else{

            Toast.makeText(getApplicationContext(),"Needs a Contact Name",Toast.LENGTH_SHORT).show();
        }

    }

    private void deleteContact() {

        final String id = String.valueOf(HomeActivity.id);

        progressBar.setVisibility(View.VISIBLE);

        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_deleteContact,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        progressBar.setVisibility(View.GONE);
                        update_contact_name.getText().clear();
                        update_contact_number.getText().clear();


                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            Toast.makeText(getApplicationContext(),jsonObject.getString("message"),Toast.LENGTH_SHORT).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        progressBar.setVisibility(View.GONE);
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> params = new HashMap<>();
                params.put("contact_id",id);

                return params;
            }
        };

        RequestHandler.getInstance(this).addToRequestQueue(stringRequest);

    }


    @Override
    public void onClick(View view) {

        if(view == btn_back){

            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            finish();

        }else if(view == btn_update){

            updateContact();

        }else if(view == btn_delete){

            deleteContact();
        }

    }

}

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

public class CreateContactActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "CreateContactActivity";

    private TextView create_nameText;
    private TextView create_numberText;
    private EditText create_contact_name;
    private EditText create_contact_number;
    private Button btn_back;
    private Button btn_create;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_contact);

        iniWidgets();

    }

    private void iniWidgets() {

        create_nameText = (TextView) findViewById(R.id.create_nameText);
        create_numberText = (TextView) findViewById(R.id.create_numberText);
        create_contact_name = (EditText) findViewById(R.id.create_contact_name);
        create_contact_number = (EditText) findViewById(R.id.create_contact_number);
        btn_back = (Button) findViewById(R.id.btn_back);
        btn_create = (Button) findViewById(R.id.btn_create);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        progressBar.setVisibility(View.GONE);

        btn_back.setOnClickListener(this);
        btn_create.setOnClickListener(this);
    }

    private void createContact() {

        final String contact_name = create_contact_name.getText().toString().trim();
        final String contact_number = create_contact_number.getText().toString().trim();

        if(!contact_name.isEmpty()){

            if(!contact_number.isEmpty()){

                progressBar.setVisibility(View.VISIBLE);

                StringRequest stringRequest = new StringRequest(Request.Method.POST,
                        Constants.URL_createContact,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {

                                progressBar.setVisibility(View.GONE);
                                create_contact_name.getText().clear();
                                create_contact_number.getText().clear();


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


    @Override
    public void onClick(View view) {

        if(view == btn_back){

            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
            finish();

        } else if (view == btn_create){

            createContact();
        }

    }


}

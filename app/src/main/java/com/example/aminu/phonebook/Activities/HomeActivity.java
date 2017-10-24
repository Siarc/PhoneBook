package com.example.aminu.phonebook.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.aminu.phonebook.Constants.Constants;
import com.example.aminu.phonebook.Models.PhoneBook;
import com.example.aminu.phonebook.R;
import com.example.aminu.phonebook.Utils.PhoneListAdapter;
import com.example.aminu.phonebook.Utils.RequestHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";

    public ListView listView;
    private EditText searchView;

    public static Integer id;
    public static String name;
    public static String number;
    /*private int[] id ={1,2,3,4,5,6,7,8,9,10};
    private String [] contact_name = {"Aminul Islam","Neela Moni","Lunatia Aru","Muneef Timu","Ahad Rony","Nazra Antara","Sabrina Rashid","Sabila Nur","Sarika","Shokh"};
    private long[] contact_number = {1123123,1231231,2352,12312,235,123,5211,1345323,522341,123123};*/
    private ArrayList<PhoneBook> phoneList = new ArrayList<PhoneBook>();
    private ArrayList<PhoneBook> testList = new ArrayList<PhoneBook>();

    public static PhoneListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        iniWidgets();

        listOfContact();

    }

    private void listOfContact(){

        Log.d(TAG, "listOfContact: started");


        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                Constants.URL_listOfContact,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {


                        try{
                            //converting the string to json array
                            JSONArray array = new JSONArray(response);

                            //traversing through all the object
                            for(int i = 0; i <array.length();i++){

                                //getting phoneBook object from json object array
                                JSONObject phoneBook =array.getJSONObject(i);

                                //adding the phoneBook to testList
                                testList.add(new PhoneBook(
                                        phoneBook.getInt("id"),
                                        phoneBook.getString("contact_name"),
                                        phoneBook.getString("contact_number")
                                ));

                            }
                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                        setupListView();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

                RequestHandler.getInstance(this).addToRequestQueue(stringRequest);

    }

    private void setupListView() {

        /*for(int i = 0; i <contact_name.length;i++){
            PhoneBook phoneBook = new PhoneBook(id[i],contact_name[i],contact_number[i]);

            phoneList.add(phoneBook);
        }*/
        phoneList=testList;


        listView = (ListView) findViewById(R.id.phoneList);


        adapter = new PhoneListAdapter(this,phoneList);
        listView.setAdapter(adapter);

        listView.setTextFilterEnabled(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> adapterView, View view, int i, long l) {

                PhoneBook phoneBook = (PhoneBook) adapterView.getItemAtPosition(i);
                Toast.makeText(HomeActivity.this,phoneBook.getContact_name(),Toast.LENGTH_SHORT).show();

            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                PhoneBook phoneBook = (PhoneBook) adapterView.getItemAtPosition(i);
                id = phoneBook.getId();
                name = phoneBook.getContact_name();
                number = phoneBook.getContact_number();

                Intent intent =new Intent(getApplicationContext(), ContactInformationActivity.class);
                startActivity(intent);
                return true;
            }
        });

        searchView = (EditText) findViewById(R.id.searchView);
        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                String text = searchView.getText().toString().toLowerCase(Locale.getDefault());

                adapter.filter(text);

            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });


    }

    private void iniWidgets() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CreateContactActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement


        return super.onOptionsItemSelected(item);
    }
}

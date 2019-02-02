package com.example.aminu.phonebook.Utils;

import com.example.aminu.phonebook.Models.PhoneBook;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;

public interface Api {

    String BASE_URL = "http://192.168.0.104:51028/api/PhoneBook/";

    @FormUrlEncoded
    @GET("ContactList")
    Call<List<PhoneBook>> getContactList();

}

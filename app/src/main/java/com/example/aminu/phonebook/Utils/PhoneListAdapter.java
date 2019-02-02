package com.example.aminu.phonebook.Utils;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.aminu.phonebook.Models.PhoneBook;
import com.example.aminu.phonebook.Models.PhoneBooks;
import com.example.aminu.phonebook.R;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

/**
 * Created by aminu on 10/16/2017.
 */

public class PhoneListAdapter extends BaseAdapter {

    private static final String TAG = "PhoneListAdapter";

    private Context context;
    LayoutInflater inflater;
    private List<PhoneBook> phoneList;
    private List<PhoneBook> tempList;

    public PhoneListAdapter(Context context, List<PhoneBook> phoneList) {
        Log.d(TAG, "PhoneListAdapter: started");

        this.context = context;
        this.phoneList = phoneList;
        this.tempList = new LinkedList<PhoneBook>();
        this.tempList.addAll(phoneList);
    }

    @Override
    public int getCount() {
        return phoneList.size();
    }

    @Override
    public Object getItem(int i) {
        return phoneList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    private static class ViewHolder{
        TextView nameView;
        TextView numberView;
    }


    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        final ViewHolder holder;

        if (view == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.custom_phone_list, null);

            holder.nameView = (TextView) view.findViewById(R.id.personName);
            holder.numberView = (TextView) view.findViewById(R.id.personNumber);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        holder.nameView.setText(phoneList.get(position).getContact_name());
        holder.numberView.setText(String.valueOf(phoneList.get(position).getContact_number()));

        return view;
    }

    public void filter(String charText){

        charText = charText.toLowerCase(Locale.getDefault());
        phoneList.clear();
        if(charText.length() == 0){
            phoneList.addAll(tempList);
        }else {
            for(PhoneBook phoneBook : tempList){
                if(phoneBook.getContact_name().toLowerCase(Locale.getDefault())
                        .contains(charText)){
                    phoneList.add(phoneBook);
                }
            }
        }
        notifyDataSetChanged();
    }


}

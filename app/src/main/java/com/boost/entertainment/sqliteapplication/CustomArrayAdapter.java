package com.boost.entertainment.sqliteapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by waqas on 19/09/2016.
 */
public class CustomArrayAdapter extends ArrayAdapter<Contact> {

    private List<Contact> contactList ;

    private int resourceId ;

    private Context context ;

    public CustomArrayAdapter(Context context, int resource, List<Contact> contactList) {
        super(context, resource, contactList);
        this.context = context ;
        this.resourceId = resource ;
        this.contactList = contactList ;
    }

    @Override
    public int getCount() {
        return contactList.size();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            Contact contact = contactList.get(position) ;
            convertView = LayoutInflater.from(parent.getContext()).inflate(resourceId , parent , false);
            TextView textView = (TextView) convertView.findViewById(android.R.id.text1);
            textView.setText(contact.getName());
        }
        return convertView;
    }

}

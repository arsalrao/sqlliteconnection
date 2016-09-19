package com.boost.entertainment.sqliteapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MyDialog.YesNoListener{

    private ListView listView ;
    private ContactDataSource dataSource ;
    private Contact dialogContact ;
    private CustomArrayAdapter adapter ;
    private ArrayList<Contact> contactArrayList ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listview);
        dataSource = new ContactDataSource(this);

        Toast.makeText(MainActivity.this, "created", Toast.LENGTH_SHORT).show();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                showContactDialog((Contact)adapterView.getItemAtPosition(i));

            }
        });



    }

    private void showContactDialog(Contact contact) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        dialogContact = contact ;

        builder.setTitle(contact.getName());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(contact.getName()).append("\n")
                .append(contact.getEmail()).append("\n")
                .append(contact.getAddress());
        builder.setMessage(stringBuilder);

         MyDialog dialog = MyDialog.newInstance(builder);

        dialog.show(getSupportFragmentManager() , "tag");





    }

    private ArrayList<String> getContactsName(ArrayList<Contact> contactArrayList) {
        ArrayList<String> stringArrayList = new ArrayList<>();
        for (Contact contact : contactArrayList){
            stringArrayList.add(contact.getName());
        }

        return stringArrayList ;
    }

    @Override
    protected void onResume() {

         contactArrayList = dataSource.getAllContacts() ;



        if (contactArrayList != null) {

             adapter = new CustomArrayAdapter(this, android.R.layout.simple_list_item_1 , contactArrayList);

            listView.setAdapter(adapter);

        }



        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_contact_menu , menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_contact_menu :
                startActivity(new Intent(this , AddContactDetail.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void yes(DialogInterface dialogInterface) {

        if (dialogContact !=null){

            if (dataSource.deleteContactById(dialogContact.getId())){
                contactArrayList.remove(dialogContact);
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this,dialogContact.getName()+ " is deleted", Toast.LENGTH_SHORT).show();
            }


        }

        dialogInterface.dismiss();


    }

    @Override
    public void no(DialogInterface dialogInterface) {

        dialogInterface.dismiss();

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT)
        Toast.makeText(MainActivity.this, "portrait", Toast.LENGTH_SHORT).show();

        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE)
        Toast.makeText(MainActivity.this, "landscape", Toast.LENGTH_SHORT).show();

        super.onConfigurationChanged(newConfig);
    }
}

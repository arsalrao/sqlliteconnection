package com.boost.entertainment.sqliteapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddContactDetail extends AppCompatActivity {

    private EditText name , email , address ;
    private Button saveButton ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact_detail);

        name = (EditText) findViewById(R.id.name_box);
        email = (EditText) findViewById(R.id.email_box);
        address = (EditText) findViewById(R.id.address_box);



        saveButton = (Button) findViewById(R.id.save_button);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                view.setVisibility(View.INVISIBLE);
                saveData();
            }
        });
    }

    private void saveData() {

        ContactDataSource dataSource = new ContactDataSource(this);

        String contactName = name.getText().toString();
        String contactEmail = email.getText().toString();
        String contactAddress = address.getText().toString();

        if(dataSource.insertContact(new Contact(contactName , contactEmail , contactAddress))) {

            Toast.makeText(AddContactDetail.this, contactName + " saved", Toast.LENGTH_SHORT).show();
        }
        finish();
    }
}

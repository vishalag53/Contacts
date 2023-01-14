package com.vishalag53.contacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerViewContacts;
    FloatingActionButton btnAddContact;
    ArrayList<ContactModel> arrContacts = new ArrayList<>();
    RecyclerContactAdapeter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewContacts = findViewById(R.id.recyclerViewContacts);
        btnAddContact = findViewById(R.id.btnAddContact);

        // Click on button, to adding more contact number on a Contact List
        btnAddContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.add_contact);

                EditText edtName = dialog.findViewById(R.id.edtName);
                EditText edtNumber = dialog.findViewById(R.id.edtNumber);
                Button btnAction  = dialog.findViewById(R.id.btnAction);

                btnAction.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name="",number="";
                        if(!edtName.getText().toString().equals("")){
                            name = edtName.getText().toString();
                        }
                        else{
                            Toast.makeText(MainActivity.this,"Please Enter the Contact Name",Toast.LENGTH_SHORT).show();
                        }

                        if(!edtNumber.getText().toString().equals("")){
                            number = edtNumber.getText().toString();
                        }
                        else{
                            Toast.makeText(MainActivity.this,"Please Enter the Contact Number",Toast.LENGTH_SHORT).show();
                        }

                        arrContacts.add(new ContactModel(R.drawable.e,name,number));
                        adapter.notifyItemInserted(arrContacts.size()-1);
                        recyclerViewContacts.scrollToPosition(arrContacts.size()-1);

                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });

        // Set layout on recycler view
        recyclerViewContacts.setLayoutManager(new LinearLayoutManager(this));

        arrContacts.add(new ContactModel(R.drawable.b,"A","4788965547"));
        arrContacts.add(new ContactModel(R.drawable.c,"B","8745958145"));
        arrContacts.add(new ContactModel(R.drawable.d,"C","8452145633"));
        arrContacts.add(new ContactModel(R.drawable.e,"D","5475112365"));
        arrContacts.add(new ContactModel(R.drawable.f,"E","4741225864"));
        arrContacts.add(new ContactModel(R.drawable.g,"F","5552223688"));
        arrContacts.add(new ContactModel(R.drawable.h,"G","8745123654"));
        arrContacts.add(new ContactModel(R.drawable.k,"H","7845845874"));
        arrContacts.add(new ContactModel(R.drawable.j,"I","1441258965"));
        arrContacts.add(new ContactModel(R.drawable.d,"J","2544125632"));
        arrContacts.add(new ContactModel(R.drawable.b,"K","4514562145"));
        arrContacts.add(new ContactModel(R.drawable.c,"L","8895478556"));
        arrContacts.add(new ContactModel(R.drawable.o,"M","9874562145"));
        arrContacts.add(new ContactModel(R.drawable.e,"N","7845874589"));
        arrContacts.add(new ContactModel(R.drawable.g,"O","7845715884"));

        adapter = new RecyclerContactAdapeter(this,arrContacts);
        recyclerViewContacts.setAdapter(adapter);

    }
}
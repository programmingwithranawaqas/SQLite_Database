package com.example.sqlv1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etName, etCell;
    ContactsDB contactsDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

    }
    public void init()
    {
        etName = findViewById(R.id.etName);
        etCell = findViewById(R.id.etCell);
        contactsDB = new ContactsDB(this);
    }

    public void clear()
    {
        etName.setText("");
        etCell.setText("");
    }
    public void btnSubmit(View v)
    {
        String name = etName.getText().toString().trim();
        String cell = etCell.getText().toString().trim();


        contactsDB.open();
        contactsDB.createEntry(name, cell);
        contactsDB.close();

    }

    public void btnShowData(View v)
    {
        startActivity(new Intent(MainActivity.this, ShowData.class));
    }

    public void btnEditData(View v)
    {
        contactsDB.open();
        long totalUpdatedRecords =  contactsDB.updateEntry("1", "Rana Waqas Ali", "03234677035");
        Toast.makeText(this, ""+totalUpdatedRecords, Toast.LENGTH_SHORT).show();
        contactsDB.close();
    }

    public void btnDeleteData(View v)
    {
        contactsDB.open();
        contactsDB.deleteEntry("1");
        contactsDB.close();
    }


}
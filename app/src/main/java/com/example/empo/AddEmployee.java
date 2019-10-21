package com.example.empo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioButton;

public class AddEmployee extends AppCompatActivity {

    EditText txtnname, txtphone , txtemail;
    RadioButton male,female;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_employee);
    }
}

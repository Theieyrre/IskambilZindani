package com.example.iskambilzindani;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Spinner spinner1 = findViewById(R.id.spinner);
        spinner1.setOnItemSelectedListener(this);

        final Spinner spinner2 = findViewById(R.id.spinner2);
        spinner2.setOnItemSelectedListener(this);

        final Spinner spinner3 = findViewById(R.id.spinner3);
        spinner3.setOnItemSelectedListener(this);

        final Spinner spinner4 = findViewById(R.id.spinner4);
        spinner4.setOnItemSelectedListener(this);

        List<String> heroes = new ArrayList<>();
        heroes.add("--Savaşa katılmıyor--");
        heroes.add("Savaşçı");
        heroes.add("Büyücü");
        heroes.add("Haydut");
        heroes.add("Şaman");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, heroes);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner1.setAdapter(dataAdapter);
        spinner2.setAdapter(dataAdapter);
        spinner3.setAdapter(dataAdapter);
        spinner4.setAdapter(dataAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String item = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(),item, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
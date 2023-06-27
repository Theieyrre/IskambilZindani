package com.example.iskambilzindani;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Spinner spinner1 = findViewById(R.id.spinner1);
        spinner1.setOnItemSelectedListener(this);
        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String text = spinner1.getSelectedItem().toString();
                Intent i = new Intent(getApplicationContext(),EditHero.class);
                i.putExtra("kahraman", text);
                /*startActivityForResult(); */
            }
        });

        final Spinner spinner2 = findViewById(R.id.spinner2);
        spinner2.setOnItemSelectedListener(this);
        Button button2 = (Button) findViewById(R.id.button2);

        final Spinner spinner3 = findViewById(R.id.spinner3);
        spinner3.setOnItemSelectedListener(this);
        Button button3 = (Button) findViewById(R.id.button3);

        final Spinner spinner4 = findViewById(R.id.spinner4);
        spinner4.setOnItemSelectedListener(this);
        Button button4 = (Button) findViewById(R.id.button4);

        List<String> kahramanlar = new ArrayList<>();
        kahramanlar.add("--Savaşa katılmıyor--");
        kahramanlar.add("Savaşçı");
        kahramanlar.add("Büyücü");
        kahramanlar.add("Haydut");
        kahramanlar.add("Şaman");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, kahramanlar);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner1.setAdapter(dataAdapter);
        spinner2.setAdapter(dataAdapter);
        spinner3.setAdapter(dataAdapter);
        spinner4.setAdapter(dataAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String item = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
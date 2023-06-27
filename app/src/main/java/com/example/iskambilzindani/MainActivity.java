package com.example.iskambilzindani;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.iskambilzindani.varliklar.Varlik;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    ArrayList<Varlik> secilenKahramanlar = new ArrayList<>();
    ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), (ActivityResult result) -> {
            if(result.getData() != null && result.getResultCode() == RESULT_OK){
                Varlik kahraman = (Varlik) result.getData().getSerializableExtra("kahraman");
                secilenKahramanlar.add(kahraman);
            }
    });
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Spinner spinner1 = findViewById(R.id.spinner1);
        spinner1.setOnItemSelectedListener(this);
        Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener((View v) -> {
                String text = spinner1.getSelectedItem().toString();
                Intent i = new Intent(getApplicationContext(),EditHero.class);
                i.putExtra("kahraman", text);
                activityResultLauncher.launch(i);
            });

        final Spinner spinner2 = findViewById(R.id.spinner2);
        spinner2.setOnItemSelectedListener(this);
        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener((View v) -> {
                String text = spinner2.getSelectedItem().toString();
                Intent i = new Intent(getApplicationContext(),EditHero.class);
                i.putExtra("kahraman", text);
                activityResultLauncher.launch(i);
            });

        final Spinner spinner3 = findViewById(R.id.spinner3);
        spinner3.setOnItemSelectedListener(this);
        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener((View v) -> {
            String text = spinner3.getSelectedItem().toString();
            Intent i = new Intent(getApplicationContext(),EditHero.class);
            i.putExtra("kahraman", text);
            activityResultLauncher.launch(i);
        });

        final Spinner spinner4 = findViewById(R.id.spinner4);
        spinner4.setOnItemSelectedListener(this);
        Button button4 = findViewById(R.id.button4);
        button4.setOnClickListener((View v) -> {
            String text = spinner4.getSelectedItem().toString();
            Intent i = new Intent(getApplicationContext(),EditHero.class);
            i.putExtra("kahraman", text);
            activityResultLauncher.launch(i);
        });

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

        Button baslaButton = findViewById(R.id.button);
        baslaButton.setOnClickListener((View v) -> Toast.makeText(getApplicationContext(),""+secilenKahramanlar.get(0), Toast.LENGTH_SHORT).show());
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String item = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

}
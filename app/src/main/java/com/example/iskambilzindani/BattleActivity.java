package com.example.iskambilzindani;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.iskambilzindani.varliklar.Varlik;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Predicate;

public class BattleActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);

        Bundle extras = getIntent().getExtras();
        AtomicReference<ArrayList<Varlik>> kahramanlar  = new AtomicReference<>((ArrayList<Varlik>) extras.getSerializable("kahramanlar"));
        AtomicReference<ArrayList<Varlik>> dusmanlar  = new AtomicReference<>((ArrayList<Varlik>) extras.getSerializable("dusmanlar"));

        Queue<Varlik> saldiriSirasi = new LinkedList<>(kahramanlar.get());
        saldiriSirasi.addAll(dusmanlar.get());

        Integer[] sayilar = new Integer[]{2,3,4,5,6,7,8,9,10};
        String[] suitler = new String[]{"Maça", "Kupa", "Sinek", "Karo"};

        ArrayAdapter<Integer> sayilarAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sayilar);
        sayilarAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<Varlik> varliklarAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, dusmanlar.get());
        varliklarAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<String> suitlerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, suitler);
        suitlerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner dusmanSpinner = findViewById(R.id.spinner);
        Spinner yetenekSpinner = findViewById(R.id.spinner7);
        Spinner suitSpinner = findViewById(R.id.spinner5);
        Spinner sayiSpinner = findViewById(R.id.spinner6);

        dusmanSpinner.setAdapter(varliklarAdapter);
        suitSpinner.setAdapter(suitlerAdapter);
        sayiSpinner.setAdapter(sayilarAdapter);

        Button savas = findViewById(R.id.button5);
        while(saldiriSirasi.size() != kahramanlar.get().size()){
            Varlik saldiran = saldiriSirasi.remove();
            saldiran.turBasi();
            ArrayAdapter<String> yeteneklerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, saldiran.yetenekler);
            yeteneklerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            savas.setOnClickListener((View v) -> {
                Varlik dusman = (Varlik) dusmanSpinner.getSelectedItem();
                IskambilKart kart = new IskambilKart(suitSpinner.getSelectedItemPosition(), (Integer) sayiSpinner.getSelectedItem());
                saldiran.saldirArayuz(yetenekSpinner.getSelectedItem().toString(), (Varlik[]) dusmanlar.get().toArray(), 0, kart);
                saldiran.turSonu();
                saldiriSirasi.add(saldiran);
            });

            Predicate<Varlik> pr = e -> (e.mevcutCan == 0);
            saldiriSirasi.removeIf(pr);
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String item = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
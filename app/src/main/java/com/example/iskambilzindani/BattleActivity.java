package com.example.iskambilzindani;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.iskambilzindani.bolumler.BasitBolum;
import com.example.iskambilzindani.efektler.OlumDonmasi;
import com.example.iskambilzindani.varliklar.Varlik;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Predicate;

public class BattleActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battle);

        Bundle extras = getIntent().getExtras();
        ArrayList<Varlik> kahramanlar  = (ArrayList<Varlik>) extras.getSerializable("kahramanlar");
        BasitBolum seviye = new BasitBolum(extras.getInt("seviye"));
        ArrayList<Varlik> dusmanlar = seviye.bolumYukle();

        Queue<Varlik> saldiriSirasi = new LinkedList<>(kahramanlar);
        saldiriSirasi.addAll(dusmanlar);

        HUDguncelle(dusmanlar, kahramanlar);

        Integer[] sayilar = new Integer[]{2,3,4,5,6,7,8,9,10};
        String[] suitler = new String[]{"Maça", "Kupa", "Sinek", "Karo"};

        ArrayAdapter<Integer> sayilarAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sayilar);
        sayilarAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<Varlik> varliklarAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, dusmanlar);
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

        TextView savasSonuclari = findViewById(R.id.textView10);
        TextView saldiriTakip = findViewById(R.id.textView11);

        Button savas = findViewById(R.id.button5);
        Button savasAI = findViewById(R.id.button8);
        int kahramanSayisi = kahramanlar.size();

        Varlik ilkSaldiran = saldiriSirasi.peek();
        saldiriTakip.setText("Saldıran: " + ilkSaldiran);

        savas.setOnClickListener((View v) -> {
            Varlik saldiran = saldiriSirasi.remove();
            Varlik siradaki = saldiriSirasi.peek();
            saldiriTakip.setText("Saldıran: " + saldiran +"\nSıradaki: " + siradaki);
            saldiran.turBasi();
            ArrayAdapter<String> yeteneklerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, saldiran.yetenekler);
            yeteneklerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            Varlik dusman = (Varlik) dusmanSpinner.getSelectedItem();
            IskambilKart kart = new IskambilKart(suitSpinner.getSelectedItemPosition(), (Integer) sayiSpinner.getSelectedItem());
            String sonuc = saldiran.saldirArayuz(yetenekSpinner.getSelectedItem().toString(), dusmanlar, dusman, kart);
            saldiran.turSonu();
            saldiriSirasi.add(saldiran);
            savasSonuclari.setText(savasSonuclari.getText() + sonuc);
            oyunKontrol(saldiriSirasi, kahramanlar);
        });

        savasAI.setOnClickListener((View v) -> {
            Varlik saldiran = saldiriSirasi.remove();
            Varlik siradaki = saldiriSirasi.peek();
            saldiriTakip.setText("Saldıran: " + saldiran +"\nSıradaki: " + siradaki);
            saldiran.turBasi();
            int rng = (int) (Math.random() * kahramanSayisi);
            String sonuc = saldiran.saldirArayuz("", kahramanlar, kahramanlar.get(rng), null);
            saldiran.turSonu();
            saldiriSirasi.add(saldiran);
            savasSonuclari.setText(savasSonuclari.getText() + sonuc);
            oyunKontrol(saldiriSirasi, kahramanlar);
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String item = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void oyunKontrol(Queue<Varlik> sira, ArrayList<Varlik> kahramanlar){
        Predicate<Varlik> pr = e -> (e.mevcutCan == 0);
        sira.removeIf(pr);
        if(sira.size() == kahramanlar.size()){
            Intent intent = new Intent();
            intent.putExtra("kahramanlar", kahramanlar);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    public void HUDguncelle(ArrayList<Varlik> dusmanlar, ArrayList<Varlik> kahramanlar){

        TextView dusman1 = findViewById(R.id.textView8);
        TextView dusman2 = findViewById(R.id.textView9);
        TextView dusman3 = findViewById(R.id.textView17);
        TextView dusman4 = findViewById(R.id.textView18);
        try{
            dusman1.setText(dusmanlar.get(0).toString());
            dusman2.setText(dusmanlar.get(1).toString());
            dusman3.setText(dusmanlar.get(2).toString());
            dusman4.setText(dusmanlar.get(3).toString());
        }catch (IndexOutOfBoundsException e){}

        TextView kahraman1 = findViewById(R.id.textView19);
        TextView kahraman2 = findViewById(R.id.textView20);
        TextView kahraman3 = findViewById(R.id.textView21);
        TextView kahraman4 = findViewById(R.id.textView22);
        try{
            kahraman1.setText(kahramanlar.get(0).toString());
            kahraman2.setText(kahramanlar.get(1).toString());
            kahraman3.setText(kahramanlar.get(2).toString());
            kahraman4.setText(kahramanlar.get(3).toString());
        }catch (IndexOutOfBoundsException e){}

        Predicate<Varlik> pr = e -> (e.mevcutCan == 0);
        dusmanlar.removeIf(pr);

        for(Varlik v: kahramanlar){
            if(v.mevcutCan == 0){
                v.efektler.add(new OlumDonmasi());
            }
        }
    }

}
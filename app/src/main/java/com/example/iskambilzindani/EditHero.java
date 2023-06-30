package com.example.iskambilzindani;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.iskambilzindani.varliklar.Varlik;
import com.example.iskambilzindani.varliklar.kahramanlar.Buyucu;
import com.example.iskambilzindani.varliklar.kahramanlar.Haydut;
import com.example.iskambilzindani.varliklar.kahramanlar.Saman;
import com.example.iskambilzindani.varliklar.kahramanlar.Savasci;

import java.util.ArrayList;

public class EditHero extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_hero);

        Bundle extras = getIntent().getExtras();
        String value  = extras.getString("kahraman");

        Varlik kahraman = new Varlik("Hayalet", 0, 0,0, 2, 1);
        switch(value){
            case "Savaşçı": kahraman = new Savasci();
            break;
            case "Büyücü": kahraman = new Buyucu();
            break;
            case "Haydut": kahraman = new Haydut();
            break;
            case "Şaman": kahraman = new Saman();
            break;
        }

        TextView title = findViewById(R.id.textView2);
        title.setText(value);

        CheckBox cb1 = findViewById(R.id.checkbox1);
        cb1.setText(kahraman.basitYetenekler.get(0));
        CheckBox cb2 = findViewById(R.id.checkbox2);
        cb2.setText(kahraman.basitYetenekler.get(1));
        CheckBox cb3 = findViewById(R.id.checkbox3);
        cb3.setText(kahraman.basitYetenekler.get(2));
        CheckBox cb4 = findViewById(R.id.checkbox4);
        cb4.setText(kahraman.basitYetenekler.get(3));
        CheckBox cb5 = findViewById(R.id.checkbox5);
        cb5.setText(kahraman.basitYetenekler.get(4));


        Button kaydetButonu = findViewById(R.id.button6);
        Varlik finalKahraman = kahraman;
        kaydetButonu.setOnClickListener((View v) -> {
                ArrayList<String> secilenYetenekler = new ArrayList<>();
                if(cb1.isChecked()){
                    secilenYetenekler.add(cb1.getText().toString());
                }
                if(cb2.isChecked()){
                    secilenYetenekler.add(cb2.getText().toString());
                }
                if(cb3.isChecked()){
                    secilenYetenekler.add(cb3.getText().toString());
                }
                if(cb4.isChecked()){
                    secilenYetenekler.add(cb4.getText().toString());
                }
                if(cb5.isChecked()){
                    secilenYetenekler.add(cb5.getText().toString());
                }

                finalKahraman.yeteneklerEkle(secilenYetenekler);
                Intent intent = new Intent();
                intent.putExtra("kahraman", finalKahraman);
                setResult(RESULT_OK, intent);
                finish();
            });
    }
}
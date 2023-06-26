package com.example.iskambilzindani;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.iskambilzindani.varliklar.Varlik;
import com.example.iskambilzindani.varliklar.kahramanlar.Buyucu;
import com.example.iskambilzindani.varliklar.kahramanlar.Haydut;
import com.example.iskambilzindani.varliklar.kahramanlar.Saman;
import com.example.iskambilzindani.varliklar.kahramanlar.Savasci;

public class EditHero extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_hero);

        Bundle extras = getIntent().getExtras();
        String value  = extras.getString("kahraman");

        Varlik kahraman;
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

        TextView title = (TextView)findViewById(R.id.textView2);
        title.setText(value);
    }
}
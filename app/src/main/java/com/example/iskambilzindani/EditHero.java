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

        Varlik kahraman = new Varlik("bos", 0, 0,0);
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

        for(int i = 0; i < 5; i++){
            String checkboxId = "checkbox" + (i+1);
            int checkBoxResId = getResources().getIdentifier(checkboxId, "id", getPackageName());
            CheckBox cb = (CheckBox) findViewById(checkBoxResId);
            cb.setText(kahraman.basitYetenekler.get(i));
        }



        Button kaydetButonu = (Button) findViewById(R.id.button6);
        Varlik finalKahraman = kahraman;
        kaydetButonu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ArrayList<String> secilenYetenekler = new ArrayList<>();
                for(int i = 0; i < 5; i++){
                    String checkboxId = "checkbox" + (i+1);
                    int checkBoxResId = getResources().getIdentifier(checkboxId, "id", getPackageName());
                    CheckBox cb = (CheckBox) findViewById(checkBoxResId);
                    if(cb.isChecked()){
                        secilenYetenekler.add(cb.getText().toString());
                    }
                }
                finalKahraman.yeteneklerEkle(secilenYetenekler);
                /* Intent intent = new Intent(); */

            }
        });

    }
}
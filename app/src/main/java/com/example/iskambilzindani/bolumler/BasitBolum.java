package com.example.iskambilzindani.bolumler;

import com.example.iskambilzindani.varliklar.Varlik;
import com.example.iskambilzindani.varliklar.dusmanlar.Iskelet;
import com.example.iskambilzindani.varliklar.dusmanlar.Kurt;
import com.example.iskambilzindani.varliklar.dusmanlar.Yaratik;

import java.util.ArrayList;

public class BasitBolum extends Bolum{
    public BasitBolum(int levelDegeri){
        super(levelDegeri);
    }

    public ArrayList<Varlik> bolumYukle(){
        Varlik[] dusmanTaslaklar = {new Iskelet(), new Kurt(), new Yaratik()};
        int dusmanRng = (int)(Math.random() * 2);
        int taslakRng;
        for (int i = 0; i< dusmanRng + 2; i++){
            taslakRng = (int) (Math.random() * 3);
            this.dusmanlar.add(dusmanTaslaklar[taslakRng]);
        }
        for(Varlik v: this.dusmanlar){
            v.levelAtla(this.level);
        }
        return this.dusmanlar;
    }
}

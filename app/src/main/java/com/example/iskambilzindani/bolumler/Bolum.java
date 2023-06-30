package com.example.iskambilzindani.bolumler;

import com.example.iskambilzindani.varliklar.Varlik;

import java.util.ArrayList;

public class Bolum {
    public ArrayList<Varlik> dusmanlar;
    public int level;

    public Bolum(int levelDeger){
        this.level = levelDeger;
        dusmanlar = new ArrayList<>();
    }
}

package com.example.iskambilzindani.varliklar.dusmanlar;

import com.example.iskambilzindani.IskambilKart;
import com.example.iskambilzindani.varliklar.Varlik;

import java.util.ArrayList;

public class Yaratik extends Varlik {
    public Yaratik(){
        super("Yaratık", 10, 1, 0,3, 1);
    }

    public String saldirArayuz(String yetenekAdi, ArrayList<Varlik> dusmanlar, Varlik dusman, IskambilKart kart){
        int rng = (int)(Math.random() * 20);
        if(rng >= 4){
            return this.saldir(dusman, this.hasar);
        }else{
            return this.tumSaldir(dusmanlar, this.hasar);
        }
    }
}

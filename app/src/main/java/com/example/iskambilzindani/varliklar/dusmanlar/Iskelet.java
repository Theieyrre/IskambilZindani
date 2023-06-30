package com.example.iskambilzindani.varliklar.dusmanlar;

import com.example.iskambilzindani.IskambilKart;
import com.example.iskambilzindani.varliklar.Varlik;

import java.util.ArrayList;

public class Iskelet extends Varlik {
    public Iskelet(){
        super("İskelet", 5, 1, 0, 1, 1);
    }

    public String saldirArayuz(String yetenekAdi, ArrayList<Varlik> dusmanlar, Varlik dusman, IskambilKart kart){
        int rng = (int)(Math.random() * 21);
        if(rng >= 2){
            return this.saldir(dusman, this.hasar);
        }else{
            this.mevcutCan = 0;
            return this.tumSaldir(dusmanlar, this.hasar * 2);
        }
    }
}

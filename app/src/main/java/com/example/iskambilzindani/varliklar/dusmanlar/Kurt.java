package com.example.iskambilzindani.varliklar.dusmanlar;

import com.example.iskambilzindani.IskambilKart;
import com.example.iskambilzindani.varliklar.Varlik;

import java.util.ArrayList;

public class Kurt extends Varlik {
    public Kurt(){
        super("Kurt", 7, 1, 0, 1, 1);
    }

    public String saldirArayuz(String yetenekAdi, ArrayList<Varlik> dusmanlar, Varlik dusman, IskambilKart kart){
        int rng = (int)(Math.random() * 20);
        if(rng >= 10){
            return this.saldir(dusman, this.hasar);
        }else{
            this.hasar += 2;
            return "Kurt uludu ve hasarı 2 arttı";
        }
    }
}

package com.example.iskambilzindani.efektler;

import com.example.iskambilzindani.varliklar.Varlik;

public class Efekt {
    private final String ad;
    public int tur;

    public Efekt(String isim, int turSayisi) {
        this.ad = isim;
        this.tur = turSayisi;
    }

    public String tetikle(Varlik v){
        this.tur--;
        return v.ad + " için " + this.ad + " tetiklendi";
    }

    @Override
    public String toString(){
        return this.ad + "(" + this.tur + ")";
    }
}

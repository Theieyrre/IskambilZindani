package com.example.iskambilzindani.siniflar;

public class Efekt {
    private final String ad;
    private int tur;

    public Efekt(String isim, int turSayisi) {
        this.ad = isim;
        this.tur = turSayisi;
    }

    public void setTur(int yeniTur){
        this.tur = yeniTur;
    }

    @Override
    public String toString(){
        return this.ad + "(" + this.tur + ")";
    }
}

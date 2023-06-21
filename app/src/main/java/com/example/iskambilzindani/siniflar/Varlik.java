package com.example.iskambilzindani.siniflar;

import java.util.ArrayList;

public class Varlik {
    private final String ad;
    private int saglik;
    private int hasar;
    private int zirh;
    private int kaplama;
    ArrayList<Efekt> pozitifEfektler;
    ArrayList<Efekt> negatifEfektler;

    public Varlik(String isim, int saglikDegeri, int hasarGucu, int zirhDegeri, int kaplamaDegeri){
        this.ad = isim;
        this.saglik = saglikDegeri;
        this.hasar = hasarGucu;
        this.zirh = zirhDegeri;
        this.kaplama = kaplamaDegeri;
        this.pozitifEfektler = new ArrayList<>();
        this.negatifEfektler = new ArrayList<>();
    }

    public String hasarAl(int hasarDegeri) {
        int gercekHasar = hasarDegeri - this.zirh;
        int canHasar = gercekHasar - this.kaplama;
        this.kaplama = Math.max(0, gercekHasar - this.kaplama);
        this.saglik = Math.max(0, this.saglik - canHasar);
        return ad + " " + gercekHasar + " hasar aldı, mevcut can: " + this.saglik;
    }

    public String saldir(Varlik dusman, IskambilKart kart){
        int hasar = (kart != null ? kart.getDeger() : 0) + this.hasar;
        String hasarDonut = dusman.hasarAl(hasar + this.hasar);
        return ad + ", " + dusman.getAd() + " varlığına " + hasar + " hasar ile saldırdı\n" + hasarDonut;
    }

    public String getAd(){
        return this.ad;
    }

    public void setZirh(int yeniZirh) {
        this.zirh = yeniZirh;
    }

    public void setKaplama(int ekKaplama) {
        this.kaplama += ekKaplama;
    }

    public void kaplamaSifirla(){
        this.kaplama = 0;
    }

    @Override
    public String toString(){
        String metin =  this.ad + "\nCan: " + this.saglik + " Hasar: " + this.hasar + " Zırh: " + this.zirh + " Kaplama: " + this.kaplama  +"\n";
        for(Efekt e:this.pozitifEfektler){
            metin = metin + e;
        }
        for(Efekt e:this.negatifEfektler){
            metin = metin + e;
        }
        return metin;
    }
}

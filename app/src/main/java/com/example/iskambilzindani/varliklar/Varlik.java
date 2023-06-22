package com.example.iskambilzindani.varliklar;

import com.example.iskambilzindani.efektler.Efekt;

import java.util.ArrayList;
import java.util.function.Predicate;

public class Varlik {
    public final String ad;
    public int maksimumCan;
    public int mevcutCan;
    public int hasar;
    public int zirh;
    public int kaplama;
    public boolean saldirabilir;
    public boolean yetenekKullanabilir;

    ArrayList<Efekt> efektler;

    public Varlik(String isim, int maksimumCanDegeri, int hasarGucu, int zirhDegeri, int kaplamaDegeri){
        this.ad = isim;
        this.maksimumCan = maksimumCanDegeri;
        this.mevcutCan = maksimumCanDegeri;
        this.hasar = hasarGucu;
        this.zirh = zirhDegeri;
        this.kaplama = kaplamaDegeri;
        this.saldirabilir = true;
        this.yetenekKullanabilir = true;
        this.efektler = new ArrayList<>();
    }

    public String hasarAl(int hasarDegeri) {
        int gercekHasar = hasarDegeri - this.zirh;
        int canHasar = gercekHasar - this.kaplama;
        this.kaplama = Math.max(0, gercekHasar - this.kaplama);
        return this.gercekHasarAl(canHasar);
    }

    public String gercekHasarAl(int hasarDegeri) {
        this.mevcutCan = Math.max(0, this.mevcutCan - hasarDegeri);
        return this.ad + " " + hasarDegeri + " hasar aldı, mevcut can: " + this.mevcutCan;
    }

    public String saldir(Varlik dusman, int hasar){
        if(this.saldirabilir) {
            String hasarDonut = dusman.hasarAl(hasar);
            return this.ad + ", " + dusman.ad + " varlığına " + hasar + " hasar ile saldırdı\n" + hasarDonut;
        }else{
            return this.ad + " bu tur saldıramadı";
        }
    }

    public void turSonu(){
        this.kaplama = 0;
        this.saldirabilir = true;
        this.yetenekKullanabilir = true;
        Predicate<Efekt> pr = e->(e.tur==0);
        this.efektler.removeIf(pr);
    }

    @Override
    public String toString(){
        String metin =  this.ad + "\nCan: " + this.maksimumCan + " Hasar: " + this.hasar + " Zırh: " + this.zirh + " Kaplama: " + this.kaplama  +"\n";
        StringBuilder sb = new StringBuilder();
        sb.append(metin);
        for(Efekt e:this.efektler) {
            sb.append(e);
        }
        return sb.toString();
    }
}

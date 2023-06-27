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
    public boolean saldirabilir;
    public boolean yetenekKullanabilir;

    public ArrayList<Efekt> efektler;
    public ArrayList<String> yetenekler;

    public ArrayList<String> basitYetenekler;

    public Varlik(String isim, int maksimumCanDegeri, int hasarGucu, int zirhDegeri){
        this.ad = isim;
        this.maksimumCan = maksimumCanDegeri;
        this.mevcutCan = maksimumCanDegeri;
        this.hasar = hasarGucu;
        this.zirh = zirhDegeri;
        this.saldirabilir = true;
        this.yetenekKullanabilir = true;
        this.efektler = new ArrayList<>();
        this.yetenekler = new ArrayList<>();
        this.basitYetenekler = new ArrayList<>();
    }

    public void yeteneklerEkle(ArrayList<String> ekYetenekler){
        for(String yetenek: ekYetenekler){
            yetenekler.add(yetenek);
        }
    }

    public String hasarAl(int hasarDegeri) {
        int gercekHasar = hasarDegeri - this.zirh;
        this.zirh = Math.max(0, gercekHasar - this.zirh);
        return this.gercekHasarAl(gercekHasar);
    }

    public String gercekHasarAl(int hasarDegeri) {
        this.mevcutCan = Math.max(0, this.mevcutCan - hasarDegeri);
        return this.ad + " " + hasarDegeri + " hasar aldı, mevcut can: " + this.mevcutCan + "\n";
    }

    public String saldir(Varlik dusman, int hasarGucu){
        if(this.saldirabilir) {
            String hasarDonut = dusman.hasarAl(hasarGucu);
            return this.ad + ", " + dusman.ad + " varlığına " + hasarGucu + " hasar ile saldırdı\n" + hasarDonut + "\n";
        }else{
            return this.ad + " bu tur saldıramadı\n";
        }
    }

    public String tumSaldir(Varlik[] dusmanlar, int hasarGucu){
        StringBuilder sb = new StringBuilder();
        for(Varlik v: dusmanlar){
            sb.append(v.hasarAl(hasarGucu));
        }
        return sb.toString();
    }

    public String tumUygula(Varlik[] dusmanlar, Efekt yeniEfekt){
        StringBuilder sb = new StringBuilder();
        for (Varlik v : dusmanlar) {
            v.efektler.add(yeniEfekt);
        }
        sb.append("Tüm düşmanlara" + yeniEfekt + " uygulandı\n");
        return sb.toString();
    }

    public void turSonu() {
        this.saldirabilir = true;
        this.yetenekKullanabilir = true;
        Predicate<Efekt> pr = e -> (e.tur == 0);
        this.efektler.removeIf(pr);
    }

    public void turBasi(){
        this.zirh = 0;
        for(Efekt e: this.efektler){
            e.tetikle(this);
        }
    }

    public void sifirla(){
        this.efektler = new ArrayList<>();
    }

    @Override
    public String toString(){
        String metin =  this.ad + "\nCan: " + this.maksimumCan + " Hasar: " + this.hasar + " Zırh: " + this.zirh +"\n";
        StringBuilder sb = new StringBuilder();
        sb.append(metin);
        for(Efekt e:this.efektler) {
            sb.append(e + "\n");
        }
        return sb.toString();
    }
}

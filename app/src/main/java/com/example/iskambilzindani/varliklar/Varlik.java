package com.example.iskambilzindani.varliklar;

import com.example.iskambilzindani.IskambilKart;
import com.example.iskambilzindani.efektler.Efekt;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.function.Predicate;

public class Varlik implements Serializable {
    public final String ad;
    public int maksimumCan;
    public int mevcutCan;
    public int hasar;

    public int zirh;
    public boolean saldirabilir;
    public int level;
    public int levelCanKatsayi;
    public int levelHasarKatsayi;

    public ArrayList<Efekt> efektler;
    public ArrayList<String> yetenekler;

    public ArrayList<String> basitYetenekler;

    public Varlik(String isim, int maksimumCanDegeri, int hasarGucu, int zirhDegeri, int levelCanKatsayiDeger, int levelHasarKatsayiDeger){
        this.ad = isim;
        this.maksimumCan = maksimumCanDegeri;
        this.mevcutCan = maksimumCanDegeri;
        this.hasar = hasarGucu;
        this.zirh = zirhDegeri;
        this.saldirabilir = true;
        this.efektler = new ArrayList<>();
        this.yetenekler = new ArrayList<>();
        this.basitYetenekler = new ArrayList<>();
        this.level = 1;
        this.levelCanKatsayi = levelCanKatsayiDeger;
        this.levelHasarKatsayi = levelHasarKatsayiDeger;
    }

    public void yeteneklerEkle(ArrayList<String> ekYetenekler){
        yetenekler.addAll(ekYetenekler);
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

    public String uygula(Varlik dusman, Efekt yeniEfekt){
        dusman.efektler.add(yeniEfekt);
        return dusman.ad + " karakterine " + yeniEfekt + " uygulandı\n";
    }

    public String tumSaldir(ArrayList<Varlik> dusmanlar, int hasarGucu){
        StringBuilder sb = new StringBuilder();
        if(this.saldirabilir){
            for(Varlik v: dusmanlar){
                sb.append(v.hasarAl(hasarGucu));
            }
        }else{
            return this.ad + " bu tur saldıramadı\n";
        }
        return sb.toString();
    }

    public String tumUygula(ArrayList<Varlik> dusmanlar, Efekt yeniEfekt){
        for (Varlik v : dusmanlar) {
            v.efektler.add(yeniEfekt);
        }
        return "Tüm düşmanlara" + yeniEfekt + " uygulandı\n";
    }

    public void turSonu() {
        this.saldirabilir = true;
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
        sb.append(metin + "Yetenekler: \n");
        for(String s: this.yetenekler){
            sb.append(s +"\n");
        }
        sb.append("Efektler: \n");
        for(Efekt e:this.efektler) {
            sb.append(e + "\n");
        }
        return sb.toString();
    }

    public String ozet(){
        return this.ad + " " + "Lv." + this.level +  "\n" + "Can: " + this.mevcutCan + "/" + this.maksimumCan;
    }

    public void levelAtla(int levelFark){
        this.level += levelFark;
        this.maksimumCan += levelFark * this.levelCanKatsayi;
        this.hasar += levelFark * this.levelHasarKatsayi;
    }

    public String saldirArayuz(String yetenekAdi, ArrayList<Varlik> dusmanlar, Varlik dusman, IskambilKart kart){
        return "";
    }

}

package com.example.iskambilzindani.varliklar.kahramanlar;

import com.example.iskambilzindani.IskambilKart;
import com.example.iskambilzindani.varliklar.Varlik;

import java.util.ArrayList;

public class Haydut extends Varlik {
    public int kritHasar;
    public int kritSans;
    public boolean kacin;
    public Haydut(){
        super("Haydut", 15,1, 0);
        this.basitYetenekler.add("Hazırlık");
        this.basitYetenekler.add("Gölgelere Kaçış");
        this.basitYetenekler.add("Baskın!");
        this.basitYetenekler.add("Çift Diş");
        this.basitYetenekler.add("Ağır Darbe");
        this.kritHasar = 5;
        this.kritSans = 2;
        this.kacin = false;
    }

    public int kritliHasar(int hasarDegeri, int rng){
        if(rng <= kritSans){
            hasarDegeri += this.kritHasar;
            this.kritSans = 2;
            this.kritHasar = 5;
        }
        return hasarDegeri;
    }

    public String hasarAl(int hasarDegeri) {
        if(this.kacin){
            this.kacin = false;
            return "Saldırıdan kaçınıldı";
        }else{
            return super.hasarAl(hasarDegeri);
        }
    }

    /* Sinek 6, 2, Karo */
    public void yeteneklerEkle(ArrayList<String> ekYetenekler){
        this.yetenekler.add("Basit Saldırı");
        super.yeteneklerEkle(ekYetenekler);
        this.yetenekler.add("Karanlık Hançerler");
    }

    /* Basit SALDIRI */
    /* 2 hasar 0.5K krit hasar eğer Sinek 6 ise kritSans +5 ve kritHasar +5 */
    public String basitSaldiri(Varlik dusman, IskambilKart kart, int rng){
        int verilecekHasar = this.hasar + 2;
        this.kritHasar += (int) Math.ceil(kart.deger * 0.5);
        if(kart.suit == 2 && kart.deger == 6){
            this.kritSans += 5;
            this.kritHasar += 5;
        }
        verilecekHasar = this.kritliHasar(verilecekHasar, rng);
        return this.saldir(dusman, verilecekHasar);
    }

    /* Hazırlık */
    /* 1K krit hasar ve 5 krit sans eğer kırmızıysa ek 0.5K krit hasar ve 2 krit sans */
    public String hazirlik(IskambilKart kart){
        if(kart.kirmiziMi()){
            this.kritHasar += (int) Math.ceil(kart.deger * 1.5);
            this.kritSans += 7;
            return ((int) Math.ceil(kart.deger * 1.5)) + " krit hasar ve 7 krit şansı kazanıldı";
        }else{
            this.kritHasar += kart.deger;
            this.kritSans += 5;
            return kart.deger + " krit hasar ve 5 krit şansı kazanıldı";
        }
    }

    /* Gölgelere kaçış */
    /* 0.6K krit hasarı ve %50 kaçın eğer siyahsa %90 kaçın */
    public String golgelereKacis(IskambilKart kart, int rng){
        int ekKritHasar = (int) Math.ceil(kart.deger * 0.6);
        this.kritHasar += ekKritHasar;
        int rngSinir = kart.siyahMi() ? 10 : 18;
        if(rng <= rngSinir){
            this.kacin = true;
            return ekKritHasar + " krit hasarı ve kaçınma kazanıldı";
        }else{
            return ekKritHasar + " krit hasarı kazanıldı";
        }
    }

    /* Baskın! */
    /* 2 hasar ve 1.5K krit hasarı eğer kaçınma kesin krit */
    public String baskin(Varlik dusman, IskambilKart kart){
        int verilecekHasar = this.hasar + 2;
        this.kritHasar += (int) Math.ceil(kart.deger * 1.5);
        if(this.kacin){
            verilecekHasar = kritliHasar(verilecekHasar, 0);
        }
        return this.saldir(dusman, verilecekHasar);
    }

    /* Çift Diş */
    /* 2 kere 1 hasar ve 0.5K krit hasarı eğer 2 ise 9 krit sans */
    public String ciftDis(Varlik dusman, IskambilKart kart, int rng){
        StringBuilder sb = new StringBuilder();
        int verilecekHasar = this.hasar + 1;
        this.kritHasar += (int) Math.ceil(kart.deger * 0.5);
        if(kart.deger == 2){
            this.kritSans += 9;
        }
        verilecekHasar = kritliHasar(verilecekHasar, rng);
        sb.append(this.saldir(dusman, verilecekHasar));
        this.kritHasar += (int) Math.ceil(kart.deger * 0.5);
        if(kart.deger == 2){
            this.kritSans += 9;
        }
        verilecekHasar = kritliHasar(verilecekHasar, rng);
        sb.append(this.saldir(dusman, verilecekHasar));
        return sb.toString();
    }

    /* Ağır Darbe */
    /* 3 hasar ve 0.5K krit hasar eğer karoysa 1K hasar */
    public String agirDarbe(Varlik dusman, IskambilKart kart, int rng){
        int verilecekHasar = this.hasar + 3;
        this.kritHasar += (int) Math.ceil(kart.deger * 0.5);
        if(kart.suit == 3){
            verilecekHasar += kart.deger;
        }
        verilecekHasar = kritliHasar(verilecekHasar, rng);
        return this.saldir(dusman, verilecekHasar);
    }

    /* ULT */
    /* Tüm düşmanlara 2 hasar 10 krit şansı eğer kırmızıysa 10 krit hasar eğer siyahsa %100 kaçın */
    public String karanlikHancerler(Varlik[] dusmanlar, IskambilKart kart, int rng){
        int verilecekHasar = this.hasar + 2;
        this.kritSans += 10;
        if(kart.kirmiziMi()){
            this.kritHasar += 10;
        }else{
            this.kacin = true;
        }
        verilecekHasar = kritliHasar(verilecekHasar, rng);
        return this.tumSaldir(dusmanlar, verilecekHasar);
    }

    public void sifirla(){
        super.sifirla();
        this.kritHasar = 5;
        this.kritSans = 2;
    }

    public String saldirArayuz(String yetenekAdi, Varlik[] dusmanlar, int dusmanIndex, IskambilKart kart){
        int rng = (int)(Math.random() * 21);
        String sonuc = "";
        switch(yetenekAdi){
            case "Basit Saldırı": sonuc = this.basitSaldiri(dusmanlar[dusmanIndex], kart, rng);
                break;
            case "Hazırlık": sonuc = this.hazirlik(kart);
                break;
            case "Gölgelere Kaçış": sonuc = this.golgelereKacis(kart, rng);
                break;
            case "Baskın!": sonuc = this.baskin(dusmanlar[dusmanIndex], kart);
                break;
            case "Çift Diş": sonuc = this.ciftDis(dusmanlar[dusmanIndex], kart, rng);
                break;
            case "Ağır Darbe": sonuc = this.agirDarbe(dusmanlar[dusmanIndex], kart, rng);
                break;
            case "Karanlık Hançerler": sonuc = this.karanlikHancerler(dusmanlar, kart, rng);
                break;
        }
        return sonuc;
    }

    @Override
    public String toString(){
        return super.toString() + "Krit Hasar " + this.kritHasar + " Krit Şans" + this.kritSans + " Kaçınma " + this.kacin;
    }

}

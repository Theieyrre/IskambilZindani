package com.example.iskambilzindani.varliklar.kahramanlar;

import com.example.iskambilzindani.IskambilKart;
import com.example.iskambilzindani.efektler.Efekt;
import com.example.iskambilzindani.efektler.ZayiflatEfekti;
import com.example.iskambilzindani.varliklar.Varlik;

import java.util.ArrayList;
import java.util.Arrays;

public class Savasci extends Varlik {
    public int hiddet;
    public Savasci(){
        super("Savaşçı", 25,1, 0);
        this.basitYetenekler.add("Hiddet Patlaması");
        this.basitYetenekler.add("Dayan!");
        this.basitYetenekler.add("Uyuşturan Darbe");
        this.basitYetenekler.add("En İyi Defans");
        this.basitYetenekler.add("Diren!");
        this.hiddet = 0;
    }

    /* Karo 9, 5 Maça */
    public void yeteneklerEkle(ArrayList<String> ekYetenekler){
        this.yetenekler.add("Basit Saldırı");
        super.yeteneklerEkle(ekYetenekler);
        this.yetenekler.add("Savaşın Dengesi");
    }

    /* Basit SALDIRI */
    /* 0.5K hasar +1 hiddet eğer Karo 9 ise 15 hasar +2 hiddet */
    public String basitSaldiri(Varlik dusman, IskambilKart kart){
        int verilecekHasar = this.hasar + this.hiddet * 2;
        if(kart.suit == 3 && kart.deger == 9){
            verilecekHasar += 15;
            this.hiddet = Math.min(++this.hiddet, 5);
        }else{
            hasar += (int) Math.ceil(kart.deger * 0.5);
        }
        this.hiddet = Math.min(++this.hiddet, 5);
        return this.saldir(dusman, verilecekHasar);
    }

    /* Hiddet Patlaması */
    /* 1K hasar eğer 3+ hiddetse ardışık 3 düşmana 1,5k hasar */
    public String hiddetPatlamasi(Varlik[] dusmanlar, IskambilKart kart){
        int verilecekHasar = this.hasar + this.hiddet * 2;
        if(this.hiddet >= 3){
            verilecekHasar += Math.ceil(kart.deger * 1.5);
            return this.tumSaldir(dusmanlar, verilecekHasar);
        }else{
            verilecekHasar += kart.deger;
            return this.saldir(dusmanlar[1], verilecekHasar);
        }
    }

    /* Dayan */
    /* 1K zırh +1 hidder eğer kırmızı kartsa +2 hiddet */
    public String dayan(IskambilKart kart){
        int ekZirh = kart.deger;
        this.zirh += ekZirh;
        this.hiddet = Math.min(++this.hiddet, 5);
        if(kart.kirmiziMi()){
            this.hiddet = Math.min(++this.hiddet, 5);
        }
        return ad + " " + ekZirh + " zırh kazandı, toplam zırh " + this.zirh + "\n";
    }

    /* Uyuşturan Darbe */
    /* 0.2K hasar %50: Zayıflat(2) eğer siyah kartsa +5 kaplama ve %75: Zayıflat(2) */
    public String uyusturanDarbe(Varlik dusman, IskambilKart kart, int rng) {
        StringBuilder sb = new StringBuilder();
        int verilecekHasar = this.hasar + this.hiddet * 2;
        verilecekHasar += (int) Math.ceil(kart.deger * 0.3);
        sb.append(this.saldir(dusman, verilecekHasar));
        int rngSinir = 10;
        if(kart.siyahMi()) {
            this.zirh += 5;
            sb.append(ad + " ek 5 zırh kazandı, toplam zırh" + this.zirh + "\n");
            rngSinir = 16;
        }if(rng <= rngSinir){
            Efekt yeniEfekt = new ZayiflatEfekti(2);
            dusman.efektler.add(yeniEfekt);
            sb.append(dusman.ad + " " + yeniEfekt + " uygulandı\n");
        }
        return sb.toString();
    }

    /* En iyi defans */
    /* 0,5K kaplama kaplama kadar hasar Eğer kart 5 ise ek 5 hasar */
    public String enIyiDefans(Varlik dusman, IskambilKart kart){
        StringBuilder sb = new StringBuilder();
        int ekZirh = (int) Math.ceil(kart.deger * 0.5);
        this.zirh += ekZirh;
        sb.append(ad + " " + ekZirh + " zırh kazandı, toplam zırh " + this.zirh + "\n");
        int verileckeHasar = this.zirh;
        if(kart.deger == 5)
            verileckeHasar += 5;
        sb.append(this.saldir(dusman, verileckeHasar));
        return sb.toString();
    }

    /* Diren */
    /* 0.5K can yenile +1 hiddet eğer maça ise eksin can / 4 yenile */
    public String diren(IskambilKart kart){
        int yenilenecekCan = (int) Math.ceil(kart.deger * 0.5);
        this.hiddet = Math.min(++this.hiddet, 5);
        if(kart.suit == 0)
            yenilenecekCan += (int) Math.ceil( (this.maksimumCan - this.mevcutCan ) *0.25);
        this.mevcutCan = Math.min(mevcutCan + yenilenecekCan, maksimumCan);
        return ad + " " + yenilenecekCan + "can iyileşti\n";
    }

    /* ULT */
    /* Tüm düşmanlara %90 Zayiflat(3) eğer siyah kartsa 10 kaplama eğer kırmızıysa 10 hasar tüm düşmanlara */
    public String savasinDengesi(Varlik[] dusmanlar, IskambilKart kart, int rng){
        int rngSinir = 18;
        StringBuilder sb = new StringBuilder();
        if(rng <= rngSinir) {
            sb.append(this.tumUygula(dusmanlar, new ZayiflatEfekti(3)));
        }
        if(kart.siyahMi()){
            this.zirh += 10;
            sb.append(ad + " 10 zırh kazandı, toplam zırh " + this.zirh + "\n");
        }else{
            this.tumSaldir(dusmanlar, 10);
        }
        return sb.toString();
    }

    public void sifirla(){
        super.sifirla();
        this.hiddet = 0;
    }

    public String saldirArayuz(String yetenekAdi, Varlik[] dusmanlar, int dusmanIndex, IskambilKart kart){
        int rng = (int)(Math.random() * 21);
        String sonuc = "";
        switch(yetenekAdi){
            case "Basit Saldırı": sonuc = this.basitSaldiri(dusmanlar[dusmanIndex], kart);
            break;
            case "Hiddet Patlaması": sonuc = this.hiddetPatlamasi(Arrays.copyOfRange(dusmanlar, dusmanIndex-1, dusmanIndex+1), kart);
            break;
            case "Dayan!": sonuc = this.dayan(kart);
            break;
            case "Uyuşturan Darbe": sonuc = this.uyusturanDarbe(dusmanlar[dusmanIndex], kart, rng);
            break;
            case "En İyi Defans": sonuc = this.enIyiDefans(dusmanlar[dusmanIndex], kart);
            break;
            case "Diren!": sonuc = this.diren(kart);
            break;
            case "Savaşın Dengesi": sonuc = this.savasinDengesi(dusmanlar, kart, rng);
            break;
        }
        return sonuc;
    }

    @Override
    public String toString(){
        return super.toString() + "Hiddet " + this.hiddet;
    }
}

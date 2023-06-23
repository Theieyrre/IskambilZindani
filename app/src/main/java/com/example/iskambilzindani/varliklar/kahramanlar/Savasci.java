package com.example.iskambilzindani.varliklar.kahramanlar;

import com.example.iskambilzindani.IskambilKart;
import com.example.iskambilzindani.efektler.Efekt;
import com.example.iskambilzindani.efektler.ZayiflatEfekti;
import com.example.iskambilzindani.varliklar.Varlik;

public class Savasci extends Varlik {
    public int hiddet;
    public Savasci(String[] secilenYetenekler){
        super("Savaşçı", 25,1, 0, 0);
        this.hiddet = 0;
        this.yetenekler.add("basitSaldiri");
        for(String yetenek: secilenYetenekler)
            this.yetenekler.add(yetenek);
        this.yetenekler.add("savasinDengesi");
    }

    /* Basit SALDIRI */
    /* 0.5K hasar +1 hiddet eğer Karo 7 ise 15 hasar +2 hiddet */
    public String basitSaldiri(Varlik dusman, IskambilKart kart){
        int verilecekHasar = this.hasar + this.hiddet * 2;
        if(kart.suit == 3 && kart.deger == 7){
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
    /* 1K kaplama +1 hidder eğer kırmızı kartsa +2 hiddet */
    public String dayan(IskambilKart kart){
        int ekKaplama = kart.deger;
        this.kaplama += ekKaplama;
        this.hiddet = Math.min(++this.hiddet, 5);
        if(kart.kirmiziMi()){
            this.hiddet = Math.min(++this.hiddet, 5);
        }
        return ad + " " + ekKaplama + " kaplama kazandı, toplam kaplama " + this.kaplama + "\n";
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
            this.kaplama += 5;
            sb.append(ad + " ek 5 kaplama kazandı, toplam kaplama" + this.kaplama + "\n");
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
        int ekKaplama = (int) Math.ceil(kart.deger * 0.5);
        this.kaplama += ekKaplama;
        sb.append(ad + " " + ekKaplama + " kaplama kazandı, toplam kaplama " + this.kaplama + "\n");
        int verileckeHasar = this.kaplama;
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
            this.kaplama += 10;
            sb.append(ad + " 10 kaplama kazandı, toplam kaplama " + this.kaplama + "\n");
        }else{
            this.tumSaldir(dusmanlar, 10);
        }
        return sb.toString();
    }

    public void sifirla(){
        super.sifirla();
        this.hiddet = 0;
    }

    @Override
    public String toString(){
        return super.toString() + "Hiddet " + this.hiddet;
    }
}

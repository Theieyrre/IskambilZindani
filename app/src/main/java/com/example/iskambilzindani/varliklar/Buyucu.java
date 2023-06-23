package com.example.iskambilzindani.varliklar;

import com.example.iskambilzindani.IskambilKart;
import com.example.iskambilzindani.efektler.DonmaEfekti;
import com.example.iskambilzindani.efektler.Efekt;
import com.example.iskambilzindani.efektler.YanmaEfekti;

public class Buyucu extends Varlik{
    public int sihirGucu;
    public Buyucu(){
        super("Büyücü", 20,1, 0, 0);
        this.sihirGucu = 0;
    }

    /* Basit Saldırı */
    /* 0,5K hasar ve +1 sihirGucu Eğer kupa6 ise 10 hasar ve 5 sihir gücü */
    public String basitSaldiri(Varlik dusman, IskambilKart kart){
        int verilecekHasar = this.hasar + this.sihirGucu;
        if(kart.suit == 1 && kart.deger == 6){
            verilecekHasar += 10;
            this.sihirGucu += 5;
        }else{
            verilecekHasar += (int) Math.ceil(kart.deger * 0.5);
        }
        return this.saldir(dusman, verilecekHasar);
    }

    /* Ateş Topu */
    /* 1K hasar ve Yanma(2,2) Eğer kırmızıysa 2K hasar Yanma(3,2) */
    /* GUNCELLE */
    public String atesTopu(Varlik dusman, IskambilKart kart){
        StringBuilder sb = new StringBuilder();
        int verilecekHasar = this.hasar + this.sihirGucu;
        verilecekHasar += kart.deger;
        sb.append(this.saldir(dusman, verilecekHasar));
        if(kart.kirmiziMi()){
            Efekt yeniEfekt = new YanmaEfekti(3, 2);
            dusman.efektler.add(yeniEfekt);
            sb.append(dusman.ad + " " + yeniEfekt + " uygulandı\n");
        }
        return sb.toString();
    }

    /* Magma patlamaları */
    /* 0.3K hasar sihirGucunden 3 kat etkilenir */
    public String magmaPatlamalari(Varlik[] dusmanlar, IskambilKart kart){
        StringBuilder sb = new StringBuilder();
        int verilecekHasar = this.hasar + (this.sihirGucu * 3);
        verilecekHasar += (int) Math.ceil(kart.deger * 0.3);
        for(Varlik v: dusmanlar){
            sb.append(v.hasarAl(verilecekHasar));
        }
        return sb.toString();
    }

    /* Donduran Yıkım */
    /* 0.5K hasar tüm %10 Dondur(1) eğer siyahsa %30 Dondur(1) */
    public String donduranYikim(Varlik[] dusmanlar, IskambilKart kart, int rng){
        StringBuilder sb = new StringBuilder();
        int verilecekHasar = this.hasar + this.sihirGucu;
        verilecekHasar += (int) Math.ceil(kart.deger * 0.5);
        int rngSinir = kart.siyahMi() ? 6 : 2;
        boolean donma = rng <= rngSinir;
        Efekt yeniEfekt = new DonmaEfekti(1);
        for(Varlik v: dusmanlar){
            sb.append(v.hasarAl(verilecekHasar));
        }
        if(donma) {
            for (Varlik v : dusmanlar) {
                v.efektler.add(yeniEfekt);
            }
            sb.append("Tüm düşmanlara" + yeniEfekt + " uygulandı\n");
        }
        return sb.toString();
    }

    /* Buz Şoku */
    /* 1K hasar %25 Dondur(1) */

    /* Sonsuz Yıkım */
    /* 1K hasar ver tüm eğer ölen olursa yeniden yıkım */
}

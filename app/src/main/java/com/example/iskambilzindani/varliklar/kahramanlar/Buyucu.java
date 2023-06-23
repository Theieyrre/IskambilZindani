package com.example.iskambilzindani.varliklar.kahramanlar;

import com.example.iskambilzindani.IskambilKart;
import com.example.iskambilzindani.efektler.DonmaEfekti;
import com.example.iskambilzindani.efektler.Efekt;
import com.example.iskambilzindani.efektler.YanmaEfekti;
import com.example.iskambilzindani.varliklar.Varlik;

public class Buyucu extends Varlik {
    public int sihirGucu;
    public Buyucu(String[] secilenYetenekler){
        super("Büyücü", 20,1, 0, 0);
        this.sihirGucu = 0;
        this.yetenekler.add("basitSaldiri");
        for(String yetenek: secilenYetenekler)
            this.yetenekler.add(yetenek);
        this.yetenekler.add("enerjiPatlamasi");
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
    /* 1K hasar ve Yanma(3,2) Eğer kırmızıysa 2K hasar Yanma(3,3) */
    /* GUNCELLE */
    public String atesTopu(Varlik dusman, IskambilKart kart){
        StringBuilder sb = new StringBuilder();
        int verilecekHasar = this.hasar + this.sihirGucu;
        verilecekHasar += kart.deger;
        sb.append(this.saldir(dusman, verilecekHasar));
        if(kart.kirmiziMi()){
            Efekt yeniEfekt = new YanmaEfekti(3, 3);
            dusman.efektler.add(yeniEfekt);
            sb.append(dusman.ad + " " + yeniEfekt + " uygulandı\n");
        }else{
            Efekt yeniEfekt = new YanmaEfekti(3, 2);
            dusman.efektler.add(yeniEfekt);
            sb.append(dusman.ad + " " + yeniEfekt + " uygulandı\n");
        }
        return sb.toString();
    }

    /* Magma patlamaları */
    /* 0.3K hasar Yanma(2,2) sihirGucunden 2 kat etkilenir */
    public String magmaPatlamalari(Varlik[] dusmanlar, IskambilKart kart){
        StringBuilder sb = new StringBuilder();
        int verilecekHasar = this.hasar + (this.sihirGucu * 3);
        verilecekHasar += (int) Math.ceil(kart.deger * 0.3);
        sb.append(this.tumUygula(dusmanlar, new YanmaEfekti(2,2)));
        sb.append(this.tumSaldir(dusmanlar, verilecekHasar));
        return sb.toString();
    }

    /* Donduran Yıkım */
    /* 0.5K hasar tüm %10 Dondur(1) eğer siyahsa %30 Dondur(1) */
    public String donduranYikim(Varlik[] dusmanlar, IskambilKart kart, int rng){
        StringBuilder sb = new StringBuilder();
        int verilecekHasar = this.hasar + this.sihirGucu;
        verilecekHasar += (int) Math.ceil(kart.deger * 0.5);
        int rngSinir = kart.siyahMi() ? 6 : 2;
        sb.append(this.tumSaldir(dusmanlar, verilecekHasar));
        if(rng <= rngSinir) {
            this.tumUygula(dusmanlar, new DonmaEfekti(1));
        }
        return sb.toString();
    }

    /* Buz Şoku */
    /* 1K hasar %25 Dondur(1) Eğer 4 ise %30 Dondur(1) 2 zirh */
    public String buzSoku(Varlik dusman, IskambilKart kart, int rng){
        StringBuilder sb = new StringBuilder();
        int verilecekHasar = this.hasar + this.sihirGucu;
        verilecekHasar += kart.deger;
        sb.append(this.saldir(dusman, verilecekHasar));
        int rngSinir = 5;
        if(kart.deger == 4){
            this.ekZirh++;
            rngSinir = 6;
            int toplamZirh = this.kaliciZirh + this.ekZirh;
            sb.append(ad + " 1 zırh kazandı, toplam zırh " + toplamZirh + "\n");
        }
        if(rng <= rngSinir){
            Efekt yeniEfekt = new DonmaEfekti(1);
            dusman.efektler.add(yeniEfekt);
            sb.append(dusman.ad + " " + yeniEfekt + " uygulandı\n");
        }
        return sb.toString();
    }

    /* Çifte Patlama */
    /* 1K hasar ver tüm eğer sinek ise yeniden hasar ver */
    public String cifteYikim(Varlik[] dusmanlar, IskambilKart kart){
        StringBuilder sb = new StringBuilder();
        int verilecekHasar = this.hasar + this.sihirGucu;
        verilecekHasar += kart.deger;
        if(kart.suit == 2){
            sb.append(this.tumSaldir(dusmanlar, verilecekHasar));
        }
        sb.append(this.tumSaldir(dusmanlar, verilecekHasar));
        return sb.toString();
    }

    /* ULT */
    /* 5 hasar ver eğer kırmızıysa Yanma(5,3) eğer siyahsa %50 Dondur(2) */
    public String enerjiPatlamasi(Varlik[] dusmanlar, IskambilKart kart, int rng) {
        StringBuilder sb = new StringBuilder();
        sb.append(this.tumSaldir(dusmanlar, 5));
        if (kart.kirmiziMi()) {
            sb.append(this.tumUygula(dusmanlar, new YanmaEfekti(5, 3)));
        } else if (kart.siyahMi() && rng <= 10) {
            sb.append(this.tumUygula(dusmanlar, new DonmaEfekti(2)));
        }
        return sb.toString();
    }

    public void sifirla(){
        super.sifirla();
        this.sihirGucu = 0;
    }

    @Override
    public String toString(){
        return super.toString() + "Sihir Gücü " + this.sihirGucu;
    }
}

package com.example.iskambilzindani.varliklar;

import com.example.iskambilzindani.IskambilKart;
import com.example.iskambilzindani.efektler.Efekt;
import com.example.iskambilzindani.efektler.ZayiflatEfekti;

/* TODO sb.append için \n ekle */
public class Savasci extends Varlik{
    public int hiddet;
    public Savasci(){
        super("Savaşçı", 25,1, 0, 0);
        this.hiddet = 0;
    }

    /* Basit SALDIRI */
    /* 0.5K hasar +1 hiddet eğer Karo 7 ise 2K hasar +2 hiddet */
    public String basitSaldiri(Varlik dusman, IskambilKart kart){
        int verilecekHasar = this.hasar + this.hiddet * 2;
        if(kart.suit == 3 && kart.deger == 7){
            verilecekHasar += kart.deger * 2;
            this.hiddet = Math.min(++this.hiddet, 5);
        }else{
            hasar += (int) Math.ceil(kart.deger * 0.5);
        }
        this.hiddet = Math.min(++this.hiddet, 5);
        return this.saldir(dusman, verilecekHasar);
    }

    /* Hiddet Patlaması */
    /* 1K hasar eğer 3+ hiddetse ardışık 3 düşmana 1,5k hasar */
    public String hiddetPatlamasi(Varlik dusman1, Varlik dusman2, Varlik dusman3, IskambilKart kart){
        int verilecekHasar = this.hasar + this.hiddet * 2;
        StringBuilder sb = new StringBuilder();
        if(this.hiddet >= 3){
            verilecekHasar += Math.ceil(kart.deger * 1.5);
            sb.append(this.saldir(dusman1, verilecekHasar));
            sb.append(this.saldir(dusman2, verilecekHasar));
            sb.append(this.saldir(dusman3, verilecekHasar));
        }else{
            verilecekHasar += kart.deger;
            sb.append(this.saldir(dusman2, verilecekHasar));
        }
        return sb.toString();
    }

    /* Dayan */
    /* 0,5K kaplama +1 hidder eğer kırmızı kartsa +2 hiddet */
    public String dayan(IskambilKart kart){
        int ekKaplama = (int) Math.ceil(kart.deger * 0.5);
        this.kaplama += ekKaplama;
        this.hiddet = Math.min(++this.hiddet, 5);
        if(kart.kirmiziMi()){
            this.hiddet = Math.min(++this.hiddet, 5);
        }
        return ad + " " + ekKaplama + " kaplama kazandı, toplam kaplama " + this.kaplama;
    }

    /* Uyuşturan Darbe */
    /* 0.3K hasar Zar3: Zayıflat(2) eğer siyah kartsa +5 kaplama ve Zar4: Zayıflat(2) */
    public String uyusturanDarbe(Varlik dusman, IskambilKart kart, boolean zarSonucu) {
        StringBuilder sb = new StringBuilder();
        int verilecekHasar = this.hasar + this.hiddet * 2;
        verilecekHasar += (int) Math.ceil(kart.deger * 0.3);
        sb.append(this.saldir(dusman, verilecekHasar));
        if(zarSonucu){
            this.kaplama += 5;
            sb.append(ad + " ek 5 kaplama kazandı, toplam kaplama" + this.kaplama);
            Efekt yeniEfekt = new ZayiflatEfekti(2);
            dusman.efektler.add(yeniEfekt);
            sb.append(dusman.ad + " " + yeniEfekt + " uygulandı");
        }
        return sb.toString();
    }

    /* En iyi defans */
    /* 0,5K kaplama kaplama kadar hasar Eğer kart 5 ise ek 5 hasar */

    /* Diren */
    /* 0.5K can yenile +1 hiddet eğer maça ise eksin can / 4 yenile */

    /* Arkamda Dur */
    /* Kendine ve 1 dosta 0.3K kaplama */

    /* ULT */
    /* Tüm düşmanlara Zar5 Zayiflat(3) eğer siyah kartsa 10 kaplama eğer kırmızıysa 10 hasar tüm düşmanlara */
}

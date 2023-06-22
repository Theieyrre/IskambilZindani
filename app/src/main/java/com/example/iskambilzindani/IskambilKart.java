package com.example.iskambilzindani;

@SuppressWarnings("FieldCanBeLocal")
public class IskambilKart {
    public final int suit; /* 0: maça, 1: kupa, 2: sinek 3: karo */
    public final int deger;
    public final boolean asMi;
    public final boolean ozelMi;

    public IskambilKart(int suit, int kartDeger){
        this.suit = suit;
        this.deger = kartDeger;
        this.ozelMi = kartDeger == 10;
        this.asMi = kartDeger == 15;
    }

    public boolean kirmiziMi(){
        return this.suit % 2 == 0;
    }

    public boolean siyahMi() {
        return this.suit % 2 == 1;
    }
}

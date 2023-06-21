package com.example.iskambilzindani.siniflar;

@SuppressWarnings("FieldCanBeLocal")
public class IskambilKart {
    private final int suit; /* 0: maça, 1: kupa, 2: sinek 3: karo */
    private final int deger;
    private final boolean asMi;
    private final boolean ozelMi;

    public IskambilKart(int suit, int kartDeger){
        this.suit = suit;
        this.deger = kartDeger;
        this.ozelMi = kartDeger == 10;
        this.asMi = kartDeger == 14;
    }

    public int getDeger(){
        return this.deger;
    }

    public boolean kirmiziMi(){
        return this.suit % 2 == 0;
    }

    public boolean siyahMi() {
        return this.suit % 2 == 1;
    }
}

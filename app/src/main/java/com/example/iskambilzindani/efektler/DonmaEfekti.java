package com.example.iskambilzindani.efektler;

import com.example.iskambilzindani.varliklar.Varlik;

public class DonmaEfekti extends Efekt{
    public DonmaEfekti(int tur){
        super("Donma", tur);
    }

    public String tetikle(Varlik v){
        v.saldirabilir = false;
        return super.tetikle(v);
    }
}

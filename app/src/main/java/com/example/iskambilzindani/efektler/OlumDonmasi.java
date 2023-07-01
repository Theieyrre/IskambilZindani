package com.example.iskambilzindani.efektler;

import com.example.iskambilzindani.varliklar.Varlik;

public class OlumDonmasi extends Efekt{
    public OlumDonmasi(){
        super("Ölüm", 100);
    }

    public String tetikle(Varlik v){
        v.saldirabilir = false;
        return v.ad + " halen ölü";
    }
}

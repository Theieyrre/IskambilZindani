package com.example.iskambilzindani.efektler;

import com.example.iskambilzindani.varliklar.Varlik;

public class ZayiflatEfekti extends Efekt{
    public ZayiflatEfekti(int tur){
        super("Zayıflat", tur);
    }

    @Override
    public String tetikle(Varlik v) {
        v.hasar /= 2;
        return super.tetikle(v);
    }
}

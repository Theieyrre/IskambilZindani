package com.example.iskambilzindani.efektler;

import com.example.iskambilzindani.varliklar.Varlik;

public class YanmaEfekti extends Efekt{
    int hasar;
    public YanmaEfekti(int hasarGucu, int tur) {
        super("Yanma", tur);
        this.hasar = hasarGucu;
    }

    @Override
    public String tetikle(Varlik v) {
        v.gercekHasarAl(this.hasar);
        return super.tetikle(v);
    }
}

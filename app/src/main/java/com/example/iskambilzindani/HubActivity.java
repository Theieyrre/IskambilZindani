package com.example.iskambilzindani;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.iskambilzindani.varliklar.Varlik;
import java.util.ArrayList;

public class HubActivity extends AppCompatActivity {
    public ArrayList<Varlik> kahramanlar;
    public int seviye;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hub);

        this.seviye = 1;

        Bundle extras = getIntent().getExtras();
        this.kahramanlar  = (ArrayList<Varlik>) extras.getSerializable("kahramanlar");

        ProgressBar yukBar = findViewById(R.id.progressBar);

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), (ActivityResult result) -> {
            if(result.getData() != null && result.getResultCode() == RESULT_OK){
                hubGuncelle((ArrayList<Varlik>)result.getData().getSerializableExtra("kahramanlar"));
                yukBar.incrementProgressBy(1);
            }
        });

        TextView tv1 = findViewById(R.id.textView4);
        TextView tv2 = findViewById(R.id.textView5);
        TextView tv3 = findViewById(R.id.textView12);
        TextView tv4 = findViewById(R.id.textView13);
        ArrayList<TextView> tvs = new ArrayList<>();
        tvs.add(tv1);
        tvs.add(tv2);
        tvs.add(tv3);
        tvs.add(tv4);

        ImageButton kart_buton = findViewById(R.id.imageButton);
        ImageButton levelup_buton = findViewById(R.id.imageButton2);
        ImageButton can_buton = findViewById(R.id.imageButton3);

        kart_buton.setOnClickListener((View v) -> yukBar.incrementProgressBy(-1));

        levelup_buton.setOnClickListener((View v) -> {
            for(Varlik kahraman: this.kahramanlar){
                kahraman.levelAtla(1);
            }
            yukBar.incrementProgressBy(-1);
            HUDguncelle(tvs);
        });

        can_buton.setOnClickListener((View v) -> {
            for(Varlik kahraman: this.kahramanlar){
                kahraman.mevcutCan = Math.min(kahraman.maksimumCan, kahraman.mevcutCan + 10);
            }
            yukBar.incrementProgressBy(-2);
            HUDguncelle(tvs);
        });

        HUDguncelle(tvs);



        Button savas_buton = findViewById(R.id.button7);
        savas_buton.setOnClickListener((View v) -> {
            Intent i = new Intent(getApplicationContext(),BattleActivity.class);
            i.putExtra("kahramanlar", this.kahramanlar);
            i.putExtra("seviye", seviye);
            activityResultLauncher.launch(i);
        });
    }

    public void hubGuncelle(ArrayList<Varlik> kahramanlarSonuc){
        this.kahramanlar = kahramanlarSonuc;
        this.seviye++;
    }

    public void HUDguncelle(ArrayList<TextView> tvs){
        for(int i = 0; i<4; i++){
            try {
                tvs.get(i).setText(this.kahramanlar.get(i).ozet());
            }catch (IndexOutOfBoundsException e){
                ViewGroup parent = (ViewGroup) tvs.get(i).getParent();
                parent.removeView(tvs.get(i));
            }
        }
    }
}
package com.example.bayrakuygulamasisqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashSet;

public class QuizActivity extends AppCompatActivity {

    private TextView textViewDogru, textViewYanlis,textViewSoruSayi;
    private ImageView imageViewBayrak;
    private Button buttonA,buttonB,buttonC,buttonD;
    private ArrayList<Flags> sorularListe;
    private ArrayList<Flags> yanlisSeceneklerListe;
    private Flags dogruSoru;
    private Veritabani vt;
    //Sayaç
    private int soruSayac =0,yanlisSayac=0,dogruSayac=0;
    //Seçenekler
    private HashSet<Flags> secenekleriKaristirmaListe = new HashSet<>();
    private ArrayList<Flags> seceneklerListe = new ArrayList<>();

    MediaPlayer mediaPlayer = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        textViewDogru = findViewById(R.id.textViewDogru);
        textViewYanlis = findViewById(R.id.textViewYanlis);
        textViewSoruSayi = findViewById(R.id.textViewSoruSayi);
        imageViewBayrak = findViewById(R.id.imageViewBayrak);
        buttonA = findViewById(R.id.buttonA);
        buttonB = findViewById(R.id.buttonB);
        buttonC = findViewById(R.id.buttonC);
        buttonD = findViewById(R.id.buttonD);

        vt = new Veritabani(this);
        sorularListe = new FlagsDao().rastgele10Getir(vt);
        soruYukle();

        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dogruKontrol(buttonA);
                sayacKontrol();
            }
        });

        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dogruKontrol(buttonB);
                sayacKontrol();
            }
        });

        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dogruKontrol(buttonC);
                sayacKontrol();
            }
        });

        buttonD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dogruKontrol(buttonD);
                sayacKontrol();
            }
        });
    }

    public void soruYukle(){
        textViewSoruSayi.setText((soruSayac+1)+". SORU");
        textViewDogru.setText("Doğru : "+dogruSayac);
        textViewYanlis.setText("Yanlış : "+yanlisSayac);

        dogruSoru = sorularListe.get(soruSayac);
        yanlisSeceneklerListe = new FlagsDao().getRandom3Selection(vt,dogruSoru.getBayrak_id());
        imageViewBayrak.setImageResource(getResources().getIdentifier(dogruSoru.getBayrak_resim(),"drawable",getPackageName()));

        secenekleriKaristirmaListe.clear();
        secenekleriKaristirmaListe.add(dogruSoru);
        secenekleriKaristirmaListe.add(yanlisSeceneklerListe.get(0));
        secenekleriKaristirmaListe.add(yanlisSeceneklerListe.get(1));
        secenekleriKaristirmaListe.add(yanlisSeceneklerListe.get(2));
        seceneklerListe.clear();

        for (Flags b:secenekleriKaristirmaListe){
            seceneklerListe.add(b);
        }
        buttonA.setText(seceneklerListe.get(0).getBayrak_ad());
        buttonB.setText(seceneklerListe.get(1).getBayrak_ad());
        buttonC.setText(seceneklerListe.get(2).getBayrak_ad());
        buttonD.setText(seceneklerListe.get(3).getBayrak_ad());
    }

    public void dogruKontrol(Button button){
        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        String buttonYazi = button.getText().toString();
        String dogruCevap = dogruSoru.getBayrak_ad();

        if (buttonYazi.equals(dogruCevap)){
            dogruSoruSesi();
            dogruSayac++;
        }
        else {
            yanlisSoruSesi();
            v.vibrate(400);
            yanlisSayac++;
        }

    }

    public void sayacKontrol(){
        soruSayac++;

        if(soruSayac!=10){
            soruYukle();
        }
        else {
            Intent intent = new Intent(QuizActivity.this,ResultActivity.class);
            intent.putExtra("dogruSayac",dogruSayac);
            startActivity(intent);
            finish();
        }
    }

    public void dogruSoruSesi(){
        mediaPlayer = mediaPlayer.create(this,R.raw.correct);
        mediaPlayer.start();
    }

    public void yanlisSoruSesi(){
        mediaPlayer = mediaPlayer.create(this,R.raw.wrong);
        mediaPlayer.start();
    }
}
package com.example.bayrakuygulamasisqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private TextView textViewSonuc, textViewYuzdeSonuc;
    private Button buttonTekrar, buttonCikis;
    private int dogruSayac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        textViewSonuc = findViewById(R.id.textViewSonuc);
        textViewYuzdeSonuc = findViewById(R.id.textViewYuzdeSonuc);
        buttonTekrar = findViewById(R.id.buttonTekrar);
        buttonCikis = findViewById(R.id.buttonCikis);

        dogruSayac = getIntent().getIntExtra("dogruSayac",0);
        textViewSonuc.setText(dogruSayac+ " DOĞRU "+(10-dogruSayac)+ " YANLIŞ");
        textViewYuzdeSonuc.setText("% "+(dogruSayac*100)/10+" BAŞARI");

        buttonTekrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ResultActivity.this,QuizActivity.class));
                finish();
            }
        });

        buttonCikis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });
    }
}
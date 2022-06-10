package com.example.flagquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private Button buttonBasla;
    private RadioButton radioButton10, radioButton15, radioButton20;
    boolean kontrol=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioButton10 = findViewById(R.id.radioButton10);
        radioButton15 = findViewById(R.id.radioButton15);
        radioButton20 = findViewById(R.id.radioButton20);

        buttonBasla = findViewById(R.id.buttonBasla);
        copyDatabase();

        buttonBasla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,QuizActivity.class));
            }
        });
    }

    public void copyDatabase(){
        DatabaseCopyHelper helper = new DatabaseCopyHelper(this);

        try {
            helper.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        helper.openDataBase();
    }

}
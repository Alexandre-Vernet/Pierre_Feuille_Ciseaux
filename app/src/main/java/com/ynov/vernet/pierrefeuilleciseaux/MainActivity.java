package com.ynov.vernet.pierrefeuilleciseaux;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView textNbManches, textScoreJ1, textScoreJ2, textStats;
    Button btnPierre, btnFeuille, btnCiseaux;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Référencement de l'interface*/

        // Textes
        textNbManches = findViewById(R.id.textNbManches);
        textScoreJ1 = findViewById(R.id.textScoreJ1);
        textScoreJ2 = findViewById(R.id.textScoreJ2);
        textStats = findViewById(R.id.textStats);

        // Boutons
        btnPierre = findViewById(R.id.btnPierre);
        btnFeuille = findViewById(R.id.btnFeuille);
        btnCiseaux = findViewById(R.id.btnCiseaux);
    }
}
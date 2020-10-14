package com.ynov.vernet.pierrefeuilleciseaux;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // Eléments graphiques
    TextView textNbManches, textScoreJ1, textScoreJ2, textStats;
    Button btnPierre, btnFeuille, btnCiseaux;

    // Scores
    private int scoreJ1, scoreJ2 = 0;

    // Nombre de manche
    private int nbManches = 0;

    // Booléen
    // 0 = pierre   1 = feuille     2 = ciseaux
    private boolean[] choixJ1 = new boolean[3];
    private boolean[] choixJ2 = new boolean[3];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Référencement des textes
        textNbManches = findViewById(R.id.textNbManches);
        textScoreJ1 = findViewById(R.id.textScoreJ1);
        textScoreJ2 = findViewById(R.id.textScoreJ2);
        textStats = findViewById(R.id.textStats);

        // Référencement des boutons
        btnPierre = findViewById(R.id.btnPierre);
        btnPierre.setOnClickListener(this);

        btnFeuille = findViewById(R.id.btnFeuille);
        btnFeuille.setOnClickListener(this);

        btnCiseaux = findViewById(R.id.btnCiseaux);
        btnCiseaux.setOnClickListener(this);

        // Initialisation des choix des joueurs
        for (int i = 0; i < choixJ1.length; i++) {
            choixJ1[i] = false;
            choixJ2[i] = false;
        }
    }

    // Au clic d'un bouton
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnPierre:
                choixJoueur("pierre");
                break;
            case R.id.btnFeuille:
                choixJoueur("feuille");
                break;
            case R.id.btnCiseaux:
                choixJoueur("ciseaux");
                break;
        }
    }

    // Choix de l'item du joueur
    public void choixJoueur(String objet) {
        switch (objet) {
            case "pierre":
                choixJ1[0] = true;
                break;
            case "feuille":
                choixJ1[1] = true;
                break;
            case "ciseaux":
                choixJ1[2] = true;
                break;
        }

        // Faire jouer l'ordi
        choixOrdi();
    }

    public void choixOrdi() {
        // Choisir un nombre aléatoire entre 0 et 2
        int nbAlea = ThreadLocalRandom.current().nextInt(0, 2);

        // En fonction de ce nombre, choisir un item correspondant
        switch (nbAlea) {
            case 0:
                choixJ2[0] = true;
                break;
            case 1:
                choixJ2[1] = true;
                break;
            case 2:
                choixJ2[2] = true;
                break;
        }

        // Afficher les stats
        stats();
    }

    // Gérer les statistiques du jeu
    public void stats() {
        // Si J1 choisit Pierre
        if (choixJ1[0] && choixJ2[1]) {
            scoreJ2++;
            textStats.setText("Perdu ! \n L'ordi avait choisi Feuille");
            textStats.setTextColor(getResources().getColor(R.color.colorJ2));
        }
        if (choixJ1[0] && choixJ2[2]) {
            scoreJ1++;
            textStats.setText("Gagné ! \n L'ordi avait choisi Ciseaux");
            textStats.setTextColor(getResources().getColor(R.color.colorJ1));
        }
        if (choixJ1[0] && choixJ2[0]) {
            textStats.setText("Egalité !");
            textStats.setTextColor(getResources().getColor(R.color.colorEgalite));
        }

        // Si J1 choisit Feuille
        if (choixJ1[1] && choixJ2[2]) {
            scoreJ2++;
            textStats.setText("Perdu ! \n L'ordi avait choisi Ciseaux");
            textStats.setTextColor(getResources().getColor(R.color.colorJ2));
        }
        if (choixJ1[1] && choixJ2[0]) {
            scoreJ1++;
            textStats.setText("Gagné ! \n L'ordi avait choisi Pierre");
            textStats.setTextColor(getResources().getColor(R.color.colorJ1));
        }
        if (choixJ1[1] && choixJ2[1]) {
            textStats.setText("Egalité !");
            textStats.setTextColor(getResources().getColor(R.color.colorEgalite));
        }

        // Si J1 choisit Ciseaux
        if (choixJ1[2] && choixJ2[0]) {
            scoreJ2++;
            textStats.setText("Perdu ! \n L'ordi avait choisi Pierre");
            textStats.setTextColor(getResources().getColor(R.color.colorJ2));
        }
        if (choixJ1[2] && choixJ2[1]) {
            scoreJ1++;
            textStats.setText("Gagné ! \n L'ordi avait choisi Feuille");
            textStats.setTextColor(getResources().getColor(R.color.colorJ1));
        }
        if (choixJ1[2] && choixJ2[2]) {
            textStats.setText("Egalité !");
            textStats.setTextColor(getResources().getColor(R.color.colorEgalite));
        }

        reinit();
    }

    // Recommencer un round
    public void reinit() {
        // Mettre à jour les scores graphiquement
        textScoreJ1.setText("" + scoreJ1);
        textScoreJ2.setText("" + scoreJ2);

        // Réinitialiser les choix des joueurs
        for (int i = 0; i < choixJ1.length; i++) {
            choixJ1[i] = false;
            choixJ2[i] = false;
        }

        // Mettre à jour le nombre de manche
        nbManches++;
        textNbManches.setText("Nombre de manches : " + nbManches);


        // Si le J1 gagne 5 rounds
        if (scoreJ1 >= 5) {
            AlertDialog alertDialog = new AlertDialog.Builder(this)
                    .setTitle("Victoire")
                    .setMessage("Vous avez gagné ! \n Rejouer ?")
                    .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            scoreJ1 = 0;
                            scoreJ2 = 0;
                            nbManches =-1;
                            textStats.setText("");
                            reinit();
                        }
                    })
                    .setNegativeButton("Non", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    })
                    .show();
            alertDialog.setCanceledOnTouchOutside(false);
        }

        // Si le J2 gagne 5 rounds
        if (scoreJ2 >= 5) {
            AlertDialog alertDialog = new AlertDialog.Builder(this)
                    .setTitle("Défaite")
                    .setMessage("Vous avez perdu ! \n Rejouer ?")
                    .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            scoreJ1 = 0;
                            scoreJ2 = 0;
                            nbManches =-1;
                            textStats.setText("");
                            reinit();
                        }
                    })
                    .setNegativeButton("Non", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    })
                    .show();
            alertDialog.setCanceledOnTouchOutside(false);
        }
    }
}
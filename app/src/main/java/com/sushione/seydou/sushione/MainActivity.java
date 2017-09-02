package com.sushione.seydou.sushione;

import android.app.Dialog;
import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends ListActivity {

    private Button btnEnter;
    private Button btntendane;
    private Button btnPlateau;
    private Button btnPlat;
    private Button btnMidi;
    private Button btnBoisson;
    private Button btnCouvert;
    private Button btnDessert;
    private TextView commande;

    private CommandeDataSource datasource;

    final Context context = this;

    String CMD ="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // database connecting
        datasource = new CommandeDataSource(this);
        datasource.open();


        btnEnter = (Button) findViewById(R.id.entree);
        btnBoisson = (Button)findViewById(R.id.boissons);
        btnMidi = (Button)findViewById(R.id.midi);
        btnPlat = (Button)findViewById(R.id.plats);
        btntendane = (Button)findViewById(R.id.tendance);
        btnPlateau = (Button)findViewById(R.id.plateau);
        btnCouvert = (Button)findViewById(R.id.couverts);
        btnDessert = (Button)findViewById(R.id.dessert);
        commande = (TextView)findViewById(R.id.commande);

        //affiche database

        List<Repas> values = datasource.getAllCommand();
        ArrayAdapter<Repas> adapter = new ArrayAdapter<Repas>(this,
                android.R.layout.simple_list_item_1, values);
        setListAdapter(adapter);
        /*
        Pour dialogue des entrées boite de dialogue qui permet de choisir les entrée choisi par le client
         */
        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Dialog dEntree = new Dialog(context);
                dEntree.setContentView(R.layout.les_entrees);

                        ArrayAdapter<Repas> adapter = (ArrayAdapter<Repas>) getListAdapter();
                        Repas repas = null;
                            switch (v.getId()){
                                case R.id.brochthon:
                                    repas = datasource.createCommande("Brochette Thon");
                                    adapter.add(repas); break;
                                case R.id.brochsaumon:
                                    repas = datasource.createCommande("Brochette Saumon");adapter.add(repas); break;
                                case R.id.boeuffrom:
                                    repas = datasource.createCommande("Brochette Boeu fromage");
                                    adapter.add(repas); break;
                                default:
                            }


                        adapter.notifyDataSetChanged();


                dEntree.show();
            }
        });

        /*
        boissson
         */
        btnBoisson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CMD+="Boisson \n";
                Dialog dboissons = new Dialog(context);
                dboissons.setContentView(R.layout.boissons);

                dboissons.show();
            }
        });

        /*

         */
        btnPlat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CMD+="plateau\n";
                Dialog dPlats = new Dialog(context);
                dPlats.setContentView(R.layout.plats);

                dPlats.show();

            }
        });

        btntendane.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Dialog dtendance = new Dialog(context);
                  dtendance.setContentView(R.layout.tendances);

                  dtendance.show();
              }
          }
        );
    btnDessert.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Dialog ddessert = new Dialog(context);
                  ddessert.setContentView(R.layout.desserts);

                  ddessert.show();
              }
          }
        );

        btnCouvert.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Dialog dcouverts = new Dialog(context);
                  dcouverts.setContentView(R.layout.couverts);

                  dcouverts.show();
              }
          }
        );
    }

    @Override
    protected void onResume() {
        datasource.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        datasource.close();
        super.onPause();
    }
}

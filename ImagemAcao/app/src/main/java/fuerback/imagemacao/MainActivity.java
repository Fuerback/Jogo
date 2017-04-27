package fuerback.imagemacao;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Build;
import android.os.CountDownTimer;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import fuerback.imagemacao.adapter.CartasAdapter;

//----------------------------------------------------------------------------------------

public class MainActivity extends AppCompatActivity {

    private List<Carta> listaCartas;
    private List<Carta> listaCartas2;
    private ListView listViewCartas;
    private int cartasLidas;
    private Carta objCarta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cartasLidas = 0;

        listViewCartas = (ListView)findViewById(R.id.lista_cartas);
        listaCartas2 = new ArrayList<Carta>();

        CarregaDadosJson();

        Carta carta = listaCartas.get(0);
        MostraCarta(carta);
        cartasLidas++;

        //Click na lista
        listViewCartas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Carta carta = (Carta)listViewCartas.getItemAtPosition(i);
                Intent intentTrocaTelaFormulario = new Intent(MainActivity.this, CronometroActivity.class);
                intentTrocaTelaFormulario.putExtra("carta", carta);
                startActivity(intentTrocaTelaFormulario);
            }
        });

        //Botao voltar
        ImageView botaoVoltar = (ImageView)findViewById(R.id.botao_volta);
        botaoVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //Botao cronometro
        ImageView botaoCronometro = (ImageView)findViewById(R.id.cronometro);
        botaoCronometro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentTrocaTelaFormulario = new Intent(MainActivity.this, CronometroActivity.class);
                startActivity(intentTrocaTelaFormulario);
            }
        });

        //botao Pular carta
        ImageView pularCarta = (ImageView)findViewById(R.id.botao_pular);
        pularCarta.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {
                if(cartasLidas < listaCartas.size()) {
                    Carta carta = listaCartas.get(cartasLidas);
                    MostraCarta(carta);
                }
                cartasLidas++;
            }
        });
    }

    //----------------------------------------------------------------------------------------

    @Override
    protected void onResume() {
        super.onResume();
    }

    //----------------------------------------------------------------------------------------

    private void MostraCarta(Carta carta) {
        listaCartas2.clear();
        listaCartas2.add(carta);
        CartasAdapter adapter = new CartasAdapter(listaCartas2, this);
        listViewCartas.setAdapter(adapter);
    }

    //----------------------------------------------------------------------------------------

    private void CarregaDadosJson() {
        String json = null;
        try {
            InputStream is = getAssets().open("JSon");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            JSONObject obj = new JSONObject(json);
            JSONArray cartas  = obj.getJSONArray("Cartas");
            listaCartas = new ArrayList<Carta>();
            for(int i = 0; i < cartas.length(); i++) {
                JSONObject carta = cartas.getJSONObject(i);
                objCarta = new Carta();

                JSONObject pessoa = carta.getJSONObject("Pessoa");
                objCarta.setTextoPessoa(pessoa.getString("Texto"));
                objCarta.setPontosPessoa(pessoa.getInt("Pontuacao"));

                JSONObject objeto = carta.getJSONObject("Objeto");
                objCarta.setTextoObjeto(objeto.getString("Texto"));
                objCarta.setPontosObjeto(objeto.getInt("Pontuacao"));

                JSONObject acao = carta.getJSONObject("Acao");
                objCarta.setTextoAcao(acao.getString("Texto"));
                objCarta.setPontosAcao(acao.getInt("Pontuacao"));

                JSONObject dificil = carta.getJSONObject("Dificil");
                objCarta.setTextoDificil(dificil.getString("Texto"));
                objCarta.setPontosDificil(dificil.getInt("Pontuacao"));

                JSONObject lazer = carta.getJSONObject("Lazer");
                objCarta.setTextoLazer(lazer.getString("Texto"));
                objCarta.setPontosLazer(lazer.getInt("Pontuacao"));

                JSONObject mix = carta.getJSONObject("Mix");
                objCarta.setTextoMix(mix.getString("Texto"));
                objCarta.setPontosMix(mix.getInt("Pontuacao"));

                listaCartas.add(objCarta);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //----------------------------------------------------------------------------------------
}


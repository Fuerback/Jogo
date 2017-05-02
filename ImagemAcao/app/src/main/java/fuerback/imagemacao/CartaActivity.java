package fuerback.imagemacao;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import fuerback.imagemacao.adapter.CartasAdapter;

//----------------------------------------------------------------------------------------

public class CartaActivity extends AppCompatActivity {

    private Carta objCarta;
    private List<Carta> listaCartas;
    private List<Item> listaItens;
    private ListView listViewCartas;
    private int cartasLidas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carta);
        cartasLidas = 0;
        listaCartas = new ArrayList<Carta>();

        listViewCartas = (ListView)findViewById(R.id.lista_cartas);

        CarregaDadosJson();

        Carta carta = listaCartas.get(0); // mudar para randomico
        MostraCarta(carta);
        cartasLidas++;

        //Click na lista
        listViewCartas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Item item = (Item)listViewCartas.getItemAtPosition(i);
                Intent intentTrocaTelaFormulario = new Intent(CartaActivity.this, CronometroActivity.class);
                intentTrocaTelaFormulario.putExtra("carta", item);
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
                Intent intentTrocaTelaFormulario = new Intent(CartaActivity.this, CronometroActivity.class);
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
        listaItens = new ArrayList<Item>();
        listaItens.clear();
        listaItens.add(carta.getPessoa());
        listaItens.add(carta.getObjeto());
        listaItens.add(carta.getAcao());
        listaItens.add(carta.getDificil());
        listaItens.add(carta.getLazer());
        listaItens.add(carta.getMix());
        CartasAdapter adapter = new CartasAdapter(listaItens, this);
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
            for(int i = 0; i < cartas.length(); i++) {
                JSONObject carta = cartas.getJSONObject(i);
                objCarta = new Carta();

                Item objItem = new Item();
                JSONObject pessoa = carta.getJSONObject("Pessoa");
                objItem.setDescricao(pessoa.getString("Texto"));
                objItem.setPontuacao(pessoa.getInt("Pontuacao"));
                objItem.setPropriedade("Pessoa");
                objCarta.setPessoa(objItem);

                objItem = new Item();
                JSONObject objeto = carta.getJSONObject("Objeto");
                objItem.setDescricao(objeto.getString("Texto"));
                objItem.setPontuacao(objeto.getInt("Pontuacao"));
                objItem.setPropriedade("Objeto");
                objCarta.setObjeto(objItem);

                objItem = new Item();
                JSONObject acao = carta.getJSONObject("Acao");
                objItem.setDescricao(acao.getString("Texto"));
                objItem.setPontuacao(acao.getInt("Pontuacao"));
                objItem.setPropriedade("Acao");
                objCarta.setAcao(objItem);

                objItem = new Item();
                JSONObject dificil = carta.getJSONObject("Dificil");
                objItem.setDescricao(dificil.getString("Texto"));
                objItem.setPontuacao(dificil.getInt("Pontuacao"));
                objItem.setPropriedade("Dificil");
                objCarta.setDificil(objItem);

                objItem = new Item();
                JSONObject lazer = carta.getJSONObject("Lazer");
                objItem.setDescricao(lazer.getString("Texto"));
                objItem.setPontuacao(lazer.getInt("Pontuacao"));
                objItem.setPropriedade("Lazer");
                objCarta.setLazer(objItem);

                objItem = new Item();
                JSONObject mix = carta.getJSONObject("Mix");
                objItem.setDescricao(mix.getString("Texto"));
                objItem.setPontuacao(mix.getInt("Pontuacao"));
                objItem.setPropriedade("Mix");
                objCarta.setMix(objItem);

                listaCartas.add(objCarta);

            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //----------------------------------------------------------------------------------------
}


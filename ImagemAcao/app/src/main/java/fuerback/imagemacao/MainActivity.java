package fuerback.imagemacao;

import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Carta> listaCartas;
    private int cartasLidas;
    private Carta carta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cartasLidas = 0;

        CarregaDados();

        Button gerarCarta = (Button)findViewById(R.id.botao_carta);
        gerarCarta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cartasLidas < listaCartas.size()) {
                    Carta carta = listaCartas.get(cartasLidas);

                    TextView texoPessoa = (TextView)findViewById(R.id.texto_pessoa);
                    texoPessoa.setText(carta.getTextoPessoa());
                    TextView pontuacaoPessoa = (TextView)findViewById(R.id.pontuacao_pessoa);
                    pontuacaoPessoa.setText(String.valueOf(carta.getPontosPessoa()));

                    TextView texoObjeto = (TextView)findViewById(R.id.texto_objeto);
                    texoObjeto.setText(carta.getTextoObjeto());
                    TextView pontuacaoObjeto = (TextView)findViewById(R.id.pontuacao_objeto);
                    pontuacaoObjeto.setText(String.valueOf(carta.getPontosObjeto()));

                    TextView texoAcao = (TextView)findViewById(R.id.texto_acao);
                    texoAcao.setText(carta.getTextoAcao());
                    TextView pontuacaoAcao = (TextView)findViewById(R.id.pontuacao_acao);
                    pontuacaoAcao.setText(String.valueOf(carta.getPontosAcao()));

                    TextView texoDificil = (TextView)findViewById(R.id.texto_dificil);
                    texoDificil.setText(carta.getTextoDificil());
                    TextView pontuacaoDificil = (TextView)findViewById(R.id.pontuacao_dificil);
                    pontuacaoDificil.setText(String.valueOf(carta.getPontosDificil()));
                }
                cartasLidas++;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void CarregaDados() {
        LinkedList<String> linhas = new LinkedList<String>();
        try {
            AssetManager assetManager = getResources().getAssets();
            InputStream inputStream = assetManager.open("dados_cartas");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String linha;
            while((linha = bufferedReader.readLine())!=null){
                linhas.add(linha);
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        listaCartas = new ArrayList<Carta>();
        carta = new Carta();
        String tipoCarta = "";
        String descricao = "";
        int pontuacao = 0;
        for(int i = 0; i < linhas.size(); i++) {
            String linha = linhas.get(i);
            if(linha.isEmpty()) {
                listaCartas.add(carta);
                carta = new Carta();
            }
            else {
                String[] palavras = linha.split(" ");
                int numeroPalavras = palavras.length;
                descricao = "";
                for(int p = 0; p < numeroPalavras; p++) {
                    if(p == 0) {
                        tipoCarta = palavras[p];
                    } else if (palavras[p].matches("^[0-9]")){
                        pontuacao = Integer.parseInt(palavras[p]);
                    } else {
                        if(!descricao.isEmpty())
                            descricao = descricao.concat(" " + palavras[p]);
                        else
                            descricao = palavras[p];
                    }
                }
                PreencheCarta(carta, tipoCarta, pontuacao, descricao);
            }
        }
    }

    private void PreencheCarta(
            Carta carta,
            String tipoCarta,
            int pontuacao,
            String descricao
    )
    {
        switch (tipoCarta) {
            case "pessoa" :
                carta.setTextoPessoa(descricao);
                carta.setPontosPessoa(pontuacao);
                break;
            case "objeto" :
                carta.setTextoObjeto(descricao);
                carta.setPontosObjeto(pontuacao);
                break;
            case "acao" :
                carta.setTextoAcao(descricao);
                carta.setPontosAcao(pontuacao);
                break;
            case "dificil" :
                carta.setTextoDificil(descricao);
                carta.setPontosDificil(pontuacao);
        }
    }
}


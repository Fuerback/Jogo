package fuerback.imagemacao.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import fuerback.imagemacao.Carta;
import fuerback.imagemacao.R;

/**
 * Created by Usuario on 17/04/2017.
 */

//----------------------------------------------------------------------------------------

public class CartasAdapter extends BaseAdapter {

    private final List<Carta> carta;
    private final Context context;

    //----------------------------------------------------------------------------------------

    public CartasAdapter(List<Carta> carta, Context context) {
        this.carta = carta;
        this.context = context;
    }

    //----------------------------------------------------------------------------------------

    @Override
    public int getCount() {
        return carta.size();
    }

    //----------------------------------------------------------------------------------------

    @Override
    public Object getItem(int i) {
        return carta.get(i);
    }

    //----------------------------------------------------------------------------------------

    @Override
    public long getItemId(int i) {
        return carta.get(i).getId();
    }

    //----------------------------------------------------------------------------------------

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        Carta carta2 = carta.get(i);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = convertView;
        if(view == null) {
            view = inflater.inflate(R.layout.list_carta, viewGroup, false);
        }

        TextView textoPessoa = (TextView) view.findViewById(R.id.nome_pessoa);
        textoPessoa.setText(carta2.getTextoPessoa());

        TextView textoObjeto = (TextView) view.findViewById(R.id.nome_objeto);
        textoObjeto.setText(carta2.getTextoObjeto());

        TextView textoAcao = (TextView) view.findViewById(R.id.nome_acao);
        textoAcao.setText(carta2.getTextoAcao());

        TextView textoDificil = (TextView) view.findViewById(R.id.nome_dificil);
        textoDificil.setText(carta2.getTextoDificil());

        TextView textoLazer = (TextView) view.findViewById(R.id.nome_lazer);
        textoLazer.setText(carta2.getTextoLazer());

        TextView textoMix = (TextView) view.findViewById(R.id.nome_mix);
        textoMix.setText(carta2.getTextoMix());

        TextView pontuacaoPessoa = (TextView) view.findViewById(R.id.pontuacao_pessoa);
        pontuacaoPessoa.setText(Integer.toString(carta2.getPontosPessoa()));

        TextView pontuacaoObjeto = (TextView) view.findViewById(R.id.pontuacao_objeto);
        pontuacaoObjeto.setText(Integer.toString(carta2.getPontosObjeto()));

        TextView pontuacaoAcao = (TextView) view.findViewById(R.id.pontuacao_acao);
        pontuacaoAcao.setText(Integer.toString(carta2.getPontosAcao()));

        TextView pontuacaoDificil = (TextView) view.findViewById(R.id.pontuacao_dificil);
        pontuacaoDificil.setText(Integer.toString(carta2.getPontosDificil()));

        TextView pontuacaoLazer = (TextView) view.findViewById(R.id.pontuacao_lazer);
        pontuacaoLazer.setText(Integer.toString(carta2.getPontosLazer()));

        TextView pontuacaoMix = (TextView) view.findViewById(R.id.pontuacao_mix);
        pontuacaoMix.setText(Integer.toString(carta2.getPontosMix()));

        return view;
    }

    //----------------------------------------------------------------------------------------
}

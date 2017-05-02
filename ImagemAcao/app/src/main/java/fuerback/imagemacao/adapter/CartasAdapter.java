package fuerback.imagemacao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import fuerback.imagemacao.Item;
import fuerback.imagemacao.R;

/**
 * Created by Usuario on 17/04/2017.
 */

//----------------------------------------------------------------------------------------

public class CartasAdapter extends BaseAdapter {

    private final List<Item> listaItens;
    private final Context context;

    //----------------------------------------------------------------------------------------

    public CartasAdapter(List<Item> itens, Context context) {
        this.listaItens = itens;
        this.context = context;
    }

    //----------------------------------------------------------------------------------------

    @Override
    public int getCount() {
        return listaItens.size();
    }

    //----------------------------------------------------------------------------------------

    @Override
    public Object getItem(int i) {
        return listaItens.get(i);
    }

    //----------------------------------------------------------------------------------------

    @Override
    public long getItemId(int i) {
        return listaItens.get(i).getId();
    }

    //----------------------------------------------------------------------------------------

    @Override
    public View getView(int posicao, View convertView, ViewGroup viewGroup) {
        Item objItem = listaItens.get(posicao);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = convertView;
        if(view == null) {
            view = inflater.inflate(R.layout.item_carta, viewGroup, false);
        }

        TextView textoCarta = (TextView) view.findViewById(R.id.descricao_carta);
        textoCarta.setText(objItem.getDescricao());

        TextView pontuacaoCarta = (TextView) view.findViewById(R.id.pontuacao_carta);
        pontuacaoCarta.setText(Integer.toString(objItem.getPontuacao()));

        ImageView imagem = (ImageView)view.findViewById(R.id.imagem_carta);

        if(objItem.getPropriedade() == "Pessoa") {
            imagem.setImageResource(R.drawable.pessoa);
        }
        if(objItem.getPropriedade() == "Objeto") {
            imagem.setImageResource(R.drawable.objeto);
        }
        if(objItem.getPropriedade() == "Acao") {
            imagem.setImageResource(R.drawable.acao);
        }
        if(objItem.getPropriedade() == "Dificil") {
            imagem.setImageResource(R.drawable.dificil);
        }
        if(objItem.getPropriedade() == "Lazer") {
            imagem.setImageResource(R.drawable.lazer);
        }
        if(objItem.getPropriedade() == "Mix") {
            imagem.setImageResource(R.drawable.mix);
        }

        return view;
    }

    //----------------------------------------------------------------------------------------
}

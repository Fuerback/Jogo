package fuerback.imagemacao;

import java.io.Serializable;

/**
 * Created by Usuario on 28/03/2017.
 */

public class Item implements Serializable {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getPontuacao() {return pontuacao;}

    public void setPontuacao(int pontuacao) {this.pontuacao = pontuacao;}

    public String getPropriedade() {return propriedade;}

    public void setPropriedade(String propriedade) {this.propriedade = propriedade;}

    private String descricao;
    private int pontuacao;
    private int id;
    private String propriedade;

}

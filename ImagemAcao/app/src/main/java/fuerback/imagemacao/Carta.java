package fuerback.imagemacao;

/**
 * Created by Usuario on 02/05/2017.
 */

public class Carta {
    public Item getPessoa() {
        return pessoa;
    }

    public void setPessoa(Item pessoa) {
        this.pessoa = pessoa;
    }

    public Item getObjeto() {
        return objeto;
    }

    public void setObjeto(Item objeto) {
        this.objeto = objeto;
    }

    public Item getLazer() {
        return lazer;
    }

    public void setLazer(Item lazer) {
        this.lazer = lazer;
    }

    public Item getMix() {
        return mix;
    }

    public void setMix(Item mix) {
        this.mix = mix;
    }

    public Item getDificil() {
        return dificil;
    }

    public void setDificil(Item dificil) {
        this.dificil = dificil;
    }

    public Item getAcao() {
        return acao;
    }

    public void setAcao(Item acao) {
        this.acao = acao;
    }

    private Item pessoa;
    private Item objeto;
    private Item lazer;
    private Item mix;
    private Item dificil;
    private Item acao;
}

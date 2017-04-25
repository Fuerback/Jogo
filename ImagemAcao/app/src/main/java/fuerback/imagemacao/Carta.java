package fuerback.imagemacao;

/**
 * Created by Usuario on 28/03/2017.
 */

public class Carta {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTextoPessoa() {
        return textoPessoa;
    }

    public void setTextoPessoa(String textoPessoa) {
        this.textoPessoa = textoPessoa;
    }

    public String getTextoObjeto() {
        return textoObjeto;
    }

    public void setTextoObjeto(String textoObjeto) {
        this.textoObjeto = textoObjeto;
    }

    public String getTextoAcao() {
        return textoAcao;
    }

    public void setTextoAcao(String textoAcao) {
        this.textoAcao = textoAcao;
    }

    public String getTextoDificil() {
        return textoDificil;
    }

    public void setTextoDificil(String textoDificil) {
        this.textoDificil = textoDificil;
    }

    public int getPontosPessoa() {
        return pontosPessoa;
    }

    public void setPontosPessoa(int pontosPessoa) {
        this.pontosPessoa = pontosPessoa;
    }

    public int getPontosObjeto() {
        return pontosObjeto;
    }

    public void setPontosObjeto(int pontosObjeto) {
        this.pontosObjeto = pontosObjeto;
    }

    public int getPontosAcao() {
        return pontosAcao;
    }

    public void setPontosAcao(int pontosAcao) {
        this.pontosAcao = pontosAcao;
    }

    public int getPontosDificil() {
        return pontosDificil;
    }

    public void setPontosDificil(int pontosDificil) {
        this.pontosDificil = pontosDificil;
    }

    public String getTextoLazer() {return textoLazer;}

    public void setTextoLazer(String textoLazer) {this.textoLazer = textoLazer;}

    public String getTextoMix() {return textoMix;}

    public void setTextoMix(String textoMix) {this.textoMix = textoMix;}

    public int getPontosLazer() {return pontosLazer;}

    public void setPontosLazer(int pontosLazer) {this.pontosLazer = pontosLazer;}

    public int getPontosMix() {return pontosMix;}

    public void setPontosMix(int pontosMix) {this.pontosMix = pontosMix;}

    private String textoPessoa;
    private String textoObjeto;
    private String textoAcao;
    private String textoDificil;
    private String textoLazer;
    private String textoMix;
    private int pontosLazer;
    private int pontosMix;
    private int pontosPessoa;
    private int pontosObjeto;
    private int pontosAcao;
    private int pontosDificil;
    private int id;

}

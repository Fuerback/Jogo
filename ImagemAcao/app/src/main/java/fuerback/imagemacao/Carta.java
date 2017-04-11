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

    private String textoPessoa;
    private String textoObjeto;
    private String textoAcao;
    private String textoDificil;
    private int pontosPessoa;
    private int pontosObjeto;
    private int pontosAcao;
    private int pontosDificil;
    private int id;

}

package pessoto.android.myheroes.model;

import android.widget.ImageView;

public class Personagem {

    private String nomePersonagem;
    private String descricaoPersonagem;
    private int imagemPersonagem;

    public Personagem() {
    }

    public Personagem(String nomePersonagem, int imagemPersonagem) {
        this.nomePersonagem = nomePersonagem;
        this.imagemPersonagem = imagemPersonagem;
    }

    public String getNomePersonagem() {
        return nomePersonagem;
    }

    public void setNomePersonagem(String nomePersonagem) {
        this.nomePersonagem = nomePersonagem;
    }

    public String getDescricaoPersonagem() {
        return descricaoPersonagem;
    }

    public void setDescricaoPersonagem(String descricaoPersonagem) {
        this.descricaoPersonagem = descricaoPersonagem;
    }

    public int getImagemPersonagem() {
        return imagemPersonagem;
    }

    public void setImagemPersonagem(int imagemPersonagem) {
        this.imagemPersonagem = imagemPersonagem;
    }
}

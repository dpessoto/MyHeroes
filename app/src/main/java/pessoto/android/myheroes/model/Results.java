package pessoto.android.myheroes.model;

import java.util.List;

public class Results {
    public Thumbnail thumbnail;
    private String id;
    private String name;
    private String description;
    private String imagem;

    public Results(String id, String name, String description, String imagem) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imagem = imagem;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}

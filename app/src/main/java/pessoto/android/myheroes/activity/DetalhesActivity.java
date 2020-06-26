package pessoto.android.myheroes.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import pessoto.android.myheroes.R;

public class DetalhesActivity extends AppCompatActivity {

    private TextView textNomePersonagem, textDescricao;
    private ImageView imagePersonagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        inicializarComponentes();

        RecuperarDadosMain recuperarDadosMain = new RecuperarDadosMain().invoke();
        String nome = recuperarDadosMain.getNome();
        String descricao = recuperarDadosMain.getDescricao();
        //int image = recuperarDadosMain.getImage();

        configurarDadosMain(nome, descricao);

    }

    private void configurarDadosMain(String nome, String descricao) {
        textNomePersonagem.setText(nome);

        if (descricao.isEmpty())
            textDescricao.setText("Personagem sem descrição");
        else
            textDescricao.setText(descricao);
        //imagePersonagem.setImageResource(image);
    }

    private void inicializarComponentes() {
        textNomePersonagem = findViewById(R.id.textNomePersonagemDetalhes);
        textDescricao = findViewById(R.id.textDescricaoDetalhes);
        imagePersonagem = findViewById(R.id.imagePersonagemDetalhes);
    }

    @Override
    public void onBackPressed() {
        voltarParaMain();
    }

    public void telaMain(View view) {
        voltarParaMain();
    }

    private void voltarParaMain() {
        Intent intent = new Intent(DetalhesActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private class RecuperarDadosMain {
        private String nome;
        private String descricao;
        private int image;


        public String getDescricao() {
            return descricao;
        }


        public String getNome() {
            return nome;
        }

        public int getImage() {
            return image;
        }

        public RecuperarDadosMain invoke() {
            Bundle dados = getIntent().getExtras();
            nome = dados.getString("nome");
            descricao = dados.getString("descricao");
            //image = dados.getInt("imagem");
            return this;
        }
    }
}

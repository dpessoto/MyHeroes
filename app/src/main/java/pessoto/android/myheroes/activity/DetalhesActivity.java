package pessoto.android.myheroes.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

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
        String image = recuperarDadosMain.getImage();

        configurarDadosMain(nome, descricao, image);
    }

    private void configurarDadosMain(String nome, String descricao, String image) {
        textNomePersonagem.setText(nome);

        if (descricao.isEmpty())
            textDescricao.setText("Personagem sem descrição");
        else
            textDescricao.setText(descricao);
        Picasso.get()
                .load(image)
                .into(imagePersonagem);
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
        private String image;


        public String getDescricao() {
            return descricao;
        }


        public String getNome() {
            return nome;
        }

        public String getImage() {
            return image;
        }

        public RecuperarDadosMain invoke() {
            Bundle dados = getIntent().getExtras();
            nome = dados.getString("nome");
            descricao = dados.getString("descricao");
            image = dados.getString("imagem");
            return this;
        }
    }
}

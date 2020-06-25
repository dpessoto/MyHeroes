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
        int image = recuperarDadosMain.getImage();

        configurarDadosMain(nome, image);

    }

    private void configurarDadosMain(String nome, int image) {
        textNomePersonagem.setText(nome);
        imagePersonagem.setImageResource(image);
        textDescricao.setText("Homem de ferro é o melhor");
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
        private int image;

        public String getNome() {
            return nome;
        }

        public int getImage() {
            return image;
        }

        public RecuperarDadosMain invoke() {
            Bundle dados = getIntent().getExtras();
            nome = dados.getString("nome");
            image = dados.getInt("imagem");
            return this;
        }
    }
}

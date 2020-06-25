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

        textNomePersonagem = findViewById(R.id.textNomePersonagemDetalhes);
        textDescricao = findViewById(R.id.textDescricaoDetalhes);
        imagePersonagem = findViewById(R.id.imagePersonagemDetalhes);

        //Recuperar os dados
        Bundle dados = getIntent().getExtras();
        String nome = dados.getString("nome");
        int image = dados.getInt("imagem");

        //configurar o dado recebido
        textNomePersonagem.setText(nome);
        imagePersonagem.setImageResource(image);
        textDescricao.setText("Homem de ferro é o melhor");

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(DetalhesActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void telaMain(View view) {
        Intent i = new Intent(DetalhesActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }

}

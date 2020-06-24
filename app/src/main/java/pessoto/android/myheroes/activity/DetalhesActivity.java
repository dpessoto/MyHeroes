package pessoto.android.myheroes.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import pessoto.android.myheroes.R;

public class DetalhesActivity extends AppCompatActivity {

    private TextView textNomeVinho, textDescricao;
    private ImageView imageVinho;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        textNomeVinho = findViewById(R.id.textNomeVinho);
        textDescricao = findViewById(R.id.textDescricao);
        imageVinho = findViewById(R.id.imageVinho);

        //Recuperar os dados
        Bundle dados = getIntent().getExtras();
        String nomeVinho = dados.getString("nome");

        //configurar o dado recebido
        textNomeVinho.setText(nomeVinho);

        vinhoSelecionado(nomeVinho);
    }

    public void vinhoSelecionado(String vinho) {

        switch (vinho) {
            case "Divin Marquis 2019":
                textDescricao.setText(R.string.descricao_1);
                imageVinho.setImageResource(R.drawable.divin_marquis_2019);
                break;
            case "Fuenteviña Pinot Noir":
                textDescricao.setText(R.string.descricao_2);
                imageVinho.setImageResource(R.drawable.fuentevina_pinot_noir);
                break;
            case "Fundo Las Lagunas Reserva Pinot Noir 2019":
                textDescricao.setText(R.string.descricao_3);
                imageVinho.setImageResource(R.drawable.fundo_las_lagunas_reserva_pinot_noir_2019);
                break;
            case "Alto Vez Vinho Verde Branco DOC":
                textDescricao.setText(R.string.descricao_4);
                imageVinho.setImageResource(R.drawable.alto_vez_vinho_verde_vranco_doc);
                break;
            case "Famiglia Castellani Chianti Riserva DOCG 2015":
                textDescricao.setText(R.string.descricao_5);
                imageVinho.setImageResource(R.drawable.famiglia_castellani_chianti_riserva_docg_2015);
                break;
            case "Villa Pampini Vino Bianco":
                textDescricao.setText(R.string.descricao_6);
                imageVinho.setImageResource(R.drawable.villa_pampini_vino_bianco);
                break;
            case "Primi Luis Gurpegui Gran Reserva Navarra D.O. 2007":
                textDescricao.setText(R.string.descricao_7);
                imageVinho.setImageResource(R.drawable.primi_luis_gurpegui_gran_reserva_navarra_do_2007);
                break;
            case "Ajimez Vino Blanco 2018":
                textDescricao.setText(R.string.descricao_8);
                imageVinho.setImageResource(R.drawable.ajimez_vino_blanco_2018);
                break;

            default:
                textNomeVinho.setText(R.string.wine_not_found);
                imageVinho.setVisibility(View.INVISIBLE);
                textDescricao.setVisibility(View.INVISIBLE);
        }
    }

    public void telaMain(View view) {
        Intent i = new Intent(DetalhesActivity.this, MainActivity.class);
        startActivity(i);
        finish();
    }

}

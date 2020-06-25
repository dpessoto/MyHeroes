package pessoto.android.myheroes.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import pessoto.android.myheroes.R;
import pessoto.android.myheroes.RecyclerItemClickListener;
import pessoto.android.myheroes.adapter.Adapter;
import pessoto.android.myheroes.model.Personagem;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Personagem> listaPersonagens = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

        //Lista de personagens;
        criarPersonagens();

        //Configurar o adapter
        Adapter adapter = new Adapter(listaPersonagens);

        //Configurar RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        //tela de detalhes
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Intent intent = new Intent(MainActivity.this, DetalhesActivity.class);
                                startActivity(intent);

                                Personagem personagem = listaPersonagens.get(position);

                                //passar dados para DetalhesActivity
                                intent.putExtra("nome", personagem.getNomePersonagem());
                                intent.putExtra("imagem", personagem.getImagemPersonagem());
                                startActivity(intent);
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {

                            }

                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                            }
                        }
                )
        );

    }

    public void criarPersonagens() {
        Personagem personagem = new Personagem("Homem de ferro", R.drawable.logo_white);
        listaPersonagens.add(personagem);

        personagem = new Personagem("Capitao America", R.drawable.logo_white);
        listaPersonagens.add(personagem);

        personagem = new Personagem("Homem Aranha", R.drawable.logo_white);
        listaPersonagens.add(personagem);

        personagem = new Personagem("Hulk", R.drawable.logo_white);
        listaPersonagens.add(personagem);

        personagem = new Personagem("Thanos", R.drawable.logo_white);
        listaPersonagens.add(personagem);

    }

    @Override
    public void onBackPressed() {
        // não permitir que o usuario volte
    }
}

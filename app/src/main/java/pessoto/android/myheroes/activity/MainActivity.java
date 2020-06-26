package pessoto.android.myheroes.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import pessoto.android.myheroes.R;
import pessoto.android.myheroes.RecyclerItemClickListener;
import pessoto.android.myheroes.adapter.Adapter;
import pessoto.android.myheroes.model.Marvel;
import pessoto.android.myheroes.model.Personagem;
import pessoto.android.myheroes.model.Results;
import pessoto.android.myheroes.model.Thumbnail;
import pessoto.android.myheroes.service.MarvelService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Personagem> listaPersonagens = new ArrayList<>();
    private List<Results> listaResults = new ArrayList<>();
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializarComponentes();

        Toast.makeText(this, R.string.click_on_some_wine, Toast.LENGTH_SHORT).show();

        //retrofit
        retrofit = new Retrofit.Builder()
                .baseUrl("https://gateway.marvel.com/v1/public/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        recuperarListaRetrofit();

        criarListaDePersonagens();

        //Configurar o adapter
        Adapter adapter = new Adapter(listaResults);
        configuraRecyclerView(adapter);

        enviarDadosDetalhesActivity();
    }

    private void recuperarListaRetrofit() {

        MarvelService marvelService = retrofit.create(MarvelService.class);
        Call<Marvel> requestMarval = marvelService.listMarvel();

        Log.i("ok", "Retrofit");

        requestMarval.enqueue(new Callback<Marvel>() {
            @Override
            public void onResponse(Call<Marvel> call, Response<Marvel> response) {
                if (response.isSuccessful()) {
                    Marvel marvelLista = response.body();
                    int count = Integer.parseInt(marvelLista.data.getCount());
                    for (int i = 0; i < count; i++) {
                        Results results = marvelLista.data.results.get(i);
                        Thumbnail thumbnail = results.thumbnail;

                        String urlImagem = thumbnail.getPath() + "." + thumbnail.getExtension();

                        String id = results.getId();
                        String name = results.getName();
                        String description = results.getDescription();

                        results = new Results(id, name, description, urlImagem);
                        listaResults.add(results);


                        Log.i("FOI", "Personagem: " + results.getName());
                        Log.i("FOI", "descri: " + results.getDescription());
                        Log.i("FOI",
                                "Imagem: " + urlImagem);

                    }
                }
            }

            @Override
            public void onFailure(Call<Marvel> call, Throwable t) {
                Log.e("Fail", "Erro: " + t.getMessage());
            }
        });

    }

    private void inicializarComponentes() {
        recyclerView = findViewById(R.id.recyclerView);
    }

    private void enviarDadosDetalhesActivity() {
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recyclerView,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                Intent intent = new Intent(MainActivity.this, DetalhesActivity.class);
                                startActivity(intent);

                                Results personagem = listaResults.get(position);

                                //passar dados para DetalhesActivity
                                intent.putExtra("nome", personagem.getName());
                                intent.putExtra("descricao", personagem.getDescription());
                                //intent.putExtra("imagem", personagem.getImagemPersonagem());
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

    private void configuraRecyclerView(Adapter adapter) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    public void criarListaDePersonagens() {

        Personagem personagem = new Personagem("vai", R.drawable.logo_white);
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
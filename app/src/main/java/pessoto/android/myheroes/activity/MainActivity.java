package pessoto.android.myheroes.activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import pessoto.android.myheroes.R;
import pessoto.android.myheroes.RecyclerItemClickListener;
import pessoto.android.myheroes.adapter.Adapter;
import pessoto.android.myheroes.model.Marvel;
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
    private List<Results> listaResults = new ArrayList<>();
    private Retrofit retrofit;
    private ProgressBar progressPersonagem;
    private TextView textCarregando;

    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inicializarComponentes();

        verificaConexao();

        enviarDadosDetalhesActivity();
    }

    private void retrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl("https://gateway.marvel.com/v1/public/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private void recuperarListaRetrofit() {

        MarvelService marvelService = retrofit.create(MarvelService.class);
        Call<Marvel> requestMarval = marvelService.listMarvel();

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
                        String urlTratada = "https" + urlImagem.substring(4);

                        String id = results.getId();
                        String name = results.getName();
                        String description = results.getDescription();

                        results = new Results(id, name, description, urlTratada);
                        listaResults.add(results);
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
        progressPersonagem = findViewById(R.id.progressPesonagem);
        textCarregando = findViewById(R.id.textCarregando);
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
                                intent.putExtra("imagem", personagem.getImagem());
                                startActivity(intent);
                                finish();
                            }

                            @Override
                            public void onLongItemClick(View view, int position) {

                                Toast.makeText(MainActivity.this, "Homem de ferro é o melhor!!!", Toast.LENGTH_SHORT).show();
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

    @Override
    public void onBackPressed() {
        // não permitir que o usuario volte
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void verificaConexao() {


        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo net = cm.getActiveNetworkInfo();

        if (net != null && net.isConnectedOrConnecting()) {

            retrofit();

            recuperarListaRetrofit();

            delayParaCarregarRecycler();

        } else {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle(getString(R.string.no_internet));
            alert.setIcon(R.mipmap.ic_launcher);
            alert.setMessage(getString(R.string.we_need_an_intenert));
            alert.setCancelable(false);
            alert.setPositiveButton(getString(R.string.try_again), (dialogInterface, i) -> verificaConexao());

            alert.setNegativeButton(getString(R.string.close), (dialogInterface, i) -> {
                Toast.makeText(getApplicationContext(),
                        getString(R.string.try_later),
                        Toast.LENGTH_LONG).show();
                finishAffinity();
            });

            alert.create();
            alert.show();
        }
    }

    private void delayParaCarregarRecycler() {
        new Handler().postDelayed(() -> {
            Adapter adapter = new Adapter(listaResults);
            configuraRecyclerView(adapter);

            textCarregando.setVisibility(View.INVISIBLE);
            progressPersonagem.setVisibility(View.INVISIBLE);
        }, 2300);
    }
}
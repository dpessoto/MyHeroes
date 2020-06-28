package pessoto.android.myheroes.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import pessoto.android.myheroes.R;

public class DetalhesFragment extends Fragment {

    private ImageView icon;
    private TextView textNome;
    private TextView textDescricao;
    private ImageView imagePersonagem;
    private Bundle personagem;

    public DetalhesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detalhes, container, false);

        personagem = getArguments();

        icon = view.findViewById(R.id.iconDetalhes);
        icon.setImageResource(R.mipmap.ic_launcher);

        textNome = view.findViewById(R.id.textNamePersonagem);
        textNome.setText(personagem.getString("nome"));

        textDescricao = view.findViewById(R.id.textDescricaoDetalhe);
        if (personagem.getString("descricao").isEmpty())
            textDescricao.setText("Personagem sem descrição");
        else
            textDescricao.setText(personagem.getString("descricao"));

        imagePersonagem = view.findViewById(R.id.imagePersonagemDetalhes);
        Picasso.get()
                .load(personagem.getString("imagem"))
                .into(imagePersonagem);

        return view;
    }

}
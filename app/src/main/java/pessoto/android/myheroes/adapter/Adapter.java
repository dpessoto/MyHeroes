package pessoto.android.myheroes.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import pessoto.android.myheroes.R;
import pessoto.android.myheroes.model.Personagem;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {

    private List<Personagem> listaPersonagens;

    public Adapter(List<Personagem> lista) {
        listaPersonagens = lista;
    }

    @NonNull
    @Override
    public Adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_lista, parent, false);

        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Personagem personagem;
        personagem = listaPersonagens.get(position);
        holder.nomePersonagem.setText(personagem.getNomePersonagem());
        holder.imagemPersonagem.setImageResource(personagem.getImagemPersonagem());

    }

    @Override
    public int getItemCount() {
        return listaPersonagens.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nomePersonagem;
        ImageView imagemPersonagem;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nomePersonagem = itemView.findViewById(R.id.textNomePersonagemDetalhes);
            imagemPersonagem = itemView.findViewById(R.id.imagePersonagem);

        }
    }
}

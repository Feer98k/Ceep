package com.example.ceep.classes.ui.adapter.listaNotasAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ceep.R;
import com.example.ceep.classes.constantes.general.coresEnum;
import com.example.ceep.classes.database.dao.NotaDataDao;
import com.example.ceep.classes.model.Nota;

import java.util.Collections;
import java.util.List;

public class ListaNotasAdapter extends RecyclerView.Adapter<ListaNotasAdapter.notaViewHolder> {

    private final Context context;
    private final List<Nota> list;
    private onClickListernet onClickListernet;
    final NotaDataDao dao;


    public ListaNotasAdapter(Context context, List<Nota> list, NotaDataDao dataDao) {
        this.context = context;
        this.list = list;
        this.dao=dataDao;
    }

    public void setOnClickListerner(onClickListernet onClickListernet) {
        this.onClickListernet = onClickListernet;
    }

    @NonNull
    @Override
    public notaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflater = LayoutInflater
                .from(context)
                .inflate(R.layout.item_nota, parent, false);
        return new notaViewHolder(inflater);
    }

    @Override
    public synchronized void onBindViewHolder(@NonNull notaViewHolder holder, int position) {
        Nota notaBind = list.get(position);
        coresEnum corPadraoDaNota = notaBind.getCorPadrao();
        setColorByHolder(holder, corPadraoDaNota);
        holder.vincula(notaBind);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    private void setColorByHolder(notaViewHolder holder, coresEnum colorDefault) {
        switch (colorDefault) {
            case BRANCO:
                holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.BRANCO));

                break;
            case AZUL:
                holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.AZUL));
                break;
            case VERMELHO:
                holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.VERMELHO));
                break;
            case AMARELO:
                holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.AMARELO));
                break;

            case VERDE:
                holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.VERDE));
                break;

            case LILAS:
                holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.LILÁS));
                break;
            case CINZA:
                holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.CINZA));
                break;
            case MARROM:
                holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.MARROM));
                break;
            case ROXO:
                holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.ROXO));
                break;
        }
    }


    public void remover(int bindingAdapterPosition) {
        Nota nota= list.get(bindingAdapterPosition);
        for (Nota a: list){
            if(a.getPosicao()>nota.getPosicao()){
                a.setPosicao(a.getPosicao()-1);
                dao.edita(a);
            }
        }
        notifyItemRemoved(bindingAdapterPosition);
        list.remove(bindingAdapterPosition);
    }

    public void troca(int first, int targeter) {
        Nota primeiro = list.get(first);
        Nota segundo = list.get(targeter);
        long rec = primeiro.getPosicao();
        primeiro.setPosicao(segundo.getPosicao());
        segundo.setPosicao(rec);
        Collections.swap(list, first, targeter);
        dao.edita(primeiro);
        dao.edita(segundo);
        notifyItemMoved(first, targeter);
    }

    class notaViewHolder extends RecyclerView.ViewHolder {

        private Nota nota;
        final TextView titulo;
        final TextView descricao;
        final CardView cardView;
        public notaViewHolder(View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.item_nota_titulo);
            descricao = itemView.findViewById(R.id.item_nota_descricao);
            cardView = itemView.findViewById(R.id.item_nota_cardView);
            itemView.setOnClickListener(v -> onClickListernet.onItemClick(nota));
        }
        public void vincula(Nota nota) {
            this.nota = nota;
            titulo.setText(nota.getTitulo());
            descricao.setText(nota.getDescricao());

        }
    }

}

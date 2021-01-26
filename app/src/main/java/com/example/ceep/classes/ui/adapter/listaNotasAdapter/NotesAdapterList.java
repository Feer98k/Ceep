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
import com.example.ceep.classes.constants.general.ColorsEnum;
import com.example.ceep.classes.database.dao.NoteDAO;
import com.example.ceep.classes.model.Note;

import java.util.Collections;
import java.util.List;

public class NotesAdapterList extends RecyclerView.Adapter<NotesAdapterList.NoteViewHolder> {

    private final Context context;
    private final List<Note> list;
    private OnClickListernet onClickListernet;
    final NoteDAO dao;


    public NotesAdapterList(Context context, List<Note> list, NoteDAO dataDao) {
        this.context = context;
        this.list = list;
        this.dao=dataDao;
    }

    public void setOnClickListerner(OnClickListernet onClickListernet) {
        this.onClickListernet = onClickListernet;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflater = LayoutInflater
                .from(context)
                .inflate(R.layout.item_nota, parent, false);
        return new NoteViewHolder(inflater);
    }

    @Override
    public synchronized void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note noteBind = list.get(position);
        ColorsEnum defaultColorNote = noteBind.getDefaultColor();
        setColorByHolder(holder, defaultColorNote);
        holder.vincule(noteBind);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    private void setColorByHolder(NoteViewHolder holder, ColorsEnum colorDefault) {
        switch (colorDefault) {
            case WHITE:
                holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.BRANCO));

                break;
            case BLUE:
                holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.AZUL));
                break;
            case RED:
                holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.VERMELHO));
                break;
            case YELLOW:
                holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.AMARELO));
                break;

            case GREEN:
                holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.VERDE));
                break;

            case LILAC:
                holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.LILÁS));
                break;
            case GRAY:
                holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.CINZA));
                break;
            case BROWN:
                holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.MARROM));
                break;
            case PURPLE:
                holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.ROXO));
                break;
        }
    }


    public void remove(int bindingAdapterPosition) {
        Note note = list.get(bindingAdapterPosition);
        for (Note a: list){
            if(a.getPosition()> note.getPosition()){
                a.setPosition(a.getPosition()-1);
                dao.update(a);
            }
        }
        notifyItemRemoved(bindingAdapterPosition);
        list.remove(bindingAdapterPosition);
    }

    public void swap(int first, int targeter) {
        Note primeiro = list.get(first);
        Note segundo = list.get(targeter);
        long rec = primeiro.getPosition();
        primeiro.setPosition(segundo.getPosition());
        segundo.setPosition(rec);
        Collections.swap(list, first, targeter);
        dao.update(primeiro);
        dao.update(segundo);
        notifyItemMoved(first, targeter);
    }

    class NoteViewHolder extends RecyclerView.ViewHolder {

        private Note note;
        final TextView titulo;
        final TextView descricao;
        final CardView cardView;
        public NoteViewHolder(View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.item_nota_titulo);
            descricao = itemView.findViewById(R.id.item_nota_descricao);
            cardView = itemView.findViewById(R.id.item_nota_cardView);
            itemView.setOnClickListener(v -> onClickListernet.onItemClick(note));
        }
        public void vincule(Note note) {
            this.note = note;
            titulo.setText(note.getTitle());
            descricao.setText(note.getDescription());

        }
    }

}

package com.example.ceep.classes.adapter.listaNotasAdapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ceep.classes.database.dao.NotaDataDao;
import com.example.ceep.classes.database.entites.Nota;

import java.util.List;

public class NotaItemTouchHelper extends ItemTouchHelper.Callback {
    final ListaNotasAdapter adapter;
    final List<Nota> lista;
    final NotaDataDao dao;

    public NotaItemTouchHelper(ListaNotasAdapter adapter, List<Nota> listaNotas, NotaDataDao dao) {
        this.adapter = adapter;
        this.lista = listaNotas;
        this.dao = dao;
    }


    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int moviments = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        int movimentDraf = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.DOWN | ItemTouchHelper.UP;
        return makeMovementFlags(movimentDraf, moviments);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        int first = viewHolder.getBindingAdapterPosition();
        int targeter = target.getBindingAdapterPosition();
        adapter.troca(first, targeter);
        return true;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        int posicao = viewHolder.getBindingAdapterPosition();
        Nota notaMovida = lista.get(posicao);
        dao.remove(notaMovida);
        adapter.remover(posicao);

    }
}

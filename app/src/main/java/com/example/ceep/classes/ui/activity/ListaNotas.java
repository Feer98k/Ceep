package com.example.ceep.classes.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.ceep.R;
import com.example.ceep.classes.ui.adapter.listaNotasAdapter.ListaNotasAdapter;
import com.example.ceep.classes.ui.adapter.listaNotasAdapter.NotaItemTouchHelper;
import com.example.ceep.classes.database.dao.NotaDataDao;
import com.example.ceep.classes.database.Database;
import com.example.ceep.classes.model.Nota;

import java.util.List;

import static com.example.ceep.classes.constantes.general.ConstantesGerais.NOTAS;
import static com.example.ceep.classes.constantes.general.ConstantesGerais.NOTA_INTENT;
import static com.example.ceep.classes.constantes.sharedPreference.layoutPreference.GRID;
import static com.example.ceep.classes.constantes.sharedPreference.layoutPreference.LINEAR;
import static com.example.ceep.classes.constantes.sharedPreference.layoutPreference.USER_PREFERENCES;

public class ListaNotas extends AppCompatActivity {


    private RecyclerView listaReyclerView;
    private ListaNotasAdapter adapter;
    private NotaDataDao notaDAO;
    private LinearLayoutManager linearLayoutManager;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    SharedPreferences preferences;
    List<Nota> listaNotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_lista_notas);
        super.onCreate(savedInstanceState);
        configurarLista();
        iniciaLayoutManager();
        verificaPreferenciaLayout();
        onItemNotaClick();
        botaoFormulario();
    }


    @Override
    protected void onResume() {
        atualizaLista();
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menur = getMenuInflater();
        menur.inflate(R.menu.menu_lista, menu);
        menur.inflate(R.menu.menu_feedback, menu);
        return true;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        MenuItem linear = menu.findItem(R.id.menu_linear);
        MenuItem grid = menu.findItem(R.id.menu_grid);
        MenuItem help = menu.findItem(R.id.menu_feedback_help);

        logicaVisibilidadesBotaoMenu(linear, grid);

        linearItemMenuClick(linear, grid);
        gridItemMenuClick(linear, grid);
        helpItemMenuClick(help);

        return true;
    }

    private void logicaVisibilidadesBotaoMenu(MenuItem linear, MenuItem grid) {
        if (preferences.getBoolean(LINEAR, true)) {
            linear.setVisible(false);
            grid.setVisible(true);
        } else if (preferences.getBoolean(GRID, true)) {
            linear.setVisible(true);
            grid.setVisible(false);
        }
    }

    private void linearItemMenuClick(MenuItem linear, MenuItem grid) {
        linear.setOnMenuItemClickListener(item -> {
            criarLinear(preferences);
            setToLinear();
            linear.setVisible(false);
            grid.setVisible(true);
            return false;
        });
    }

    private void gridItemMenuClick(MenuItem linear, MenuItem grid) {
        grid.setOnMenuItemClickListener(item -> {
            criarGrid(preferences);
            setToGrid();
            linear.setVisible(true);
            grid.setVisible(false);
            return false;
        });
    }

    private void helpItemMenuClick(MenuItem help) {
        help.setOnMenuItemClickListener(item -> {
            intentFormularioHelper();
            return true;
        });
    }


    private void iniciaLayoutManager() {
        linearLayoutManager =
                new LinearLayoutManager(this);
        staggeredGridLayoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
    }

    private synchronized void configurarLista() {
        notaDAO = Database.getInstance(this).getNotaDataDao();
        listaNotas = notaDAO.todos();
        listaReyclerView = findViewById(R.id.lista_notas_recyclerview);
        adapter = new ListaNotasAdapter(this, listaNotas, notaDAO);
        listaReyclerView.setAdapter(adapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new NotaItemTouchHelper(adapter, listaNotas, notaDAO));
        itemTouchHelper.attachToRecyclerView(listaReyclerView);
        setTitle(NOTAS);

    }


    private void botaoFormulario() {
        TextView novaNota = findViewById(R.id.lista_notas_insere_nota);
        novaNota.setOnClickListener(v -> intentFormularioNotas());
    }

    private void intentFormularioNotas() {
        Intent intent = new Intent(getApplicationContext(), FormularioDeNotas.class);
        startActivity(intent);

    }

    private void intentFormularioHelper() {
        Intent intent = new Intent(getApplicationContext(), FormularioFeedback.class);
        startActivity(intent);

    }

    private void onItemNotaClick() {
        adapter.setOnClickListerner(nota -> {
            atualizaLista();
            Intent intent = (new Intent(getApplicationContext(), FormularioDeNotas.class));
            intent.putExtra(NOTA_INTENT, nota);
            startActivity(intent);
        });
    }

    private void criarLinear(SharedPreferences preferences) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(LINEAR, true);
        editor.putBoolean(GRID, false);
        editor.apply();
    }

    private void criarGrid(SharedPreferences preferences) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(LINEAR, false);
        editor.putBoolean(GRID, true);

        editor.apply();
    }

    public void setToLinear() {
        listaReyclerView.setLayoutManager(linearLayoutManager);
    }

    public void setToGrid() {
        listaReyclerView.setLayoutManager(staggeredGridLayoutManager);
    }

    private void verificaPreferenciaLayout() {
        preferences = getSharedPreferences(USER_PREFERENCES, MODE_PRIVATE);
        boolean preferenciaLinear = preferences.getBoolean(LINEAR, true);
        if (preferenciaLinear) {
            setToLinear();
        } else {
            setToGrid();
        }
    }

    private void atualizaLista() {
        listaNotas.clear();
        listaNotas.addAll(notaDAO.todos());
        adapter.notifyDataSetChanged();
    }

}
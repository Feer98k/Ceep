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
import com.example.ceep.classes.database.dao.NoteDAO;
import com.example.ceep.classes.ui.adapter.listaNotasAdapter.NotesAdapterList;
import com.example.ceep.classes.ui.adapter.listaNotasAdapter.NotaItemTouchHelper;
import com.example.ceep.classes.database.Database;
import com.example.ceep.classes.model.Note;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import static com.example.ceep.classes.constants.general.GeneralConstants.NOTE;
import static com.example.ceep.classes.constants.general.GeneralConstants.NEW_INTENT;
import static com.example.ceep.classes.constants.sharedPreference.LayoutPreference.GRID;
import static com.example.ceep.classes.constants.sharedPreference.LayoutPreference.LINEAR;
import static com.example.ceep.classes.constants.sharedPreference.LayoutPreference.USER_PREFERENCES;

public class NotesList extends AppCompatActivity {


    private RecyclerView listaReyclerView;
    private NotesAdapterList adapterNoteList;
    private NoteDAO noteDao;
    private LinearLayoutManager linearLayoutManager;
    private StaggeredGridLayoutManager staggeredGridLayoutManager;
    SharedPreferences preferences;
    List<Note> listaNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_lista_notas);
        super.onCreate(savedInstanceState);
        setList();
        startLayoutManagerSet();
        checkPreferenceLayout();
        onItemNoteClick();
        btnForm();
    }


    @Override
    protected void onResume() {
        refreshList();
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

        visibilityLogicMenuItem(linear, grid);

        linearItemMenuClick(linear, grid);
        gridItemMenuClick(linear, grid);
        helpItemMenuClick(help);

        return true;
    }

    private void visibilityLogicMenuItem(MenuItem linear, MenuItem grid) {
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
            createLinear(preferences);
            setToLinear();
            linear.setVisible(false);
            grid.setVisible(true);
            return false;
        });
    }

    private void gridItemMenuClick(MenuItem linear, MenuItem grid) {
        grid.setOnMenuItemClickListener(item -> {
            createGrid(preferences);
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


    private void startLayoutManagerSet() {
        linearLayoutManager =
                new LinearLayoutManager(this);
        staggeredGridLayoutManager =
                new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
    }

    private synchronized void setList() {
        noteDao = Database.getInstance(this).getNotaDataDao();
        listaNotes = noteDao.allNote();
        listaReyclerView = findViewById(R.id.lista_notas_recyclerview);
        adapterNoteList = new NotesAdapterList(this, listaNotes, noteDao);
        listaReyclerView.setAdapter(adapterNoteList);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new NotaItemTouchHelper(adapterNoteList, listaNotes, noteDao));
        itemTouchHelper.attachToRecyclerView(listaReyclerView);
        setTitle(NOTE);

    }


    private void btnForm() {
       TextView novaNota = findViewById(R.id.lista_notas_insere_nota);
        novaNota.setOnClickListener(v -> intentFormNote());
    }

    private void intentFormNote() {
        Intent intent = new Intent(getApplicationContext(), NotesForm.class);
        startActivity(intent);

    }

    private void intentFormularioHelper() {
        Intent intent = new Intent(getApplicationContext(), FeedbackForm.class);
        startActivity(intent);

    }

    private void onItemNoteClick() {
        adapterNoteList.setOnClickListerner(nota -> {
            refreshList();
            Intent intent = (new Intent(getApplicationContext(), NotesForm.class));
            intent.putExtra(NEW_INTENT, nota);
            startActivity(intent);
        });
    }

    private void createLinear(SharedPreferences preferences) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(LINEAR, true);
        editor.putBoolean(GRID, false);
        editor.apply();
    }

    private void createGrid(SharedPreferences preferences) {
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

    private void checkPreferenceLayout() {
        preferences = getSharedPreferences(USER_PREFERENCES, MODE_PRIVATE);
        boolean linearPreference = preferences.getBoolean(LINEAR, true);
        if (linearPreference) {
            setToLinear();
        } else {
            setToGrid();
        }
    }

    private void refreshList() {
        listaNotes.clear();
        listaNotes.addAll(noteDao.allNote());
        adapterNoteList.notifyDataSetChanged();
    }

}
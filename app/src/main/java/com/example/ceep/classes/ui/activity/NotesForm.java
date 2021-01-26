package com.example.ceep.classes.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ceep.R;
import com.example.ceep.classes.constants.general.ColorsEnum;
import com.example.ceep.classes.database.Database;
import com.example.ceep.classes.database.dao.NoteDAO;
import com.example.ceep.classes.model.Cor;
import com.example.ceep.classes.model.ListaDeCores;
import com.example.ceep.classes.model.Note;
import com.example.ceep.classes.ui.adapter.listaCoresAdapter.ColorsAdapterForm;

import java.util.List;

import static com.example.ceep.classes.constants.general.GeneralConstants.ALTER_NOTE;
import static com.example.ceep.classes.constants.general.GeneralConstants.NEW_INTENT;
import static com.example.ceep.classes.constants.general.GeneralConstants.NEW_NOTE;

@SuppressWarnings("ALL")
public class NotesForm extends AppCompatActivity {

    private EditText titleField;
    private EditText descriptionField;

    private ConstraintLayout backgroundConstaintLayout;

    private final ListaDeCores listColors = new ListaDeCores();

    private ColorsAdapterForm colorsAdapter;

    private NoteDAO noteDao;
    private Note note = new Note();

    private List<Note> listNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_nota);
        loadingComponents();
    }

    @Override
    protected void onResume() {
        onColorItemClick();
        refreshListNote();
        super.onResume();
    }

    private void refreshListNote() {
        listNotes.clear();
        listNotes.addAll(noteDao.allNote());
    }


    public void loadingComponents() {
        noteDao = Database.getInstance(this).getNotaDataDao();
        titleField = findViewById(R.id.formulario_nota_titulo);
        descriptionField = findViewById(R.id.formulario_nota_descricao);
        RecyclerView recyclerView = findViewById(R.id.formulario_nota_recyclerView);
        backgroundConstaintLayout = findViewById(R.id.formulario_Fundo);
        List<Cor> listaCor = listColors.getAllColors();
        listNotes = noteDao.allNote();
        colorsAdapter = new ColorsAdapterForm(this, listaCor);
        recyclerView.setAdapter(colorsAdapter);
        setTitle(NEW_NOTE);
        hasIntent();
    }

    private void hasIntent() {
        Intent intent = getIntent();
        if (intent.hasExtra(NEW_INTENT)) {
            note = (Note) intent.getSerializableExtra(NEW_INTENT);
            fillFields();
            setTitle(ALTER_NOTE);
            setColorBackground(note.getDefaultColor());

        }
    }

    private void fillFields() {
        titleField.setText(note.getTitle());
        descriptionField.setText(note.getDescription());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menur = getMenuInflater();
        menur.inflate(R.menu.menu_formulario, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        createNote();
        finish();
        colorsAdapter.notifyDataSetChanged();
        return super.onOptionsItemSelected(item);
    }

    public void createNote() {
        int position;
        note.setTitle(titleField.getText().toString());
        note.setDescription(descriptionField.getText().toString());
        if (note.getId() == 0) {
            refreshListNote();
            position = listNotes.size();
            note.setPosition(position);
            Long idNote = noteDao.insert(note);

        } else if (note.getId() != 0) {
            noteDao.update(note);
        }

    }

    private void onColorItemClick() {
        colorsAdapter.setOnClickListerner(cor -> {
            ColorsEnum corClicada = cor.getCor();
            setColorBackground(corClicada);
        });
    }

    private void setColorBackground(ColorsEnum colorDefault) {
        switch (colorDefault) {
            case WHITE:
                backgroundConstaintLayout.setBackgroundColor(getResources().getColor(R.color.BRANCO));
                note.setDefaultColor(ColorsEnum.WHITE);
                break;
            case BLUE:
                backgroundConstaintLayout.setBackgroundColor(getResources().getColor(R.color.AZUL));
                note.setDefaultColor(ColorsEnum.BLUE);
                break;
            case RED:
                backgroundConstaintLayout.setBackgroundColor(getResources().getColor(R.color.VERMELHO));
                note.setDefaultColor(ColorsEnum.RED);
                break;
            case YELLOW:
                backgroundConstaintLayout.setBackgroundColor(getResources().getColor(R.color.AMARELO));
                note.setDefaultColor(ColorsEnum.YELLOW);
                break;

            case GREEN:
                backgroundConstaintLayout.setBackgroundColor(getResources().getColor(R.color.VERDE));
                note.setDefaultColor(ColorsEnum.GREEN);
                break;

            case LILAC:
                backgroundConstaintLayout.setBackgroundColor(getResources().getColor(R.color.LILÁS));
                note.setDefaultColor(ColorsEnum.LILAC);
                break;
            case GRAY:
                backgroundConstaintLayout.setBackgroundColor(getResources().getColor(R.color.CINZA));
                note.setDefaultColor(ColorsEnum.GRAY);
                break;
            case BROWN:
                backgroundConstaintLayout.setBackgroundColor(getResources().getColor(R.color.MARROM));
                note.setDefaultColor(ColorsEnum.BROWN);
                break;
            case PURPLE:
                backgroundConstaintLayout.setBackgroundColor(getResources().getColor(R.color.ROXO));
                note.setDefaultColor(ColorsEnum.PURPLE);
                break;
        }


    }
}

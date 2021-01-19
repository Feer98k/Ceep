package com.example.ceep.classes.ui;

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
import com.example.ceep.classes.adapter.listaCoresAdapter.FormularioCoresAdapter;
import com.example.ceep.classes.constantes.general.coresEnum;
import com.example.ceep.classes.database.dao.NotaDataDao;
import com.example.ceep.classes.database.data.Database;
import com.example.ceep.classes.database.entites.Nota;
import com.example.ceep.classes.model.Cor;
import com.example.ceep.classes.model.ListaDeCores;

import java.util.List;

import static com.example.ceep.classes.constantes.general.ConstantesGerais.ALTERAR_NOTA;
import static com.example.ceep.classes.constantes.general.ConstantesGerais.NOTA_INTENT;
import static com.example.ceep.classes.constantes.general.ConstantesGerais.NOVA_NOTA;

@SuppressWarnings("ALL")
public class FormularioDeNotas extends AppCompatActivity {

    private EditText titulo;
    private EditText descricao;
    private ConstraintLayout fundoConstraintLayout;
    private FormularioCoresAdapter coresAdapter;
    private final ListaDeCores listaDeCores = new ListaDeCores();
    private NotaDataDao notaDAO;
    private Nota nota = new Nota();
    private List<Nota> listaNotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_nota);
        carregaComponentes();
    }

    @Override
    protected void onResume() {
        onCorItemClick();
        atualizaListaNotas();
        super.onResume();
    }

    private void atualizaListaNotas() {
        listaNotas.clear();
        listaNotas.addAll(notaDAO.todos());
    }


    public void carregaComponentes() {
        notaDAO= Database.getInstance(this).getNotaDataDao();
        titulo = findViewById(R.id.formulario_nota_titulo);
        descricao = findViewById(R.id.formulario_nota_descricao);
        RecyclerView recyclerView = findViewById(R.id.formulario_nota_recyclerView);
        fundoConstraintLayout = findViewById(R.id.formulario_Fundo);
        List<Cor> listaCor = listaDeCores.todos();
        listaNotas = notaDAO.todos();
        coresAdapter = new FormularioCoresAdapter(this, listaCor);
        recyclerView.setAdapter(coresAdapter);
        setTitle(NOVA_NOTA);
        hasIntent();
    }

    private void hasIntent() {
        Intent intent = getIntent();
        if (intent.hasExtra(NOTA_INTENT)) {
            nota = (Nota) intent.getSerializableExtra(NOTA_INTENT);
            preencheCampos();
            setTitle(ALTERAR_NOTA);
            setColorFundo(nota.getCorPadrao());

        }
    }

    private void preencheCampos() {
        titulo.setText(nota.getTitulo());
        descricao.setText(nota.getDescricao());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menur = getMenuInflater();
        menur.inflate(R.menu.menu_formulario, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = (new Intent(getApplicationContext(), ListaNotas.class));
        criaAluno();
        startActivity(intent);
        finish();
        coresAdapter.notifyDataSetChanged();
        return super.onOptionsItemSelected(item);
    }

    public void criaAluno() {
        int posicao;
        nota.setTitulo(titulo.getText().toString());
        nota.setDescricao(descricao.getText().toString());
        if (nota.getId() == 0) {
            atualizaListaNotas();
            posicao = listaNotas.size();
            nota.setPosicao(posicao);
            Long idAluno = notaDAO.insere(nota);

        } else if (nota.getId() != 0) {
            notaDAO.edita(nota);
        }

    }

    private void onCorItemClick() {
        coresAdapter.setOnClickListerner(cor -> {
            coresEnum corClicada = cor.getCor();
            setColorFundo(corClicada);
        });
    }

    private void setColorFundo(coresEnum corDefault) {
        switch (corDefault) {
            case BRANCO:
                fundoConstraintLayout.setBackgroundColor(getResources().getColor(R.color.BRANCO));
                nota.setCorPadrao(coresEnum.BRANCO);
                break;
            case AZUL:
                fundoConstraintLayout.setBackgroundColor(getResources().getColor(R.color.AZUL));
                nota.setCorPadrao(coresEnum.AZUL);
                break;
            case VERMELHO:
                fundoConstraintLayout.setBackgroundColor(getResources().getColor(R.color.VERMELHO));
                nota.setCorPadrao(coresEnum.VERMELHO);
                break;
            case AMARELO:
                fundoConstraintLayout.setBackgroundColor(getResources().getColor(R.color.AMARELO));
                nota.setCorPadrao(coresEnum.AMARELO);
                break;

            case VERDE:
                fundoConstraintLayout.setBackgroundColor(getResources().getColor(R.color.VERDE));
                nota.setCorPadrao(coresEnum.VERDE);
                break;

            case LILAS:
                fundoConstraintLayout.setBackgroundColor(getResources().getColor(R.color.LILÁS));
                nota.setCorPadrao(coresEnum.LILAS);
                break;
            case CINZA:
                fundoConstraintLayout.setBackgroundColor(getResources().getColor(R.color.CINZA));
                nota.setCorPadrao(coresEnum.CINZA);
                break;
            case MARROM:
                fundoConstraintLayout.setBackgroundColor(getResources().getColor(R.color.MARROM));
                nota.setCorPadrao(coresEnum.MARROM);
                break;
            case ROXO:
                fundoConstraintLayout.setBackgroundColor(getResources().getColor(R.color.ROXO));
                nota.setCorPadrao(coresEnum.ROXO);
                break;
        }
    }
}

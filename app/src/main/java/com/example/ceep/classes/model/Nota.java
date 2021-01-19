package com.example.ceep.classes.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.ceep.classes.constantes.general.coresEnum;

import java.io.Serializable;
@Entity
public class Nota implements Serializable {

    private String titulo;
    private String descricao;
    private long posicao;
    private coresEnum corPadrao=coresEnum.BRANCO;
    @PrimaryKey(autoGenerate = true)
    private int id;



    public long getPosicao() {
        return posicao;
    }

    public void setPosicao(long posicao) {
        this.posicao = posicao;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getId() {
        return id;
    }


    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setId(int id) {
        this.id = id;
    }

    public coresEnum getCorPadrao() {
        return corPadrao;
    }

    public void setCorPadrao(coresEnum corPadrao) {
        this.corPadrao = corPadrao;
    }
}
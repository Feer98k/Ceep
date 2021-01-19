package com.example.ceep.classes.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.ceep.classes.model.Nota;

import java.util.List;

@Dao
public interface NotaDataDao {

    @Insert()
    Long insere(Nota nota);

    @Delete()
    void remove(Nota nota);

    @Update()
     void edita(Nota nota);


    @Query("SELECT * FROM Nota  ORDER BY posicao DESC")
    List<Nota> todos();


}

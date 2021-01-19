package com.example.ceep.classes.database;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.ceep.classes.database.conveter.coresEnumConverter;
import com.example.ceep.classes.database.dao.NotaDataDao;
import com.example.ceep.classes.model.Nota;

import static com.example.ceep.classes.constantes.general.ConstantesGerais.CEEP_DB;
import static com.example.ceep.classes.database.migrations.migrations.MIGRATION;

@androidx.room.Database(entities = {Nota.class},version = 2,exportSchema = false)
@TypeConverters({coresEnumConverter.class})
public abstract class Database extends RoomDatabase {


    public abstract NotaDataDao getNotaDataDao();

    public static  Database getInstance(Context context){
        return Room.databaseBuilder(context,Database.class, CEEP_DB)
                .allowMainThreadQueries()
                .addMigrations(MIGRATION)
                .build();
    }
}

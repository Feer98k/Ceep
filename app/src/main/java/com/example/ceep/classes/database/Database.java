package com.example.ceep.classes.database;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.ceep.classes.database.conveter.ColorsEnumConverter;
import com.example.ceep.classes.database.dao.NoteDAO;
import com.example.ceep.classes.model.Note;

import static com.example.ceep.classes.constants.general.GeneralConstants.CEEP_DB;
import static com.example.ceep.classes.database.migrations.Migrations.MIGRATION;

@androidx.room.Database(entities = {Note.class},version = 3,exportSchema = false)
@TypeConverters({ColorsEnumConverter.class})
public abstract class Database extends RoomDatabase {


    public abstract NoteDAO getNotaDataDao();

    public static  Database getInstance(Context context){
        return Room.databaseBuilder(context,Database.class, CEEP_DB)
                .allowMainThreadQueries()
                .addMigrations(MIGRATION)
                .build();
    }
}

package com.example.ceep.classes.database.migrations;

import androidx.annotation.NonNull;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

public class Migrations {
    public static final Migration [] MIGRATION  = {new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE IF NOT EXISTS `Nota_nova` (" +
                    "`titulo` TEXT, " +
                    "`descricao` TEXT, " +
                    "`posicao` INTEGER NOT NULL," +
                    " `corPadrao` TEXT, " +
                    "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL)");
            database.execSQL("DROP TABLE Nota");
            database.execSQL("ALTER TABLE Nota_nova RENAME TO Nota");
        }
    },new Migration(2,3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE IF NOT EXISTS `Note` (" +
                    "`title` TEXT, " +
                    "`description` TEXT, " +
                    "`position` INTEGER NOT NULL, " +
                    "`defaultColor` TEXT, " +
                    "`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL)");

            database.execSQL("DROP TABLE Nota");

        }
    }};

}

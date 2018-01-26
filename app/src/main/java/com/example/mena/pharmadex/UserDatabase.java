package com.example.mena.pharmadex;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;

/**
 * Created by Mena Shafik
 */

@Database(entities = {User.class}, version = 3)
public abstract class UserDatabase extends RoomDatabase {

    private static UserDatabase INSTANCE;

    public abstract UserDao userDaoAccess();

/*
    static final Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            // Since we didn't alter the table, there's nothing else to do here.
        }
    };


    database = Room.databaseBuilder(context.getApplicationContext(),
    UserDatabase.class, "Sample.db")
            .addMigrations(MIGRATION_1_2)
        .build();
    */
}

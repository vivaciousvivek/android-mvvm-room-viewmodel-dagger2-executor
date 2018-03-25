package com.techfirebase.android.mvvmdemodagger2executor.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.techfirebase.android.mvvmdemodagger2executor.data.domain.entity.Word;
import com.techfirebase.android.mvvmdemodagger2executor.data.local.db.dao.WordDao;

/** Created by DUKE SINGH on 24-03-2018. */
@Database(
  entities = {Word.class},
  version = 1
)
public abstract class AppRoomDatabase extends RoomDatabase {
  public abstract WordDao wordDao();

  /*
   * To make the singleton object of this class we can also use Dagger2 Api
   */
  private static AppRoomDatabase INSTANCE;

  public static synchronized AppRoomDatabase getDatabase(final Context context) {
    if (INSTANCE == null) {
      if (INSTANCE == null)
        INSTANCE =
            Room.databaseBuilder(
                    context.getApplicationContext(), AppRoomDatabase.class, "word_database")
                .build();
    }
    return INSTANCE;
  }
}

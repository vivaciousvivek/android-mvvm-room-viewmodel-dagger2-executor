package com.techfirebase.android.mvvmdemodagger2executor.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.techfirebase.android.mvvmdemodagger2executor.data.domain.entity.Word;
import com.techfirebase.android.mvvmdemodagger2executor.data.local.db.dao.WordDao;

/** Created by VIVEK KUMAR SINGH on 3/24/2018. */
@Database(
  entities = {Word.class},
  version = 1
)
public abstract class AppRoomDatabase extends RoomDatabase {
  public abstract WordDao wordDao();
}

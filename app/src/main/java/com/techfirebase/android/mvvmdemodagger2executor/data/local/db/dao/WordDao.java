package com.techfirebase.android.mvvmdemodagger2executor.data.local.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.techfirebase.android.mvvmdemodagger2executor.data.domain.entity.Word;

import java.util.List;

/** Created by VIVEK KUMAR SINGH on 3/24/2018. */
@Dao
public interface WordDao {
  //  @Insert(onConflict = OnConflictStrategy.REPLACE)
  @Insert
  void insert(Word word);

  @Query("DELETE from Word")
  void deleteAll();

  @Query("SELECT * from Word ORDER BY word ASC")
  LiveData<List<Word>> getAllWords();
}

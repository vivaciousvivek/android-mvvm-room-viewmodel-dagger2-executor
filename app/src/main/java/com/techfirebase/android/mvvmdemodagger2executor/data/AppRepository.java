package com.techfirebase.android.mvvmdemodagger2executor.data;

import android.arch.lifecycle.LiveData;

import com.techfirebase.android.mvvmdemodagger2executor.data.domain.entity.Word;

import java.util.List;

/** Created by VIVEK KUMAR SINGH on 3/27/2018. */
public interface AppRepository {
  LiveData<List<Word>> getAllWords();

  void insert(Word word);
}

package com.techfirebase.android.mvvmdemodagger2executor.data;

import android.arch.lifecycle.LiveData;

import com.techfirebase.android.mvvmdemodagger2executor.data.domain.api.Resource;
import com.techfirebase.android.mvvmdemodagger2executor.data.domain.entity.Word;

import java.util.List;

/** Created by VIVEK KUMAR SINGH on 3/27/2018. */
public interface AppRepository {
  public LiveData<Resource<List<Word>>> loadWords();

//  LiveData<List<Word>> getAllWords();

  void insert(Word word);
}

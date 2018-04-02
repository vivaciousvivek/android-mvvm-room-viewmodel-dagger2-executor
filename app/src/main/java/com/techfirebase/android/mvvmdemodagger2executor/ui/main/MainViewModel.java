package com.techfirebase.android.mvvmdemodagger2executor.ui.main;

import android.arch.lifecycle.LiveData;

import com.techfirebase.android.mvvmdemodagger2executor.data.AppRepository;
import com.techfirebase.android.mvvmdemodagger2executor.data.domain.entity.Word;
import com.techfirebase.android.mvvmdemodagger2executor.ui.BaseViewModel;
import com.techfirebase.android.mvvmdemodagger2executor.utils.rx.SchedulerProvider;

import java.util.List;

/**
 * Created by ﻿VIVEK KUMAR SINGH on 3/27/2018.
 *
 * <p>Class is Intentionally blank as we don't have any data to show on its corresponding activity
 */
public class MainViewModel extends BaseViewModel<MainNavigator> {

  AppRepository repository;

  public MainViewModel(AppRepository appRepository, SchedulerProvider schedulerProvider) {
    super(appRepository, schedulerProvider);
    this.repository = appRepository;
    //    getAllWords();
  }

  public LiveData<List<Word>> getAllWords() {
    return repository.getAllWords();
  }

  public void insert(Word word) {
    repository.insert(word);
  }

  //  public void getAllWords() {
  //    getCompositeDisposable().add(getAppRepository().getAllWords())
  //  }
  //
  //  public void insert(Word word) {
  //    repository.insert(word);
  //  }
}

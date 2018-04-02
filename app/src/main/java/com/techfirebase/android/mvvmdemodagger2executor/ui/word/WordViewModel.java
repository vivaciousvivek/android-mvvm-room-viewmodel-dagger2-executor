package com.techfirebase.android.mvvmdemodagger2executor.ui.word;

import android.arch.lifecycle.LiveData;

import com.techfirebase.android.mvvmdemodagger2executor.data.AppRepository;
import com.techfirebase.android.mvvmdemodagger2executor.data.domain.entity.Word;
import com.techfirebase.android.mvvmdemodagger2executor.ui.BaseViewModel;
import com.techfirebase.android.mvvmdemodagger2executor.utils.rx.SchedulerProvider;

import java.util.List;

/** Created by VIVEK KUMAR SINGH on 3/27/2018. */
public class WordViewModel extends BaseViewModel<WordNavigator> {
  //      @Inject
  AppRepository repository;

  //    public WordViewModel(@NonNull Application application) {
  //        super(application);
  //    }

  public WordViewModel(AppRepository appRepository, SchedulerProvider schedulerProvider) {
    super(appRepository, schedulerProvider);
    this.repository = appRepository;
  }

  public LiveData<List<Word>> getAllWords() {
    return repository.getAllWords();
  }

  public void insert(Word word) {
    repository.insert(word);
  }

  //  public LiveData<List<Word>> getAllWords() {
  //    return getCompositeDisposable().add()
  //  }
  //
  //  public void insert(Word word) {
  //    repository.insert(word);
  //  }

  public void openMainActivity() {
    getNavigator().openMainActivity();
  }
}

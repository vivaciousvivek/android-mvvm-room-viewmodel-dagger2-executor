package com.techfirebase.android.mvvmdemodagger2executor.ui.word;

import com.techfirebase.android.mvvmdemodagger2executor.data.AppRepository;
import com.techfirebase.android.mvvmdemodagger2executor.ui.BaseViewModel;

/** Created by VIVEK KUMAR SINGH on 3/27/2018. */
public class WordViewModel extends BaseViewModel<WordNavigator> {
  //      @Inject
  AppRepository repository;

  /*public WordViewModel(@NonNull Application application) {
      super(application);
  }*/

  public WordViewModel(AppRepository appRepository) {
    super(appRepository);
    this.repository = appRepository;
  }

  /*public LiveData<Resource<List<Word>>> getAllWords() {
    return repository.loadWords(null);
  }

  public void insert(Word word) {
    repository.loadWords(word);
  }*/

  /*public LiveData<List<Word>> getAllWords() {
    return getCompositeDisposable().add()
  }

  public void insert(Word word) {
    repository.insert(word);
  }*/

  public void openMainActivity() {
    getNavigator().openMainActivity();
  }
}

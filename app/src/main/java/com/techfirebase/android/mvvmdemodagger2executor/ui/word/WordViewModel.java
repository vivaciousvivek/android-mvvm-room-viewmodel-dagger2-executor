package com.techfirebase.android.mvvmdemodagger2executor.ui.word;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.techfirebase.android.mvvmdemodagger2executor.data.AppRepository;
import com.techfirebase.android.mvvmdemodagger2executor.data.domain.entity.Word;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by DUKE SINGH on 24-03-2018.
 */
public class WordViewModel extends AndroidViewModel{
    @Inject
    AppRepository repository;

    public WordViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<List<Word>> getAllWords() {
        return repository.getAllWords();
    }

    public void insert(Word word) {
        repository.insert(word);
    }
}

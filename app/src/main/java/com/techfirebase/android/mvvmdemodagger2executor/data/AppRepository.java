package com.techfirebase.android.mvvmdemodagger2executor.data;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.techfirebase.android.mvvmdemodagger2executor.data.domain.entity.Word;
import com.techfirebase.android.mvvmdemodagger2executor.data.local.AppRoomDatabase;
import com.techfirebase.android.mvvmdemodagger2executor.data.local.db.dao.WordDao;

import java.util.List;

/** Created by DUKE SINGH on 24-03-2018. This class should be singleton. */
public class AppRepository {
  private AppRoomDatabase db;

  /*
   * We can also inject required dependencies by Dagger 2 here
   */
  public AppRepository(Application application) {
    db = AppRoomDatabase.getDatabase(application);
  }

  /**
   * Get all words from dao layer, Room executes all queries on a separate thread. Observed LiveData
   * will notify the observer when the data has changed.
   *
   * @return
   */
  public LiveData<List<Word>> getAllWords() {
    return db.wordDao().getAllWords();
  }

  /**
   * Room ensures that you don't do any long-running operations on the main thread, blocking the UI.
   * So we used AsyncTask to create separate thread to do this task
   *
   * @param word
   */
  public void insert(Word word) {
    new InsertAsyncTask(db.wordDao()).execute(word);
  }

  private class InsertAsyncTask extends AsyncTask<Word, Void, Void> {
    private WordDao wordDaoAsync;

    public InsertAsyncTask(WordDao wordDao) {
      wordDaoAsync = wordDao;
    }

    @Override
    protected Void doInBackground(Word... words) {
      wordDaoAsync.insert(words[0]);
      return null;
    }
  }
}

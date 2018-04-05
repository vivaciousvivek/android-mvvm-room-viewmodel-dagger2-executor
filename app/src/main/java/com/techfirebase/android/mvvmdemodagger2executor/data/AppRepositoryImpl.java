package com.techfirebase.android.mvvmdemodagger2executor.data;

import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.techfirebase.android.mvvmdemodagger2executor.data.domain.api.ApiResponse;
import com.techfirebase.android.mvvmdemodagger2executor.data.domain.api.Resource;
import com.techfirebase.android.mvvmdemodagger2executor.data.domain.entity.Word;
import com.techfirebase.android.mvvmdemodagger2executor.data.local.AppRoomDatabase;
import com.techfirebase.android.mvvmdemodagger2executor.data.local.db.dao.WordDao;
import com.techfirebase.android.mvvmdemodagger2executor.data.remote.AppRetrofitApi;
import com.techfirebase.android.mvvmdemodagger2executor.utils.executor.AppExecutor;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class AppRepositoryImpl implements AppRepository {
  private final AppRoomDatabase db;
  private final AppRetrofitApi api;
  private final AppExecutor appExecutor;

  @Inject
  public AppRepositoryImpl(AppRoomDatabase db, AppRetrofitApi api, AppExecutor appExecutor) {
    this.db = db;
    this.api = api;
    this.appExecutor = appExecutor;
  }

  @Override
  public LiveData<Resource<List<Word>>> loadWords() {
    return new NetworkBoundResource<List<Word>, List<Word>>(appExecutor) {
      /*
       * Implement abstract methods of this class
       */
      @Override
      protected void saveCallResult(@NonNull List<Word> item) {
        db.wordDao().insertAll(item);
      }

      @Override
      protected boolean shouldFetch(@Nullable List<Word> data) {
//        return data == null || data.isEmpty();
        return true;
      }

      @NonNull
      @Override
      protected LiveData<List<Word>> loadFromDb() {
        return db.wordDao().getAllWords();
      }

      @NonNull
      @Override
      protected LiveData<ApiResponse<List<Word>>> createCall() {
        return api.getAllWords();
      }

      @Override
      protected void onFetchFailed() {
        super.onFetchFailed();
        // need to implement
      }
    }.getAsLiveData();
  }

  /**
   * Get all words from dao layer, Room executes all queries on a separate thread. Observed LiveData
   * will notify the observer when the data has changed.
   *
   * @return
   */
  /*@Override
  public LiveData<List<Word>> getAllWords() {
    return db.wordDao().getAllWords();
  }*/

  /**
   * Room ensures that you don't do any long-running operations on the main thread, blocking the UI.
   * So we used AsyncTask to create separate thread to do this task
   *
   * @param word
   */
  @Override
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

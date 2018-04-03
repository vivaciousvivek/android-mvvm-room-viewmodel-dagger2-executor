package com.techfirebase.android.mvvmdemodagger2executor.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;

import com.techfirebase.android.mvvmdemodagger2executor.data.domain.api.ApiResponse;
import com.techfirebase.android.mvvmdemodagger2executor.data.domain.api.Resource;
import com.techfirebase.android.mvvmdemodagger2executor.utils.executor.AppExecutor;

/**
 * Created by VIVEK KUMAR SINGH on 4/2/2018.
 *
 * <p>Generic helper class that can provide a resource backed by both the sqlite database and the
 * network.
 *
 * <p>It starts by observing database for the resource. When the entry is loaded from the database
 * for the first time, NetworkBoundResource checks whether the result is good enough to be
 * dispatched and/or it should be fetched from network. Note that both of these can happen at the
 * same time since you probably want to show the cached data while updating it from the network.
 *
 * <p>If the network call completes successfully, it saves the response into the database and
 * re-initializes the stream. If network request fails, we dispatch a failure directly.
 *
 * @param <ResultType>: Type for the Resource data
 * @param <RequestType>: Type for the API response
 */
public abstract class NetworkBoundResource<ResultType, RequestType> {
  private final AppExecutor appExecutor;
  private final MediatorLiveData<Resource<ResultType>> result = new MediatorLiveData<>();

  @MainThread
  public NetworkBoundResource(AppExecutor appExecutor) {
    this.appExecutor = appExecutor;

    /*
     * This method must be called from the main thread. If you need set a value from a background
     * thread, you can use postValue(Object)
     */
    result.setValue(Resource.loading(null));

    LiveData<ResultType> dbSource = loadFromDb();
    result.addSource(
        dbSource,
        data -> {
          result.removeSource(dbSource);

          if (shouldFetch(data)) {
            fetchFromNetwork(dbSource);
          } else {
            result.addSource(dbSource, newData -> setValue(Resource.success(newData)));
          }
        });
  }

  /**
   * Fetch from network if data is not available in cache(sqllite)
   *
   * @param dbSource
   */
  private void fetchFromNetwork(final LiveData<ResultType> dbSource) {
    // TODO: 4/2/2018 need to check how to do extract data from list of words(response)
    LiveData<ApiResponse<RequestType>> apiResponse = createCall();
    // we re-attach dbSource as a new source, it will dispatch its latest value quickly
    result.addSource(dbSource, newData -> setValue(Resource.loading(newData)));
    result.addSource(
        apiResponse,
        response -> {
          result.removeSource(apiResponse);
          result.removeSource(dbSource);

          if (response.isSuccessful()) {
            appExecutor.getDiskIO().execute(() -> saveCallResult(processResponse(response)));
            appExecutor
                .getMainThread()
                .execute(
                    () ->
                        /**
                         * we specially request a new live data, otherwise we'll get immediately
                         * last cached value, which may not be updated with latest results received
                         * from network.
                         */
                        result.addSource(
                            loadFromDb(), newData -> setValue(Resource.success(newData))));
          } else {
            onFetchFailed();
            result.addSource(
                dbSource, newData -> setValue(Resource.error(newData, response.errorMessage)));
          }
        });
  }

  @MainThread
  private void setValue(Resource<ResultType> newValue) {
    //    if (!Objects.equals(result.getValue(), newValue))
    if (result.getValue() != null && newValue != null && !result.getValue().equals(newValue))
      result.setValue(newValue);
  }

  @WorkerThread
  private RequestType processResponse(ApiResponse<RequestType> response) {
    return response.body;
  }

  /**
   * returns a LiveData that represents the resource, implemented in the base class.
   *
   * @return
   */
  public final LiveData<Resource<ResultType>> getAsLiveData() {
    return result;
  }

  /**
   * Called to save the result of the API response into the database
   *
   * @param item
   */
  @WorkerThread
  protected abstract void saveCallResult(@NonNull RequestType item);

  /**
   * Called with the data in the database to decide whether it should be fetched from the network.
   *
   * @param data
   * @return
   */
  @MainThread
  protected abstract boolean shouldFetch(@Nullable ResultType data);

  /**
   * Called to get the cached data from the database
   *
   * @return
   */
  @NonNull
  @MainThread
  protected abstract LiveData<ResultType> loadFromDb();

  /**
   * Called to create the API call.
   *
   * @return
   */
  @NonNull
  @MainThread
  protected abstract LiveData<ApiResponse<RequestType>> createCall();

  /**
   * Called when the fetch fails. The child class may want to reset components like rate limiter.
   */
  @MainThread
  protected void onFetchFailed() {}
}

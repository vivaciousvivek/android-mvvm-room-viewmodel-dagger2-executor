package com.techfirebase.android.mvvmdemodagger2executor.utils.executor;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by VIVEK KUMAR SINGH on 4/2/2018.
 *
 * <p>Global executor pools for the whole application to best use of threads for their specific use
 */
@Singleton
public class AppExecutor {
  private final Executor diskIO;
  private final Executor networkIO;
  private final Executor mainThread;

  public AppExecutor(Executor diskIO, Executor networkIO, Executor mainThread) {
    this.diskIO = diskIO;
    this.networkIO = networkIO;
    this.mainThread = mainThread;
  }

  @Inject
  public AppExecutor() {
    this(
        Executors.newCachedThreadPool(), Executors.newFixedThreadPool(3), new MainThreadExecutor());
  }

  public Executor getDiskIO() {
    return diskIO;
  }

  public Executor getNetworkIO() {
    return networkIO;
  }

  public Executor getMainThread() {
    return mainThread;
  }

  private static class MainThreadExecutor implements Executor {
    /**
     * Use android Handler and Looper, as it is recommended by android, since android created these
     * specially in more efficient way
     *
     * <p>Looper.getMainLooper() will returns the application's main looper, which lives in the main
     * thread of the application.
     */
    private Handler mainThreadHandler = new Handler(Looper.getMainLooper());

    @Override
    public void execute(@NonNull Runnable runnable) {
      /*
       * Handler.post() method will add the Runnable to the message queue. The runnable will be run
       * on the thread to which this handler is attached.
       */
      mainThreadHandler.post(runnable);
    }
  }
}

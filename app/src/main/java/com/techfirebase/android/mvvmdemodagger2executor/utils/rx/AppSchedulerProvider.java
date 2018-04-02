package com.techfirebase.android.mvvmdemodagger2executor.utils.rx;

import android.support.annotation.NonNull;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
/**
 * Created by VIVEK KUMAR SINGH on 3/27/2018.
 *
 * <p>Reactive Scheduler for concurrently access the required thread for specific purpose
 */
public class AppSchedulerProvider implements SchedulerProvider {
  @NonNull
  @Override
  public Scheduler computation() {
    return Schedulers.computation();
  }

  @NonNull
  @Override
  public Scheduler io() {
    return Schedulers.io();
  }

  @NonNull
  @Override
  public Scheduler ui() {
    return AndroidSchedulers.mainThread();
  }
}

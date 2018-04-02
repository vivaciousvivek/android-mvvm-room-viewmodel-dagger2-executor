package com.techfirebase.android.mvvmdemodagger2executor.utils.rx;

import android.support.annotation.NonNull;

import io.reactivex.Scheduler;

public interface SchedulerProvider {
  @NonNull
  Scheduler computation();

  @NonNull
  Scheduler io();

  @NonNull
  Scheduler ui();
}

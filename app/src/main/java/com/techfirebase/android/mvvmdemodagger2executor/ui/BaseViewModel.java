package com.techfirebase.android.mvvmdemodagger2executor.ui;

import android.arch.lifecycle.ViewModel;

import com.techfirebase.android.mvvmdemodagger2executor.data.AppRepository;

import io.reactivex.disposables.CompositeDisposable;

/** Created by VIVEK KUMAR SINGH on 3/27/2018. */
public abstract class BaseViewModel<N> extends ViewModel {

  private final AppRepository appRepository;
  private final CompositeDisposable compositeDisposable;
  //    private final ObservableBoolean isLoading = new ObservableBoolean(false);
  private N navigator;

  public BaseViewModel(AppRepository appRepository) {
    this.appRepository = appRepository;

    this.compositeDisposable = new CompositeDisposable();
  }

  @Override
  protected void onCleared() {
    // clear composite disposable before clearing view model
    compositeDisposable.dispose();
    super.onCleared();
  }

  public AppRepository getAppRepository() {
    return appRepository;
  }

  public CompositeDisposable getCompositeDisposable() {
    return compositeDisposable;
  }

  public N getNavigator() {
    return navigator;
  }

  public void setNavigator(N navigator) {
    this.navigator = navigator;
  }
}

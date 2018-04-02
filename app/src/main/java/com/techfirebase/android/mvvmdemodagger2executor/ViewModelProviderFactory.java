package com.techfirebase.android.mvvmdemodagger2executor;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;
/**
 * Created by VIVEK KUMAR SINGH on 3/27/2018.
 *
 * <p>A provider factory that persists ViewModels {@link ViewModel}. Used if the view model has a
 * parameterized constructor.
 */
public class ViewModelProviderFactory<V> implements ViewModelProvider.Factory {

  private V viewModel;

  public ViewModelProviderFactory(V viewModel) {
    this.viewModel = viewModel;
  }

  @NonNull
  @Override
  public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
    if (modelClass.isAssignableFrom(viewModel.getClass())) {
      return (T) viewModel;
    }
    throw new IllegalArgumentException("Unknown class name");
  }
}

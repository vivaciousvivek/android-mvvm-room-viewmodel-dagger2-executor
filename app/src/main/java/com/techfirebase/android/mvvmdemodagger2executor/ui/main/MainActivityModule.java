package com.techfirebase.android.mvvmdemodagger2executor.ui.main;

import android.arch.lifecycle.ViewModelProvider;

import com.techfirebase.android.mvvmdemodagger2executor.ViewModelProviderFactory;
import com.techfirebase.android.mvvmdemodagger2executor.data.AppRepository;
import com.techfirebase.android.mvvmdemodagger2executor.utils.rx.SchedulerProvider;

import dagger.Module;
import dagger.Provides;

/**
 * Created by VIVEK KUMAR SINGH on 3/27/2018.
 *
 * <p>This Module is responsible for providing the ViewModel object to the corresponding activity
 * and we can also provide fragments by ViewModelProvider.Factory, Adapter classes
 */
@Module
public class MainActivityModule {

  @Provides
  ViewModelProvider.Factory mainViewModelProvider(MainViewModel mainViewModel) {
    return new ViewModelProviderFactory<>(mainViewModel);
  }

  @Provides
  MainViewModel provideMainViewModel(
      AppRepository appRepository, SchedulerProvider schedulerProvider) {
    return new MainViewModel(appRepository, schedulerProvider);
  }
}

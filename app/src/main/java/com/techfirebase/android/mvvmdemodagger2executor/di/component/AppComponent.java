package com.techfirebase.android.mvvmdemodagger2executor.di.component;

import android.app.Application;

import com.techfirebase.android.mvvmdemodagger2executor.MvvmApp;
import com.techfirebase.android.mvvmdemodagger2executor.di.builder.ActivityBuilder;
import com.techfirebase.android.mvvmdemodagger2executor.di.module.AppModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * Created by ﻿VIVEK KUMAR SINGH on 3/27/2018.
 *
 * <p>AppComponent is responsible for injecting all modules required of our application like
 * AppModule, ActivityModule, etc.
 *
 * <p>This class also provides methods that are used to access the dependencies that exist in the
 * dependency graph.
 *
 * <p>AppComponent is an interface that is implemented by Dagger2 Using @Component
 */
@Singleton
@Component(modules = {AndroidInjectionModule.class, AppModule.class, ActivityBuilder.class})
public interface AppComponent {
  /**
   * When the dependencies are provided through field injection i.e. @inject on the member
   * variables, we have to tell the Dagger to scan this class through the implementation of this
   * interface.
   *
   * @param mvvmApp
   */
  void inject(MvvmApp mvvmApp);

  /** Dagger allows us to customize the generated builder by @Component.Builder */
  @Component.Builder
  interface Builder {
    @BindsInstance
    Builder application(Application application);

    AppComponent build();
  }
}

package com.techfirebase.android.mvvmdemodagger2executor.di.component;

import com.techfirebase.android.mvvmdemodagger2executor.di.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by viveks on 3/27/2018.
 *
 * <p>AppComponent is responsible for injecting all modules of our application like AppModule.
 *
 * <p>When the dependencies are provided through field injection i.e. @inject on the member
 * variables, we have to tell the Dagger to scan this class through the implementation of this
 * interface.
 *
 * <p>This class also provides methods that are used to access the dependencies that exist in the
 * dependency graph.
 */
@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {}

package com.techfirebase.android.mvvmdemodagger2executor.di.module;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.techfirebase.android.mvvmdemodagger2executor.data.AppRepository;
import com.techfirebase.android.mvvmdemodagger2executor.data.AppRepositoryImpl;
import com.techfirebase.android.mvvmdemodagger2executor.data.local.AppRoomDatabase;
import com.techfirebase.android.mvvmdemodagger2executor.di.DatabaseInfo;
import com.techfirebase.android.mvvmdemodagger2executor.utils.constant.AppConstants;
import com.techfirebase.android.mvvmdemodagger2executor.utils.rx.AppSchedulerProvider;
import com.techfirebase.android.mvvmdemodagger2executor.utils.rx.SchedulerProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by DUKE SINGH on 26-03-2018.
 *
 * <p>AppModule is a Dagger module responsible for providing singleton services on the application
 * level, We have to provide the dependencies expressed in the Application class. This class needs
 * some othre dependencies like DataManager, etc and to provide this class we have to provide the
 * dependencies expressed by DataManager or other dependencies, so we have to provide every
 * dependencies before using them so that Dagger can make dependency graph. We then move further in
 * the graph.
 */
@Module
public class AppModule {

  @Provides
  @Singleton
  AppRoomDatabase provideAppDatabase(@DatabaseInfo final String dbName, final Context context) {
    return Room.databaseBuilder(context, AppRoomDatabase.class, dbName)
        .fallbackToDestructiveMigration()
        .build();
  }

//  @Provides
//  @Singleton
//  AppWebService provideAppWebService() {
//    return new Retrofit
//  }

  @Provides
  @Singleton
  Context provideContext(final Application application) {
    return application;
  }

  @Provides
  @Singleton
  AppRepository provideAppRepository(final AppRepositoryImpl appRepositoryImpl) {
    return appRepositoryImpl;
  }

  @Provides
  @DatabaseInfo
  String provideDatabaseName() {
    return AppConstants.DB_NAME.toString();
  }
}

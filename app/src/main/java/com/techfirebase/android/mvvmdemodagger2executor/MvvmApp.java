package com.techfirebase.android.mvvmdemodagger2executor;

import android.app.Application;
import android.content.Context;

import com.techfirebase.android.mvvmdemodagger2executor.di.component.AppComponent;

/**
 * Created by DUKE SINGH on 3/27/2018.
 *
 * <p>DaggerApplicationComponent is the generated class by the Dagger, implementing the
 * ApplicationComponent. We provide the ApplicationModule class that is used to construct the
 * dependencies.
 *
 * <p>In our case its name will be DaggerAppComponent.
 *
 * <p>This DaggerAppComponent stub will be generated automatically by Dagger when you build your
 * project.
 *
 * <p>Add name attribute of your application <application ... android:name=".MvvmApp" ....> //
 * activities </application>
 */
public class MvvmApp extends Application {
  protected AppComponent appComponent;

  public static MvvmApp get(Context context) {
    return (MvvmApp) context.getApplicationContext();
  }

  /**
   * Application class and MainActivity class. These classes don’t have a constructor and Android
   * System is responsible for instantiating these. To get the dependency we use the OnCreate method
   * as it is called once when they are instantiated.
   *
   * <p>We have called the inject method of appComponent and passed the instance of the MvvmApp
   * class.
   */
  @Override
  public void onCreate() {
    super.onCreate();
    appComponent = DaggerAppComponent.builder().application(this).build().inject(this);
  }
}

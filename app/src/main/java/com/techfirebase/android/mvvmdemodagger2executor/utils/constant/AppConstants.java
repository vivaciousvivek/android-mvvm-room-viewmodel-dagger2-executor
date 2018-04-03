package com.techfirebase.android.mvvmdemodagger2executor.utils.constant;
/**
 * Created by VIVEK KUMAR SINGH on 3/27/2018.
 *
 * <p>Application Constants
 */
public enum AppConstants {
  DB_NAME("word_database"), BASE_URL("http://localhost:3000/");

  private final String constant;

  AppConstants(String constant) {
    this.constant = constant;
  }

  @Override
  public String toString() {
    return constant;
  }
}

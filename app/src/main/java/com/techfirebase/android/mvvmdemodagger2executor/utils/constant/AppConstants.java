package com.techfirebase.android.mvvmdemodagger2executor.utils.constant;
/**
 * Created by VIVEK KUMAR SINGH on 3/27/2018.
 *
 * <p>Application Constants
 */
public enum AppConstants {
  /**
   * BASE_URL("http://192.168.170.66:3000/") for Device BASE_URL("http://10.0.2.2:3000/") for
   * Emulator
   */
  DB_NAME("word_database"),
//    BASE_URL("http://jsonplaceholder.typicode.com/");
//  BASE_URL("http://192.168.170.66:3000/");
  BASE_URL("http://10.0.2.2:3000/");

  private final String constant;

  AppConstants(String constant) {
    this.constant = constant;
  }

  @Override
  public String toString() {
    return constant;
  }
}

package com.techfirebase.android.mvvmdemodagger2executor.ui.word;

/**
 * Created by VIVEK KUMAR SINGH on 3/27/2018.
 *
 * <p>This interface is used for Saparation of Concern of and screen navigation
 */
public interface WordNavigator {
  void handleError(Throwable throwable);

  void openMainActivity();
}

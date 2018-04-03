package com.techfirebase.android.mvvmdemodagger2executor.data.domain.api;

import android.support.annotation.Nullable;

import java.io.IOException;

import retrofit2.Response;

/**
 * Created by VIVEK KUMAR SINGH on 4/2/2018.
 *
 * <p>Common class used by API responses
 */
public class ApiResponse<T> {
  public final int code;
  @Nullable public final T body;
  @Nullable public final String errorMessage;

  public ApiResponse(Throwable throwable) {
    this.code = 500;
    this.body = null;
    this.errorMessage = throwable.getMessage();
  }

  public ApiResponse(Response<T> response) {
    code = response.code();

    if (response.isSuccessful()) {
      body = response.body();
      errorMessage = null;
    } else {
      String message = null;

      if (response.errorBody() != null) {
        try {
          message = response.errorBody().string();
        } catch (IOException e) {
          // TODO: 4/2/2018 need to use Timber
          e.printStackTrace();
        }
      }

      if (message == null || message.trim().length() == 0) {
          message = response.message();
      }

      errorMessage = message;
      body = null;
    }
  }

  public boolean isSuccessful() {
    return code >= 200 && code < 300;
  }
}

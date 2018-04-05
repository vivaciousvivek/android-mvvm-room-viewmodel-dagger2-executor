package com.techfirebase.android.mvvmdemodagger2executor.utils;

import android.arch.lifecycle.LiveData;
import android.support.annotation.Nullable;

import com.techfirebase.android.mvvmdemodagger2executor.data.domain.api.ApiResponse;
import com.techfirebase.android.mvvmdemodagger2executor.data.remote.AppRetrofitApi;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import retrofit2.CallAdapter;
import retrofit2.Retrofit;

/** Created by VIVEK KUMAR SINGH on 4/5/2018. */
public class LiveDataCallAdapterFactory extends CallAdapter.Factory {
  @Nullable
  @Override
  public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
    if (getRawType(returnType) != LiveData.class) return null;

    Type observableType = getParameterUpperBound(0, (ParameterizedType) returnType);
    Class<?> rawObservableType = getRawType(observableType);

    if (rawObservableType != ApiResponse.class)
      throw new IllegalArgumentException("resource must be parametrized");

    Type bodyType = getParameterUpperBound(0, (ParameterizedType) observableType);
    return new LiveDataCallAdapter<>(bodyType);
  }
}

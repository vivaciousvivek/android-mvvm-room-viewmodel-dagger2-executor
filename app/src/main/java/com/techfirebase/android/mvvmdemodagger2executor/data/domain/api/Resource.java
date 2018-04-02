package com.techfirebase.android.mvvmdemodagger2executor.data.domain.api;

import android.support.annotation.NonNull;

import com.techfirebase.android.mvvmdemodagger2executor.utils.constant.ResponseStatus;

/**
 * Created by VIVEK KUMAR SINGH on 4/2/2018.
 *
 * <p>Generic class that describes data with status(to expose network status with a Resource class
 * to encapsulate both the data and its state)
 */
public class Resource<T> {
  @NonNull public final ResponseStatus status;
  @NonNull public final T data;
  @NonNull public final String message;

  public Resource(ResponseStatus status, T data, String message) {
    this.status = status;
    this.data = data;
    this.message = message;
  }

  public static <T> Resource<T> success(@NonNull T data) {
    return new Resource<>(ResponseStatus.SUCCESS, data, null);
  }

  public static <T> Resource<T> error(@NonNull T data, String message) {
    return new Resource<>(ResponseStatus.ERROR, data, message);
  }

  public static <T> Resource<T> loading(@NonNull T data) {
    return new Resource<>(ResponseStatus.LOADING, data, null);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Resource<?> resource = (Resource<?>) o;

    if (status != resource.status) return false;
    if (!data.equals(resource.data)) return false;
    return message.equals(resource.message);
  }

  @Override
  public int hashCode() {
    int result = status.hashCode();
    result = 31 * result + data.hashCode();
    result = 31 * result + message.hashCode();
    return result;
  }

  @Override
  public String toString() {
    return "Resource{"
        + "status="
        + status
        + ", data="
        + data
        + ", message='"
        + message
        + '\''
        + '}';
  }
}

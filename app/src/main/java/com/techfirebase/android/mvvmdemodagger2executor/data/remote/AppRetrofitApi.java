package com.techfirebase.android.mvvmdemodagger2executor.data.remote;

import android.arch.lifecycle.LiveData;

import com.techfirebase.android.mvvmdemodagger2executor.data.domain.api.ApiResponse;
import com.techfirebase.android.mvvmdemodagger2executor.data.domain.entity.Word;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by VIVEK KUMAR SINGH on 4/2/2018.
 *
 * <p>REST API access points
 */
public interface AppRetrofitApi {
  @GET("word")
  LiveData<ApiResponse<List<Word>>> getAllWords();

  @POST("word")
  LiveData<ApiResponse<Word>> saveWord(@Body Word newWord);
}

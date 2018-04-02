package com.techfirebase.android.mvvmdemodagger2executor.data.remote;

import android.arch.lifecycle.LiveData;

import com.techfirebase.android.mvvmdemodagger2executor.data.domain.api.ApiResponse;
import com.techfirebase.android.mvvmdemodagger2executor.data.domain.entity.Word;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by VIVEK KUMAR SINGH on 4/2/2018.
 *
 * <p>REST API access points
 */
public interface AppRetrofitApi {
  @GET("word/all")
  LiveData<ApiResponse<List<Word>>> getAllWords();

  @POST("word/{newWord}")
  LiveData<ApiResponse<Word>> saveWord(@Path("newWord") String newWord);
}

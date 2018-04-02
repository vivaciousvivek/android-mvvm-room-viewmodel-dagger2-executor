package com.techfirebase.android.mvvmdemodagger2executor.data.domain.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/** Created by VIVEK KUMAR SINGH on 3/24/2018. */
@Entity
public class Word {
  @PrimaryKey @NonNull private String word;

  public Word(String word) {
    this.word = word;
  }

  public String getWord() {
    return word;
  }

  public void setWord(String word) {
    this.word = word;
  }
}

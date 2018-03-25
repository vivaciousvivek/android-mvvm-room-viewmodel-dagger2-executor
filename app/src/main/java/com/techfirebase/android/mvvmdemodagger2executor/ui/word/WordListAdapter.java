package com.techfirebase.android.mvvmdemodagger2executor.ui.word;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.techfirebase.android.mvvmdemodagger2executor.R;
import com.techfirebase.android.mvvmdemodagger2executor.data.domain.entity.Word;

import java.util.List;

/** Created by DUKE SINGH on 24-03-2018. */
public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.WordViewHolder> {

  class WordViewHolder extends RecyclerView.ViewHolder {
    private final TextView wordItemView;

    private WordViewHolder(View itemView) {
      super(itemView);
      wordItemView = itemView.findViewById(R.id.textView);
    }
  }

  private final LayoutInflater mInflater;
  private List<Word> mWords; // Cached copy of words

  public WordListAdapter(Context context) {
    mInflater = LayoutInflater.from(context);
  }

  @Override
  public WordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
    return new WordViewHolder(itemView);
  }

  @Override
  public void onBindViewHolder(WordViewHolder holder, int position) {
    Word current = mWords.get(position);
    holder.wordItemView.setText(current.getWord());
  }

  /**
   * getItemCount() is called many times, and when it is first called, mWords has not been updated
   * (means initially, it's null, and we can't return null)
   *
   * @return
   */
  @Override
  public int getItemCount() {
    if (mWords != null) return mWords.size();
    else return 0;
  }

  public void setWords(List<Word> words) {
    mWords = words;
    notifyDataSetChanged();
  }
}

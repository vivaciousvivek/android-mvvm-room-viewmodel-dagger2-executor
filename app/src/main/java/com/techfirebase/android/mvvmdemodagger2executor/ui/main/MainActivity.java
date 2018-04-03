package com.techfirebase.android.mvvmdemodagger2executor.ui.main;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.techfirebase.android.mvvmdemodagger2executor.BR;
import com.techfirebase.android.mvvmdemodagger2executor.R;
import com.techfirebase.android.mvvmdemodagger2executor.data.domain.api.Resource;
import com.techfirebase.android.mvvmdemodagger2executor.data.domain.entity.Word;
import com.techfirebase.android.mvvmdemodagger2executor.databinding.ActivityMainBinding;
import com.techfirebase.android.mvvmdemodagger2executor.ui.BaseActivity;
import com.techfirebase.android.mvvmdemodagger2executor.ui.word.WordActivity;
import com.techfirebase.android.mvvmdemodagger2executor.ui.word.WordListAdapter;

import java.util.List;

import javax.inject.Inject;

/** Created by VIVEK KUMAR SINGH on 3/27/2018. */
public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel>
    implements MainNavigator {

  public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;

  //  @Inject WordViewModel wordViewModel;
  @Inject MainViewModel mainViewModel;

  @Inject ViewModelProvider.Factory viewModelFactory;

  private ActivityMainBinding activityMainBinding;

  public static Intent newIntent(Context context) {
    return new Intent(context, MainActivity.class);
  }

  @Override
  public int getLayoutId() {
    return R.layout.activity_main;
  }

  @Override
  public int getBindingVariable() {
    return BR.viewModel;
  }

  @Override
  public MainViewModel getViewModel() {
    mainViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel.class);
    return mainViewModel;
  }

  @Override
  public void handleError(Throwable throwable) {
    // handel Error here
  }

  @Override
  public void openWordActivity() {
    startActivity(WordActivity.newIntent(MainActivity.this));
    finish();
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    activityMainBinding = getViewDataBinding();
    mainViewModel.setNavigator(this);

    Toolbar toolbar = findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);

    RecyclerView recyclerView = findViewById(R.id.recyclerview);
    final WordListAdapter adapter = new WordListAdapter(this);
    recyclerView.setAdapter(adapter);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));

    // Get a new or existing ViewModel from the ViewModelProvider.
    //    mainViewModel = ViewModelProviders.of(this).get(MainViewModel.class);

    // Add an observer on the LiveData returned by getAlphabetizedWords.
    // The onChanged() method fires when the observed data changes and the activity is
    // in the foreground.
    mainViewModel
        .getAllWords()
        .observe(
            this,
            new Observer<Resource<List<Word>>>() {
              @Override
              public void onChanged(@Nullable Resource<List<Word>> wordResource) {
                // Update the cached copy of the words in the adapter.
                adapter.setWords(wordResource == null ? null : wordResource.data);
              }
              /*@Override
              public void onChanged(@Nullable final List<Word> words) {
                // Update the cached copy of the words in the adapter.
                adapter.setWords(words);
              }*/
            });

    FloatingActionButton fab = findViewById(R.id.fab);
    fab.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            Intent intent;
            intent = new Intent(MainActivity.this, WordActivity.class);
            startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
          }
        });
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    if (id == R.id.action_settings) {
      return true;
    }
    return super.onOptionsItemSelected(item);
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);

    if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
      Word word = new Word(data.getStringExtra(WordActivity.EXTRA_REPLY));
      mainViewModel.insert(word);
    } else {
      Toast.makeText(getApplicationContext(), R.string.empty_not_saved, Toast.LENGTH_LONG).show();
    }
  }
}

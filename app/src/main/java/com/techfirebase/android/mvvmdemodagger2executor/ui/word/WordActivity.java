package com.techfirebase.android.mvvmdemodagger2executor.ui.word;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;

import com.techfirebase.android.mvvmdemodagger2executor.BR;
import com.techfirebase.android.mvvmdemodagger2executor.R;
import com.techfirebase.android.mvvmdemodagger2executor.databinding.ActivityWordBinding;
import com.techfirebase.android.mvvmdemodagger2executor.ui.BaseActivity;

import javax.inject.Inject;

public class WordActivity extends BaseActivity<ActivityWordBinding, WordViewModel>
    implements WordNavigator {
  public static final String EXTRA_REPLY = "com.techfirebase.android.wordlistsql.REPLY";
  @Inject WordViewModel wordViewModel;
  private EditText editTextWord;
  private ActivityWordBinding activityWordBinding;

  public static Intent newIntent(Context context) {
    return new Intent(context, WordActivity.class);
  }

  @Override
  public int getLayoutId() {
    return R.layout.activity_word;
  }

  @Override
  public int getBindingVariable() {
    return BR.viewModel;
  }

  /*
   * methods of WordNavigator interface, below
   *
   */

  @Override
  public WordViewModel getViewModel() {
    return wordViewModel;
  }

  @Override
  public void handleError(Throwable throwable) {
    // handle Error here
  }

  @Override
  public void openMainActivity() {
    //    startActivity(MainActivity.newIntent(WordActivity.this));
    Intent replyIntent = new Intent();
    if (TextUtils.isEmpty(activityWordBinding.editWord.getText()))
      setResult(RESULT_CANCELED, replyIntent);
    else {
      String word = activityWordBinding.editWord.getText().toString();
      replyIntent.putExtra(EXTRA_REPLY, word);
      setResult(RESULT_OK, replyIntent);
    }
    finish();
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    activityWordBinding = getViewDataBinding();
    wordViewModel.setNavigator(this);

    //    setContentView(R.layout.activity_word);
    //
    //    editTextWord = findViewById(R.id.edit_word);
    //
    //    final Button button = findViewById(R.id.button_save);
    //    button.setOnClickListener(
    //        new View.OnClickListener() {
    //          @Override
    //          public void onClick(View view) {
    //            Intent replyIntent = new Intent();
    //            if (TextUtils.isEmpty(editTextWord.getText())) setResult(RESULT_CANCELED,
    // replyIntent);
    //            else {
    //              String word = editTextWord.getText().toString();
    //              replyIntent.putExtra(EXTRA_REPLY, word);
    //              setResult(RESULT_OK, replyIntent);
    //            }
    //            finish();
    //          }
    //        });
  }
}

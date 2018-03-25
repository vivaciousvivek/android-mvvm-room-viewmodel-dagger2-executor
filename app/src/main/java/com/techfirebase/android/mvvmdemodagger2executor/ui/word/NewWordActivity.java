package com.techfirebase.android.mvvmdemodagger2executor.ui.word;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.techfirebase.android.mvvmdemodagger2executor.R;

public class NewWordActivity extends AppCompatActivity {
    public static final String EXTRA_REPLY = "com.techfirebase.android.wordlistsql.REPLY";
    private EditText editTextWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word);

        editTextWord = findViewById(R.id.edit_word);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(editTextWord.getText()))
                    setResult(RESULT_CANCELED, replyIntent);
                else {
                    String word = editTextWord.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY, word);
                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}

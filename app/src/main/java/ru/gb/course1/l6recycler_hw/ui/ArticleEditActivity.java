package ru.gb.course1.l6recycler_hw.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import ru.gb.course1.l6recycler_hw.App;
import ru.gb.course1.l6recycler_hw.R;
import ru.gb.course1.l6recycler_hw.domain.TimeLineEntity;

public class ArticleEditActivity extends AppCompatActivity {
    private EditText articleEditText;
    private Button saveButton;
    private Button backButton;

    public static final String ARTICLE_EXTRA_KEY = "ARTICLE_EXTRA_KEY";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_edit_article);

        articleEditText = findViewById(R.id.edit_text);
        TimeLineEntity timeLineEntity = getIntent().getParcelableExtra(ARTICLE_EXTRA_KEY);
        articleEditText.setText(timeLineEntity.getArticleText());

        saveButton = findViewById(R.id.save_button);
        saveButton.setOnClickListener(v -> {
            timeLineEntity.setArticleText(articleEditText.getText().toString());
            App.get(this).getArticleRepo().replaceArticle(timeLineEntity);
            String str = articleEditText.getText().toString();
            setResult(RESULT_OK);
            finish();
        });
        backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener((v -> {
            finish();
        }));
    }
}

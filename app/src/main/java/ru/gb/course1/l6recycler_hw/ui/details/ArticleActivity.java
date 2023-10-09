package ru.gb.course1.l6recycler_hw.ui.details;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import ru.gb.course1.l6recycler_hw.App;
import ru.gb.course1.l6recycler_hw.R;
import ru.gb.course1.l6recycler_hw.domain.TimeLineEntity;

public class ArticleActivity extends AppCompatActivity {
    private TextView articleTextView;
    private Button deleteButton;
    private Button backButton;

    public static final String ARTICLE_EXTRA_KEY = "ARTICLE_EXTRA_KEY";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.fragment_article_details);

        articleTextView = findViewById(R.id.fragment_article_details__text_view);
        TimeLineEntity timeLineEntity = getIntent().getParcelableExtra(ARTICLE_EXTRA_KEY);
        articleTextView.setText(timeLineEntity.getArticleText());

        deleteButton = findViewById(R.id.delete_button);
        deleteButton.setOnClickListener(v -> {
            App.get(this).getArticleRepo().deleteArticle(timeLineEntity);
            setResult(RESULT_OK);
            finish();
        });
        backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener((v -> {
            finish();
        }));
    }
}

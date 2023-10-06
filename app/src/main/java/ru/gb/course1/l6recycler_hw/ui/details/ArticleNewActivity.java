package ru.gb.course1.l6recycler_hw.ui.details;

import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.time.*;

import ru.gb.course1.l6recycler_hw.App;
import ru.gb.course1.l6recycler_hw.R;
import ru.gb.course1.l6recycler_hw.data.LocalDateTimeForTimeLine;
import ru.gb.course1.l6recycler_hw.domain.ArticleRepository;
import ru.gb.course1.l6recycler_hw.domain.TimeLineEntity;

public class ArticleNewActivity extends AppCompatActivity {
    private ArticleRepository articleRepository;
    private TextView dateTime;
    private EditText articleTitle;
    private EditText articleEditText;
    private Button saveButton;
    private Button cancelButton;

//    private LocalDateTime localDateTime = LocalDateTime.now();


    public static final String ARTICLE_EXTRA_KEY = "ARTICLE_EXTRA_KEY";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_new_article);

        dateTime = findViewById(R.id.date_time);
        dateTime.setText(new LocalDateTimeForTimeLine().LocalDateTimeForTimeLine());
        articleEditText = findViewById(R.id.edit_text);
        articleTitle = findViewById(R.id.edit_text_title);
        TimeLineEntity timeLineEntity = getIntent().getParcelableExtra(ARTICLE_EXTRA_KEY);
//        articleEditText.setText(timeLineEntity.getArticleText());

        saveButton = findViewById(R.id.save_button);
        saveButton.setOnClickListener(v -> {
            String newID = CalcUniqId();
            timeLineEntity.setArticleId(newID);
            timeLineEntity.setArticleDateTime(dateTime.getText().toString());
            timeLineEntity.setArticleTitle(articleTitle.getText().toString());
            timeLineEntity.setArticleText(articleEditText.getText().toString());
            App.get(this).getArticleRepo().addNewArticle(timeLineEntity);
            setResult(RESULT_OK);
            finish();
        });
        cancelButton = findViewById(R.id.cancel_button);
        cancelButton.setOnClickListener((v -> {
            finish();
        }));
    }

    private String CalcUniqId() {
        articleRepository = App.get(this).getArticleRepo();
        int newValueId = Integer.parseInt(articleRepository.getArticle().get(articleRepository.getArticle().size() - 1).getArticleId()) + 1;
        return String.valueOf(newValueId);
    }
}

package ru.gb.course1.l6recycler_hw.ui.details;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ru.gb.course1.l6recycler_hw.App;
import ru.gb.course1.l6recycler_hw.R;
import ru.gb.course1.l6recycler_hw.data.LocalDateTimeForTimeLine;
import ru.gb.course1.l6recycler_hw.domain.ArticleRepository;
import ru.gb.course1.l6recycler_hw.domain.TimeLineEntity;

public class ArticleNewFragment extends Fragment {
    private ArticleRepository articleRepository;
    private TextView dateTime;
    private EditText articleTitle;
    private EditText articleEditText;
    private Button saveButton;
    private Button cancelButton;
    public static final String NEW_ARTICLE_EXTRA_KEY = "NEW_ARTICLE_EXTRA_KEY";

    public interface Controller {
        void onNewArticle(TimeLineEntity timeLineEntity);
    }

    private ArticleNewFragment.Controller controller;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof ArticleEditFragment.Controller) {
            controller = (ArticleNewFragment.Controller) context;
        } else {
            throw new IllegalStateException("Activity must implement ArticleEditFragment.Controller");
        }
    }
    public static ArticleNewFragment newInstanceNewArticle(TimeLineEntity timeLineEntity) {
        ArticleNewFragment fragment = new ArticleNewFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("NEW_ARTICLE_EXTRA_KEY", timeLineEntity);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_new_article, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        dateTime = view.findViewById(R.id.new_fragment__date_time);
        dateTime.setText(new LocalDateTimeForTimeLine().LocalDateTimeForTimeLine());
        articleEditText = view.findViewById(R.id.new_fragment__edit_text);
        articleTitle = view.findViewById(R.id.new_fragment__edit_text_title);
        TimeLineEntity timeLineEntity = getArguments().getParcelable(NEW_ARTICLE_EXTRA_KEY);

        saveButton = view.findViewById(R.id.new_fragment__save_button);
        saveButton.setOnClickListener(v -> {
            String newID = CalcUniqId();
            timeLineEntity.setArticleId(newID);
            timeLineEntity.setArticleDateTime(dateTime.getText().toString());
            timeLineEntity.setArticleTitle(articleTitle.getText().toString());
            timeLineEntity.setArticleText(articleEditText.getText().toString());
            App.get().getArticleRepo().addNewArticle(timeLineEntity);
            controller.onNewArticle(timeLineEntity);
        });
        cancelButton = view.findViewById(R.id.new_fragment__cancel_button);
        cancelButton.setOnClickListener((v -> {
            controller.onNewArticle(timeLineEntity);
        }));
    }
    private String CalcUniqId() {
        articleRepository = App.get().getArticleRepo();
        int newValueId = Integer.parseInt(articleRepository.getArticle().get(articleRepository.getArticle().size() - 1).getArticleId()) + 1;
        return String.valueOf(newValueId);
    }
}

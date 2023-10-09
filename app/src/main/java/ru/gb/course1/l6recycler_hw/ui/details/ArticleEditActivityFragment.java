package ru.gb.course1.l6recycler_hw.ui.details;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ru.gb.course1.l6recycler_hw.App;
import ru.gb.course1.l6recycler_hw.R;
import ru.gb.course1.l6recycler_hw.domain.TimeLineEntity;
import ru.gb.course1.l6recycler_hw.ui.main.ArticleListFragment;

public class ArticleEditActivityFragment extends Fragment {

    private static final String EDIT_ARG_KEY = "EDIT_ARG_KEY";
    private EditText articleEditText;
    private EditText articleEditTitle;
    private Button saveButton;
    private Button backButton;
    private TimeLineEntity timeLineEntity;

    public interface Controller {
        void onEditArticle(TimeLineEntity timeLineEntity);
    }

    private Controller controller;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Controller) {
            controller = (Controller) context;
        } else {
            throw new IllegalStateException("Activity must implement ArticleEditFragment.Controller");
        }
    }

    public static ArticleEditActivityFragment newInstance(TimeLineEntity timeLineEntity) {
        ArticleEditActivityFragment fragment = new ArticleEditActivityFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("EDIT_ARG_KEY", timeLineEntity);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_edit_article, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        articleEditTitle = view.findViewById(R.id.activity_edit_article_title);
        articleEditText = view.findViewById(R.id.activity_edit_article_text);

        timeLineEntity = getArguments().getParcelable(EDIT_ARG_KEY);
        articleEditTitle.setText(timeLineEntity.getArticleTitle());
        articleEditText.setText(timeLineEntity.getArticleText());

        saveButton = view.findViewById(R.id.save_button);
        saveButton.setOnClickListener(v -> {
            timeLineEntity.setArticleText(articleEditText.getText().toString());
            timeLineEntity.setArticleTitle(articleEditTitle.getText().toString());
            App.get(getContext()).getArticleRepo().replaceArticle(timeLineEntity);
            controller.onEditArticle(timeLineEntity);

        });
        backButton = view.findViewById(R.id.back_button);
        backButton.setOnClickListener((v -> {
          controller.onEditArticle(timeLineEntity);
        }));
    }
}

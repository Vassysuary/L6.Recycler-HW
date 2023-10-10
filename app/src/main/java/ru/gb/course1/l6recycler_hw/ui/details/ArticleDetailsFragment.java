package ru.gb.course1.l6recycler_hw.ui.details;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ru.gb.course1.l6recycler_hw.App;
import ru.gb.course1.l6recycler_hw.R;
import ru.gb.course1.l6recycler_hw.domain.TimeLineEntity;

public class ArticleDetailsFragment extends Fragment {
    private TextView articleTextView;
    private Button deleteButton;
    private Button backButton;
    private TimeLineEntity timeLineEntity;
    private Controller controller;

    public static final String FRAGMENT_DETAILS_EXTRA_KEY = "FRAGMENT_DETAILS_EXTRA_KEY";

    public interface Controller {
        void onDeleteDetailsArticle(String articleId);
    }

    public static ArticleDetailsFragment newInstanceDetails(TimeLineEntity timeLineEntity){
        ArticleDetailsFragment fragment = new ArticleDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(FRAGMENT_DETAILS_EXTRA_KEY,timeLineEntity);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Controller) {
            controller = (Controller) context;
        } else {
            throw new IllegalStateException("Activity must implement ArticleDetailsFragment.Controller");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_article_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        articleTextView = view.findViewById(R.id.fragment_article_details__text_view);
        deleteButton = view.findViewById(R.id.fragment_article_details__delete_button);
        timeLineEntity = getArguments().getParcelable(FRAGMENT_DETAILS_EXTRA_KEY);
        articleTextView.setText(timeLineEntity.getArticleText());

        deleteButton.setOnClickListener(v -> {
            App.get().articleRepository.deleteArticleItemDismiss(timeLineEntity.getArticleId());
            controller.onDeleteDetailsArticle(timeLineEntity.getArticleId());
        });
        backButton = view.findViewById(R.id.fragment_article_details__back_button);
        backButton.setOnClickListener((v -> {
            controller.onDeleteDetailsArticle(timeLineEntity.getArticleId());
        }));
    }
}

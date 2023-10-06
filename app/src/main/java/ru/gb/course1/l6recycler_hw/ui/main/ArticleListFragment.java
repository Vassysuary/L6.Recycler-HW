package ru.gb.course1.l6recycler_hw.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ru.gb.course1.l6recycler_hw.App;
import ru.gb.course1.l6recycler_hw.R;
import ru.gb.course1.l6recycler_hw.data.CacheArticleRepositoryImpl;
import ru.gb.course1.l6recycler_hw.domain.ArticleRepository;
import ru.gb.course1.l6recycler_hw.domain.TimeLineEntity;
import ru.gb.course1.l6recycler_hw.ui.details.ArticleActivity;
import ru.gb.course1.l6recycler_hw.ui.details.ArticleEditActivity;

public class ArticleListFragment extends Fragment {

    private RecyclerView recyclerView;
    private ArticleRepository articleRepository = new CacheArticleRepositoryImpl();
    //    private ArticleRepository articleRepository;
    private static final int ARTICLE_REQUEST_CODE = 42;
    private Button insertNewArticle;
    private TimeLineAdapter adapter;
    private TimeLineEntity timeLineEntity = new TimeLineEntity();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_articles_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
//        Intent intent = new Intent(view.getContext(), ArticleNewActivity.class);
//        intent.putExtra(ArticleNewActivity.ARTICLE_EXTRA_KEY, timeLineEntity);
//        startActivityForResult(intent, ARTICLE_REQUEST_CODE);

        articleRepository = App.get(view.getContext()).getArticleRepo();

//        getArticleRepo();

        initRecycler(view);

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter);
        ItemTouchHelper mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(recyclerView);
    }

    private void initRecycler(@NonNull View view) {
        recyclerView = view.findViewById(R.id.fragment_articles_list__recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new TimeLineAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setData(articleRepository.getArticle());
//        adapter.setData(App.get().getArticleRepo().getArticle());
        /*
        adapter.setOnDeleteClickListener(this);
         */
        adapter.setOnItemClickListener(new OnArticleListener() {
            @Override
            public void onDeleteArticle(TimeLineEntity timeLineEntity) {
                articleRepository.deleteArticle(timeLineEntity);
                adapter.setData(articleRepository.getArticle());
            }

            @Override
            public void onClickArticle(TimeLineEntity timeLineEntity) {
                Intent intent = new Intent(getContext(), ArticleActivity.class);
                intent.putExtra(ArticleActivity.ARTICLE_EXTRA_KEY, timeLineEntity);
                startActivityForResult(intent, ARTICLE_REQUEST_CODE);
//        Toast.makeText(this, timeLineEntity.getArticleTitle(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onEditArticle(TimeLineEntity timeLineEntity){
                Intent intent = new Intent(getContext(), ArticleEditActivity.class);
                intent.putExtra(ArticleEditActivity.ARTICLE_EXTRA_KEY, timeLineEntity);
                startActivityForResult(intent, ARTICLE_REQUEST_CODE);
            }

            @Override
            public void onItemDismiss(String id) {
                articleRepository.deleteArticleItemDismiss(id);
                adapter.setData(articleRepository.getArticle());
            }
        });
    }

}

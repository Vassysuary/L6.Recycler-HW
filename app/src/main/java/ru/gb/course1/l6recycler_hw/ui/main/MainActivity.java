package ru.gb.course1.l6recycler_hw.ui.main;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import ru.gb.course1.l6recycler_hw.R;
import ru.gb.course1.l6recycler_hw.domain.ArticleRepository;
import ru.gb.course1.l6recycler_hw.domain.TimeLineEntity;
import ru.gb.course1.l6recycler_hw.ui.details.ArticleActivity;
import ru.gb.course1.l6recycler_hw.ui.details.ArticleEditActivity;
import ru.gb.course1.l6recycler_hw.ui.details.ArticleEditActivityFragment;

public class MainActivity
        extends AppCompatActivity
        implements ArticleListFragment.Controller, ArticleEditActivityFragment.Controller {
//    private final ArticleRepository articleRepository = new CacheArticleRepositoryImpl();
    private ArticleRepository articleRepository;
    private static final int ARTICLE_REQUEST_CODE = 42;
    private static final String TAG_EDIT_FRAGMENT = "TAG_EDIT_FRAGMENT";
//    private RecyclerView recyclerView;
    private TimeLineAdapter adapter;
//    private Button insertNewArticle;
//    private TimeLineEntity timeLineEntity = new TimeLineEntity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            Fragment articleListFragment = new ArticleListFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.activity_main__fragment_container, articleListFragment,TAG_EDIT_FRAGMENT)
                    .commit();
        }
    }
    @Override
    public void articleEdit(TimeLineEntity timeLineEntity) {
        Toast.makeText(this, timeLineEntity.getArticleText(), Toast.LENGTH_SHORT).show();

        Fragment articleEditFragment =  ArticleEditActivityFragment.newInstance(timeLineEntity);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_main__fragment_container,articleEditFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onEditArticle(TimeLineEntity timeLineEntity) {
        getSupportFragmentManager().popBackStack();
        ArticleListFragment articleListFragment = (ArticleListFragment) getSupportFragmentManager().findFragmentByTag(TAG_EDIT_FRAGMENT);
        articleListFragment.onArticleEdit(timeLineEntity);
    }
}
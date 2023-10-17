package ru.gb.course1.l6recycler_hw.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import ru.gb.course1.l6recycler_hw.R;
import ru.gb.course1.l6recycler_hw.domain.ArticleRepository;
import ru.gb.course1.l6recycler_hw.domain.TimeLineEntity;
import ru.gb.course1.l6recycler_hw.ui.details.ArticleDetailsFragment;
import ru.gb.course1.l6recycler_hw.ui.details.ArticleEditFragment;
import ru.gb.course1.l6recycler_hw.ui.details.ArticleNewFragment;

public class MainActivity
        extends AppCompatActivity
        implements ArticleListFragment.Controller,
        ArticleEditFragment.Controller,
        ArticleDetailsFragment.Controller,
        ArticleNewFragment.Controller {
    //    private final ArticleRepository articleRepository = new CacheArticleRepositoryImpl();
    private ArticleRepository articleRepository;
    private static final int ARTICLE_REQUEST_CODE = 42;
    private static final String TAG_EDIT_FRAGMENT = "TAG_EDIT_FRAGMENT";
    //    private RecyclerView recyclerView;
    private TimeLineAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            Fragment articleListFragment = new ArticleListFragment();
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.activity_main__main_fragment_container, articleListFragment, TAG_EDIT_FRAGMENT)
                    .commit();
        }
    }

    @Override
    public void articleEdit(TimeLineEntity timeLineEntity) {
//        Toast.makeText(this, timeLineEntity.getArticleText(), Toast.LENGTH_SHORT).show();

        Fragment articleEditFragment = ArticleEditFragment.newInstance(timeLineEntity);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_main__main_fragment_container, articleEditFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void articleDetails(TimeLineEntity timeLineEntity) {
        Fragment articleDetailsFragment = ArticleDetailsFragment.newInstanceDetails(timeLineEntity);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_main__second_fragment_container, articleDetailsFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void articleNew(TimeLineEntity timeLineEntity) {
        Fragment articleNewFragment = ArticleNewFragment.newInstanceNewArticle(timeLineEntity);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_main__main_fragment_container, articleNewFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onNewArticle(TimeLineEntity timeLineEntity) {
        getSupportFragmentManager().popBackStack();
    }

    @Override
    public void onEditArticle(TimeLineEntity timeLineEntity) {
        getSupportFragmentManager().popBackStack();
//        ArticleListFragment articleListFragment = (ArticleListFragment) getSupportFragmentManager().findFragmentByTag(TAG_EDIT_FRAGMENT);
//        if (articleListFragment == null) throw new IllegalStateException("articleListFragment not found.");
    }

    @Override
    public void onDeleteDetailsArticle(String articleId){
        getSupportFragmentManager().popBackStack();
//        ArticleListFragment articleListFragment = (ArticleListFragment) getSupportFragmentManager().findFragmentByTag(TAG_EDIT_FRAGMENT);
//        if (articleListFragment == null) throw new IllegalStateException("articleListFragment not found.");
    }
}
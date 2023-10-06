package ru.gb.course1.l6recycler_hw.ui.main;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;

import ru.gb.course1.l6recycler_hw.R;
import ru.gb.course1.l6recycler_hw.domain.ArticleRepository;
import ru.gb.course1.l6recycler_hw.domain.TimeLineEntity;
import ru.gb.course1.l6recycler_hw.ui.details.ArticleActivity;
import ru.gb.course1.l6recycler_hw.ui.details.ArticleEditActivity;

public class MainActivity extends AppCompatActivity {
//    private final ArticleRepository articleRepository = new CacheArticleRepositoryImpl();
    private ArticleRepository articleRepository;
    private static final int ARTICLE_REQUEST_CODE = 42;
//    private RecyclerView recyclerView;
    private TimeLineAdapter adapter;
//    private Button insertNewArticle;
//    private TimeLineEntity timeLineEntity = new TimeLineEntity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        Fragment articleListFragment = new ArticleListFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.activity_main__fragment_container, articleListFragment)
                .commit();

//        insertNewArticle = findViewById(R.id.ins_new_article_button);
//        insertNewArticle.setOnClickListener(v -> {
//            Intent intent = new Intent(this, ArticleNewActivity.class);
//            intent.putExtra(ArticleNewActivity.ARTICLE_EXTRA_KEY, timeLineEntity);
//            startActivityForResult(intent, ARTICLE_REQUEST_CODE);
//
//        });
//        articleRepository = App.get(this).getArticleRepo();
//        initRecycler();
//        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter);
//        ItemTouchHelper mItemTouchHelper = new ItemTouchHelper(callback);
//        mItemTouchHelper.attachToRecyclerView(recyclerView);
    }

//    private void initRecycler() {
//        recyclerView = findViewById(R.id.recycler_view);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        adapter = new TimeLineAdapter();
//        recyclerView.setAdapter(adapter);
//        adapter.setData(articleRepository.getArticle());
//        adapter.setOnDeleteClickListener(this);
//    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ARTICLE_REQUEST_CODE && resultCode == RESULT_OK) {
            adapter.setData(articleRepository.getArticle());
        }
    }
}
package ru.gb.course1.l6recycler_hw.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import ru.gb.course1.l6recycler_hw.App;
import ru.gb.course1.l6recycler_hw.R;
import ru.gb.course1.l6recycler_hw.domain.ArticleRepository;
import ru.gb.course1.l6recycler_hw.domain.TimeLineEntity;

public class MainActivity extends AppCompatActivity implements OnArticleListener {
//    private final ArticleRepository articleRepository = new CacheArticleRepositoryImpl();
    private ArticleRepository articleRepository;
    private static final int ARTICLE_REQUEST_CODE = 42;
    private RecyclerView recyclerView;
    private TimeLineAdapter adapter;
    private Button insertNewArticle;
    private TimeLineEntity timeLineEntity = new TimeLineEntity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        insertNewArticle = findViewById(R.id.ins_new_article_button);
        insertNewArticle.setOnClickListener(v -> {
            Intent intent = new Intent(this, ArticleNewActivity.class);
            intent.putExtra(ArticleNewActivity.ARTICLE_EXTRA_KEY, timeLineEntity);
            startActivityForResult(intent, ARTICLE_REQUEST_CODE);

        });
        articleRepository = App.get(this).getArticleRepo();
        initRecycler();
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback(adapter);
        ItemTouchHelper mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(recyclerView);
    }

    private void initRecycler() {
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new TimeLineAdapter();
        recyclerView.setAdapter(adapter);
        adapter.setData(articleRepository.getArticle());
        adapter.setOnDeleteClickListener(this);
    }
    @Override
    public void onDeleteArticle(TimeLineEntity timeLineEntity) {
        articleRepository.deleteArticle(timeLineEntity);
        adapter.setData(articleRepository.getArticle());
    }
    @Override
    public void onEditArticle(TimeLineEntity timeLineEntity){
        Intent intent = new Intent(this, ArticleEditActivity.class);
        intent.putExtra(ArticleEditActivity.ARTICLE_EXTRA_KEY, timeLineEntity);
        startActivityForResult(intent, ARTICLE_REQUEST_CODE);
    }

//    @Override
//    public void onItemDismiss(TimeLineEntity timeLineEntity) {
//        articleRepository.deleteArticle(timeLineEntity);
//        adapter.setData(articleRepository.getArticle());
//    }

    @Override
    public void onClickArticle(TimeLineEntity timeLineEntity) {
        Intent intent = new Intent(this, ArticleActivity.class);
        intent.putExtra(ArticleActivity.ARTICLE_EXTRA_KEY, timeLineEntity);
        startActivityForResult(intent, ARTICLE_REQUEST_CODE);
//        Toast.makeText(this, timeLineEntity.getArticleTitle(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ARTICLE_REQUEST_CODE && resultCode == RESULT_OK) {
            adapter.setData(articleRepository.getArticle());
        }
    }
}
package ru.gb.course1.l6recycler_hw.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import ru.gb.course1.l6recycler_hw.App;
import ru.gb.course1.l6recycler_hw.domain.ArticleRepository;
import ru.gb.course1.l6recycler_hw.domain.TimeLineEntity;

public class SimpleItemTouchHelperCallback extends ItemTouchHelper.Callback  {

    private final TimeLineAdapter mAdapter;
    private ArticleRepository articleRepository;
    private TimeLineEntity timeLineEntity = new TimeLineEntity();
    private OnArticleListener onArticleListener;

    public SimpleItemTouchHelperCallback(TimeLineAdapter mAdapter) {
        this.mAdapter = mAdapter;
    }

    @Override
    public boolean isLongPressDragEnabled(){
        return false;
    }

    @Override
    public boolean isItemViewSwipeEnabled(){
        return true;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int dragFlags = 0;
        int swipeFlags = ItemTouchHelper.RIGHT;

        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

//        articleRepository.deleteArticle(timeLineEntity);
//        adapter.setData(articleRepository.getArticle());
//        notifyDataSetChanged();
        mAdapter.onItemDismiss(viewHolder.getAdapterPosition());

    }
}

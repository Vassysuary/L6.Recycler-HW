package ru.gb.course1.l6recycler_hw.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.gb.course1.l6recycler_hw.domain.ArticleRepository;
import ru.gb.course1.l6recycler_hw.domain.TimeLineEntity;

public class TimeLineAdapter extends RecyclerView.Adapter<TimeLineViewHolder> {

    private List<TimeLineEntity> data;
    private OnArticleListener onArticleListener;
    private ArticleRepository articleRepository;

    @NonNull
    @Override
    public TimeLineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        return new TimeLineViewHolder(inflater, parent, onArticleListener);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public void onBindViewHolder(@NonNull TimeLineViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    private TimeLineEntity getItem(int position){
        return data.get(position);
    }

    protected void onItemDismiss(int position) {
        String id = data.get(position).getArticleId();
//        articleRepository.deleteArticle(data.get(position));
//        articleRepository.deleteArticleItemDismiss(id);
//        articleRepository.deleteArticle(data);
//        data.remove(position);
//        notifyItemRemoved(position);

        onArticleListener.onItemDismiss(id);
    }
    public void setData(List<TimeLineEntity> articles) {
        data = articles;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnArticleListener onArticleListener){
        this.onArticleListener = onArticleListener;
    }
}

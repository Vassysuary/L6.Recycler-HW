package ru.gb.course1.l6recycler_hw.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ru.gb.course1.l6recycler_hw.domain.TimeLineEntity;

public class TimeLineAdapter extends RecyclerView.Adapter<TimeLineViewHolder> {

    private List<TimeLineEntity> data;
    private OnArticleListener onArticleListener;

    public void setOnDeleteClickListener(OnArticleListener onArticleListener){
        this.onArticleListener = onArticleListener;
    }
    public void setData(List<TimeLineEntity> articles) {
        data = articles;
        notifyDataSetChanged();
    }

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
//        final TimeLineEntity timeLineEntity = getItem(position);
        holder.bind(getItem(position));

    }

    private TimeLineEntity getItem(int position){
        return data.get(position);
    }
}

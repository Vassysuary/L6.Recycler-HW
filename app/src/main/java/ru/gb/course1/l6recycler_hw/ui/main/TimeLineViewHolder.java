package ru.gb.course1.l6recycler_hw.ui.main;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import ru.gb.course1.l6recycler_hw.R;
import ru.gb.course1.l6recycler_hw.domain.TimeLineEntity;

public class TimeLineViewHolder extends RecyclerView.ViewHolder {
    private final TextView dateTextView = itemView.findViewById(R.id.date_text_view);
    private final TextView nameTextView = itemView.findViewById(R.id.name_text_view);
//    private final TextView articleTextView = itemView.findViewById(R.id.article_text_view);
    private final TextView buttonDelete = itemView.findViewById(R.id.delete_button);
    private final TextView buttonEdite = itemView.findViewById(R.id.edit_button);
    private OnArticleListener onArticleListener;
    @NonNull
    private TimeLineEntity timeLineEntity;

    public TimeLineViewHolder(
            @NonNull LayoutInflater inflater,
            @NonNull ViewGroup parent,
            OnArticleListener onArticleListener
    ) {
//        super(inflater.inflate(R.layout.item_article, parent, false));
        super(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_article, parent, false));
        this.onArticleListener = onArticleListener;
        buttonDelete.setOnClickListener( v -> onArticleListener.onDeleteArticle(timeLineEntity));
        itemView.setOnClickListener( v -> onArticleListener.onClickArticle(timeLineEntity));
        buttonEdite.setOnClickListener( v -> onArticleListener.onEditArticle(timeLineEntity));
    }

    public void bind(TimeLineEntity article) {

        dateTextView.setText(article.getArticleDateTime());
        nameTextView.setText(article.getArticleTitle());
        timeLineEntity = article;
    }
}

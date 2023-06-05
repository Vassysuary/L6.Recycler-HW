package ru.gb.course1.l6recycler_hw.ui;

import android.view.MotionEvent;

import ru.gb.course1.l6recycler_hw.domain.TimeLineEntity;

interface OnArticleListener {
    void onDeleteArticle(TimeLineEntity timeLineEntity);
    void onClickArticle(TimeLineEntity timeLineEntity);
    void onEditArticle(TimeLineEntity timeLineEntity);
    void onItemDismiss(String articleID);
//    boolean onTouchEvent(MotionEvent event);
}

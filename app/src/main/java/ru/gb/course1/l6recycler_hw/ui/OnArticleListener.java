package ru.gb.course1.l6recycler_hw.ui;

import ru.gb.course1.l6recycler_hw.domain.TimeLineEntity;

interface OnArticleListener {
    void onDeleteArticle(TimeLineEntity timeLineEntity);
    void onClickArticle(TimeLineEntity timeLineEntity);
    void onEditArticle(TimeLineEntity timeLineEntity);
//    void onAddNewArticle(TimeLineEntity timeLineEntity);
}

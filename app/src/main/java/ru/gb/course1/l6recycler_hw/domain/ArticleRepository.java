package ru.gb.course1.l6recycler_hw.domain;

import java.util.List;

public interface ArticleRepository {
    List<TimeLineEntity> getArticle();
    void deleteArticle(TimeLineEntity timeLineEntity);
    void replaceArticle(TimeLineEntity timeLineEntity);
    void addNewArticle(TimeLineEntity timeLineEntity);
}

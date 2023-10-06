package ru.gb.course1.l6recycler_hw.data;

import java.util.ArrayList;
import java.util.List;

import ru.gb.course1.l6recycler_hw.domain.ArticleRepository;
import ru.gb.course1.l6recycler_hw.domain.TimeLineEntity;

public class CacheArticleRepositoryImpl implements ArticleRepository {
    private final List<TimeLineEntity> cache = new ArrayList<>();

    public CacheArticleRepositoryImpl() {
        cache.addAll(createDummyArticlesData());
    }
    @Override
    public List<TimeLineEntity> getArticle() {
        return new ArrayList<>(cache);
    }

    @Override
    public void deleteArticle(TimeLineEntity timeLineEntity) {
        try {
            cache.remove(findPosition(timeLineEntity));
        } catch (IllegalArgumentException iae) {
            iae.printStackTrace();
        }
    }
    @Override
    public void replaceArticle(TimeLineEntity timeLineEntity) {
        cache.set(findPosition(timeLineEntity), timeLineEntity);
    }
    @Override
    public void addNewArticle(TimeLineEntity timeLineEntity){
        cache.add(timeLineEntity);
    }

    private int findPosition(TimeLineEntity timeLineEntity) {
        for (int i = 0; i < cache.size(); i++) {
            if (timeLineEntity.getArticleId().equals(cache.get(i).getArticleId())) {
                return i;
            }
        }
        throw new IllegalArgumentException("Нет такого элемента! Где нашёл?!");
    }

    public void deleteArticleItemDismiss(String articleID) {
        String id = articleID;
        for (int i = 0; i < cache.size(); i++) {
            if (articleID.equals(cache.get(i).getArticleId())) {
                cache.remove(i);
                return;
            }
        }
        throw new IllegalArgumentException("Нет такого элемента0! Где нашёл?!");
    }

    private ArrayList<TimeLineEntity> createDummyArticlesData() {
        final ArrayList<TimeLineEntity> articleEntity = new ArrayList<>();
        final String dateTime = new LocalDateTimeForTimeLine().LocalDateTimeForTimeLine();
        articleEntity.add(new TimeLineEntity("0", "Заголовок0", "Текст0", dateTime));
        articleEntity.add(new TimeLineEntity("1", "Заголовок1", "Текст1", dateTime));
        articleEntity.add(new TimeLineEntity("2", "Заголовок2", "Текст2", dateTime));
        articleEntity.add(new TimeLineEntity("3", "Заголовок3", "Текст3", dateTime));
        articleEntity.add(new TimeLineEntity("4", "Заголовок4", "Текст4", dateTime));
        articleEntity.add(new TimeLineEntity("5", "Заголовок5", "Текст5", dateTime));

        return articleEntity;
    }

}

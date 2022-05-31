package ru.gb.course1.l6recycler_hw;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;

import ru.gb.course1.l6recycler_hw.data.CacheArticleRepositoryImpl;
import ru.gb.course1.l6recycler_hw.domain.ArticleRepository;

public class App extends Application {
    private ArticleRepository articleRepository = new CacheArticleRepositoryImpl();

    @Override
    public void onCreate() {
        super.onCreate();
    }
    public static App get(Context context){ return (App) context.getApplicationContext(); }
    public ArticleRepository getArticleRepo() {
        return articleRepository;
    }
}

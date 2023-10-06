package ru.gb.course1.l6recycler_hw;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;

import ru.gb.course1.l6recycler_hw.data.CacheArticleRepositoryImpl;
import ru.gb.course1.l6recycler_hw.domain.ArticleRepository;

public class App extends Application {

    private static App sInstance = null;

    public final ArticleRepository articleRepository = new CacheArticleRepositoryImpl();

    @Override
    public void onCreate() {

        super.onCreate();
        sInstance = this;
    }
    public static App get(Context context){ return (App) context.getApplicationContext(); }
    public static App get() {return sInstance;}
    public ArticleRepository getArticleRepo() {
        return articleRepository;
    }
}

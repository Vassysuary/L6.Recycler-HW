package ru.gb.course1.l6recycler_hw.utils;

import android.content.Context;

import ru.gb.course1.l6recycler_hw.App;

public class ContextUtils {
    public static App getApp(Context context) {
        return (App) context.getApplicationContext();
    }
}

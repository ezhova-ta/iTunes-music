package com.example.itunesmusic.app;

import android.app.Application;

/**
 * Базовый класс для поддержания глобального состояния приложения.
 */
public class ITunesMusicApplication extends Application {
    private static ITunesMusicApplication instance;
    private DependencyInjectionFactory diFactory;

    public static ITunesMusicApplication getInstance() {
        return instance;
    }

    /**
     * Возвращает класс, осуществляющий разрешение зависимостей.
     *
     * @return класс, осуществляющий разрешение зависимостей
     */
    public DependencyInjectionFactory getDiFactory(){
        return diFactory;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        diFactory = new DependencyInjectionFactory();
    }
}

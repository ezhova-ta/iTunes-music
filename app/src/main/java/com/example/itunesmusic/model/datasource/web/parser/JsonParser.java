package com.example.itunesmusic.model.datasource.web.parser;

import org.json.JSONException;

import java.io.IOException;

/**
 * Абстрактное представление о классе для разбора объекта JSON.
 *
 * @param <T> тип результата разбора
 */
public interface JsonParser<T> {
    /**
     * Разбирает объект JSON.
     *
     * @param jsonString строка, представляющая объект JSON
     * @return результат разбора
     * @throws JSONException - если произошла ошибка разбора
     * @throws IOException - если произошла ошибка ввода/вывода
     */
    T parse(String jsonString) throws JSONException, IOException;
}

package org.example.bookworm.util;

public class Converter {
    private static Converter converter;

    private Converter(){}

    public static Converter getInstance(){return converter == null? converter = new Converter() : converter;}


}

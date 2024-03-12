package org.example.bookworm.util;

import java.util.regex.Pattern;

public class RegExFactory {
    private static RegExFactory regExFactory;

    private final Pattern namePattern;
    private final Pattern UserIdPattern;
    private final Pattern passwordPattern;
    private final Pattern BookIdPattern;
    private final Pattern BookTitlePattern;
    private final Pattern emailPattern;
    private final Pattern mobilePattern;
    private final Pattern readerIDPattern;

    private RegExFactory() {
        namePattern = Pattern.compile("^[a-zA-Z0-9 '.-]{4,}$");
        UserIdPattern = Pattern.compile("^U\\d{3}$");
        passwordPattern = Pattern.compile("^[a-zA-Z0-9]{4,}$");
        BookIdPattern = Pattern.compile("\"^B\\\\d{3}$\"");
        BookTitlePattern = Pattern.compile("^[a-zA-Z0-9 '.-]{4,}$");
        mobilePattern = Pattern.compile("^(?:0|94|\\+94|0094)?(?:(11|21|23|24|25|26|27|31|32|33|34|35|36|37|38|41|45|47|51|52|54|55|57|63|65|66|67|81|91)(0|2|3|4|5|7|9)|7(0|1|2|4|5|6|7|8)\\d)\\d{6}$");
        emailPattern = Pattern.compile("(^[a-zA-Z0-9_.-]+)@([a-zA-Z]+)([\\.])([a-zA-Z]+)$");
        readerIDPattern = Pattern.compile("^R\\d{3}$");
    }

    public static RegExFactory getInstance(){
        return regExFactory == null? regExFactory = new RegExFactory() : regExFactory;
    }

    public Pattern getPattern(RegExType regExType) throws RuntimeException{
        switch (regExType){
            case NAME:
                return namePattern;
            case USERID:
                return UserIdPattern;
            case PASSWORD:
                return passwordPattern;
            case BOOK_ID:
                return BookIdPattern;
            case BOOK_TITLE:
                return BookTitlePattern;
            case READER_ID:
                return readerIDPattern;
            case MOBILE:
                return mobilePattern;
            case EMAIL:
                return emailPattern;
            default:
                throw new RuntimeException("Pattern Not Found");
        }
    }
}

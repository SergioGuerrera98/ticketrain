package com.corso.ticketrain.application;

public class StringsUtils {
    public static String upFirst(String str) {
        if (str == null) return null;
        String news = str.toLowerCase();

        if (news.length() > 1)
            news = (news.charAt(0)+"").toUpperCase() + news.substring(1);
        for (int index = 1; index < str.length(); ++index){
            char c = str.charAt(index);
            if (!isLetter(c)) {
                if (index + 1 < str.length() && isLetter(str.charAt(index+1)))
                    news = news.substring(0, index+1) +
                            (news.charAt(index+1)+"").toUpperCase() +
                            news.substring(index+2);
            }}
        return news;
    }

    public static boolean isLetter(char c) {
        return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z');
    }
}

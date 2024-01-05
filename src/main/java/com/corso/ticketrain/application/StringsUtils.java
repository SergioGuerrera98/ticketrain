package com.corso.ticketrain.application;

public class StringsUtils {
    public static String upFirst(String str) {
        String news = new String(str);
        if (news.length() > 1)
            news = news.charAt(0) + news.substring(1);
        for (int index = 1; index < str.length(); ++index){
            char c = str.charAt(index);
            if (!(c > 'A' && c < 'Z') && !(c > 'a' && c < 'z')) {
                if (index + 1 < str.length())
                    news = news.substring(0, index+1) + news.substring(index+2);
            }}
        return news;
    }
}

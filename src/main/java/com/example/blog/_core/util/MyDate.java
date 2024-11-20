package com.example.blog._core.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class MyDate {

    public static String formatToStr(Timestamp createdAt){
        // when
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
        String formattedDate = sdf.format(createdAt);
        return formattedDate;
    }
}
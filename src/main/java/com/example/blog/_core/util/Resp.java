package com.example.blog._core.util;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Resp<T> { //new할 때 타입결정

    private boolean success; // 상태코드 -> 실패시 e.getmsg
    private String msg;
    private T body;

    // 메서드 실행시 타입 정하고 싶을 때 사용

    public static <T> Resp<T> ok(T body){
        return new Resp<>(true,"성공",body);
    }
    public static <T> Resp<T> fail(String msg){
        return new Resp<>(false,msg,null);
    }
}


/*
session 객체 하나!!!!에 여러개 들어감
request 여러개
*/

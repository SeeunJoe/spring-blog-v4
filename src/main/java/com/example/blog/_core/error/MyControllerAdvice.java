package com.example.blog._core.error;

import com.example.blog._core.error.ex.Exception400;
import com.example.blog._core.error.ex.Exception404;
import com.example.blog._core.util.Resp;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// 리플렉션의 연산을 줄이기 -> @

// 에러처리 담당!!!!
@RestControllerAdvice
public class MyControllerAdvice {
    //클라이언트에게 최종 응답을 해야한다 -> viewResolver로
    @ExceptionHandler(Exception400.class)
    public ResponseEntity<?> err400(Exception400 e){
        ResponseEntity rn = new ResponseEntity(Resp.fail(e.getMessage()), HttpStatus.BAD_REQUEST);
        return rn;
    }

    @ExceptionHandler(Exception404.class)
    public ResponseEntity<?> err404(Exception404 e){
        ResponseEntity rn = new ResponseEntity(Resp.fail(e.getMessage()), HttpStatus.NOT_FOUND);
        return rn;
    }
}

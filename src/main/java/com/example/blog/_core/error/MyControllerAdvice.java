package com.example.blog._core.error;

import com.example.blog._core.error.ex.Exception400;
import com.example.blog._core.error.ex.Exception404;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

// 리플렉션의 연산을 줄이기 -> @

// 에러처리 담당!!!!
@ControllerAdvice
public class MyControllerAdvice {
    //클라이언트에게 최종 응답을 해야한다 -> viewResolver로
    @ResponseBody
    @ExceptionHandler(Exception400.class)
    public String err400(RuntimeException e){
        System.out.println("err400");
        // 브라우저 html css
        String body = """
                <script>
                    alert('${msg}');
                    history.back();
                </script>
                """.replace("${msg}",e.getMessage());
        return body;
    }
    @ResponseBody
    @ExceptionHandler(Exception404.class)
    public String err404(RuntimeException e){
        System.out.println("err404");
        // 브라우저 html css
        String body = """
                <script>
                    alert('${msg}');
                    history.back();
                </script>
                """.replace("${msg}",e.getMessage());
        return body;
    }
}

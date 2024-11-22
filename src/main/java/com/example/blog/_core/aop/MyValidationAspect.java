package com.example.blog._core.aop;

import com.example.blog._core.error.ex.Exception404;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component // SIP가 아닌데 무조건 띄워야하니까
@Aspect
public class MyValidationAspect {

    //포인트컷 안만들고 아래 바로 넣었다@!!!

    @Around("@annotation(org.springframework.web.bind.annotation.PostMapping)") //포인트컷 자리
    public Object validationCheck(ProceedingJoinPoint jp) throws Throwable { //joinPoint에 필요한 정보가 다 담겨져있어
        //jp.getArgs(); // 매개변수들... -> reflection과 같다
        Object[] args = jp.getArgs();

        for(Object arg : args) {
            if (arg instanceof Errors) {
                Errors errors = (Errors) arg;

                if (errors.hasErrors()) {
                    String errMsg = errors.getFieldErrors().get(0).getField() + ": "
                            + errors.getFieldErrors().get(0).getDefaultMessage();
                    throw new Exception404(errMsg);
                }
            }
        }
        System.out.println("직전");
        Object ob = jp.proceed();
        System.out.println("메롱");
        return ob;
    }

}

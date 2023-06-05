package com.example.demo.controller.exception;

import com.example.demo.pojo.vo.ResponseVo;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName:ExceptionHandleController
 * @Author:LiGou
 * @Date:2023/5/5 15:47
 * @Version:1.0
 * @Description:
 */
@RestControllerAdvice("com.example.demo")
public class ExceptionHandleController {

    @ExceptionHandler(Exception.class)
    public ResponseVo exceptionHandler(HttpServletRequest request, Exception exception) throws Exception {
        ResponseVo responseVo = new ResponseVo();
        responseVo.setCode(100);

        StackTraceElement[] stackTrace = exception.fillInStackTrace().getStackTrace();
        String s = stackTrace[0].toString();


        String message = exception.getMessage() + "*" + request.getRequestURL().toString() + "*" + s;
        responseVo.setMes(message);
        return responseVo;
    }
}

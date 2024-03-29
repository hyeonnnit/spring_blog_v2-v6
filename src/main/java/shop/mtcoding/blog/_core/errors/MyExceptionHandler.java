package shop.mtcoding.blog._core.errors;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import shop.mtcoding.blog._core.errors.exception.*;

@ControllerAdvice // RUNTIME 예외 발생 시 여기서 오류 처리
public class MyExceptionHandler {
    // 커스텀 예외를 만들어서 런타임 예외 상속하기
    @ExceptionHandler(Exception400.class)
    public String ex400(RuntimeException e, HttpServletRequest request){
        request.setAttribute("msg", e.getMessage());
        return "err/400";
    }
    @ExceptionHandler(Exception401.class)
    public String ex401(RuntimeException e, HttpServletRequest request){
        request.setAttribute("msg", e.getMessage());
        return "err/401";
    }
    @ExceptionHandler(Exception403.class)
    public String ex403(RuntimeException e, HttpServletRequest request){
        request.setAttribute("msg", e.getMessage());
        return "err/403";
    }
    @ExceptionHandler(Exception404.class)
    public String ex404(RuntimeException e, HttpServletRequest request){
        request.setAttribute("msg", e.getMessage());
        return "err/404";
    }
    @ExceptionHandler(Exception500.class)
    public String ex500(RuntimeException e, HttpServletRequest request){
        request.setAttribute("msg", e.getMessage());
        return "err/500";
    }
//    @ExceptionHandler(Exception.class)
//    public String exUnknown(RuntimeException e){
//        // DB에 에러 로그 남기기
//        // 관리자에게 알리기
//        // 이메일 보내기
//        return "err/unknown";
//    }
}

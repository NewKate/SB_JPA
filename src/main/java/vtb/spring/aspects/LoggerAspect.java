package vtb.spring.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggerAspect {

    @Pointcut("execution(* vtb.spring.service.OperaService.getOperaByName (..))")
    public void operaServiceLog(){}

    @Before("operaServiceLog()")
    public void before(JoinPoint point){
        String methodName = point.getSignature().getName() + "()";
        print("Готовимся к выполнениею метода " + methodName);
    }

    @After("operaServiceLog()")
    public void after(JoinPoint point){
        String methodName = point.getSignature().getName() + "()";
        print("Метод " + methodName + " завершен");
    }

    public void print(String message){
        System.out.println("Отправка письма (аспект): \n" + message);
    }
}

package aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(2)
public class LoggingAspect {

    // this is where we add all of our related advices for logging

    // let's start with an @Before advice


    @Before("aop.aspect.AopExpression.forDaoPackkageNoGetterSettter()")
    public void beforeAddAccountAdvice(){
        System.out.println("====>>> Executing @Before advice on method");
    }



}

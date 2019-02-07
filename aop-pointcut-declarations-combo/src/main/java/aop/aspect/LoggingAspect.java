package aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    // this is where we add all of our related advices for logging

    // let's start with an @Before advice

    @Pointcut("execution(* aop.dao.*.*(..))")
    private void forDaoPackage(){}

    // create pointcut for getter methods
    @Pointcut("execution(* aop.dao.*.get*(..))")
    private void getter(){}

    // create pointcut for getter methods
    @Pointcut("execution(* aop.dao.*.set*(..))")
    private void setter(){}

    // create pointcut: include package... exclude getter/setter
    @Pointcut("forDaoPackage() && !(getter() || setter())")
    private void forDaoPackkageNoGetterSettter(){}

    @Before("forDaoPackkageNoGetterSettter()")
    public void beforeAddAccountAdvice(){
        System.out.println("\n====>>> Executing @Before advice on method");
    }

    @Before("forDaoPackkageNoGetterSettter()")
    public void performApiAnalytics(){
        System.out.println("====>>> Performing API analytics");
    }
}

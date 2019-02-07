package aop.aspect;

import aop.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(2)
public class LoggingAspect {

    // this is where we add all of our related advices for logging

    // let's start with an @Before advice

    @Before("aop.aspect.AopExpression.forDaoPackkageNoGetterSettter()")
    public void beforeAddAccountAdvice(JoinPoint joinPoint) {

        System.out.println("====>>> Executing @Before advice on method");

        // display the method signature
        MethodSignature methodSignature =
                (MethodSignature) joinPoint.getSignature();

        System.out.println("Method: " + methodSignature);

        // display method arguments

        // get args
        Object[] args = joinPoint.getArgs();

        // loop thru args
        for (Object tempArg : args) {
            System.out.println(tempArg);

            if (tempArg instanceof Account){

                // downcast and print Account specific stuff
                Account account = (Account) tempArg;

                System.out.println("account name: " + account.getName());
                System.out.println("account level: " + account.getLevel());
            }
        }

    }
}

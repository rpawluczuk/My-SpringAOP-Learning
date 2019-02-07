package aop.aspect;

import aop.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Logger;

@Aspect
@Component
@Order(2)
public class LoggingAspect {

    private Logger myLogger = Logger.getLogger(getClass().getName());

    @Around("execution(* aop.service.*.getFortune(..))")
    public Object aroundGetFortune(
            ProceedingJoinPoint proceedingJoinPoint) throws Throwable{

        // print out method we are advising on
        String method = proceedingJoinPoint.getSignature().toShortString();
        myLogger.info("\n=====>>> Executing @Around on method: "
                + method);

        // get begin timestamp
        long begin = System.currentTimeMillis();

        // execute the method
        Object result = null;

        try {
            result = proceedingJoinPoint.proceed();
        } catch (Exception e) {

            // log the exception
            myLogger.warning(e.getMessage());

            // rethrow exception
            throw e;
        }

        // get end timestamp
        long end = System.currentTimeMillis();

        // compute duration and display it
        long duration = end - begin;
        myLogger.info("\n====>>> Duration: " + duration / 1000.0 + " seconds");

        return result;
    }

    @After("execution(* aop.dao.AccountDAO.findAccounts(..))")
    public void afterFinallyFindAccountsAdvice(JoinPoint joinPoint){

        // print out which method we are advising on
        String method = joinPoint.getSignature().toShortString();
        myLogger.info("\n=====>>> Executing @After (finally) on method: "
                + method);
    }

    @AfterThrowing(
            pointcut = "execution(* aop.dao.AccountDAO.findAccounts(..))",
            throwing = "exc")
    public void afterThrowingFindAccountsAdvice(
            JoinPoint joinPoint, Throwable exc){

        // print out which method we are advising on
        String method = joinPoint.getSignature().toShortString();
        myLogger.info("\n=====>>> Executing @AfterThrowing on method: " + method);

        // log the exception
        myLogger.info("\n=====>>> The exception is: " + exc);

    }

    @AfterReturning(
            pointcut = "execution(* aop.dao.AccountDAO.findAccounts(..))",
            returning = "result")
    public void afterReturningFindAccountsAdvice(
            JoinPoint joinPoint, List<Account> result){

        // print out which method we are advising on
        String method = joinPoint.getSignature().toShortString();
        myLogger.info("\n=====>>> Executing @AfterRetuning on method: " + method);

        // print out the results of the method call
        myLogger.info("\n=====>>> result is: " + result);

        // convert the account names to uppercase
        convertAccountNamesToUpperCase(result);

        myLogger.info("\n=====>>> result is: " + result);

    }

    private void convertAccountNamesToUpperCase(List<Account> result) {

        // loop through accounts

        for (Account account : result){

            // get uppercase version of name
            String upperName = account.getName().toUpperCase();

            // update the name on the account
            account.setName(upperName);
        }

    }

    @Before("aop.aspect.AopExpression.forDaoPackkageNoGetterSettter()")
    public void beforeAddAccountAdvice(JoinPoint joinPoint) {

        myLogger.info("====>>> Executing @Before advice on method");

        // display the method signature
        MethodSignature methodSignature =
                (MethodSignature) joinPoint.getSignature();

        myLogger.info("Method: " + methodSignature);

        // display method arguments

        // get args
        Object[] args = joinPoint.getArgs();

        // loop thru args
        for (Object tempArg : args) {
            myLogger.info(tempArg.toString());

            if (tempArg instanceof Account){

                // downcast and print Account specific stuff
                Account account = (Account) tempArg;

                myLogger.info("account name: " + account.getName());
                myLogger.info("account level: " + account.getLevel());
            }
        }

    }
}

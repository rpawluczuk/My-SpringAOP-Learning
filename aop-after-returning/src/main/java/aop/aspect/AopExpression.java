package aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AopExpression {

    @Pointcut("execution(* aop.dao.*.*(..))")
    public void forDaoPackage(){}

    // create pointcut for getter methods
    @Pointcut("execution(* aop.dao.*.get*(..))")
    public void getter(){}

    // create pointcut for getter methods
    @Pointcut("execution(* aop.dao.*.set*(..))")
    public void setter(){}

    // create pointcut: include package... exclude getter/setter
    @Pointcut("forDaoPackage() && !(getter() || setter())")
    public void forDaoPackkageNoGetterSettter(){}

}

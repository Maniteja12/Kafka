package com.springboot.aop.aspect;

import com.springboot.aop.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {

    //This is a point cut expression
    // Modifier ReturnType and MethodName
    //@Before("execution(public void addAccount())")

    //This is a wildcard calling all methods that start with add
    //@Before("execution(public void add*())")

    //Using it for any return type
    //@Before("execution(* add*())")

    //@Before("execution(* com.springboot.aop.doa.*.*(..))")

    //pointcut exp for getter methods

    @Before("com.springboot.aop.aspect.AopExpressions.forDoaPackageNoGetterSetter()")
    public void beforeAddAccount(JoinPoint joinPoint){
        System.out.println("\n =====> Executing @Before advice on method");

        //display method signatures
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        System.out.println("Method::"+methodSignature);

        //display method arguments
        Object[] args = joinPoint.getArgs();
        for(Object arg: args){
            System.out.println(arg);

            if(arg instanceof Account){
                //downcast and print account specific stuff
                Account account = (Account) arg;
                System.out.println("accoutn name ::"+account.getName());
                System.out.println("level name ::"+account.getLevel());
            }
        }
    }

    @AfterReturning(
            pointcut = "execution(* com.springboot.aop.dao.AccountDAO.findAccounts(..))",
            returning = "result")
    public void afterReturningFindAccountsAdvice(JoinPoint joinPoint, List<Account> result){
        String method = joinPoint.getSignature().toShortString();
        System.out.println("=========>>>> Executing @After Returning on method:: "+method);
        System.out.println("=========>>>> Result is :: "+result);

        // postprocess and modify the data
        convertAccountNamestoUpperCase(result);
        System.out.println("=========>>>> Result is :: "+result);
    }

    private void convertAccountNamestoUpperCase(List<Account> result) {
        for(Account acc:result){
            String upperName = acc.getName().toUpperCase();
            acc.setName(upperName);
        }
    }

    @AfterThrowing(
            pointcut = "execution(* com.springboot.aop.dao.AccountDAO.findAccounts(..))",
            throwing = "exc")
    public void afterThrowingFindAccountsAdvice(JoinPoint joinPoint, Throwable exc){
        String method = joinPoint.getSignature().toShortString();
        System.out.println("=========>>>> Executing @After Throwing on method:: "+method);
        System.out.println("\n=========>>>> Exception is :: "+exc);
    }

    @After("execution(* com.springboot.aop.dao.AccountDAO.findAccounts(..))")
    public void afterFinallyFindAccountsAdvice(JoinPoint joinPoint){
        String method = joinPoint.getSignature().toShortString();
        System.out.println("=========>>>> Executing @After (finally) on method:: "+method);
    }

    @Around("execution(* com.springboot.aop.service.*.getFortune(..))")
    public Object aroundGetFortune(
            ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        String method = proceedingJoinPoint.getSignature().toShortString();
        System.out.println("=========>>>> Executing @Around on method:: "+method);

        long begin = System.currentTimeMillis();
        Object result = null;
        try {
            result = proceedingJoinPoint.proceed();
        }catch (Exception ex){
            System.out.println(ex.getMessage());
            //result="Major accident!! But no worries";
            throw ex;
        }
        long end = System.currentTimeMillis();
        long duration = end-begin;
        System.out.println("========>> Duration ::" + duration/1000 + "seconds");
        return result;
    }
}

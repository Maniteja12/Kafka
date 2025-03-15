package com.springboot.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect // only required when we have @before and @after advices
public class AopExpressions {

    @Pointcut("execution(* com.springboot.aop.dao.*.get*(..))")
    public void getter(){

    }
    @Pointcut("execution(* com.springboot.aop.dao.*.set*(..))")
    public void setter(){

    }

    //For reusing the pointcut expression
    @Pointcut("execution(* com.springboot.aop.dao.*.*(..))")
    public void forDoaPackage(){
    }

    @Pointcut("forDoaPackage() && !(getter() || setter())")
    public void forDoaPackageNoGetterSetter(){
    }
}

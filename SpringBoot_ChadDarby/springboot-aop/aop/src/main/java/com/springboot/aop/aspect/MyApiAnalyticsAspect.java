package com.springboot.aop.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(3)
public class MyApiAnalyticsAspect {
    @Before("com.springboot.aop.aspect.AopExpressions.forDoaPackageNoGetterSetter()")
    public void performAPIAnalytics(){
        System.out.println("\n =====> Performing API Analytics");
    }
}

package com.springboot.coredemo.common;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
public class BaseballCoach implements Coach{

    public BaseballCoach(){
        System.out.println("In constructor" + getClass().getSimpleName());
    }
    @Override
    public String getDailyWorkout() {
        return "Sping 30 minutes in batting practice";
    }
}

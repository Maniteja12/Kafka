package com.springboot.coredemo.rest;

import com.springboot.coredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    //define a private field for dependency
    private Coach myCoach;
/*
    //define a contructor for dependency injection
    @Autowired
    public DemoController (Coach theCoach){
        myCoach = theCoach;
    }
*/

    //Using setter injection
    @Autowired
    public void setMyCoach(@Qualifier("cricketCoach") Coach theCoach){
        myCoach=theCoach;
    }

    @GetMapping("/dailyWorkout")
    public  String getDailyWorkout(){
        return myCoach.getDailyWorkout();
    }


}

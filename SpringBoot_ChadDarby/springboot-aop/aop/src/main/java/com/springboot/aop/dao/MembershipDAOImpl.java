package com.springboot.aop.dao;

import org.springframework.stereotype.Repository;

@Repository //makes available for component scanning
public class MembershipDAOImpl implements MembershipDAO{
    @Override
    public Boolean addSillyMember() {
        System.out.println(getClass() + " DOING MY DB WORK :: ADDING A MEMBERSHIP ACCOUNT");
        return true;
    }

    @Override
    public void goToSleep() {
        System.out.println(getClass() + " DOING MY DB WORK :: Going to sleep now");
    }
}

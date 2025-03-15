package com.springboot.aop.dao;

import com.springboot.aop.Account;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository //makes available for component scanning
public class AccountDAOImpl implements AccountDAO{

    private String name;
    private String serviceCode;

    public String getName() {
        System.out.println(getClass() + " getName()");
        return name;
    }

    public void setName(String name) {
        System.out.println(getClass() + " setName()");
        this.name = name;
    }

    public String getServiceCode() {
        System.out.println(getClass() + " getServiceCode()");
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        System.out.println(getClass() + " setServiceCode()");
        this.serviceCode = serviceCode;
    }

    @Override
    public List<Account> findAccounts(boolean tripWire) {

        //simulating an exception
        if(tripWire){
            throw  new RuntimeException(("No soup for you"));
        }

        List<Account> accounts = new ArrayList<>();
        Account account1 = new Account("John","Silver");
        Account account2 = new Account("Nymi","Gold");
        Account account3 = new Account("Erin","Platinum");

        accounts.add(account1);
        accounts.add(account2);
        accounts.add(account3);
        return accounts;
    }

    @Override
    public List<Account> findAccounts() {
       return findAccounts(false);
   }

    @Override
    public void addAccount(Account account, boolean vipFlag) {
        System.out.println(getClass() + " DOING MY DB WORK :: ADDING AN ACCOUNT");
    }

    @Override
    public boolean doWork() {
        System.out.println(getClass() + " doWork()");
        return false;
    }
}

package com.springboot.aop.dao;

import com.springboot.aop.Account;

import java.util.List;

public interface AccountDAO {

    void addAccount(Account account, boolean vipFlag);
    boolean doWork();
     String getName();
     void setName(String name);
     String getServiceCode();
     void setServiceCode(String serviceCode);
     List<Account> findAccounts(boolean tripWire);
    List<Account> findAccounts();
}

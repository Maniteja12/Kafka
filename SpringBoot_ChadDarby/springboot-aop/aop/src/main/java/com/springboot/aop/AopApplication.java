package com.springboot.aop;

import com.springboot.aop.dao.AccountDAO;
import com.springboot.aop.dao.MembershipDAO;
import com.springboot.aop.service.TrafficFortuneService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class AopApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AccountDAO theAccountDAO, MembershipDAO membershipDAO
	, TrafficFortuneService trafficFortuneService){
		return  runner->{
			//demoTheBeforeAdvice(theAccountDAO, membershipDAO);
			//demoAfterReturningAdvice(theAccountDAO);
			//demoTheAfterThrowingAdvice(theAccountDAO);
			//demoTheAfterAdvice(theAccountDAO);
			//demoTheAroundAdvice(trafficFortuneService);
			demoTheAroundAdviceHandleException(trafficFortuneService);
		};
	}

	private void demoTheAroundAdviceHandleException(TrafficFortuneService trafficFortuneService) {
		System.out.println("Main Program: demoTheAroundAdviceHandleException");
		System.out.println("Calling getFortune");
		boolean tripWire = true;
		String data = trafficFortuneService.getFortune(tripWire);
		System.out.println("My Data is ::"+data);
	}

	private void demoTheAroundAdvice(TrafficFortuneService trafficFortuneService) {
		System.out.println("Main Program: demoTheAroundService");
		System.out.println("Calling getFortune");
		String data = trafficFortuneService.getFortune();
		System.out.println("My Data is ::"+data);
	}

	private void demoTheAfterAdvice(AccountDAO theAccountDAO) {
		List<Account> theAccounts = null;
		try {
			boolean tripWire = false;
			theAccounts = theAccountDAO.findAccounts(tripWire);
		}catch (Exception ex){
			System.out.println("\n\nMain Program : .....exception caught "+ex);

		}
		System.out.println("\n\n\n Main Program :: demoAfterReturningAdvice");
		System.out.println("---------");
		System.out.println(theAccounts);
	}

	private void demoTheAfterThrowingAdvice(AccountDAO accountDAO) {
		List<Account> theAccounts = null;
		try {
			boolean tripWire = true;
			theAccounts = accountDAO.findAccounts(tripWire);
		}catch (Exception ex){
			System.out.println("\n\nMain Program : .....exception caught "+ex);
		}
		System.out.println("\n\n\n Main Program :: demoAfterReturningAdvice");
		System.out.println("---------");
		System.out.println(theAccounts);
	}

	private void demoTheBeforeAdvice(AccountDAO theAccountDAO, MembershipDAO membershipDAO) {
		Account account = new Account();
		account.setName("Dan");
		account.setLevel("Gold");
		theAccountDAO.addAccount(account, true);
		theAccountDAO.doWork();
		membershipDAO.goToSleep();
		membershipDAO.addSillyMember();

		//Call to getter and setter methods
		theAccountDAO.setName("foobar");
		theAccountDAO.setServiceCode("silver");

		String name = theAccountDAO.getName();
		String code = theAccountDAO.getServiceCode();
	}

	private void demoAfterReturningAdvice(AccountDAO accountDAO){
		List<Account> accounts = accountDAO.findAccounts();
		List<Account> theAccounts = accountDAO.findAccounts();
		System.out.println("\n\n\n Main Program :: demoAfterReturningAdvice");
		System.out.println("---------");
		System.out.println(theAccounts);
	}
}

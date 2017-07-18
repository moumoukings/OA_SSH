package com.jboa.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public abstract class BaseTest {
	protected ApplicationContext context ;
	public BaseTest(){
		context = new ClassPathXmlApplicationContext("applicationContext.xml");
	}
	public BaseTest (String configFilePath){
		context = new ClassPathXmlApplicationContext(configFilePath);
	}
	public ApplicationContext getContext() {
		return context;
	}
	public void setContext(ApplicationContext context) {
		this.context = context;
	}
}

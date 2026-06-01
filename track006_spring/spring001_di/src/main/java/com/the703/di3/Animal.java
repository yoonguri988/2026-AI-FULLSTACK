package com.the703.di3;

import org.springframework.stereotype.Component;

@Component("animal")
public interface Animal {
	public String eat();
	public String sleep();
	public String poo();
}

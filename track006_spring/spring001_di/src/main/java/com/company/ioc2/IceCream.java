package com.company.ioc2;

import org.springframework.stereotype.Component;

@Component("iceCream")
public interface IceCream {
    public String flavor();
    public String scoop();
    public String melt();
}

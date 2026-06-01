package spring001_di;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.company.ioc1.IceCreamShop;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/test1.xml")
public class IceCreamTest1 {
	@Autowired ApplicationContext context;
	
	@Test
	public void test1() {
	    IceCreamShop shop = (IceCreamShop) context.getBean("iceCreamShop");
	    shop.print();
	    
	    IceCreamShop shop2 = (IceCreamShop) context.getBean("iceCreamShop2");
	    shop2.print();
	}
}

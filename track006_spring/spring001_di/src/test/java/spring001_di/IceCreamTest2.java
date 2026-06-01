package spring001_di;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.company.ioc2.IceCreamShop;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/test2.xml")
public class IceCreamTest2 {
	@Autowired ApplicationContext context;
	
	@Test
	public void test1() {
	    IceCreamShop shop = (IceCreamShop) context.getBean("iceCreamShop");
	    shop.print();
	}
}

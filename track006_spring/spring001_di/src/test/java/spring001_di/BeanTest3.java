package spring001_di;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.the703.di3.AnimalFarm;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/beans3.xml")
public class BeanTest3 {
	// import org.springframework.context.ApplicationContext
	@Autowired ApplicationContext context;
	
	@Test
	public void test() { // 스프링이 관리하는 부품객체            .xml의 id값과 동일
		AnimalFarm animalFarm = (AnimalFarm) context.getBean("animalFarm");
		animalFarm.print();
		
//		AnimalFarm animalFarm2 = (AnimalFarm) context.getBean("animalFarm2");
//		animalFarm2.print();
	}
}

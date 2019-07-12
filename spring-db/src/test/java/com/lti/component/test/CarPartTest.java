
package com.lti.component.test;



import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lti.component.CarPart;
import com.lti.component.CarPartsInventory;
import com.lti.component.CarPartsInventoryImpl1;
 public class CarPartTest {

	 // 
	@Test
	public void insertTest() {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-config.xml");
		
		CarPartsInventory cpi = (CarPartsInventory)context.getBean("carPartsImp3");
		
		CarPart carpart=new CarPart();
		carpart.setPartNo(7);
		carpart.setPartName("Engine");
		carpart.setCarModel("Ford");
		carpart.setQuantity(100);
		
		cpi.addNewPart(carpart);
		
	}



	@Test
	public void fetchTest() {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-config.xml");
		
		CarPartsInventory cpi = (CarPartsInventory)context.getBean("carPartsImp4");
		
		List<CarPart> list=cpi.getAvailableParts(8);
		for(CarPart c:list) {
			System.out.println("Part No :"+c.getPartNo());
			System.out.println("Part name :"+c.getPartName());
			System.out.println("Car Model :"+c.getCarModel());
			System.out.println("Quantity :"+c.getQuantity());
			System.out.println("\n");
		}
		
		
		
	}
}

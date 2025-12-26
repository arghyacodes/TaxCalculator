package com.taxcalculator.property;

import java.util.List;

import com.taxcalculator.Messages;
import com.taxcalculator.Read;

public class PropertyTax {
	public void property() throws Exception{
		int optionSelector=0;
		boolean running = true;
		while(running) {
			System.out.println(Messages.propertytaxmenu);
			optionSelector = Read.sc.nextInt();
			PropertyDAO p1 = new PropertyDAO();
			switch (optionSelector) {
			case 1:
				PropertyDTO prop1 = new PropertyDTO();
				System.out.println("ENTER THE PROPERTY DETAILS");
				System.out.print("ENTER THE BASE VALUE OF LAND");
				prop1.setBasevalue(Read.sc.nextInt());
				System.out.print("ENTER THE BUILT-UP AREA OF LAND");
				prop1.setArea(Read.sc.nextInt());
				System.out.print("ENTER THE AGE OF LAND IN YEARS");
				prop1.setAge(Read.sc.nextInt());
				System.out.println("IS THE LAND LOCATED IN CITY?(Y:YES, N:NO)");
				prop1.setIncity(Read.sc.next());
				
				p1.addproperty(prop1);
				break;
			case 2:
				System.out.println("ENTER THE PROPERTY ID");
				int key = Read.sc.nextInt();
//				PropertyDTO prop2 = new PropertyDTO();
//				prop2.setId(Read.sc.nextInt());
//				System.out.println(p1.getbyid(prop2));
				PropertyTablePrinter.printById(p1, key);
				break;
			case 3:
//				List<PropertyDTO> sampleproperty = p1.getallproperties();
//				sampleproperty.forEach(System.out :: println);
				
				PropertyTablePrinter.printAll(p1);
				break;
			case 4:
				running = false;
				System.out.println(Messages.mainmenu);
				break;
			default:
				System.out.println(Messages.invalidSelection);
//				break;
			}
		}
	}
}
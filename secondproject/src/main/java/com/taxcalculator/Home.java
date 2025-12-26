package com.taxcalculator;

import com.taxcalculator.login.LoginService;
import com.taxcalculator.property.PropertyTax;
import com.taxcalculator.report.CombinedTaxPrinter;
import com.taxcalculator.vehicle.VehicleTax;

public class Home{

	public static void main(String[] args) {
		Welcome w1 = new Welcome();
		w1.welcome();
		
//		authentication
		LoginService loginService = new LoginService();
		boolean loggedIn = false;
		while(!loggedIn) {
			loggedIn = loginService.login();
		}
		
//		authentication if success then next
		System.out.println(Messages.mainmenu);
		
		int optionSelector=0;
		boolean running = true;
		while(running) {
		optionSelector = Read.sc.nextInt();
		
		switch (optionSelector) {
		case 1:
			PropertyTax pt = new PropertyTax();
			try {
				pt.property();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			break;
		case 2:
			VehicleTax vt = new VehicleTax();
			try {
				vt.vehicle();
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
			break;
		case 3:
			CombinedTaxPrinter cb = new CombinedTaxPrinter();
			cb.printCombinedReport();
			break;
		case 4:
			running = false;
			break;
		default:
			System.out.println(Messages.invalidSelection);
//			break;
		}
		}
	}

}

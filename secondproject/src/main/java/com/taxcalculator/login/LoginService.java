package com.taxcalculator.login;

import com.taxcalculator.Read;

public class LoginService {
	private final TaxpayerLoginDAO dao = new TaxpayerLoginDAO();
	
	public boolean login() {
		try {
			System.out.println("PLEASE LOGIN TO CONTINUE");
			System.out.print("USERNAME - ");
			String username = Read.sc.next();
			
			System.out.print("PASSWORD - ");
			String password = Read.sc.next();
			
			boolean valid = dao.isValidUser(username, password);
			
			if(valid) {
				System.out.println("LOGIN SUCCESSFUL");
				return true;
			}
			else {
				System.out.println("INVALID USERNAME OR PASSWORD. PLEASE TRY AGAIN");
				return false;
			}
		} catch (Exception e) {
			System.out.println("Error during login: "+e.getMessage());
			return false;
		}
	}
}

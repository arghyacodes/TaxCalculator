package com.taxcalculator.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.taxcalculator.DBconnection;

public class TaxpayerLoginDAO {
	public boolean isValidUser(String username, String password)throws Exception{
		Connection con = DBconnection.getConnection();
		
		String sql= "select 1 from taxpayerlogin where username=? and password=?";
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, username);
		ps.setString(2, password);
		
		ResultSet rs = ps.executeQuery();
		
		boolean exists= rs.next();
		
		rs.close();
		ps.close();
		
		return exists;
	}
}

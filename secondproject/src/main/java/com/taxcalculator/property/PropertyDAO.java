package com.taxcalculator.property;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

import com.taxcalculator.DBconnection;

public class PropertyDAO {
	
	
	public List<PropertyDTO> getallproperties() throws Exception{
		Connection con = DBconnection.getConnection();
		//code to check if the table is empty or not
		PreparedStatement chk = con.prepareStatement("select count(*) from property");
		ResultSet chkrs = chk.executeQuery();
		int rowCount = 0;
		if(chkrs.next())rowCount = chkrs.getInt(1);
		if(rowCount==0)System.out.println("No Data Present at This Moment");
		//code to get all properties
		PreparedStatement ps = con.prepareStatement("select * from property");
		ResultSet rs = ps.executeQuery();
		//object relation mapping
		List<PropertyDTO> sampleproperty = new ArrayList<PropertyDTO>();
		ResultSetMetaData rm = rs.getMetaData();
//		int count = rm.getColumnCount();
		while(rs.next()) {
			sampleproperty.add(new PropertyDTO(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getInt(4), rs.getString(5),rs.getDouble(6)));
		}
		return sampleproperty;
	}
	
	public int addproperty(PropertyDTO prop) throws Exception {
	    Connection con = DBconnection.getConnection();

	    PreparedStatement ps = con.prepareStatement(
	        "INSERT INTO property (basevalue, area, age, incity, propertytax) VALUES (?, ?, ?, ?, ?)",
	        PreparedStatement.RETURN_GENERATED_KEYS
	    );

	    ps.setInt(1, prop.getBasevalue());
	    ps.setInt(2, prop.getArea());
	    ps.setInt(3, prop.getAge());
	    ps.setString(4, prop.getIncity().toUpperCase());

	    double tax = calculatePropertyTax(prop);
	    ps.setDouble(5, tax);

	    ps.executeUpdate();
	    System.out.println("Property Added Successfully");

	    //get auto-generated ID
	    ResultSet rs = ps.getGeneratedKeys();
	    int generatedId = 0;

	    if (rs.next()) {
	        generatedId = rs.getInt(1);   // MySQL always returns the first column as ID
	        prop.setId(generatedId);
//	        System.out.println("Inserted Property ID = " + generatedId);
	    }

	    return generatedId;  
	}


	public double calculatePropertyTax(PropertyDTO p) {
		 double tax = p.getArea() * p.getAge() * p.getBasevalue();

		    if (p.getIncity().equalsIgnoreCase("Y")) {	//works with both y and Y
		        tax += p.getArea() / 2.0;   // add area/2 only if incity = Y
		    }

		    return tax;
	}
	
	public PropertyDTO getbyid(PropertyDTO ob)throws Exception{
		Connection con = DBconnection.getConnection();
		PreparedStatement ps = con.prepareStatement("select * from property where id=?");
		ps.setInt(1, ob.getId());
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			ob.setId(rs.getInt(1));
			ob.setBasevalue(rs.getInt(2));
			ob.setArea(rs.getInt(3));
			ob.setAge(rs.getInt(4));
			ob.setIncity(rs.getString(5));
			ob.setPropertytax(rs.getDouble(6));
		}
		else {
			throw new Exception(ob.getId()+" does not exist");
		}
		return ob;
	}
}

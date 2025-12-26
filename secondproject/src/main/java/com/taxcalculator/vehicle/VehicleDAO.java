package com.taxcalculator.vehicle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

import com.taxcalculator.DBconnection;
import com.taxcalculator.property.PropertyDTO;

public class VehicleDAO {

	public List<VehicleDTO> getallvehicles() throws Exception{
		Connection con = DBconnection.getConnection();
		
		//code to check if table is empty or not
		PreparedStatement chk = con.prepareStatement("select count(*) from vehicle");
		ResultSet chkrs = chk.executeQuery();
		int rowCount = 0;
		if(chkrs.next())rowCount = chkrs.getInt(1);
		if(rowCount==0)System.out.println("No Data Present at This Moment");
		
		//code to get all the vehicles
		PreparedStatement ps = con.prepareStatement("select * from vehicle");
		ResultSet rs = ps.executeQuery();
		//object relation mapping
		List<VehicleDTO> samplevehicle = new ArrayList<VehicleDTO>();
		ResultSetMetaData rm = rs.getMetaData();
//		int count = rm.getColumnCount();
		while(rs.next()) {
			samplevehicle.add(new VehicleDTO(rs.getString(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5),rs.getDouble(6),rs.getDouble(7))
);
		}
		return samplevehicle;
	}
	
	public void addvehicle(VehicleDTO prop) throws Exception {
	    Connection con = DBconnection.getConnection();

	    PreparedStatement ps = con.prepareStatement(
	        "INSERT INTO vehicle VALUES (?, ?, ?, ?, ?,?,?)",
	        PreparedStatement.RETURN_GENERATED_KEYS
	    );

//	    ps.setInt(1, prop.getRegno());
	    if(validateRC(prop)) {
	    	ps.setString(1, prop.getRegno());
		    ps.setString(2, prop.getBrand().toUpperCase());
		    ps.setInt(3, prop.getTopspeed());
		    ps.setInt(4, prop.getSeatingcapacity());
		    String vehfuel = fueltype(prop);
		    ps.setString(5, vehfuel);
		    ps.setDouble(6, prop.getPurchasecost());
		    

		    double tax = calculateVehicleTax(prop);
		    ps.setDouble(7, tax);

		    ps.executeUpdate();
		    System.out.println("Vehicle Added Successfully");
	    }
	    else {
	    	System.out.println("INVALID  RC NUMBER, TRY AGAIN");
	    }
	    /*
	    ps.setString(2, prop.getBrand());
	    ps.setInt(3, prop.getTopspeed());
	    ps.setInt(4, prop.getSeatingcapacity());
	    ps.setInt(5, prop.getFueltype());
	    ps.setDouble(6, prop.getPurchasecost());
	    

	    double tax = calculateVehicleTax(prop);
	    ps.setDouble(7, tax);

	    ps.executeUpdate();
	    */
	}
	public double calculateVehicleTax(VehicleDTO p) {
		 double tax = 0.0f;
		 int fuel = Integer.parseInt(p.getFueltype());
		    if (fuel==1) {
		        tax = p.getTopspeed()+p.getSeatingcapacity()+0.10*(p.getPurchasecost());   // fuel type 1 i.e. petrol
		    }
		    if (fuel==2) {
		        tax = p.getTopspeed()+p.getSeatingcapacity()+0.11*(p.getPurchasecost());   // fuel type 1 i.e. diesel
		    }
		    if (fuel==3) {
		        tax = p.getTopspeed()+p.getSeatingcapacity()+0.10*(p.getPurchasecost());   // fuel type 1 i.e. cng
		    }
		    return tax;
	}

	public boolean validateRC(VehicleDTO p) {
		//validates if rc number is in the XXXX format or not
		boolean validrc = false;
		String rc = p.getRegno();
		int rcnumber = Integer.parseInt(rc);
		if(rc.matches("\\d{4}") && rcnumber>0) validrc = true;
//		System.out.println("original "+rcnumber+" String "+rc+"validrc "+validrc);
		return validrc;
	}

	public String fueltype(VehicleDTO p) {
		String fuel = null;
		int input = Integer.parseInt(p.getFueltype());
		if(input==1)fuel="PETROL";
		if(input==2)fuel="DIESEL";
		if(input==3)fuel="CNG/LPG";
		return fuel;	
	}
	
	public VehicleDTO getbyid(VehicleDTO ob)throws Exception{
		Connection con = DBconnection.getConnection();
		PreparedStatement ps = con.prepareStatement("select * from vehicle where regno=?");
		ps.setString(1, ob.getRegno());
		ResultSet rs = ps.executeQuery();
		if(rs.next()) {
			ob.setRegno(rs.getString(1));
			ob.setBrand(rs.getString(2));
			ob.setTopspeed(rs.getInt(3));
			ob.setSeatingcapacity(rs.getInt(4));
			ob.setFueltype(rs.getString(5));
			ob.setPurchasecost(rs.getDouble(6));
			ob.setVehicletax(rs.getDouble(7));
		}
		else {
			throw new Exception(ob.getRegno()+" does not exist");
		}
		return ob;
	}
}

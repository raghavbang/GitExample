package com.lti.component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("carPartsImp2")
public class CarPartsInventoryImpl2 implements CarPartsInventory{
	
	@Autowired
	@Qualifier("ds1")
	private DataSource datasource;

	public void addNewPart(CarPart carPart) {
		
		Connection conn=null;	
		PreparedStatement stmt=null;		
		try {
			conn=datasource.getConnection();
			String sql="insert into TBL_CARPART values(?,?,?,?)";
			stmt=conn.prepareStatement(sql);
			stmt.setInt(1,carPart.getPartNo());
			stmt.setString(2, carPart.getPartName());
			stmt.setString(3, carPart.getCarModel());
			stmt.setInt(4, carPart.getQuantity());
			stmt.executeUpdate();				//any DML statement
			
			
		}
		
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			
			try {
				conn.close();
			}
			catch(Exception e) {
				
			}
		}
		
	}

	public List<CarPart> getAvailableParts(int number) {
		
		
		Connection conn=null;	//manages the connection between app and database
		PreparedStatement stmt=null;		//prepare a statement
		ResultSet rs=null;							//helps us in fetching the result from the database
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");	//loading driver
			
			//Establish Connection with the database
			conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","hr","hr");
			String sql="select * from TBL_CARPART";
			
			stmt=conn.prepareStatement(sql);
			
			
			
			rs=stmt.executeQuery();				//executing statement and storing result in rs
			
			List<CarPart> carpart=new ArrayList<CarPart>();
			while(rs.next()) {
				CarPart cp=new CarPart();
				cp.setPartNo(rs.getInt(1));
				cp.setPartName(rs.getString(2));
				cp.setCarModel(rs.getString(3));
				cp.setQuantity(rs.getInt(4));
				carpart.add(cp);
				
			}
			return carpart;
			
			
		}
		catch(ClassNotFoundException e){
			
			System.out.println("JDBC driver not found");
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			
			try {
				conn.close();
			}
			catch(Exception e) {
				
			}
		}
		
		return null;
		
		
	}
	
	

}

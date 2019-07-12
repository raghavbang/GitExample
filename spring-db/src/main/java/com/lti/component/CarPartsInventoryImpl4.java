package com.lti.component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;



@Component("carPartsImp4")
public class CarPartsInventoryImpl4 implements CarPartsInventory {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public void addNewPart(CarPart carPart) {
		entityManager.persist(carPart);
	}

	@Transactional
	public List<CarPart> getAvailableParts(int number) {
		Query q= entityManager.createQuery("select c from CarPart c where c.partNo=:br");
		q.setParameter("br", number);
		List<CarPart> list=q.getResultList();
		return list;
		
	}

	/*
	 * public void addNewPart(CarPart carPart) {
	 * 
	 * JdbcTemplate jt = new JdbcTemplate(datasource);
	 * jt.update("insert into TBL_CARPART values(?,?,?,?)",
	 * 
	 * carPart.getPartNo(), carPart.getPartName(), carPart.getCarModel(),
	 * carPart.getQuantity());
	 * 
	 * }
	 * 
	 * 
	 * class CarPartRowMapper implements RowMapper<CarPart>{
	 * 
	 * public CarPart mapRow(ResultSet rs, int rowNum) throws SQLException { CarPart
	 * carPart=new CarPart(); carPart.setPartNo(rs.getInt(1));
	 * carPart.setPartName(rs.getString(2)); carPart.setCarModel(rs.getString(3));
	 * carPart.setQuantity(rs.getInt(4));
	 * 
	 * return carPart; }
	 * 
	 * }
	 * 
	 * 
	 * public List<CarPart> getAvailableParts(int number) {
	 * 
	 * JdbcTemplate jt = new JdbcTemplate(datasource);
	 * 
	 * 
	 * String sql="select * from TBL_CARPART where part_no=?"; List<CarPart>
	 * list=jt.query(sql, new CarPartRowMapper(),number);
	 * 
	 * return list;
	 * 
	 * 
	 * 
	 * 
	 * 
	 * Connection conn=null; //manages the connection between app and database
	 * PreparedStatement stmt=null; //prepare a statement ResultSet rs=null; //helps
	 * us in fetching the result from the database
	 * 
	 * try {
	 * 
	 * Class.forName("oracle.jdbc.driver.OracleDriver"); //loading driver
	 * 
	 * //Establish Connection with the database
	 * conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","hr",
	 * "hr"); String sql="select * from TBL_CARPART";
	 * 
	 * stmt=conn.prepareStatement(sql);
	 * 
	 * stmt.setInt(1,10);
	 * 
	 * rs=stmt.executeQuery(); //executing statement and storing result in rs
	 * 
	 * List<CarPart> carpart=new ArrayList<CarPart>(); while(rs.next()) { CarPart
	 * cp=new CarPart(); cp.setPartNo(rs.getInt(1));
	 * cp.setPartName(rs.getString(2)); cp.setCarModel(rs.getString(3));
	 * cp.setQuantity(rs.getInt(4)); carpart.add(cp);
	 * 
	 * } return carpart;
	 * 
	 * 
	 * } catch(ClassNotFoundException e){
	 * 
	 * System.out.println("JDBC driver not found");
	 * 
	 * } catch(SQLException e) { e.printStackTrace(); } finally {
	 * 
	 * try { conn.close(); } catch(Exception e) {
	 * 
	 * } }
	 * 
	 * return null;
	 * 
	 * 
	 * }
	 * 
	 * 
	 * }
	 */

}

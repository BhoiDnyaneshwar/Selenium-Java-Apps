package com.rahul.DBUtils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DbPage {
	public static Connection con;
	public static ResultSet storedResultset;
	public static List<HashMap<Object, Object>> storedResultsetAsList;
	public Db_Utils util=new Db_Utils(); 
	
	public Connection getConnection(String dbName) {
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String connectionUrl = "jdbc:sqlserver://127.0.0.1:1433;encrypt=true;databaseName="+dbName+";integratedSecurity=true;trustServerCertificate=true" ;
			 con = DriverManager.getConnection(connectionUrl);
			 
			 if(con.isClosed()) {
					System.out.println("Connection is not created");
				}else{
					System.out.println("Connection is created");
					return con;
				}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return con;
		
	}
	
	public void getExecuteQuery(String query, String table) {
		switch(query){
			case "SELECT": executeSelectQuery(table);
			break;
			case "UPDATE": executeUpdateQuery(table);
			break;
			default : System.out.println("Query Not Available");
		}
	}
	
	public void executeSelectQuery(String table) {
		String query=QueryConstant.selectQuery.replace("table", table);
		try {
			Statement stmt=con.createStatement();
			storedResultset=stmt.executeQuery(query);
			storedResultsetAsList=util.getResultSetToList(storedResultset);
			System.out.println(storedResultset);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void executeUpdateQuery(String table) {

	}
	
	public List getSpecColumnValues(String columnName) {
		List dbValue=new ArrayList();
		storedResultsetAsList=util.getResultSetToList(storedResultset);
		
		for(HashMap<Object, Object> i:storedResultsetAsList) {
			String value;
			if(i.get(columnName)==null) {
				value=null;
			}else {
				value=i.get(columnName).toString();
			}
			dbValue.add(value);
		}
		
		return dbValue;
		
	}
}

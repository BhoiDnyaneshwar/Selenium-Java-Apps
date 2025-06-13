package com.rahul.DBUtils;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Db_Utils {

	public String getValueBasedOnCondn(List<HashMap<Object, Object>> ResultsetAsList, String expectedColValue, String columnName, String Condition)  {
		for(HashMap i:ResultsetAsList) {
			if(i.containsKey(columnName)&&Condition.equals(i.get(columnName))) {
				System.out.println(i.get(expectedColValue).toString());
				return i.get(expectedColValue).toString();
				
			}
		}
		return null;
	}
	
	
	public void checkExpectedValueInList(List list, String expctedValue) {
		if(expctedValue.contentEquals("null")) {
			expctedValue=null;
		}
		
		for(Object i:list) {
			String value;
			if(i==null) {
				value=null;
			}else{
				value=i.toString();
			}
			
			//Assert.assertEqual(value, expctedValue)
		}
	}
	
	//dbutil
	public List<HashMap<Object, Object>> getResultSetToList(ResultSet resultset) {
		List<HashMap<Object, Object>> list=new ArrayList<HashMap<Object, Object>>();
		try {
			ResultSetMetaData metData=resultset.getMetaData();
			int columnCnt=metData.getColumnCount();
			while(resultset.next()) {
				HashMap hm=new HashMap(columnCnt);
				
				for(int i = 1; i <= columnCnt; i++) {
				hm.put(metData.getColumnName(i), resultset.getObject(i));
				}
				
				list.add(hm);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

}

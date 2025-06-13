package com.rahul.pages;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONParsing {
	JSONObject jb;

	public String getJSONData(String filePath, String key) {
		try {
			FileReader f=new FileReader(filePath);
			JSONParser jp=new JSONParser();
			Object obj=jp.parse(f);
		    jb=(JSONObject)obj;
		   String keys= (String)jb.get(key);
			return keys;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public List<String> getJSONDataOfArray(String filePath, String arr, String key) {
		List<String> Data=new ArrayList<String>();
		try {
			FileReader f=new FileReader(filePath);
			JSONParser jp=new JSONParser();
			Object obj=jp.parse(f);			
		    jb=(JSONObject)obj;
		    JSONArray jArr=(JSONArray)jb.get(arr);
			for(int i=0;i<jArr.size();i++) {
				JSONObject jbs=(JSONObject)jArr.get(i);
				Data.add((String)jbs.get(key));
			}			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Data;
	}
	
	public List<HashMap<String, String>> getJsonDataToHashMap(String filePath) {
		String jsonContent="";
		try {
		 jsonContent=FileUtils.readFileToString(new File(filePath), StandardCharsets.UTF_8);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ObjectMapper mapper=new ObjectMapper();
		List<HashMap<String, String>> data = null;

        try {
            data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {});
        } catch (IOException e) {
            e.printStackTrace();
        }

        return data; 
	}
	
}

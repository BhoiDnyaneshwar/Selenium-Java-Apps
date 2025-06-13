package com.rahul.pages;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.hc.client5.http.utils.Base64;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class XMLParsing {

	public String configXMLReader(String tagname, String data) {
		String xmlData="";
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();

			Document document = builder.parse(".\\Config\\config.xml");
			document.getDocumentElement().normalize();
			NodeList nodeList = document.getElementsByTagName(tagname);
					
			for (int i = 0; i < nodeList.getLength(); i++) {
				Element ele = (Element) nodeList.item(i);
				if(data.equals("password")) {
					xmlData=decodePassword(ele.getAttribute(data));
					break;
				}else {
					xmlData=ele.getAttribute(data);
				}
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
		return xmlData;
	}
	
	
	public String decodePassword(String pass) {
		//String password=new String(Base64.encodeBase64(pass.getBytes()));
		String password=new String(Base64.decodeBase64(pass.getBytes()));
		return password;
	}
}

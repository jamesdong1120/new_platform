package com.pccw.system.utils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import com.pccw.system.convert.LinkedMapXmlConvert;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/*******************************************
 * <p>Description:xml工具类 </p>
 * <p>Company:PCCW </p> 
 * @author Jamesdong
 * @date 2016年2月24日 下午3:46:11
 ******************************************/
public class XMLUtils {
	
	/**
	 * 自定义转换map成xml
	 * 专属银企使用
	 * @param map
	 * @return
	 */
	public static String parseLinkedMapToXML(LinkedHashMap<String,Object> map){
		XStream xstream = new XStream(new DomDriver()); 
		 xstream.registerConverter(new LinkedMapXmlConvert());
		 xstream.alias("root", LinkedHashMap.class);
		 String xml=xstream.toXML(map).replace("<root>","").replace("</root>", "").replaceAll("((\r\n)|\n)[\\s\t ]*(\\1)+", "$1").replaceAll("^((\r\n)|\n)", "");
		 return xml;
	}
	
	/**
	 * 转换xml到map
	 * 专属银企使用
	 * @param xml
	 * @return
	 */
	public static LinkedHashMap<String,Object> parseXMLToLinkedMap(String xml){
		XStream xstream = new XStream(new DomDriver()); 
		 xstream.registerConverter(new LinkedMapXmlConvert());
		 xstream.alias("root", Map.class);
		 StringBuffer buffer=new StringBuffer();
		 buffer.append("<root> \n");
		 buffer.append(xml);
		 buffer.append("</root>");
		 LinkedHashMap<String,Object> result=(LinkedHashMap<String,Object>) xstream.fromXML(buffer.toString());
		 return result;
	}
	
	/**
	 * 转换对象成xml
	 * @param cls 对应的解析类
	 * @param rootName 根节点名字
	 * @param obj 实例对象
	 * @return
	 */
	public static String parseObjectToXML(Class cls,String rootName,Object obj){
		XStream xstream=new XStream();
		xstream.alias(rootName,cls );
		String xml=xstream.toXML(obj);
		return xml;
	}
	
	/**
	 * 解析xml到Object中
	 * @param cls
	 * @param rootName
	 * @param xml
	 * @return
	 */
	public static Object parseXMLToObject(Class cls,String rootName,String xml){
		XStream xstream=new XStream();
		xstream.alias(rootName,cls );
		Object obj=xstream.fromXML(xml);
		return obj;
	}
	
	/**
	 * 判断xml是否合法
	 * @param xml
	 * @return
	 */
	public static boolean validateXml(String xml){
		boolean flag=true;
		if(xml==null){
			flag=false;
		}else{
			InputStream is=new ByteArrayInputStream(xml.getBytes());
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			try {
				DocumentBuilder builder = dbf.newDocumentBuilder();
				builder.parse(is);
			} catch (Exception e) {
				flag=false;
			}
		}
		return flag;
	}

}

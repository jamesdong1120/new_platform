package com.pccw.system.test;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.fastjson.JSON;
import com.pccw.bedc.service.BciXmlParamsService;
import com.pccw.bedc.utils.SockectUitls;
import com.pccw.system.model.Book;
import com.pccw.system.model.PortalOrg;
import com.pccw.system.model.TestModel;
import com.pccw.system.service.PortalOrgService;
import com.pccw.system.utils.SHAUtil;
import com.pccw.system.utils.SpringUtils;
import com.pccw.system.utils.TemplateUtils;
import com.pccw.system.utils.XMLUtils;

public class MyTest {

	private BeanFactory factory = new ClassPathXmlApplicationContext(
			new String[] { "spring.xml", "spring-mybatis.xml" });

	private static final Logger logger = Logger.getLogger(MyTest.class);

	@Test
	public void testInsert() {
		PortalOrgService service = (PortalOrgService) factory
				.getBean("portalOrgService");
		for(int i=1;i<=20;i++){
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("orgId", new BigDecimal(i));
			map.put("orgFullName", "测试组织全称"+i);
			map.put("orgShortName", "测试组织简称"+i);
			map.put("orgPrincipal", "测试法人"+i);
			map.put("orgLocation", "测试所在地址"+i);
			map.put("orgAddress", "测试地址"+i);
			map.put("orgPostcode", "200123");
			map.put("orgPhone", "12345678901");
			map.put("orgLevel", new BigDecimal("1"));
			map.put("orgStatus", "1");
			map.put("createDate", new Date());
			service.insert(map);
		}
		
	}
	
	@Test
	public void testGetPageData(){
		PortalOrgService service = (PortalOrgService) factory
				.getBean("portalOrgService");
		Map<String,Object> map=new HashMap<String, Object>();
		List<PortalOrg> list=service.getPageData(map);
		String data=JSON.toJSONStringWithDateFormat(list,"yyyy-MM-dd");
		
		List<Map> mapData=JSON.parseObject(data, List.class);
		System.out.println(JSON.toJSON(mapData));
	}
	
	
	@Test
	public void test003(){
		Map<String,Object> map=new HashMap<String, Object>();
		Field[] fileds=TestModel.class.getDeclaredFields();
		for(Field field:fileds){
			String paramName=field.getName();
			String paramType=field.getType().getName();
			//String value=request.getParameter(paramName);
			map.put(paramName, paramType);
		}
		System.out.println(JSON.toJSON(map));
	}
	@Test
	public void testXMLLinkMap(){
		
		LinkedHashMap<String,Object> map=new LinkedHashMap<String, Object>();
		map.put("line1", "value1");
		
		map.put("line3", "value3");
		LinkedHashMap<String,Object> map2=new LinkedHashMap<String, Object>();
		map2.put("node1","value1_1");
		
		map.put("line2", map2);
		//String xml=XMLUtils.parseLinkedMapToXML(map);
		//System.out.print(xml);
		String xml="<trn-b2e0001-rq><b2e0001-rq><custdt>20060610114859</custdt><oprpwd>dL3ihejd</oprpwd>	<usbkey>1111111a</usbkey></b2e0001-rq></trn-b2e0001-rq>";
		LinkedHashMap<String,Object> map3=XMLUtils.parseXMLToLinkedMap(xml);
		doPraseMap(map3);
		
		
	}
	@Test
	public void test005(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(sdf.format(new Date()));
		for(int i=0;i<5;i++){
			for(int j=0;j<20;j++){
				MyThread my=new MyThread();
				my.start();
			}
			
		}
		System.out.println(sdf.format(new Date()));
	}
	
	@Test
	public void test006(){
		Properties properties=new Properties();
		InputStream in = this.getClass().getResourceAsStream( "/config.properties" );
		try {
			properties.load(in);
			String value= (String) properties.get("driverClassName");
			System.out.println(value);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testTemplate(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(sdf.format(new Date()));
		for(int i=0;i<100;i++){
			Map<String,String> sourceMap=new HashMap<String, String>();
			sourceMap.put("tempBaseLoaction", "/resources/msg_config/0045");
			sourceMap.put("tempName", "0082.ftl");
			Map<String,Object> paramMap=new HashMap<String, Object>();
			paramMap.put("key", 33434323);
			String data=TemplateUtils.readTemplate(sourceMap, paramMap);	
		}
		System.out.println(sdf.format(new Date()));
	}
	
	@Test
	public void testTemplateByString(){
		long t1 = System.currentTimeMillis(); // 排序前取得当前时间
		LinkedHashMap<String,Object> map=new LinkedHashMap<String, Object>();
		map.put("line1", "${line1}");
		
		map.put("line3", "${line3}");
		LinkedHashMap<String,Object> map2=new LinkedHashMap<String, Object>();
		map2.put("line22","${line22}");
		map.put("line2", map2);
		String xml=XMLUtils.parseLinkedMapToXML(map);
			String templateName="tf";
			String templateSource=xml;
			System.out.println(xml);
			Map<String,String> sourceMap=new HashMap<String, String>();
			Map<String,Object> paramMap=new HashMap<String, Object>();
			sourceMap.put("templateName", templateName);
			sourceMap.put("templateSource", templateSource);
			paramMap.put("line1", 11111);
			paramMap.put("line22", 22222);
			paramMap.put("line3", 3333);
			String data=TemplateUtils.readTemplateByString(sourceMap, paramMap);	
		System.out.println(data);
		long t2 = System.currentTimeMillis(); // 排序后取得当前时间  
		  
        Calendar c = Calendar.getInstance();  
        c.setTimeInMillis(t2 - t1);  
  
        System.out.println("耗时: " + c.get(Calendar.MINUTE) + "分 "  
                + c.get(Calendar.SECOND) + "秒 " + c.get(Calendar.MILLISECOND)  
                + " 毫秒");  
	}
	
	
	 
	
	
	public static String getCamelCaseString(String inputString,
			boolean firstCharacterUppercase) {
		StringBuilder sb = new StringBuilder();

		boolean nextUpperCase = false;
		for (int i = 0; i < inputString.length(); i++) {
			char c = inputString.charAt(i);

			switch (c) {
			case '_':
			case '-':
            case '@':
            case '$':
            case '#':
            case ' ':
            case '/':
            case '&':
				if (sb.length() > 0) {
					nextUpperCase = true;
				}
				break;

			default:
				if (nextUpperCase) {
					sb.append(Character.toUpperCase(c));
					nextUpperCase = false;
				} else {
					sb.append(Character.toLowerCase(c));
				}
				break;
			}
		}

		if (firstCharacterUppercase) {
			sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
		}

		return sb.toString();
	}
	@Test
	public void test007(){
		Book book=new Book("Spring in Action", "Craig Walls", "1935182358",
                "June 29th 2011", 31.98F);
		
		 Map<String, Object> map= objectToMap( book);
		System.out.println(map.get("title"));
		
		String aab="DDB_DDD";
		System.out.println(getCamelCaseString(aab, false));
		
	}
	
	
	public static Map<String, Object> objectToMap(Object obj) {    
        if(obj == null)  
            return null;      
   
        Map<String, Object> map = new HashMap<String, Object>();   
        try {
        	BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());    
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();    
            for (PropertyDescriptor property : propertyDescriptors) {    
                String key = property.getName();    
                if (key.compareToIgnoreCase("class") == 0) {   
                    continue;  
                }  
                Method getter = property.getReadMethod();  
                Object value = getter!=null ? getter.invoke(obj) : null;  
                map.put(key, value);  
            }    
			
		} catch (Exception e) {
			e.printStackTrace();
		}
        return map;  
    }    
       
 private void doPraseMap(LinkedHashMap<String,Object> map){
	 for(Map.Entry<String, Object> entry:map.entrySet()){
		 if(entry.getValue() instanceof Map){
			 System.out.println(entry.getKey()+"begin ");
			 doPraseMap((LinkedHashMap<String,Object>)entry.getValue());
			 System.out.println(entry.getKey()+"end ");
		 }else{
			 System.out.println(entry.getKey()+"---"+entry.getValue());
		 }
	 }
 }
 @Test
 public void testPattern(){
	 
	  String template = "${cat} really needs some ${beverage}."; 
	  String []  str=StringUtils.substringsBetween(template, "${", "}");
	for(String s:str){
		System.out.println(s);
	}
	 
	 
 }
 @Test
 public void testSocket(){
	 
	String msg=SockectUitls.send("10.20.41.13", 448, "1", 50000); 
	System.out.println(msg);
	 
 }
 
 @Test
 public void testMap(){
	 BciXmlParamsService bciXmlParamsService=SpringUtils.getBean("bciXmlParamsService");
	 List<Map<String,String>> list=bciXmlParamsService.testmap();
	 System.out.println(JSON.toJSONString(list));
	 
 }
 @Test
 public void testSHA(){
	 
	String pwd= SHAUtil.shaEncode("jamesdong");
	System.out.println(pwd);
	System.out.println(pwd.length());
	if(pwd.equals("479784f62006b96de9878d722d5ece9792a90e8c")){
		System.out.println("same");
	}
	 
 }
@Test 
 public void testDigui(){
	 PortalOrgService service=SpringUtils.getBean("portalOrgService");
	String data= service.getChildOrgTreeJsonData(null);
	 System.out.println(data);
	 
 }
 
}

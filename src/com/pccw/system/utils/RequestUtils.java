package com.pccw.system.utils;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class RequestUtils {

	/**
	 * 获取jquery-easyui的分页参数 start为起始位置 end为结束位置
	 * 
	 * @param request
	 * @return
	 */
	public static Map<String, Object> getPageParamForEasyUI(
			HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			request.setCharacterEncoding("UTF-8");
			Integer page = Integer.parseInt(request.getParameter("page"));
			Integer rows = Integer.parseInt(request.getParameter("rows"));
			Integer start = (page - 1) * rows;
			Integer end = page * rows;
			map.put("start", new BigDecimal(start));
			map.put("end", new BigDecimal(end));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return map;

	}

	/**
	 * 根据Class类获取条件参数
	 * 
	 * @param request
	 * @param cls
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> getConditionByClass(
			HttpServletRequest request, Class cls, String dateFormat)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		Map<String, Object> map = new HashMap<String, Object>();
		Field[] fileds = cls.getDeclaredFields();
		for (Field field : fileds) {
			String paramName = field.getName();
			String paramType = field.getType().getName();
			String paramValue = request.getParameter(paramName);
			if (!"".equals(paramValue) && null != paramValue) {
				Object value = converStringToObject(paramValue, paramType,
						dateFormat);
				map.put(paramName, value);
			}
		}
		return map;
	}
	
	
	/**
	 * 根据Class类获取条件参数
	 * 
	 * @param request
	 * @param cls
	 * @return
	 * @throws Exception
	 */
	public static Object getObjectConditionByClass(
			HttpServletRequest request, Class cls, String dateFormat)
			throws Exception {
		request.setCharacterEncoding("UTF-8");
		Object obj=cls.newInstance();
		Field[] fileds = cls.getDeclaredFields();
		Method method=null;
		for (Field field : fileds) {
			String paramName = field.getName();
			String paramType = field.getType().getName();
			String paramValue = request.getParameter(paramName);
			field.getDeclaringClass();
			if (!"".equals(paramValue) && null != paramValue) {
				Object value = converStringToObject(paramValue, paramType,
						dateFormat);
				method=controlMethodByReflect(method, paramName, paramType, paramValue, cls);
				method.invoke(obj, value);
			}
		}
		return obj;
	}

	/**
	 * 把字符串转换成对象
	 * @param paramValue
	 * @param paramType
	 * @param dateFormat
	 * @return
	 * @throws Exception
	 */
	public static Object converStringToObject(String paramValue,
			String paramType, String dateFormat) throws Exception {
		Object obj = null;
		if (!"".equals(paramValue) && null != paramValue) {
			if ("java.util.Date".equals(paramType)) {
				SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
				obj = sdf.parse(paramValue);
			} else if ("java.math.BigDecimal".equals(paramType)) {
				obj = new BigDecimal(paramValue);
			} else if ("java.lang.String".equals(paramType)) {
				obj = paramValue;
			} else if ("java.lang.Integer".equals(paramType)
					|| "int".equals(paramType)) {
				obj = new Integer(paramValue);
			} else if ("java.lang.Double".equals(paramType)
					|| "double".equals(paramType)) {
				obj = new Double(paramValue);
			} else if ("java.lang.Float".equals(paramType)
					|| "float".equals(paramType)) {
				obj = new Float(paramValue);
			}
			
		}
		return obj;
	}
	
	public static String toUpCaseFirstChar(String src){
		if(src!=null&&!src.trim().equals("")){
			String upCaseString="";
			upCaseString=src.substring(0,1).toUpperCase()+src.substring(1,src.length());
			return upCaseString;
		}
		else{
			throw new RuntimeException("输入的字段不能为null或者为\"\"");
		}
	}
	
	
	
	@SuppressWarnings("unchecked")
	private static Method controlMethodByReflect(Method mtd,String paramName,String paramType,String paramValue,Class cls){
		String setMethodName="set"+toUpCaseFirstChar(paramName);
		try {
			if ("java.util.Date".equals(paramType)) {
				mtd = cls.getMethod(setMethodName,
						new Class[] { java.util.Date.class });
			} else if ("java.math.BigDecimal".equals(paramType)) {
				mtd = cls.getMethod(setMethodName,
						new Class[] { java.math.BigDecimal.class });
			} else if ("java.lang.String".equals(paramType)) {
				mtd = cls.getMethod(setMethodName,
						new Class[] { java.lang.String.class });
			} else if ("java.lang.Integer".equals(paramType)
					|| "int".equals(paramType)) {
				if(paramType.equals("java.lang.Integer")){
					mtd = cls.getMethod(setMethodName,
							new Class[] { java.lang.Integer.class });
				}else{
					mtd = cls.getMethod(setMethodName,
							new Class[] { int.class });
				}
				
			} else if ("java.lang.Double".equals(paramType)
					|| "double".equals(paramType)) {
				
				if(paramType.equals("java.lang.Double")){
					mtd = cls.getMethod(setMethodName,
							new Class[] { java.lang.Double.class });
				}else{
					mtd = cls.getMethod(setMethodName,
							new Class[] { double.class });
				}
				
			} else if ("java.lang.Float".equals(paramType)
					|| "float".equals(paramType)) {
				
				if(paramType.equals("java.lang.Float")){
					mtd = cls.getMethod(setMethodName,
							new Class[] { java.lang.Float.class });
				}else{
					mtd = cls.getMethod(setMethodName,
							new Class[] { float.class });
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mtd;
	
	}
	
}

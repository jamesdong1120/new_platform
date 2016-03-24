package com.pccw.system.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;

public class ResponseUtils {
	
	/**
	 * 返回easyui的分页数据
	 * @param response
	 * @param list
	 * @param allCount
	 * @param dateFormat 日期格式
	 * 日期格式为:
	 * yyyy-MM-dd hh:mm:ss 12小时制
	 * yyyy-MM-dd HH:mm:ss 24小时制
	 * yyyy-MM-dd 年月日 等等
	 */
	public static void respPageData(HttpServletResponse response,List list,Integer allCount,String dateFormat){
		
		String data="{\"rows\":"+JSON.toJSONStringWithDateFormat(list,dateFormat)+",\"total\":"+allCount+"}";
		respData(response, data);
	}
	
	
	/**
	 * ajax返回数据
	 * @param response
	 * @param data
	 */
	public static void respData(HttpServletResponse response,String data){
		response.setCharacterEncoding("UTF-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print(data);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 响应操作状态字符串
	 * @param response
	 * @param data
	 */
	public static void respSubmitStatusData(HttpServletResponse response,boolean flag){
		response.setCharacterEncoding("UTF-8");
		PrintWriter out;
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("success", true);
		try {
			map.put("result", flag);
			if(flag){
				map.put("successMsg","操作成功!!!");
			}else{
				map.put("errorMsg","操作失败!!!");
			}
			out = response.getWriter();
			out.print(JSON.toJSON(map));
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void respDefineSubmitStatusData(HttpServletResponse response,boolean flag,String msg){
		response.setCharacterEncoding("UTF-8");
		PrintWriter out;
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("success", true);
		try {
			map.put("result", flag);
			if(flag){
				map.put("successMsg",msg);
			}else{
				map.put("errorMsg",msg);
			}
			out = response.getWriter();
			out.print(JSON.toJSON(map));
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}

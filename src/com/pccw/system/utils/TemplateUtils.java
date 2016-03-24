package com.pccw.system.utils;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import freemarker.cache.StringTemplateLoader;
import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.TemplateNotFoundException;

/*******************************************
 * <p>
 * Description: 模板解析工具类
 * </p>
 * <p>
 * Company:PCCW
 * </p>
 * 
 * @author Jamesdong
 * @date 2016年2月26日 下午3:03:21
 ******************************************/
public class TemplateUtils {

	/**
	 * 读取模板 返回解析后的string字符串
	 * 
	 * @Map tempBaseLoaction
	 * @Map tempName
	 * @Map encoding 字符集编码
	 * @Map numberFormat 例如"#","###,###.##" 等
	 * @Map dateFormat 例如"yyyy-MM-dd"
	 * @Map timeFormat 例如"HH:mm:ss"
	 * @Map dateTimeFormat 例如:"yyyy-MM-dd HH:mm:ss"
	 * @return
	 */
	public static String readTemplate(Map<String, String> sourceMap,
			Map<String, Object> paramMap) {
		StringWriter stringWriter = new StringWriter();
		BufferedWriter writer = new BufferedWriter(stringWriter);
		Configuration cfg = new Configuration(Configuration.getVersion());
		Template temp;
		String tempBaseLoaction = null;
		String tempName = null;
		try {
			if (sourceMap.containsKey("tempBaseLoaction")) {
				tempBaseLoaction = sourceMap.get("tempBaseLoaction");
			}

			if (sourceMap.containsKey("tempName")) {
				tempName = sourceMap.get("tempName");
			}

			if (null == tempBaseLoaction || "".equals(tempBaseLoaction.trim())
					|| null == tempName || "".equals(tempName.trim())) {
				return null;
			}
			cfg.setClassForTemplateLoading(TemplateUtils.class,
					tempBaseLoaction);
			if(sourceMap.containsKey("encoding")){
				cfg.setDefaultEncoding(sourceMap.get("encoding"));
			}
			
			if(sourceMap.containsKey("numberFormat")){
				cfg.setNumberFormat(sourceMap.get("numberFormat"));
			}
			
			if(sourceMap.containsKey("dateFormat")){
				cfg.setDateFormat(sourceMap.get("dateFormat"));
			}
			if(sourceMap.containsKey("timeFormat")){
				cfg.setTimeFormat(sourceMap.get("timeFormat"));
			}
			
			if(sourceMap.containsKey("dateTimeFormat")){
				cfg.setDateTimeFormat(sourceMap.get("dateTimeFormat"));
			}
			cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
			temp = cfg.getTemplate(tempName);
			temp.process(paramMap, writer);
			writer.flush();
			
		} catch (TemplateNotFoundException e) {
			e.printStackTrace();
		} catch (MalformedTemplateNameException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}finally{
			if(writer!=null){
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return stringWriter.getBuffer().toString();
	}
	
	
	/**
	 * 读取模板 返回解析后的string字符串
	 * 
	 * @Map templateName 模板名称
	 * @Map templateSource 模板字符串
	 * @Map encoding 字符集编码
	 * @Map numberFormat 例如"#","###,###.##" 等
	 * @Map dateFormat 例如"yyyy-MM-dd"
	 * @Map timeFormat 例如"HH:mm:ss"
	 * @Map dateTimeFormat 例如:"yyyy-MM-dd HH:mm:ss"
	 * @return
	 */
	public static String readTemplateByString(Map<String, String> sourceMap,
			Map<String, Object> paramMap) {
		StringWriter stringWriter = new StringWriter();
		BufferedWriter writer = new BufferedWriter(stringWriter);
		Configuration cfg = new Configuration(Configuration.getVersion());
		 StringTemplateLoader stringTemplateLoader=new StringTemplateLoader();
		 
		Template temp;
		String templateName = null;
		String templateSource = null;
		try {
			if (sourceMap.containsKey("templateName")) {
				templateName = sourceMap.get("templateName");
			}

			if (sourceMap.containsKey("templateSource")) {
				templateSource = sourceMap.get("templateSource");
			}

			if (null == templateName || "".equals(templateName.trim())
					|| null == templateSource || "".equals(templateSource.trim())) {
				return null;
			}
			stringTemplateLoader.putTemplate(templateName, templateSource);
			cfg.setTemplateLoader(stringTemplateLoader);
			
			if(sourceMap.containsKey("encoding")){
				cfg.setDefaultEncoding(sourceMap.get("encoding"));
			}
			
			if(sourceMap.containsKey("numberFormat")){
				cfg.setNumberFormat(sourceMap.get("numberFormat"));
			}
			
			if(sourceMap.containsKey("dateFormat")){
				cfg.setDateFormat(sourceMap.get("dateFormat"));
			}
			if(sourceMap.containsKey("timeFormat")){
				cfg.setTimeFormat(sourceMap.get("timeFormat"));
			}
			
			if(sourceMap.containsKey("dateTimeFormat")){
				cfg.setDateTimeFormat(sourceMap.get("dateTimeFormat"));
			}
			cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
			temp = cfg.getTemplate(templateName);
			temp.process(paramMap, writer);
			writer.flush();
			
		} catch (TemplateNotFoundException e) {
			e.printStackTrace();
		} catch (MalformedTemplateNameException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}finally{
			if(writer!=null){
				try {
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return stringWriter.getBuffer().toString();
	}


}




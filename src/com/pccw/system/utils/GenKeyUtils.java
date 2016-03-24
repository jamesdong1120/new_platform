package com.pccw.system.utils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import com.pccw.system.service.GenKeyService;

/*******************************************
 * <p>Description: 主键生成工具类</p>
 * <p>Company:PCCW </p> 
 * @author Jamesdong
 * @date 2016年2月19日 下午4:13:07
 ******************************************/
public class GenKeyUtils {
	
	/**
	 * UUID的主键
	 * 使用此key注意,数据库中对应的字段请对应长度32位
	 * @return
	 */
	public static String  genUUIDKey(){
		UUID uuid=UUID.randomUUID();
		String value=uuid.toString().replaceAll("-", "");
		return value;
	}
	
	/**
	 * 时间戳的主键
	 * 时间到毫秒,同时加上8为随机数
	 * 使用此key注意,数据库中对应的字段请对应25位
	 * @return
	 */
	public static String genDateTimeKey(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmssS");
		StringBuffer buffer=new StringBuffer();
		String timestamp=sdf.format(new Date());
		buffer.append(timestamp);
		Random random=new Random();
		for(int i=0;i<8;i++){
			int rd=random.nextInt(10)+1;
			buffer.append(rd);
		}
		return buffer.toString();
	}
	
	
	public static BigDecimal  genDBKey(String tableName,String primaryKey){
		GenKeyService genKeyService  =SpringUtils.getBean("genKeyService");
		Integer value=genKeyService.genKey(tableName, primaryKey);
		return new BigDecimal(value);
	}
	
	public static BigDecimal  genSeqKey(String seqName){
		GenKeyService genKeyService  =SpringUtils.getBean("genKeyService");
		Integer value=genKeyService.genSeqKey(seqName);
		return new BigDecimal(value);
	}
	
	

}

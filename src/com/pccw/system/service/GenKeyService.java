package com.pccw.system.service;

public interface GenKeyService {
	
	/**
	 * oracle获取对应表的主键的方法
	 * @param tableName
	 * @param primaryKey
	 * @return
	 */
	Integer genKey(String tableName,String primaryKey);
	
	Integer genSeqKey(String seqName);

}

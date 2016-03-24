package com.pccw.system.dao;

import java.util.Map;

/************
 * @Dao
 * @author JamesDong
 */
public interface GenKeyMapper {
	
	int genKey(Map<String,String> map);
	
	int getNextValueBySeq(Map<String,String> seqName);

}

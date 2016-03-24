package com.pccw.system.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.pccw.system.dao.GenKeyMapper;
import com.pccw.system.service.GenKeyService;
/*******************************************
 * <p>Description:自动获取oracle中所对应表的主键 </p>
 * <p>Company:PCCW </p> 
 * @author Jamesdong
 * @date 2016年2月19日 下午4:53:58
 ******************************************/
@Service("genKeyService")
public class GenkeyServiceImpl implements GenKeyService{
	private GenKeyMapper genKeyMapper;
	
	@Autowired
	public void setGenKeyMapper(GenKeyMapper genKeyMapper) {
		this.genKeyMapper = genKeyMapper;
	}


	@Override
	public Integer genKey(String tableName, String primaryKey) {
		Map<String,String> map=new HashMap<String, String>();
		map.put("tableName", tableName);
		map.put("primaryKey", primaryKey);
		Integer result=genKeyMapper.genKey(map);
		return result;
	}


	@Override
	public Integer genSeqKey(String seqName) {
		Map<String,String> map=new HashMap<String, String>();
		map.put("seqName", seqName);
		Integer result=genKeyMapper.getNextValueBySeq(map);
		return result;
	}

}

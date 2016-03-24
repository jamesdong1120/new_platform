package com.pccw.bedc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pccw.bedc.dao.BankSignMapper;
import com.pccw.bedc.model.BciBankSignInfo;
import com.pccw.bedc.service.BankSignService;
import com.pccw.bedc.utils.BankSignUtils;
@Service("bankSignService")
public class BankSignServiceImpl implements BankSignService {
	private BankSignMapper bankSignMapper;
	
	@Autowired
	public void setBankSignMapper(BankSignMapper bankSignMapper) {
		this.bankSignMapper = bankSignMapper;
	}



	@Override
	public void sign() {
		List<BciBankSignInfo> banksInfos=bankSignMapper.getNeedSignBankCodes();
		for(BciBankSignInfo bciBankSignInfo:banksInfos){
			String sendMsg="Sign&&" + bciBankSignInfo.getBankCode() + "0901<BEDC_BANKCODE>" + bciBankSignInfo.getBankCode() + "</><BEDC_SYSTEM_FLAG>sign</>";
			boolean flag=BankSignUtils.sign(bciBankSignInfo, sendMsg);
			if(!flag){
				System.out.println("签到失败"+bciBankSignInfo.getBankCode());
			}
		}
		
	}

}

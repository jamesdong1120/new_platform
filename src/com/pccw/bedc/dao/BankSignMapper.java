package com.pccw.bedc.dao;

import java.util.List;

import com.pccw.bedc.model.BciBankSignInfo;

public interface BankSignMapper {
	List<BciBankSignInfo> getNeedSignBankCodes();

}

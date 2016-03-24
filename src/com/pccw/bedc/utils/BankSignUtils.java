package com.pccw.bedc.utils;



import org.apache.commons.lang.StringUtils;

import com.pccw.bedc.model.BciBankSignInfo;

public class BankSignUtils {
	public static boolean sign(BciBankSignInfo bciBankSignInfo,String sendMsg){
		boolean flag=true;
		String msg=null;
		try {
			msg=SockectUitls.send(bciBankSignInfo.getSignAddress(), bciBankSignInfo.getSignPort().intValue(), sendMsg, bciBankSignInfo.getSignTimeOut().intValue());
			if(null==msg||"".equals(msg)){
				flag=false;
			}
			String returnCode=StringUtils.substringBetween(msg, "<returnCode>", "</>");
			if(!"000000".equals(returnCode)){
				flag=false;
			}
		} catch (Exception e) {
			flag=false;
			e.printStackTrace();
		}
		return flag;
	}

}

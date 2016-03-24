package com.pccw.bedc.model;

import java.io.Serializable;
import java.math.*;
import java.util.*;
/**
 * 银行签到信息
 * BCI_BANK_SIGN_INFO
 * @author JamesDong
 *
 */

public class BciBankSignInfo implements Serializable {

	private BigDecimal signId;//主键
	private String signAddress;//签到地址
	private BigDecimal signPort;//签到端口
	private String signDesc;//签到描述
	private BigDecimal signTimeOut;//超时时间
	private String bankCode;//银行编号
	private String createdBy;//创建者
	private Date createdDate;//创建日期
	private String lastUpdatedBy;//更新者
	private Date lastUpdatedDate;//更新日期
	
    
	public BigDecimal getSignId() {
		return signId;
	}
    
	public void setSignId(BigDecimal signId) {
		this.signId = signId;
	}
    
	public String getSignAddress() {
		return signAddress;
	}
    
	public void setSignAddress(String signAddress) {
		this.signAddress = signAddress;
	}
    
	public BigDecimal getSignPort() {
		return signPort;
	}
    
	public void setSignPort(BigDecimal signPort) {
		this.signPort = signPort;
	}
    
	public String getSignDesc() {
		return signDesc;
	}
    
	public void setSignDesc(String signDesc) {
		this.signDesc = signDesc;
	}
    
	public BigDecimal getSignTimeOut() {
		return signTimeOut;
	}
    
	public void setSignTimeOut(BigDecimal signTimeOut) {
		this.signTimeOut = signTimeOut;
	}
    
	public String getBankCode() {
		return bankCode;
	}
    
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
    
	public String getCreatedBy() {
		return createdBy;
	}
    
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
    
	public Date getCreatedDate() {
		return createdDate;
	}
    
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
    
	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}
    
	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
    
	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}
    
	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}
	

}
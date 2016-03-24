package com.pccw.bedc.model;

import java.io.Serializable;
import java.math.*;
import java.util.*;
/**
 * 銀行交易配置表
 * BCI_BANK_TRANS
 * @author JamesDong
 *
 */

public class BciBankTrans implements Serializable {

	private BigDecimal tranId;//主键
	private String tranCode;//交易编号
	private String bankCode;//银行编号
	private String tranDesc;//交易描述
	private String status;//状态
	private String createdBy;//创建者
	private Date createdDate;//创建日期
	private String lastUpdatedBy;//更新者
	private Date lastUpdatedDate;//更新日期
	
    
	public BigDecimal getTranId() {
		return tranId;
	}
    
	public void setTranId(BigDecimal tranId) {
		this.tranId = tranId;
	}
    
	public String getTranCode() {
		return tranCode;
	}
    
	public void setTranCode(String tranCode) {
		this.tranCode = tranCode;
	}
    
	public String getBankCode() {
		return bankCode;
	}
    
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
    
	public String getTranDesc() {
		return tranDesc;
	}
    
	public void setTranDesc(String tranDesc) {
		this.tranDesc = tranDesc;
	}
    
	public String getStatus() {
		return status;
	}
    
	public void setStatus(String status) {
		this.status = status;
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
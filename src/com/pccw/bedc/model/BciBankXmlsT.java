package com.pccw.bedc.model;

import java.io.Serializable;
import java.math.*;
import java.util.*;
/**
 * 报文存放
 * BCI_BANK_XMLS_T
 * @author JamesDong
 *
 */

public class BciBankXmlsT implements Serializable {

	private BigDecimal bankXmlsId;//唯一性ID
	private String bankCode;//银行编号
	private String tranCode;//指令编号
	private String xmlClob;//报文
	private String bankXmlType;//报文类型
	private BigDecimal fromBankXmlId;//来源报文ID
	private String status;//状态
	private BigDecimal createBy;//创建人
	private Date createdDate;//创建日期
	private BigDecimal lastUpdatedBy;//更新者
	private Date lastUpdatedDate;//更新日期
	
    
	public BigDecimal getBankXmlsId() {
		return bankXmlsId;
	}
    
	public void setBankXmlsId(BigDecimal bankXmlsId) {
		this.bankXmlsId = bankXmlsId;
	}
    
	public String getBankCode() {
		return bankCode;
	}
    
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
    
	public String getTranCode() {
		return tranCode;
	}
    
	public void setTranCode(String tranCode) {
		this.tranCode = tranCode;
	}
    
	public String getXmlClob() {
		return xmlClob;
	}
    
	public void setXmlClob(String xmlClob) {
		this.xmlClob = xmlClob;
	}
    
	public String getBankXmlType() {
		return bankXmlType;
	}
    
	public void setBankXmlType(String bankXmlType) {
		this.bankXmlType = bankXmlType;
	}
    
	public BigDecimal getFromBankXmlId() {
		return fromBankXmlId;
	}
    
	public void setFromBankXmlId(BigDecimal fromBankXmlId) {
		this.fromBankXmlId = fromBankXmlId;
	}
    
	public String getStatus() {
		return status;
	}
    
	public void setStatus(String status) {
		this.status = status;
	}
    
	public BigDecimal getCreateBy() {
		return createBy;
	}
    
	public void setCreateBy(BigDecimal createBy) {
		this.createBy = createBy;
	}
    
	public Date getCreatedDate() {
		return createdDate;
	}
    
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
    
	public BigDecimal getLastUpdatedBy() {
		return lastUpdatedBy;
	}
    
	public void setLastUpdatedBy(BigDecimal lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
    
	public Date getLastUpdatedDate() {
		return lastUpdatedDate;
	}
    
	public void setLastUpdatedDate(Date lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}
	

}
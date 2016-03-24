package com.pccw.bedc.model;

import java.io.Serializable;
import java.math.*;
import java.util.*;
/**
 * 银行报文头模板配置
 * BCI_BANK_HEAD_CFG
 * @author JamesDong
 *
 */

public class BciBankHeadCfg implements Serializable {

	private BigDecimal headConfigId;//主键
	private String headConfigDesc;//头模板配置描述
	private String headConfigContent;//模板配置文本
	private String bankCode;//银行编号
	private String status;//状态
	private String configVersion;//配置版本号
	private String createdBy;//创建者
	private Date createdDate;//创建时间
	private String lastUpdatedBy;//更新者
	private Date lastUpdatedDate;//更新日期
	
    
	public BigDecimal getHeadConfigId() {
		return headConfigId;
	}
    
	public void setHeadConfigId(BigDecimal headConfigId) {
		this.headConfigId = headConfigId;
	}
    
	public String getHeadConfigDesc() {
		return headConfigDesc;
	}
    
	public void setHeadConfigDesc(String headConfigDesc) {
		this.headConfigDesc = headConfigDesc;
	}
    
	public String getHeadConfigContent() {
		return headConfigContent;
	}
    
	public void setHeadConfigContent(String headConfigContent) {
		this.headConfigContent = headConfigContent;
	}
    
	public String getBankCode() {
		return bankCode;
	}
    
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
    
	public String getStatus() {
		return status;
	}
    
	public void setStatus(String status) {
		this.status = status;
	}
    
	public String getConfigVersion() {
		return configVersion;
	}
    
	public void setConfigVersion(String configVersion) {
		this.configVersion = configVersion;
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
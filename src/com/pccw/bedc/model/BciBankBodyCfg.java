package com.pccw.bedc.model;

import java.io.Serializable;
import java.math.*;
import java.util.*;
/**
 * 银行报文包体配置
 * BCI_BANK_BODY_CFG
 * @author JamesDong
 *
 */

public class BciBankBodyCfg implements Serializable {

	private BigDecimal bodyConfigId;//主键
	private String bodyConfigDesc;//包体模板配置描述
	private String bodyConfigContent;//模板配置文本
	private String configVersion;//配置版本号
	private String bankCode;//银行编号
	private String tranCode;//交易编号
	private String status;//状态
	private String createdBy;//创建者
	private Date createdDate;//创建日期
	private String lastUpdatedBy;//更新者
	private Date lastUpdatedDate;//更新日期
	private BigDecimal headConfigId;//对应的头模板编号
	
    
	public BigDecimal getBodyConfigId() {
		return bodyConfigId;
	}
    
	public void setBodyConfigId(BigDecimal bodyConfigId) {
		this.bodyConfigId = bodyConfigId;
	}
    
	public String getBodyConfigDesc() {
		return bodyConfigDesc;
	}
    
	public void setBodyConfigDesc(String bodyConfigDesc) {
		this.bodyConfigDesc = bodyConfigDesc;
	}
    
	public String getBodyConfigContent() {
		return bodyConfigContent;
	}
    
	public void setBodyConfigContent(String bodyConfigContent) {
		this.bodyConfigContent = bodyConfigContent;
	}
    
	public String getConfigVersion() {
		return configVersion;
	}
    
	public void setConfigVersion(String configVersion) {
		this.configVersion = configVersion;
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
    
	public BigDecimal getHeadConfigId() {
		return headConfigId;
	}
    
	public void setHeadConfigId(BigDecimal headConfigId) {
		this.headConfigId = headConfigId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	

}
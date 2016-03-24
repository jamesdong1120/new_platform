package com.pccw.bedc.model;

import java.io.Serializable;
import java.math.*;
import java.util.*;
/**
 * 银企参数
 * BCI_XML_PARAMS
 * @author JamesDong
 *
 */

public class BciXmlParams implements Serializable {

	private BigDecimal paramId;//参数编号
	private String paramName;//参数名称
	private String paramType;//参数类型
	private String defaultValue;//默认值
	private String bankCode;//银行编号
	private String tranCode;//交易编号
	private BigDecimal templateId;//模板编号
	private String createdBy;//创建者
	private Date createdDate;//创建日期
	private String lastUpdatedBy;//更新者
	private Date lastUpdatedDate;//更新日期
	
    
	public BigDecimal getParamId() {
		return paramId;
	}
    
	public void setParamId(BigDecimal paramId) {
		this.paramId = paramId;
	}
    
	public String getParamName() {
		return paramName;
	}
    
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}
    
	public String getParamType() {
		return paramType;
	}
    
	public void setParamType(String paramType) {
		this.paramType = paramType;
	}
    
	public String getDefaultValue() {
		return defaultValue;
	}
    
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
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
    
	public BigDecimal getTemplateId() {
		return templateId;
	}
    
	public void setTemplateId(BigDecimal templateId) {
		this.templateId = templateId;
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
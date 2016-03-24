package com.pccw.bedc.model;

import java.io.Serializable;
import java.math.*;
import java.util.*;
/**
 * 报文模板表
 * BCI_XML_FILE_TEMPLATE_T
 * @author JamesDong
 *
 */

public class BciXmlFileTemplateT implements Serializable {

	private BigDecimal templateId;//模板ID
	private String templateClob;//模板
	private String templateVersion;//模板版本
	private BigDecimal createBy;//创建时间
	private Date createdDate;//创建时间
	private BigDecimal lastUpdatedBy;//最后更新者
	private Date lastUpdatedDate;//最后更新时间
	private BigDecimal headConfigId;//头模板编号
	private BigDecimal bodyConfigId;//包体模板编号
	private String tranCode;//交易编号
	private String bankCode;//银行编码
	
    
	public BigDecimal getTemplateId() {
		return templateId;
	}
    
	public void setTemplateId(BigDecimal templateId) {
		this.templateId = templateId;
	}
    
	public String getTemplateClob() {
		return templateClob;
	}
    
	public void setTemplateClob(String templateClob) {
		this.templateClob = templateClob;
	}
    
	public String getTemplateVersion() {
		return templateVersion;
	}
    
	public void setTemplateVersion(String templateVersion) {
		this.templateVersion = templateVersion;
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
    
	public BigDecimal getHeadConfigId() {
		return headConfigId;
	}
    
	public void setHeadConfigId(BigDecimal headConfigId) {
		this.headConfigId = headConfigId;
	}
    
	public BigDecimal getBodyConfigId() {
		return bodyConfigId;
	}
    
	public void setBodyConfigId(BigDecimal bodyConfigId) {
		this.bodyConfigId = bodyConfigId;
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
	

}
package com.pccw.bedc.model;

import java.io.Serializable;
import java.math.*;
import java.util.*;
/**
 * 报文文件
 * BCI_XML_FILES_T
 * @author JamesDong
 *
 */

public class BciXmlFilesT implements Serializable {

	private BigDecimal xmlFileId;//文件ID
	private String xmlFileName;//文件名称
	private String bankCode;//银行编号
	private String tranCode;//指令编号
	private String enabledFlag;//启用标记
	private Date startDate;//开始时间
	private Date endDate;//结束世界
	private BigDecimal createBy;//创建人
	private Date createdDate;//创建日期
	private BigDecimal lastUpdatedBy;//更新者
	private Date lastUpdatedDate;//最后更新日期
	private String templateVersion;//模板版本
	private String templateFlag;//模板标记
	private String bankXmlType;//报文类型
	private BigDecimal templateId;//模板ID
	
    
	public BigDecimal getXmlFileId() {
		return xmlFileId;
	}
    
	public void setXmlFileId(BigDecimal xmlFileId) {
		this.xmlFileId = xmlFileId;
	}
    
	public String getXmlFileName() {
		return xmlFileName;
	}
    
	public void setXmlFileName(String xmlFileName) {
		this.xmlFileName = xmlFileName;
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
    
	public String getEnabledFlag() {
		return enabledFlag;
	}
    
	public void setEnabledFlag(String enabledFlag) {
		this.enabledFlag = enabledFlag;
	}
    
	public Date getStartDate() {
		return startDate;
	}
    
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
    
	public Date getEndDate() {
		return endDate;
	}
    
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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
    
	public String getTemplateVersion() {
		return templateVersion;
	}
    
	public void setTemplateVersion(String templateVersion) {
		this.templateVersion = templateVersion;
	}
    
	public String getTemplateFlag() {
		return templateFlag;
	}
    
	public void setTemplateFlag(String templateFlag) {
		this.templateFlag = templateFlag;
	}
    
	public String getBankXmlType() {
		return bankXmlType;
	}
    
	public void setBankXmlType(String bankXmlType) {
		this.bankXmlType = bankXmlType;
	}
    
	public BigDecimal getTemplateId() {
		return templateId;
	}
    
	public void setTemplateId(BigDecimal templateId) {
		this.templateId = templateId;
	}
	

}
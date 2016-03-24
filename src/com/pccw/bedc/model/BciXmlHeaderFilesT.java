package com.pccw.bedc.model;

import java.io.Serializable;
import java.math.*;
import java.util.*;
/**
 * 
 * BCI_XML_HEADER_FILES_T
 * @author JamesDong
 *
 */

public class BciXmlHeaderFilesT implements Serializable {

	private BigDecimal xmlFileId;//主键
	private String xmlFileName;//头文件名字
	private String xmlFileContent;//头文件内容
	private String bankCode;//银行编号
	private String tranCode;//交易编号
	private String enabledFlag;//是否启用
	private String xmlHeaderVersion;//头文件版本号
	private String createdBy;//创建者
	private Date createdDate;//创建日期
	private String lastUpdatedBy;//更新者
	private Date lastUpdateDate;//最后更新日期
	
    
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
    
	public String getXmlFileContent() {
		return xmlFileContent;
	}
    
	public void setXmlFileContent(String xmlFileContent) {
		this.xmlFileContent = xmlFileContent;
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
    
	public String getXmlHeaderVersion() {
		return xmlHeaderVersion;
	}
    
	public void setXmlHeaderVersion(String xmlHeaderVersion) {
		this.xmlHeaderVersion = xmlHeaderVersion;
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
    
	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}
    
	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}
	

}
package com.pccw.bedc.model;

import java.io.Serializable;
import java.math.*;
import java.util.*;
/**
 * 报文标签
 * BCI_XML_LABELS_T
 * @author JamesDong
 *
 */

public class BciXmlLabelsT implements Serializable {

	private BigDecimal xmlLabelId;//唯一性ID
	private String xmlLabelName;//标签名称
	private String xmlLabelSeq;//标签序列
	private String xmlLabelType;//标签类型
	private String isLeaf;//是否叶节点
	private String xmlLabelValue;//标签常值
	private BigDecimal parentXmlLabelId;//父标签
	private String dataSourceType;//数据源类型
	private String enabledFlag;//启用标记
	private Date startDate;//开始时间
	private Date endDate;//结束世界
	private BigDecimal createBy;//创建人
	private Date createdDate;//创建日期
	private BigDecimal lastUpdatedBy;//更新者
	private Date lastUpdatedDate;//更新日期
	private BigDecimal xmlFileId;//文件ID
	private BigDecimal dataSourceId;//唯一性ID
	private String xmlLabelLevel;//标签层级
	
    
	public BigDecimal getXmlLabelId() {
		return xmlLabelId;
	}
    
	public void setXmlLabelId(BigDecimal xmlLabelId) {
		this.xmlLabelId = xmlLabelId;
	}
    
	public String getXmlLabelName() {
		return xmlLabelName;
	}
    
	public void setXmlLabelName(String xmlLabelName) {
		this.xmlLabelName = xmlLabelName;
	}
    
	public String getXmlLabelSeq() {
		return xmlLabelSeq;
	}
    
	public void setXmlLabelSeq(String xmlLabelSeq) {
		this.xmlLabelSeq = xmlLabelSeq;
	}
    
	public String getXmlLabelType() {
		return xmlLabelType;
	}
    
	public void setXmlLabelType(String xmlLabelType) {
		this.xmlLabelType = xmlLabelType;
	}
    
	public String getIsLeaf() {
		return isLeaf;
	}
    
	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}
    
	public String getXmlLabelValue() {
		return xmlLabelValue;
	}
    
	public void setXmlLabelValue(String xmlLabelValue) {
		this.xmlLabelValue = xmlLabelValue;
	}
    
	public BigDecimal getParentXmlLabelId() {
		return parentXmlLabelId;
	}
    
	public void setParentXmlLabelId(BigDecimal parentXmlLabelId) {
		this.parentXmlLabelId = parentXmlLabelId;
	}
    
	public String getDataSourceType() {
		return dataSourceType;
	}
    
	public void setDataSourceType(String dataSourceType) {
		this.dataSourceType = dataSourceType;
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
    
	public BigDecimal getXmlFileId() {
		return xmlFileId;
	}
    
	public void setXmlFileId(BigDecimal xmlFileId) {
		this.xmlFileId = xmlFileId;
	}
    
	public BigDecimal getDataSourceId() {
		return dataSourceId;
	}
    
	public void setDataSourceId(BigDecimal dataSourceId) {
		this.dataSourceId = dataSourceId;
	}
    
	public String getXmlLabelLevel() {
		return xmlLabelLevel;
	}
    
	public void setXmlLabelLevel(String xmlLabelLevel) {
		this.xmlLabelLevel = xmlLabelLevel;
	}
	

}
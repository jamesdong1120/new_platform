package com.pccw.bedc.model;

import java.io.Serializable;
import java.math.*;
import java.util.*;
/**
 * 
 * BCI_MULTI_LEVEL_DIC
 * @author JamesDong
 *
 */

public class BciMultiLevelDic implements Serializable {

	private BigDecimal dicId;//主键
	private String dicCode;//字典编码
	private String dicValue;//字典值
	private String parentDicId;//父节点编号
	private String dicStatus;//字典状态
	private String createdBy;//创建者
	private Date createdDate;//创建时间
	private String updatedBy;//更新者
	private Date updatedDate;//更新时间
	private BigDecimal isLeaf;
	
	
	
    
	public BigDecimal getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(BigDecimal isLeaf) {
		this.isLeaf = isLeaf;
	}

	public BigDecimal getDicId() {
		return dicId;
	}
    
	public void setDicId(BigDecimal dicId) {
		this.dicId = dicId;
	}
    
	public String getDicCode() {
		return dicCode;
	}
    
	public void setDicCode(String dicCode) {
		this.dicCode = dicCode;
	}
    
	public String getDicValue() {
		return dicValue;
	}
    
	public void setDicValue(String dicValue) {
		this.dicValue = dicValue;
	}
    
	public String getParentDicId() {
		return parentDicId;
	}
    
	public void setParentDicId(String parentDicId) {
		this.parentDicId = parentDicId;
	}
    
	public String getDicStatus() {
		return dicStatus;
	}
    
	public void setDicStatus(String dicStatus) {
		this.dicStatus = dicStatus;
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
    
	public String getUpdatedBy() {
		return updatedBy;
	}
    
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
    
	public Date getUpdatedDate() {
		return updatedDate;
	}
    
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	

}
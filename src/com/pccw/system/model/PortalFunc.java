package com.pccw.system.model;

import java.io.Serializable;
import java.math.*;
import java.util.*;
/**
 * 
 * PORTAL_FUNC
 * @author JamesDong
 *
 */

public class PortalFunc implements Serializable {

	private BigDecimal funcId;//功能编号
	private String funcName;//功能名称
	private String funcUrl;//功能地址
	private BigDecimal funcSort;//功能排序
	private BigDecimal parentId;//父节点编号
	private String funcStatus;//功能状态
	private String createdBy;//创建者
	private Date createdDate;//创建时间
	private String updatedBy;//更新者
	private Date updatedDate;//更新时间
	private BigDecimal isLeaf;//不在数据库中
	
    
	public BigDecimal getFuncId() {
		return funcId;
	}
    
	public void setFuncId(BigDecimal funcId) {
		this.funcId = funcId;
	}
    
	public String getFuncName() {
		return funcName;
	}
    
	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}
    
	public String getFuncUrl() {
		return funcUrl;
	}
    
	public void setFuncUrl(String funcUrl) {
		this.funcUrl = funcUrl;
	}
    
	public BigDecimal getFuncSort() {
		return funcSort;
	}
    
	public void setFuncSort(BigDecimal funcSort) {
		this.funcSort = funcSort;
	}
    
	public BigDecimal getParentId() {
		return parentId;
	}
    
	public void setParentId(BigDecimal parentId) {
		this.parentId = parentId;
	}
    
	public String getFuncStatus() {
		return funcStatus;
	}
    
	public void setFuncStatus(String funcStatus) {
		this.funcStatus = funcStatus;
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

	public BigDecimal getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(BigDecimal isLeaf) {
		this.isLeaf = isLeaf;
	}
	
	
}
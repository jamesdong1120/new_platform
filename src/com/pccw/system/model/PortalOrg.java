package com.pccw.system.model;

import java.io.Serializable;
import java.math.*;
import java.util.*;
/**
 * 系统组织表
 * PORTAL_ORG
 * @author JamesDong
 *
 */

public class PortalOrg implements Serializable {

	private BigDecimal orgId;//组织编号
	private String orgFullName;//组织全称
	private String orgShortName;//组织简称
	private BigDecimal orgSort;//组织排序位置
	private BigDecimal orgParent;//上级组织
	private String orgStatus;//组织状态
	private String remark;//备注
	private String createdBy;//组织创建者
	private Date createdDate;//创建日期
	private String updatedBy;//更新者
	private Date updatedDate;//更新日期
	private BigDecimal isLeaf;//不在数据库中
	
    
	public BigDecimal getOrgId() {
		return orgId;
	}
    
	public void setOrgId(BigDecimal orgId) {
		this.orgId = orgId;
	}
    
	public String getOrgFullName() {
		return orgFullName;
	}
    
	public void setOrgFullName(String orgFullName) {
		this.orgFullName = orgFullName;
	}
    
	public String getOrgShortName() {
		return orgShortName;
	}
    
	public void setOrgShortName(String orgShortName) {
		this.orgShortName = orgShortName;
	}
    
	public BigDecimal getOrgSort() {
		return orgSort;
	}
    
	public void setOrgSort(BigDecimal orgSort) {
		this.orgSort = orgSort;
	}
    
	public BigDecimal getOrgParent() {
		return orgParent;
	}
    
	public void setOrgParent(BigDecimal orgParent) {
		this.orgParent = orgParent;
	}
    
	public String getOrgStatus() {
		return orgStatus;
	}
    
	public void setOrgStatus(String orgStatus) {
		this.orgStatus = orgStatus;
	}
    
	public String getRemark() {
		return remark;
	}
    
	public void setRemark(String remark) {
		this.remark = remark;
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
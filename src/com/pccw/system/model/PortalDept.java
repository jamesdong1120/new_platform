package com.pccw.system.model;

import java.io.Serializable;
import java.math.*;
import java.util.*;
/**
 * 系统部门
 * PORTAL_DEPT
 * @author JamesDong
 *
 */

public class PortalDept implements Serializable {

	private BigDecimal deptId;//部门编号
	private String deptName;//部门名称
	private BigDecimal deptParent;//上级部门
	private String deptStatus;//部门状态
	private BigDecimal deptSort;//部门排序
	private BigDecimal orgId;//组织编号
	private String createdBy;//创建者
	private Date createdDate;//创建日期
	private Date updatedDate;//更新时间
	private String updatedBy;//更新者
	private BigDecimal isLeaf;//是否叶子节点(不在数据库中)
	
    
	public BigDecimal getDeptId() {
		return deptId;
	}
    
	public void setDeptId(BigDecimal deptId) {
		this.deptId = deptId;
	}
    
	public String getDeptName() {
		return deptName;
	}
    
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
    
	public BigDecimal getDeptParent() {
		return deptParent;
	}
    
	public void setDeptParent(BigDecimal deptParent) {
		this.deptParent = deptParent;
	}
    
	public String getDeptStatus() {
		return deptStatus;
	}
    
	public void setDeptStatus(String deptStatus) {
		this.deptStatus = deptStatus;
	}
    
	public BigDecimal getDeptSort() {
		return deptSort;
	}
    
	public void setDeptSort(BigDecimal deptSort) {
		this.deptSort = deptSort;
	}
    
	public BigDecimal getOrgId() {
		return orgId;
	}
    
	public void setOrgId(BigDecimal orgId) {
		this.orgId = orgId;
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
    
	public Date getUpdatedDate() {
		return updatedDate;
	}
    
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
    
	public String getUpdatedBy() {
		return updatedBy;
	}
    
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public BigDecimal getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(BigDecimal isLeaf) {
		this.isLeaf = isLeaf;
	}
	

}
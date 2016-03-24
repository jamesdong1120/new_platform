package com.pccw.system.model;

import java.io.Serializable;
import java.math.*;
import java.util.*;
/**
 * 系统权限角色表
 * PORTAL_ROLE
 * @author JamesDong
 *
 */

public class PortalRole implements Serializable {

	private BigDecimal roleId;//角色编号
	private String roleName;//角色名称
	private String roleStatus;//角色状态
	private String roleDesc;//角色描述
	private String remark;//备注
	private String createdBy;//创建者
	private Date createdDate;//创建日期
	private String updatedBy;//更新者
	private Date updatedDate;//更新日期
	
    
	public BigDecimal getRoleId() {
		return roleId;
	}
    
	public void setRoleId(BigDecimal roleId) {
		this.roleId = roleId;
	}
    
	public String getRoleName() {
		return roleName;
	}
    
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
    
	public String getRoleStatus() {
		return roleStatus;
	}
    
	public void setRoleStatus(String roleStatus) {
		this.roleStatus = roleStatus;
	}
    
	public String getRoleDesc() {
		return roleDesc;
	}
    
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
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
	

}
package com.pccw.system.model;

import java.io.Serializable;
import java.math.*;
import java.util.*;
/**
 * 系统用户表
 * PORTAL_USER
 * @author JamesDong
 *
 */

public class PortalUser implements Serializable {

	private BigDecimal userId;//用户编号
	private String userCnName;//用户中文名
	private String userLoginName;//用户登录名
	private String userLoginPwd;//用户登录密码
	private String userJobTitle;//用户职称
	private String userStatus;//用户状态
	private String remark;//备注
	private BigDecimal deptId;//部门编号
	private BigDecimal orgId;//组织编号
	private BigDecimal createdBy;//用户创建者
	private Date createdDate;//创建日期
	private String updatedBy;//更新者
	private Date updatedDate;//更新日期
	private String orgShortName;//组织名称
	private String deptName;//部门名称
	
    
	public BigDecimal getUserId() {
		return userId;
	}
    
	public void setUserId(BigDecimal userId) {
		this.userId = userId;
	}
    
	public String getUserCnName() {
		return userCnName;
	}
    
	public void setUserCnName(String userCnName) {
		this.userCnName = userCnName;
	}
    
	public String getUserLoginName() {
		return userLoginName;
	}
    
	public void setUserLoginName(String userLoginName) {
		this.userLoginName = userLoginName;
	}
    
	public String getUserLoginPwd() {
		return userLoginPwd;
	}
    
	public void setUserLoginPwd(String userLoginPwd) {
		this.userLoginPwd = userLoginPwd;
	}
    
	public String getUserJobTitle() {
		return userJobTitle;
	}
    
	public void setUserJobTitle(String userJobTitle) {
		this.userJobTitle = userJobTitle;
	}
    
	public String getUserStatus() {
		return userStatus;
	}
    
	public void setUserStatus(String userStatus) {
		this.userStatus = userStatus;
	}
    
	public String getRemark() {
		return remark;
	}
    
	public void setRemark(String remark) {
		this.remark = remark;
	}
    
	public BigDecimal getDeptId() {
		return deptId;
	}
    
	public void setDeptId(BigDecimal deptId) {
		this.deptId = deptId;
	}
    
	public BigDecimal getOrgId() {
		return orgId;
	}
    
	public void setOrgId(BigDecimal orgId) {
		this.orgId = orgId;
	}
    
	public BigDecimal getCreatedBy() {
		return createdBy;
	}
    
	public void setCreatedBy(BigDecimal createdBy) {
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

	public String getOrgShortName() {
		return orgShortName;
	}

	public void setOrgShortName(String orgShortName) {
		this.orgShortName = orgShortName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	

}
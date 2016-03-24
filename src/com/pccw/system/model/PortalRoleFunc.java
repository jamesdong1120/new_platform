package com.pccw.system.model;

import java.io.Serializable;
import java.math.*;
import java.util.*;
/**
 * 系统角色菜单映射表
 * PORTAL_ROLE_FUNC
 * @author JamesDong
 *
 */

public class PortalRoleFunc implements Serializable {

	private BigDecimal seqNo;//主键
	private BigDecimal funcId;//菜单编号
	private BigDecimal roleId;//角色编号
	private String status;//状态
	private String createdBy;//创建者
	private Date createdDate;//创建日期
	private String updatedBy;//更新者
	private Date updatedDate;//更新日期
	
    
	public BigDecimal getSeqNo() {
		return seqNo;
	}
    
	public void setSeqNo(BigDecimal seqNo) {
		this.seqNo = seqNo;
	}
    
	public BigDecimal getFuncId() {
		return funcId;
	}
    
	public void setFuncId(BigDecimal funcId) {
		this.funcId = funcId;
	}
    
	public BigDecimal getRoleId() {
		return roleId;
	}
    
	public void setRoleId(BigDecimal roleId) {
		this.roleId = roleId;
	}
    
	public String getStatus() {
		return status;
	}
    
	public void setStatus(String status) {
		this.status = status;
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
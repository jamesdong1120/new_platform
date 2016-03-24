package com.pccw.system.model;

import java.io.Serializable;
import java.math.*;
import java.util.*;
/**
 * 区域地市表
 * PORTAL_AREA_DIC
 * @author JamesDong
 *
 */

public class PortalAreaDic implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BigDecimal seqNo;//主键
	private String name;//名称
	private BigDecimal parentId;//父亲节点
	private String status;//状态
	private BigDecimal createdBy;//创建者
	private Date createDate;//创建日期
	private Date updateDate;//最后更新时间
	
    
	public BigDecimal getSeqNo() {
		return seqNo;
	}
    
	public void setSeqNo(BigDecimal seqNo) {
		this.seqNo = seqNo;
	}
    
	public String getName() {
		return name;
	}
    
	public void setName(String name) {
		this.name = name;
	}
    
	public BigDecimal getParentId() {
		return parentId;
	}
    
	public void setParentId(BigDecimal parentId) {
		this.parentId = parentId;
	}
    
	public String getStatus() {
		return status;
	}
    
	public void setStatus(String status) {
		this.status = status;
	}
    
	public BigDecimal getCreatedBy() {
		return createdBy;
	}
    
	public void setCreatedBy(BigDecimal createdBy) {
		this.createdBy = createdBy;
	}
    
	public Date getCreateDate() {
		return createDate;
	}
    
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
    
	public Date getUpdateDate() {
		return updateDate;
	}
    
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	

}
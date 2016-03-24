package com.pccw.bedc.model;

import java.io.Serializable;
import java.math.*;
import java.util.*;
/**
 * BCI_BANK
 * BCI_BANK
 * @author JamesDong
 *
 */

public class BciBank implements Serializable {

	private BigDecimal bankId;//主鍵
	private String bankcode;//银行代码
	private String shortname;//银行简称
	private String fullname;//银行全称
	private String corpcode;//银行企业代码
	private String bankacc;//银行账号
	private String inneracc;//内部账号
	private String branchid;//接入网点
	private String areacode;//长途区位
	private String techphone;//技术电话
	private String bizphone;//业务电话
	private String postcode;//邮政编码
	private String address;//通信地址
	private String signdate;//签约日期
	private String overdate;//出约日期
	private String coopstatus;//合作状况
	private String signflag;//签名标志
	private String createdate;//创建日期
	private String createtime;//创建时间
	private String updatedate;//更新日期
	private String updatetime;//更新时间
	private String createusr;//创建用户
	private String updateusr;//更新用户
	private String setmode;//控制归集入账方式
	private String mgntype;//管理类型
	private String hisbalmode;//历史余额查询方式
	private String bankcustid;//财务公司在银行的客户号
	private String pwd;//企业授权密码
	private String bcisstatus;//工作流处理状态
	private String banktxtoken;//银行交易标识
	private String cnapstoken;//联行号必输标志
	private String bankuserno;//银行授权用户号
	
    
	public BigDecimal getBankId() {
		return bankId;
	}
    
	public void setBankId(BigDecimal bankId) {
		this.bankId = bankId;
	}
    
	public String getBankcode() {
		return bankcode;
	}
    
	public void setBankcode(String bankcode) {
		this.bankcode = bankcode;
	}
    
	public String getShortname() {
		return shortname;
	}
    
	public void setShortname(String shortname) {
		this.shortname = shortname;
	}
    
	public String getFullname() {
		return fullname;
	}
    
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
    
	public String getCorpcode() {
		return corpcode;
	}
    
	public void setCorpcode(String corpcode) {
		this.corpcode = corpcode;
	}
    
	public String getBankacc() {
		return bankacc;
	}
    
	public void setBankacc(String bankacc) {
		this.bankacc = bankacc;
	}
    
	public String getInneracc() {
		return inneracc;
	}
    
	public void setInneracc(String inneracc) {
		this.inneracc = inneracc;
	}
    
	public String getBranchid() {
		return branchid;
	}
    
	public void setBranchid(String branchid) {
		this.branchid = branchid;
	}
    
	public String getAreacode() {
		return areacode;
	}
    
	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}
    
	public String getTechphone() {
		return techphone;
	}
    
	public void setTechphone(String techphone) {
		this.techphone = techphone;
	}
    
	public String getBizphone() {
		return bizphone;
	}
    
	public void setBizphone(String bizphone) {
		this.bizphone = bizphone;
	}
    
	public String getPostcode() {
		return postcode;
	}
    
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
    
	public String getAddress() {
		return address;
	}
    
	public void setAddress(String address) {
		this.address = address;
	}
    
	public String getSigndate() {
		return signdate;
	}
    
	public void setSigndate(String signdate) {
		this.signdate = signdate;
	}
    
	public String getOverdate() {
		return overdate;
	}
    
	public void setOverdate(String overdate) {
		this.overdate = overdate;
	}
    
	public String getCoopstatus() {
		return coopstatus;
	}
    
	public void setCoopstatus(String coopstatus) {
		this.coopstatus = coopstatus;
	}
    
	public String getSignflag() {
		return signflag;
	}
    
	public void setSignflag(String signflag) {
		this.signflag = signflag;
	}
    
	public String getCreatedate() {
		return createdate;
	}
    
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
    
	public String getCreatetime() {
		return createtime;
	}
    
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
    
	public String getUpdatedate() {
		return updatedate;
	}
    
	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}
    
	public String getUpdatetime() {
		return updatetime;
	}
    
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
    
	public String getCreateusr() {
		return createusr;
	}
    
	public void setCreateusr(String createusr) {
		this.createusr = createusr;
	}
    
	public String getUpdateusr() {
		return updateusr;
	}
    
	public void setUpdateusr(String updateusr) {
		this.updateusr = updateusr;
	}
    
	public String getSetmode() {
		return setmode;
	}
    
	public void setSetmode(String setmode) {
		this.setmode = setmode;
	}
    
	public String getMgntype() {
		return mgntype;
	}
    
	public void setMgntype(String mgntype) {
		this.mgntype = mgntype;
	}
    
	public String getHisbalmode() {
		return hisbalmode;
	}
    
	public void setHisbalmode(String hisbalmode) {
		this.hisbalmode = hisbalmode;
	}
    
	public String getBankcustid() {
		return bankcustid;
	}
    
	public void setBankcustid(String bankcustid) {
		this.bankcustid = bankcustid;
	}
    
	public String getPwd() {
		return pwd;
	}
    
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
    
	public String getBcisstatus() {
		return bcisstatus;
	}
    
	public void setBcisstatus(String bcisstatus) {
		this.bcisstatus = bcisstatus;
	}
    
	public String getBanktxtoken() {
		return banktxtoken;
	}
    
	public void setBanktxtoken(String banktxtoken) {
		this.banktxtoken = banktxtoken;
	}
    
	public String getCnapstoken() {
		return cnapstoken;
	}
    
	public void setCnapstoken(String cnapstoken) {
		this.cnapstoken = cnapstoken;
	}
    
	public String getBankuserno() {
		return bankuserno;
	}
    
	public void setBankuserno(String bankuserno) {
		this.bankuserno = bankuserno;
	}
	

}
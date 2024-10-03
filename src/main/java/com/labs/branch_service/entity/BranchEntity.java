package com.labs.branch_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "branches")
public class BranchEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "br_ID")
	private int branchId;
	@Column(name = "org_id")
	private int orgId;
	@Column(name = "br_name")
	private String branchName;
	@Column(name = "br_code")
	private String branchCode;
	@Column(name = "br_createdtime")
	private String createdTime;
	@Column(name = "br_createdby")
	private String createdBy;
	@Column(name = "br_modifiedtime")
	private String modifiedTime;
	@Column(name = "br_modifiedby")
	private String modifiedBy;
	@Column(name = "br_status")
	private String status;

	public BranchEntity() {

	}

	public BranchEntity(int orgId, String branchName, String branchCode, String createdTime, String createdBy,
			String modifiedTime, String modifiedBy, String status) {
		super();
		this.orgId = orgId;
		this.branchName = branchName;
		this.branchCode = branchCode;
		this.createdTime = createdTime;
		this.createdBy = createdBy;
		this.modifiedTime = modifiedTime;
		this.modifiedBy = modifiedBy;
		this.status = status;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public int getOrgId() {
		return orgId;
	}

	public void setOrgId(int orgId) {
		this.orgId = orgId;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}

	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(String modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "BranchEntity [branchId=" + branchId + ", orgId=" + orgId + ", branchName=" + branchName
				+ ", branchCode=" + branchCode + ", createdTime=" + createdTime + ", createdBy=" + createdBy
				+ ", modifiedTime=" + modifiedTime + ", modifiedBy=" + modifiedBy + ", status=" + status + "]";
	}

}

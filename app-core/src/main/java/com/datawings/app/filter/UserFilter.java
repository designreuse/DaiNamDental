package com.datawings.app.filter;

import com.datawings.app.common.BeanUtil;

public class UserFilter extends BaseFilter {
	private String username;
	private String name;
	private String telephone;
	private String email;
	private String role;
	private String branch;

	private String usernameCreate;
	private String passwordCreate;
	private String retypePasswordCreate;
	private String nameCreate;
	private String telephoneCreate;
	private String emailCreate;
	private String roleCreate;
	private String branchCreate;

	private String usernameModify;
	private String passwordModify;
	private String retypePasswordModify;
	private String nameModify;
	private String telephoneModify;
	private String emailModify;
	private String roleModify;
	private String branchModify;

	public UserFilter() {
		init();
	}

	public void init() {
		BeanUtil.initSimplePropertyBean(this);
	}

	public void initCreate() {
		this.usernameCreate = "";
		this.passwordCreate = "";
		this.retypePasswordCreate = "";
		this.nameCreate = "";
		this.telephoneCreate = "";
		this.emailCreate = "";
	}
	
	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsernameCreate() {
		return usernameCreate;
	}

	public void setUsernameCreate(String usernameCreate) {
		this.usernameCreate = usernameCreate;
	}

	public String getPasswordCreate() {
		return passwordCreate;
	}

	public void setPasswordCreate(String passwordCreate) {
		this.passwordCreate = passwordCreate;
	}

	public String getRetypePasswordCreate() {
		return retypePasswordCreate;
	}

	public void setRetypePasswordCreate(String retypePasswordCreate) {
		this.retypePasswordCreate = retypePasswordCreate;
	}

	public String getNameCreate() {
		return nameCreate;
	}

	public void setNameCreate(String nameCreate) {
		this.nameCreate = nameCreate;
	}

	public String getTelephoneCreate() {
		return telephoneCreate;
	}

	public void setTelephoneCreate(String telephoneCreate) {
		this.telephoneCreate = telephoneCreate;
	}

	public String getEmailCreate() {
		return emailCreate;
	}

	public void setEmailCreate(String emailCreate) {
		this.emailCreate = emailCreate;
	}

	public String getRoleCreate() {
		return roleCreate;
	}

	public void setRoleCreate(String roleCreate) {
		this.roleCreate = roleCreate;
	}

	public String getBranchCreate() {
		return branchCreate;
	}

	public void setBranchCreate(String branchCreate) {
		this.branchCreate = branchCreate;
	}

	public String getUsernameModify() {
		return usernameModify;
	}

	public void setUsernameModify(String usernameModify) {
		this.usernameModify = usernameModify;
	}

	public String getPasswordModify() {
		return passwordModify;
	}

	public void setPasswordModify(String passwordModify) {
		this.passwordModify = passwordModify;
	}

	public String getRetypePasswordModify() {
		return retypePasswordModify;
	}

	public void setRetypePasswordModify(String retypePasswordModify) {
		this.retypePasswordModify = retypePasswordModify;
	}

	public String getNameModify() {
		return nameModify;
	}

	public void setNameModify(String nameModify) {
		this.nameModify = nameModify;
	}

	public String getTelephoneModify() {
		return telephoneModify;
	}

	public void setTelephoneModify(String telephoneModify) {
		this.telephoneModify = telephoneModify;
	}

	public String getEmailModify() {
		return emailModify;
	}

	public void setEmailModify(String emailModify) {
		this.emailModify = emailModify;
	}

	public String getRoleModify() {
		return roleModify;
	}

	public void setRoleModify(String roleModify) {
		this.roleModify = roleModify;
	}

	public String getBranchModify() {
		return branchModify;
	}

	public void setBranchModify(String branchModify) {
		this.branchModify = branchModify;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}

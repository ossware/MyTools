package com.ehome.sy.model;

public class UserRole {

	private String id;
	private String roleId;
	private String userId;
	private Role role;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id == null ? null : id.trim();
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId == null ? null : roleId.trim();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId == null ? null : userId.trim();
	}

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
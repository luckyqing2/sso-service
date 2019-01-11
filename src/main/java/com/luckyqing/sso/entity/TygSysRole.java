package com.luckyqing.sso.entity;


import java.util.List;

public class TygSysRole {

  private long id;
  private String roleName;
  private String roleDescription;
  private long cutOff;
  private long cutOffTime;

  private List<TygSysUser> users;

  public List<TygSysUser> getUsers() {
    return users;
  }

  public void setUsers(List<TygSysUser> users) {
    this.users = users;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getRoleName() {
    return roleName;
  }

  public void setRoleName(String roleName) {
    this.roleName = roleName;
  }


  public String getRoleDescription() {
    return roleDescription;
  }

  public void setRoleDescription(String roleDescription) {
    this.roleDescription = roleDescription;
  }


  public long getCutOff() {
    return cutOff;
  }

  public void setCutOff(long cutOff) {
    this.cutOff = cutOff;
  }


  public long getCutOffTime() {
    return cutOffTime;
  }

  public void setCutOffTime(long cutOffTime) {
    this.cutOffTime = cutOffTime;
  }

}

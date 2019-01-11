package com.luckyqing.sso.entity;


import java.io.Serializable;
import java.util.List;

public class TygSysUser implements Serializable {

  private long id;
  private String userName;
  private String userPwd;
  private long creatTime;
  private long cutOff;
  private long cutOffTime;
  private List<TygSysRole> roles;

    public List<TygSysRole> getRoles() {
        return roles;
    }

    public void setRoles(List<TygSysRole> roles) {
        this.roles = roles;
    }

    public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }


  public String getUserPwd() {
    return userPwd;
  }

  public void setUserPwd(String userPwd) {
    this.userPwd = userPwd;
  }


  public long getCreatTime() {
    return creatTime;
  }

  public void setCreatTime(long creatTime) {
    this.creatTime = creatTime;
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

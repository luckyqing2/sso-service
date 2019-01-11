package com.luckyqing.sso.service;

import com.luckyqing.sso.entity.TygSysRole;

import java.util.List;

/**
 * @program: 角色
 * @description: 用户服务类接口
 * @roleor: "清歌"
 * @create: 2018-08-21 11:51
 **/
public interface RoleService {

    /**
     * 新增一条权限
     * @param role
     */
    void insertRole(TygSysRole role);

    /**
     * 删除一条权限
     * @param id
     */
    void deleteRoleById(Long id);

    /**
     * 更新权限
     * @param role
     */
    void updateRole(TygSysRole role);

    /**
     * 通过ID查找权限
     * @param id
     * @return
     */
    TygSysRole findRoleById(Long id);

    /**
     * 根据条件查询
     * @param role
     * @return
     */
    List<TygSysRole> findRolesByCriteria(TygSysRole role);
}

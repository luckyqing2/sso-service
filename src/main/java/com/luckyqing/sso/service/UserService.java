package com.luckyqing.sso.service;

import com.luckyqing.sso.entity.TygSysUser;

import java.util.List;

/**
 * @program: notice
 * @description: 用户服务类接口
 * @useror: "清歌"
 * @create: 2018-08-21 11:51
 **/
public interface UserService {
    /**
     * 新增一条权限
     * @param user
     */
    Long insertUser(TygSysUser user);

    /**
     * 删除一条权限
     * @param user
     */
    void deleteUserById(TygSysUser user);

    /**
     * 更新权限
     * @param user
     */
    void updateUser(TygSysUser user);

    /**
     * 通过ID查找权限
     * @param id
     * @return
     */
    TygSysUser findUserById(Long id);

    /**
     * 根据条件查询
     * @param user
     * @return
     */
    List<TygSysUser> findUsersByCriteria(TygSysUser user);

    TygSysUser findUserByUserName(String userName);

    List<String> findAuthsByUserId(Long userId);

    void addRoles(Long userId, Long roleId);

    void deleteRolesIdByUserId(Long userId);
}

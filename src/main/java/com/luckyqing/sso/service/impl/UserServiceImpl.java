package com.luckyqing.sso.service.impl;

import com.luckyqing.sso.entity.TygSysUser;
import com.luckyqing.sso.mapper.UserMapper;
import com.luckyqing.sso.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: notice
 * @description:权限接口实现类
 * @useror: "清歌"
 * @create: 2018-08-21 11:51
 **/
@Service
public class UserServiceImpl implements UserService {




    @Resource
    private UserMapper userMapper;

    @Override
    public Long insertUser(TygSysUser user) {
        return userMapper.insertUser(user);
    }

    @Override
    public void deleteUserById(TygSysUser user) {
        userMapper.deleteUserById(user);
    }

    @Override
    public void updateUser(TygSysUser user) {
        userMapper.updateUser(user);
    }

    @Override
    public TygSysUser findUserById(Long id) {
        return userMapper.findUserById(id);
    }

    @Override
    public List<TygSysUser> findUsersByCriteria(TygSysUser user) {
        return userMapper.findUsersByCriteria(user);
    }

    @Override
    public List<String> findAuthsByUserId(Long userId) {
        return userMapper.findAuthsByUserId(userId);
    }

    @Override
    public void addRoles(Long userId, Long roleId) {
        userMapper.addRoles(userId,roleId);
    }

    @Override
    public TygSysUser findUserByUserName(String userName) {
        return userMapper.findUserByUserName(userName);
    }

    @Override
    public void deleteRolesIdByUserId(Long userId) {
        userMapper.deleteRolesIdByUserId(userId);
    }
}
package com.luckyqing.sso.service.impl;

import com.luckyqing.sso.entity.TygSysRole;
import com.luckyqing.sso.mapper.RoleMapper;
import com.luckyqing.sso.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @program: notice
 * @description:权限接口实现类
 * @roleor: "清歌"
 * @create: 2018-08-21 11:51
 **/
@Service
public class RoleServiceImpl implements RoleService {

    @Resource
    private RoleMapper roleMapper;

    @Override
    public void insertRole(TygSysRole role) {
        roleMapper.insertRole(role);
    }

    @Override
    public void deleteRoleById(Long id) {
        roleMapper.deleteRoleById(id);
    }

    @Override
    public void updateRole(TygSysRole role) {
        roleMapper.updateRole(role);
    }

    @Override
    public TygSysRole findRoleById(Long id) {
        return roleMapper.findRoleById(id);
    }

    @Override
    public List<TygSysRole> findRolesByCriteria(TygSysRole role) {
        return roleMapper.findRolesByCriteria(role);
    }
}
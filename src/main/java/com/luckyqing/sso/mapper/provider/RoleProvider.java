package com.luckyqing.sso.mapper.provider;

import com.luckyqing.sso.entity.TygSysRole;

/**
 * @program: 角色
 * @description: 公告动态SQL
 * @author: "清歌"
 * @create: 2018-08-16 17:13
 **/
public class RoleProvider {



    /**
     * 查询语句.
     * @param role
     * @return
     */
    public String findRolesByCriteria(TygSysRole role){
        StringBuffer sql = new StringBuffer("select * from tyg_sys_role where 1 = 1");
        if(role.getRoleName() != null && !"".equals(role.getRoleName())){
            sql.append(" and role_name like #{authName}");
        }
        sql.append(" ORDER BY id DESC");
        return sql.toString();
    }

}

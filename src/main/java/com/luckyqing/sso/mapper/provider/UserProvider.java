package com.luckyqing.sso.mapper.provider;

import com.luckyqing.sso.entity.TygSysUser;

/**
 * @program: notice
 * @description: 公告动态SQL
 * @useror: "清歌"
 * @create: 2018-08-16 17:13
 **/
public class UserProvider {



    /**
     * 查询语句.
     * @param user
     * @return
     */
    public String findUsersByCriteria(TygSysUser user){
        StringBuffer sql = new StringBuffer("select * from tyg_sys_user where 1 = 1");
        if(user.getUserName() != null && !"".equals(user.getUserName())){
            sql.append(" and user_name like #{userName}");
        }
        sql.append(" ORDER BY id DESC");
        return sql.toString();
    }

}

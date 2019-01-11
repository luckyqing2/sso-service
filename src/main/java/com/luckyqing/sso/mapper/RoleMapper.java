package com.luckyqing.sso.mapper;

import com.luckyqing.sso.entity.TygSysRole;
import com.luckyqing.sso.mapper.provider.RoleProvider;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @program: notice
 * @description: 角色DAO
 * @author: "清歌"
 * @create: 2018-08-21 14:13
 **/
public interface RoleMapper {

    @Results(id = "userRoles" ,value = {
            @Result(property ="id",  column ="id", 	javaType = Long.class),
            @Result(property ="roleUrl",  column ="role_url", 	javaType = String.class)
           /* @Result(property ="userPwd",  column ="user_pwd", 	javaType = String.class),
            @Result(property ="creatTime",  column ="creat_time", 	javaType = Long.class),
            @Result(property ="cutOff",  column ="cut_off", 	javaType = Long.class),
            @Result(property ="cutOffTime",  column ="cut_off_time", 	javaType = Long.class)*/
    })

    /**
     * 通过用户ID查找相关权限
     * userID 用户ID
     */
    @Select("SELECT\n" +
            "\tDISTINCT a.auth_permission\n" +
            "FROM\n" +
            "\ttyg_sys_user u\n" +
            "INNER JOIN tyg_user_role ur ON u.id = ur.user_id\n" +
            "INNER JOIN tyg_sys_role r ON ur.role_id = r.id\n" +
            "INNER JOIN tyg_role_auth ra ON r.id = ra.role_id\n" +
            "INNER JOIN tyg_sys_auth a ON ra.auth_id = a.id\n" +
            "WHERE\n" +
            "\tu.id = #{userId}")
    List<String> findAuthsByUserId(Long userId);


    /**
     * 插入权限
     * @param role
     */
    @Insert("insert into tyg_sys_role(role_name,role_description) values(#{roleName},#{roleDescription})")
    void insertRole(TygSysRole role);

    /**
     * 根据条件查找Role
     * @param role
     * @return
     */
    @Results(id = "roleMap" ,value = {
            @Result(property = "id", column = "id", javaType = Long.class),
            @Result(property = "roleName", column = "role_name", javaType = String.class),
            @Result(property = "roleDescription", column = "role_description", javaType = String.class),
    })
    @SelectProvider(type = RoleProvider.class,method = "findRolesByCriteria")
    List<TygSysRole> findRolesByCriteria(TygSysRole role);

    /**
     * 删除权限，实际删除
     * @param id
     */
    @Select("delete from tyg_sys_role where id = #{id}")
    void deleteRoleById(Long id);

    /**
     * 更新权限
     * @param role
     */
    @Update("update tyg_sys_role set role_name = #{roleName},role_description=#{roleDescription} where id = #{id}")
    void updateRole(TygSysRole role);

    /**
     * 通过ID查找
     * @param id
     * @return
     */
    @ResultMap("roleMap")
    @Select("select * from tyg_sys_role where id = #{id}")
    TygSysRole findRoleById(Long id);

    /**
     * 根据用户ID查找角色
     * @param userId
     * @return
     */
    @ResultMap("roleMap")
    @Select("select role_id as id from tyg_user_role where user_id = #{userId}")
    TygSysRole findRolesByUserId(@Param("id") Long userId);

}
